package ru.smak.math.polynoms;

public class Polynom {
    /**
     * Коэффициенты полинома
     */
    protected double [] coef;

    protected static final double ZERO = 1e-24;

    /**
     * Инициализация полинома нулевой степени со значением 0
     */
    public Polynom(){
        coef = new double[1];
        coef[0] = 0.0;
    }

    /**
     * Создание полинома на базе набора коэффициентов
     * с корректировкой его степени при необходимости
     * @param c коэффициенты создаваемого полинома
     */
    public Polynom(double[] c){
        coef = c.clone();
        correctPower();
    }

    /**
     * Получение степени полинома
     * @return целочисленное значение степени полинома
     */
    public int getPower(){
        return coef.length - 1;
    }

    /**
     * Метод корректировки степени полинома в зависимости от числа
     * нулевых коэффициентов при старших степенях
     */
    private void correctPower(){
        int rem = coef.length;
        while (Math.abs(coef[rem-1])<ZERO && rem > 1){
            rem--;
        }
        double [] c = new double[rem];
        System.arraycopy(coef, 0, c, 0, rem);
        coef = c;
    }

    /**
     * Сложение двух полиномов
     * @param other второй (правый) полином
     * @return полином, являющийся результатом суммирования данного полинома с другим
     */
    public Polynom plus(Polynom other){
        Polynom minP, maxP;
        if (this.coef.length>other.coef.length){
            minP = other;
            maxP = this;
        } else {
            minP = this;
            maxP = other;
        }
        double [] c = new double[maxP.coef.length];
        for (int i = 0; i<minP.coef.length; i++){
            c[i] = minP.coef[i] + maxP.coef[i];
        }
        if (maxP.coef.length - minP.coef.length > 0)
            System.arraycopy(
                    maxP.coef,
                    minP.coef.length,
                    c,
                    minP.coef.length,
                    maxP.coef.length - minP.coef.length);
        return new Polynom(c);
    }

    /**
     * Определение разности двух полиномов
     * @param other вычитаемый полином
     * @return разность данного (this) и второго (other) полиномов
     */
    public Polynom minus(Polynom other){
        return this.plus(other.times(-1.0));
    }

    /**
     * Определение произведения двух полиномов
     * @param other второй полином
     * @return произведение данного (this) и второго (other) полиномов
     */
    public Polynom times(Polynom other){
        // Создание массива нужной длины для коэффициентов нового полинома
        double[] c = new double[getPower() + other.getPower() + 1];
        // Вычисление значение новых коэффициентов
        for (int i = 0; i<coef.length; i++) {
            for (int j = 0; j<other.coef.length; j++) {
                c[i + j] += coef[i] * other.coef[j];
            }
        }
        // Создание нового полинома по рассчитанным коэффициентам
        return new Polynom(c);
    }

    /**
     * Определение значения произведения полинома на число
     * @param k вещественный коэффициент
     * @return результат умножения данного (this) полинома на число
     */
    public Polynom times(double k){
        double []c = coef.clone();
        for (int i = 0; i<coef.length; i++){
            c[i] *= k;
        }
        return new Polynom(c);
    }

    /**
     * Определение значения частного от деления полинома на число
     * @param k вещественный коэффициент
     * @return результат деления данного (this) полинома на число
     */
    public Polynom div(double k) throws Exception{
        if (Math.abs(k) < ZERO) throw new Exception("Деление на 0");
        return this.times(1./k);
    }

    /**
     * Метод вычисления значения полинома в точке
     * @param x точка, в которой нужно посчитать значение полинома
     * @return значение полинома при заданном x
     */
    public double invoke(double x){
        double p = 0;
        double mon = 1;
        for (double v : coef) {
            p += mon * v;
            mon *= x;
        }
        return p;
    }

    /**
     * Получение строкового представления полинома
     * @return строка, содержащая запись полинома в стандартном виде
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        //Для всех коэффициентов в порядке убывания степеней
        for (int i = coef.length-1; i>=0; i--) {
            //Если коэффициент равен 0...
            //(только не в случае, если это единственный коэффициент)
            if (Math.abs(coef[i]) < ZERO && coef.length!=1) continue;
            double c = coef[i];
            if (c < 0) {
                //Для отрицательных значений коэффициентов выводим "-" и далее
                //работаем с модулем коэффициента
                res.append("-");
                c = Math.abs(c);
            } else
                //для положительных коэффициентов выводим "+", если
                //это не первый коэффициент
                if (i < coef.length - 1)
                    res.append("+");
            //Проверяем, что коэффициент не равен +1 или -1
            //кроме случая, если этот коэффициент при 0й степени
            if (Math.abs(c-1) > ZERO || i == 0)
                //Выводим сам коэффициент или (если он целый)
                if (Math.abs(c-(long)c)<ZERO)
                    //приводим коэффициент к длинному целому числу
                    res.append((long)c);
                else
                    res.append(c);
            //Если степень 1 или больше, выводим символ "x"
            if (i >= 1) res.append('x');
            //Если степень строго больше 1, выводим ее значение
            if (i > 1) {
                res.append('^');
                res.append(i);
            }
        }
        //Преобразуем полученную запись в строку
        return res.toString();
    }
}