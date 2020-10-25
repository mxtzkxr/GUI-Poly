package ru.smak.math.polynoms;

import java.util.HashMap;

public class Lagrange extends Polynom {

    private final HashMap<Double, Double> dots;

    public Lagrange(HashMap<Double, Double> v){
        dots = v;
        createPoly();
    }

    private void createPoly() {
        Polynom p = new Polynom();
        for (var k : dots.keySet()){
            p = p.plus(fundamental(k).times(dots.get(k)));
        }
        coef = p.coef.clone();
    }

    private Polynom fundamental(Double x){
        Polynom p = new Polynom(new double[]{1.0});
        try {
            for (var k : dots.keySet()) {
                if (Math.abs(x-k)>=ZERO)
                    p = p.times(new Polynom(new double[]{-k, 1}).div(x - k));
            }
        } catch (Exception e){
            p = new Polynom();
        }
        return p;
    }

}
