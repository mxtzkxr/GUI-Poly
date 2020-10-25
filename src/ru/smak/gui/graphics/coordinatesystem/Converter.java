package ru.smak.gui.graphics.coordinatesystem;

/**
 * Класс для осуществления преобразования координат в различных системах
 */
public class Converter {
    /**
     * Преобразование абсциссы из декартовой системы координат в экранную
     *
     * @param x абсцисса точки в декартовой системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return абсцисса точки в экранной системе координат
     */
   public static int xCrt2Scr(double x, CartesianScreenPlane plane) {
        var r = (int)(plane.getXDen() * (x - plane.getXMin()));
        if (r < -plane.getWidth()) r = -plane.getWidth();
        if (r > 2 * plane.getWidth()) r = 2 * plane.getWidth();
        return r;
    }

    /**
     * Преобразование абсциссы из экранной системы координат в декартовую
     *
     * @param x абсцисса точки в экранной системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return абсцисса точки в декартовой системе координат
     */
    public static double xScr2Crt(int x, CartesianScreenPlane plane) {
        if (x < -plane.getWidth()) x = -plane.getWidth();
        if (x > 2 * plane.getWidth()) x = 2 * plane.getWidth();
        return x / plane.getXDen() + plane.getXMin();
    }

    /**
     * Преобразование ординаты из декартовой системы координат в экранную
     *
     * @param y ордината точки в декартовой системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return ордината точки в экранной системе координат
     */
    public static int yCrt2Scr(double y, CartesianScreenPlane plane) {
        var r = (int)(plane.getYDen() * (plane.getYMax() - y));
        if (r < -plane.getHeight()) r = -plane.getHeight();
        if (r > 2 * plane.getHeight() + 10) r = 2 * plane.getHeight();
        return r;
    }

    /**
     * Преобразование ординаты из экранной системы координат в декартовую
     *
     * @param y ордината точки в экранной системе координат
     * @param plane плоскость, для которой осуществляется преобразование
     * @return ордината точки в декартовой системе координат
     */
    public static double yScr2Crt(int y, CartesianScreenPlane plane){
        if (y < -plane.getHeight()) y = -plane.getHeight();
        if (y > 2 * plane.getHeight()) y = 2 * plane.getHeight();
        return plane.getYMax() - y / plane.getYDen();
    }
}
