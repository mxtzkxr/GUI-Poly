package ru.smak.gui.graphics.coordinatesystem;

public class CartesianPlane {
    public double xMin;
    public double xMax;
    public double yMin;
    public double yMax;
    public CartesianPlane(
            double xMin, double xMax,
            double yMin, double yMax
    ){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
}