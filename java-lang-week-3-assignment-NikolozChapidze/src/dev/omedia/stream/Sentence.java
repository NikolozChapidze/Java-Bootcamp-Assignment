package dev.omedia.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Sentence {
    public static ArrayList<String> sortedInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print("please input sentence: ");
        String input = sc.nextLine();
        ArrayList<String> result = new ArrayList<>();
        Arrays.stream(input.split("\\s+")).distinct().sorted().forEach(o -> result.add(o));
        for (final var word : result) {
            System.out.println(word);
        }
        return result;
    }
}
