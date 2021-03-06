package ru.smak.gui;

import ru.smak.gui.graphics.CartesianPainter;
import ru.smak.gui.graphics.components.ControlPanel;
import ru.smak.gui.graphics.components.GraphicsPanel;
import ru.smak.gui.graphics.coordinatesystem.CartesianScreenPlane;
import ru.smak.gui.graphics.events.CPEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    GraphicsPanel mainPanel;
    ControlPanel controlPanel;
    CartesianPainter painter;

    static final Dimension MIN_SIZE = new Dimension(450, 350);
    static final Dimension MIN_FRAME_SIZE = new Dimension(600, 500);

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(MIN_FRAME_SIZE);
        setTitle("Полиномы");

        mainPanel = new GraphicsPanel();
        controlPanel = new ControlPanel();

        mainPanel.setBackground(Color.WHITE);
        //mainPanel.addPainter(new CartesianPainter());
        controlPanel.setBorder(new EtchedBorder());

        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, (int)(MIN_SIZE.height*0.8), MIN_SIZE.height, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(mainPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                                .addComponent(controlPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4)
        );
        pack();
        painter = new CartesianPainter(new CartesianScreenPlane(
                mainPanel.getWidth(), mainPanel.getHeight(), controlPanel.getXMin(), controlPanel.getXMax(),
                controlPanel.getYMin(), controlPanel.getYMax()
        ));
        //painter.paint(mainPanel.getGraphics());
        controlPanel.addCPEListener(new CPEvent() {
            @Override
            public void dataChanged() {
                painter.getCSP().set_xMin(controlPanel.getXMin());
                painter.getCSP().set_xMax(controlPanel.getXMax());
                painter.getCSP().set_yMin(controlPanel.getYMin());
                painter.getCSP().set_yMax(controlPanel.getYMax());
                mainPanel.repaint();
            }
        });
        mainPanel.addPainter(painter);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                painter.getCSP().set_realWidth(mainPanel.getWidth());
                painter.getCSP().set_realHeight(mainPanel.getHeight());
                mainPanel.repaint();
            }
        });

    }
}
