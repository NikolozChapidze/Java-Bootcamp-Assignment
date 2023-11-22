package dev.omedia.olympic;

import dev.omedia.pti.EngineType;

import java.util.*;

public class OlympicGameGenerator {
    public static Set<Game> generateGamesForYear(int year){
        Set<Game> result =new HashSet<>();
        if(year % 4 == 0){
            Arrays.stream(OlympicGame.values()).filter(o -> o.getSeason()==OlympicGameSeason.SUMMER)
                    .forEach(olympicGame -> result.add(new Game(olympicGame,year)));
        }else if(year % 2 == 0){
            Arrays.stream(OlympicGame.values()).filter(o -> o.getSeason()==OlympicGameSeason.WINTER)
                    .forEach(olympicGame -> result.add(new Game(olympicGame,year)));
        }
        return result;
    }

    public static Set<Medal> generateMedalsForGames(Collection<Game> games, MedalType type){
        Set<Medal> result = new HashSet<>();
        games.forEach(game -> result.add(new Medal(game, type,
                Country.values()[(int) (Math.random() * Country.values().length)])));
        return result;
    }

    public static Set<Medal> generateOlympicMedalsForYear(ArrayList<Integer> years){
        Set<Game> everyGame = new HashSet<>();
        years.forEach(year -> everyGame.addAll(generateGamesForYear(year)));

        Set<Medal> result = new HashSet<>();
        Arrays.stream(MedalType.values()).forEach(medalType -> result.addAll(generateMedalsForGames(everyGame,medalType)));

        return result;
    }
}
