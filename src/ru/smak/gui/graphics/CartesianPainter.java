package ru.smak.gui.graphics;

import ru.smak.gui.graphics.coordinatesystem.CartesianScreenPlane;
import ru.smak.gui.graphics.coordinatesystem.Converter;

import java.awt.*;

public class CartesianPainter extends Painter{
    private CartesianScreenPlane infa=null;
    public CartesianPainter(CartesianScreenPlane p){
        this.infa = p;
    }
    public CartesianScreenPlane getCSP(){
        return infa;
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(Converter.xCrt2Scr(infa.getXMin(), infa), Converter.yCrt2Scr(0, infa),
                Converter.xCrt2Scr(infa.getXMax(), infa), Converter.yCrt2Scr(0, infa));
        //infa.getXMin()+(infa.getXMax()-infa.getXMax()/2
        g.drawLine(Converter.xCrt2Scr(0, infa), Converter.yCrt2Scr(infa.getYMax(), infa),
                Converter.xCrt2Scr(0, infa), Converter.yCrt2Scr(infa.getYMin(), infa));
        for (int i = (int)(getCSP().getXMin());i<getCSP().getXMax();i++){
            if (i!=0){
            g.drawLine(Converter.xCrt2Scr(i,infa),Converter.yCrt2Scr(-0.1,infa),Converter.xCrt2Scr(i,infa),Converter.yCrt2Scr(0.1,infa));
            g.drawString(""+i,Converter.xCrt2Scr(i-0.05,infa),Converter.yCrt2Scr(0.15,infa));
            }
        }
        for (int i = (int)(getCSP().getYMin());i<getCSP().getYMax();i++){
            if(i!=0) {
                g.drawLine(Converter.xCrt2Scr(-0.1, infa), Converter.yCrt2Scr(i, infa), Converter.xCrt2Scr(0.1, infa), Converter.yCrt2Scr(i, infa));
                g.drawString("" + i, Converter.xCrt2Scr(0.15, infa), Converter.yCrt2Scr(i - 0.05, infa));
            }
        }
    }
}
