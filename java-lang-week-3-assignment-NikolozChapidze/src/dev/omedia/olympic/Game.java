package dev.omedia.olympic;

import dev.omedia.pti.Vehicle;

import java.util.Calendar;
import java.util.Objects;

public class Game {
    private OlympicGame olympicGame;
    private int year;

    public Game(OlympicGame olympicGame, int year) {
        if(year > Calendar.getInstance().get(Calendar.YEAR)){
            System.err.println("მომავლის თარიღი: ჯერ ოლიპიური თამაში "+year+" წელს ჯერ არ ჩატარებულა.");
            System.exit(1);
        }
        if(year % 4 == 0 && year > 1896 && olympicGame.getSeason() == OlympicGameSeason.WINTER){
            System.err.println("Incorrect olympic game for the season, should be summer.");
            System.exit(1);
        }
        if(year % 2 == 0 && year % 4 != 0 && year > 1924 && olympicGame.getSeason() == OlympicGameSeason.SUMMER){
            System.err.println("Incorrect olympic game for the season, should be winter.");
            System.exit(1);
        }
        if(year % 2 != 0){
            System.err.println("არაწორი ოლიპიური თამაშის წელი: " + year);
            System.exit(1);
        }
        this.olympicGame = olympicGame;
        this.year = year;
    }

    public OlympicGame getOlympicGame() {
        return olympicGame;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Game{" +
                "olympicGame=" + olympicGame +
                ", year=" + year +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(olympicGame,year);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Game
                &&  Objects.equals(this.olympicGame, ((Game) obj).olympicGame)
                &&  Objects.equals(this.year, ((Game) obj).year));
    }
}
