package com.controller.segmentator;

import java.util.List;

/**
 *
 * @author Dimitar
 */
class PointRGB implements Point {

    private int imageX;
    private int imageY;

    private int red;
    private int green;
    private int blue;

    public PointRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public double distanceTo(Point o) {
        PointRGB other = (PointRGB) o;

        int sum = 0;
        sum += (this.red - other.red) * (this.red - other.red);
        sum += (this.green - other.green) * (this.green - other.green);
        sum += (this.blue - other.blue) * (this.blue - other.blue);

        return Math.sqrt((double) sum);
    }

    @Override
    public void changeValue(Point o) {
        PointRGB other = (PointRGB) o;
        this.red = other.red;
        this.green = other.green;
        this.blue = other.blue;
    }

    @Override
    public Point centroidOf(List<Point> points) {
        int reds = 0;
        int greens = 0;
        int blues = 0;

        for (Point point : points) {
            PointRGB p = (PointRGB) point;
            reds += p.red;
            greens += p.green;
            blues += p.blue;
        }

        reds /= points.size();
        greens /= points.size();
        blues /= points.size();

        return new PointRGB(reds, greens, blues);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getImageX() {
        return imageX;
    }

    public int getImageY() {
        return imageY;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setImageX(int imageX) {
        this.imageX = imageX;
    }

    public void setImageY(int imageY) {
        this.imageY = imageY;
    }

    public boolean equals(PointRGB other) {
        return this.red == other.red && this.blue == other.blue && this.green == other.green;
    }
}
