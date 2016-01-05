package com.controller.segmentator;

import java.util.List;

/**
 *
 * @author Dimitar
 */
interface Point {

    double distanceTo(Point other);

    void changeValue(Point other);

    Point centroidOf(List<Point> points);
}
