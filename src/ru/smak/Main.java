package ru.smak;

import ru.smak.gui.MainWindow;
import ru.smak.math.polynoms.Lagrange;
import ru.smak.math.polynoms.Newton;
import ru.smak.math.polynoms.Polynom;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {1, 2, 3};
        double[] c2 = {-1, -2, -4};
        Polynom p1 = new Polynom(c1);
        Polynom p2 = new Polynom(c2);
        Polynom p3 = p1.plus(p2);
        System.out.println(p1);
        System.out.println("P1(1)="+p1.invoke(1));
        System.out.println(p2);
        System.out.println("P2(-2)="+p2.invoke(-2));
        System.out.println(p3);
        System.out.println("P3(5)="+p3.invoke(5));
        System.out.println(p3.minus(p3));
        var dots = new LinkedHashMap<Double, Double>(){{put(-1.0, 1.0); put(0.0, 0.0);}};
        var newt = new Newton(dots);
        System.out.println(newt);
        dots.put(-1.0, 1.0);
        newt.addPoint(-1.0, 1.0);
        System.out.println(newt);
        dots.put(0.0, 0.0);
        newt.addPoint(0.0, 0.0);
        System.out.println(newt);
        dots.put(1., 1.);
        newt.addPoint(1.0, 1.0);
        System.out.println(newt);
        newt.addPoint(1.0, 1.0);
        System.out.println(newt);
        var lagr = new Lagrange(dots);
        var newt2 = new Newton(dots);
        System.out.println(lagr);
        System.out.println(newt2);

        var wnd = new MainWindow();
        wnd.setVisible(true);
    }
}
