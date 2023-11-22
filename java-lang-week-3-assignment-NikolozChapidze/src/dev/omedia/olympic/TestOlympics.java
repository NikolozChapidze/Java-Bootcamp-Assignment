package dev.omedia.olympic;

import java.util.*;

public class TestOlympics {
    public static void test(){
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 1950; i < 2022; i++) {
            years.add(i);
        }
        Set<Medal> medalSet = OlympicGameGenerator.generateOlympicMedalsForYear(years);
        System.out.println("2000 wlidan "+medalSet.stream().filter(medal -> medal.getGame().getYear() > 2000).count()
                +" medali gaica!");

        HashMap<Country,Integer> amountOfMedals = new HashMap<>();
        medalSet.stream().filter(medal -> medal.getGame().getYear() > 2000)
                .forEach(medal -> amountOfMedals.put(medal.getCountry(),
                        amountOfMedals.getOrDefault(medal.getCountry(),0)+1));

        ArrayList<Country> countries = new ArrayList<>(amountOfMedals.keySet());
        countries.sort((o1,o2)->{
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                return -1 * Integer.compare(amountOfMedals.get(o1), amountOfMedals.get(o2));
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? -1 : 1;
        });

        countries.forEach(country -> System.out.println(country + " : " + amountOfMedals.get(country)));
    }
}
