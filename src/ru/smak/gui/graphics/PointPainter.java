package ru.smak.gui.graphics;

import ru.smak.gui.graphics.coordinatesystem.CartesianScreenPlane;
import ru.smak.gui.graphics.coordinatesystem.Converter;

import java.awt.*;
import java.util.LinkedHashMap;

public class PointPainter extends Painter{
    private static final int POINTSIZE = 9;
    private static final int EPSILON = 6;
    private CartesianScreenPlane plane;
    private final LinkedHashMap<Double,Double> vals;
    public Color pointColor = Color.RED.darker();
   public PointPainter(CartesianScreenPlane plane){
       this.plane = plane;
       vals = new LinkedHashMap<>();
   }
   public void addPoint(double x, double y){
       if(!pointExists(x)){
           vals.put(x,y);
       }
   }
   public void addPoint(int x,int y){
       var cx = Converter.xScr2Crt(x,plane);
       var cy = Converter.yScr2Crt(y,plane);
       addPoint(cx,cy);
   }

    public boolean pointExists(double x) {
        var epsilon1 = Converter.xScr2Crt(0,plane);
        var epsilon2 = Converter.xScr2Crt(EPSILON,plane);
        var epsilon = epsilon2-epsilon1;
        for(double px:vals.keySet()){
            if(Math.abs(px-x)<epsilon) return true;
        }
        return false;
    }
    public boolean pointExists(int x){
       var cx = Converter.xScr2Crt(x,plane);
       return pointExists(cx);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(pointColor);
        vals.forEach((x,y)->{
            var ix = Converter.xCrt2Scr(x,plane);
            var iy  = Converter.yCrt2Scr(y,plane);
            g.fillOval(ix-POINTSIZE/2,iy-POINTSIZE/2,POINTSIZE,POINTSIZE);
        });
    }
}
