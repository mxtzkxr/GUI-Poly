package ru.smak.gui.graphics;

import ru.smak.gui.graphics.coordinatesystem.CartesianScreenPlane;
import ru.smak.gui.graphics.coordinatesystem.Converter;
import ru.smak.math.polynoms.Newton;
import ru.smak.math.polynoms.Polynom;

import java.awt.*;

public class PolynomPainter extends Painter{
    private final CartesianScreenPlane plane;
    private Newton p = null;
    public PolynomPainter(CartesianScreenPlane plane){
        this.plane = plane;
    }
    public void setPolynom(Newton p){
        this.p = p;

    }

    @Override
    public void paint(Graphics g) {
        for(var i=0;i<p.getKeys().length;i++){
            for(var k = 0;k<100;k++){
                if(i!=p.getKeys().length-1){
                    g.drawLine(Converter.xCrt2Scr(p.getKeys()[i]+k*(p.getKeys()[i+1]-p.getKeys()[i])/100,plane),Converter.yCrt2Scr(p.invoke(p.getKeys()[i]+k*(p.getKeys()[i+1]-p.getKeys()[i])/100),plane),Converter.xCrt2Scr(p.getKeys()[i]+(k+1)*(p.getKeys()[i+1]-p.getKeys()[i])/100,plane),Converter.yCrt2Scr(p.invoke(p.getKeys()[i]+(k+1)*(p.getKeys()[i+1]-p.getKeys()[i])/100),plane));
                }
            }
        }
    }
}
