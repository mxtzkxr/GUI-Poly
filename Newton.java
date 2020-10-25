import java.util.HashMap;
import java.util.LinkedHashMap;

class Pairs<T1, T2> {
    private T1 i;
    private T2 j;

    public Pairs(T1 i, T2 j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int hashCode() {
        return i.hashCode() + j.hashCode();
    }

    @Override
    public boolean equals(Object P) {
        if (this.getClass() != P.getClass()) {
            return false;
        }
        return this.i == ((Pairs) P).i && this.j == ((Pairs) P).j;
    }
}

public class Newton extends Polynom {
    private final LinkedHashMap<Double, Double> dots;
    private final LinkedHashMap<Pairs, Double> pairs;
    private Double[] keys;

    public Newton(LinkedHashMap<Double, Double> v) {
        dots = v;
        keys = dots.keySet().toArray(new Double[0]);
        pairs = new LinkedHashMap<>();
        createPoly();
    }
    public void addPoint(Double a, Double b){
        dots.put(a,b);
        keys= dots.keySet().toArray(new Double[0]);
        Polynom r = new Polynom(new double[]{1.0});
        for (int k = 0; k < keys.length-1; k++) {//точно ли до keys.length-1?
            r = r.times(new Polynom(new double[]{-keys[k], 1.0}));
        }
        this.coef = this.plus(new Polynom(new double[]{razdRazn(0, keys.length-1)}).times(r)).coef.clone();
    }

    private void createPoly() {
        Polynom p = new Polynom();
        p = p.plus(new Polynom(new double[]{dots.get(keys[0])}));
        for (int i = 1; i < keys.length; i++) {
            Polynom r = new Polynom(new double[]{1.0});
            for (int k = 0; k < i; k++) {
                r = r.times(new Polynom(new double[]{-keys[k], 1.0}));
            }
            p = p.plus(new Polynom(new double[]{razdRazn(0, i)}).times(r));
        }
        this.coef = p.coef.clone();
    }

    private Double razdRazn(int i, int j) {
        var ind = new Pairs(i, j);
        if (pairs.containsKey(ind)) {
            return pairs.get(ind);
        } else if (Math.abs(i - j) == 1) {
            Double rr = (dots.get(keys[j]) - dots.get(keys[i])) / (keys[j] - keys[i]);
            pairs.put(ind, rr);
            return rr;
        }
        Double rr = (razdRazn(i + 1, j) - razdRazn(i, j - 1)) / (keys[j] - keys[i]);
        pairs.put(ind, rr);
        return rr;
    }


}
