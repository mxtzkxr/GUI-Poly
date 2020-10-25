package ru.smak.gui.graphics.components;

import javax.swing.*;

import ru.smak.gui.graphics.CartesianPainter;
import ru.smak.gui.graphics.Painter;

import java.awt.*;

public class GraphicsPanel extends JPanel {
    Painter p = null;

    public void addPainter(Painter p){
        this.p = p;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (p!=null) p.paint(g);
    }
}
