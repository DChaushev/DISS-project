package com.controller.segmentator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 *
 * @author Dimitar
 */
class ImageClassifier {

    public List<Point> classify(List<Point> points, int numberOfClusters, BiFunction<Integer, List<Point>, List<Point>> randomPointsGenerator) {
        List<Point> centroids = randomPointsGenerator.apply(numberOfClusters, points);

        Map<Point, List<Point>> clusters = new HashMap<>();
        for (Point centroid : centroids) {
            clusters.put(centroid, new ArrayList<>());
        }

        while (true) {
            reset(clusters);

            for (Point point : points) {
                Point closestCentroid = getClosestCentroid(point, centroids);
                clusters.get(closestCentroid).add(point);
            }

            boolean change = moveCentroids(centroids, clusters);

            if (!change) {
                break;
            }
        }

        assignPoints(centroids, clusters);

        return points;
    }

    private Point getClosestCentroid(Point point, List<Point> centroids) {
        Point closest = null;
        double minDistance = Integer.MAX_VALUE;

        for (Point centroid : centroids) {
            double distanceTo = point.distanceTo(centroid);

            if (distanceTo <= minDistance) {
                minDistance = distanceTo;
                closest = centroid;
            }
        }

        return closest;
    }

    private void reset(Map<Point, List<Point>> clusters) {
        for (Point keySet : clusters.keySet()) {
            clusters.get(keySet).clear();
        }
    }

    private boolean moveCentroids(List<Point> centroids, Map<Point, List<Point>> clusters) {
        boolean change = false;
        for (Point centroid : centroids) {
            Point newCentroid = centroid.centroidOf(clusters.get(centroid));
            if (!newCentroid.equals(centroid)) {
                centroid.changeValue(newCentroid);
                change = true;
            }
        }
        return false;
    }

    private void assignPoints(List<Point> centroids, Map<Point, List<Point>> clusters) {
        for (Point centroid : centroids) {
            List<Point> points = clusters.get(centroid);
            for (Point point : points) {
                point.changeValue(centroid);
            }
        }
    }
}
