/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.segmentator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

/**
 *
 * @author Dimitar
 */
public class ImageSegmentator {

    public static BufferedImage segmentImage(BufferedImage image, int numberOfClusters) {
        List<Point> points = extractsPoints(image);

        List<Point> clusteredPoints = new ImageClassifier().classify(points, numberOfClusters, generateRandomPoints);

        overridePoints(clusteredPoints, image);

        return image;
    }

    private static final BiFunction<Integer, List<Point>, List<Point>> generateRandomPoints = (k, originalPoints) -> {
        Random rand = new Random(1);

        List<Point> points = new ArrayList<>(k);

        final int[] ints = rand.ints(0, originalPoints.size()).distinct().limit(k).toArray();

        for (int x : ints) {
            points.add(originalPoints.get(x));
        }

        return points;
    };

    private static void overridePoints(List<Point> clusteredPoints, BufferedImage image) {
        for (Point p : clusteredPoints) {
            PointRGB point = (PointRGB) p;
            int rgb = new Color(point.getRed(), point.getGreen(), point.getBlue()).getRGB();
            image.setRGB(point.getImageX(), point.getImageY(), rgb);
        }
    }

    private static List<Point> extractsPoints(BufferedImage image) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                PointRGB point = new PointRGB(c.getRed(), c.getGreen(), c.getBlue());
                point.setImageX(j);
                point.setImageY(i);
                points.add(point);
            }
        }
        return points;
    }

}
