package dev.omedia.calculator;

import static dev.omedia.calculator.Calculator.*;

public class Formula {
    public static double formulaA(double x, double y){
        return myDiv(myDiv(1,3), myDiv(myPow(x,2),y));
    }

    public static double formulaB(double x){
        return myDiv(myPlus(myMult(2,x) ,3) , myMinus( myMult(2,x) ,3));
    }
}
