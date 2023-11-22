package dev.omedia.calculator;

public class Calculator {
    public static double myMult(double a, double b){
        return a * b;
    }

    public static double myDiv(double a, double b){
        return a / b;
    }

    public static double myPow(double a, double b){
        return Math.pow(a,b);
    }

    public static double myPlus(double a, double b){
        return a+b;
    }

    public static double myMinus(double a, double b){
        return a - b;
    }

    public static double myMyx(double a, double b){
        return (a > b) ? a : b;
    }

    public static double myMin(double a, double b){
        return (a < b) ? a : b;
    }


}
