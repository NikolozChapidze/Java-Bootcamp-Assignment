package dev.omedia.staticmethods;

import java.util.Arrays;

public class SomeStaticMethods {
    public static long toLong(int[] numbers){
        long result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result = result * 10 + numbers[i];
        }
        return result;
    }

    public static int sum(int... numbers){
        int result = 0;
        for (var number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sum(long... numbers){
        long result = 0;
        for (var number : numbers) {
            result += number;
        }
        return result;
    }

    public static double sum(double... numbers){
        double result = 0;
        for (var number : numbers) {
            result += number;
        }
        return result;
    }

    public static char[] reverseCharsArray(char[] chars){
        for (int i = 0; i < chars.length/2; i++) {
            char temp = chars[chars.length-1-i];
            chars[chars.length-1-i] = chars[i];
            chars[i] = temp;
        }
        return chars;
    }

    public static boolean[] multiples(int size, int x){
        if(size <= 0 || x == 0){
            return new boolean[0];
        }
        boolean[] result = new boolean[size];
        for (int i = 0; i < size; i++) {
            result[i] = i % x == 0;
        }
        return result;
    }

    public static boolean[] multiplesWithoutMod(int size, int x){
        if(size <= 0 || x == 0){
            return new boolean[0];
        }
        boolean[] result = new boolean[size];
        Arrays.fill(result,false);
        int index = 0;
        while(index < size){
            result[index] = true;
            index += x;
        }
        return result;
    }

    public static int[] cubes(int size){
        if(size <= 0){
            return new int[0];
        }
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i * i;
        }
        return result;
    }
}
