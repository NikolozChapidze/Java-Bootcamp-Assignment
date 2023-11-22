package dev.omedia.olympic;

import java.util.Objects;

public class Medal {
    private Game game;
    private MedalType type;
    private Country country;

    public Medal(Game game, MedalType type, Country country) {
        this.game = game;
        this.type = type;
        this.country = country;
    }

    public Game getGame() {
        return game;
    }

    public MedalType getType() {
        return type;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Medal{" +
                "game=" + game +
                ", type=" + type +
                ", country=" + country +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, type, country);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Medal
                &&  Objects.equals(this.game, ((Medal) obj).game)
                &&  Objects.equals(this.type, ((Medal) obj).type)
                &&  Objects.equals(this.country, ((Medal) obj).country));
    }
}
