package dev.omedia.olympic;

public enum OlympicGame {
    BASKETBALL_3X3(OlympicGameSeason.SUMMER),
    ARCHERY(OlympicGameSeason.SUMMER),
    ARTISTIC_GYMNASTICS(OlympicGameSeason.SUMMER),
    ARTISTIC_SWIMMING(OlympicGameSeason.SUMMER),
    ATHLETICS(OlympicGameSeason.SUMMER),
    BADMINTON(OlympicGameSeason.SUMMER),
    BASEBALL_SOFTBALL(OlympicGameSeason.SUMMER),
    BASKETBALL(OlympicGameSeason.SUMMER),
    BEACH_VOLLEYBALL(OlympicGameSeason.SUMMER),
    BMX(OlympicGameSeason.SUMMER),
    BOXING(OlympicGameSeason.SUMMER),
    CANOE(OlympicGameSeason.SUMMER),
    DIVING(OlympicGameSeason.SUMMER),
    EQUESTRIAN(OlympicGameSeason.SUMMER),
    FENCING(OlympicGameSeason.SUMMER),
    FOOTBALL(OlympicGameSeason.SUMMER),
    GOLF(OlympicGameSeason.SUMMER),
    HANDBALL(OlympicGameSeason.SUMMER),
    HOCKEY(OlympicGameSeason.SUMMER),
    JUDO(OlympicGameSeason.SUMMER),
    KARATE(OlympicGameSeason.SUMMER),
    MARATHON_SWIMMING(OlympicGameSeason.SUMMER),
    MODERN_PENTATHLON(OlympicGameSeason.SUMMER),
    MOUNTAIN_BIKE(OlympicGameSeason.SUMMER),
    RHYTHMIC_GYMNASTICS(OlympicGameSeason.SUMMER),
    ROAD_CYCLING(OlympicGameSeason.SUMMER),
    ROWING(OlympicGameSeason.SUMMER),
    RUGBY(OlympicGameSeason.SUMMER),
    SAILING(OlympicGameSeason.SUMMER),
    SHOOTING(OlympicGameSeason.SUMMER),
    SKATEBOARDING(OlympicGameSeason.SUMMER),
    SPORT_CLIMBING(OlympicGameSeason.SUMMER),
    SURFING(OlympicGameSeason.SUMMER),
    SWIMMING(OlympicGameSeason.SUMMER),
    TABLE_TENNIS(OlympicGameSeason.SUMMER),
    TAEKWONDO(OlympicGameSeason.SUMMER),
    TENNIS(OlympicGameSeason.SUMMER),
    TRACK_CYCLING(OlympicGameSeason.SUMMER),
    TRAMPOLINE(OlympicGameSeason.SUMMER),
    TRIATHLON(OlympicGameSeason.SUMMER),
    VOLLEYBALL(OlympicGameSeason.SUMMER),
    WATER_POLO(OlympicGameSeason.SUMMER),
    WEIGHTLIFTING(OlympicGameSeason.SUMMER),
    WRESTLING(OlympicGameSeason.SUMMER),
    ALPINE_SKIING(OlympicGameSeason.WINTER),
    BIATHLON(OlympicGameSeason.WINTER),
    BOBSLEIGH(OlympicGameSeason.WINTER),
    CROSS_COUNTRY_SKIING(OlympicGameSeason.WINTER),
    CURLING(OlympicGameSeason.WINTER),
    FIGURE_SKATING(OlympicGameSeason.WINTER),
    FREESTYLE_SKIING(OlympicGameSeason.WINTER),
    ICE_HOCKEY(OlympicGameSeason.WINTER),
    LUGE(OlympicGameSeason.WINTER),
    NORDIC_COMBINED(OlympicGameSeason.WINTER),
    SHORT_TRACK_SPEED_SKATING(OlympicGameSeason.WINTER),
    SKELETON(OlympicGameSeason.WINTER),
    SKI_JUMPING(OlympicGameSeason.WINTER),
    SNOWBOARD(OlympicGameSeason.WINTER),
    SPEED_SKATING(OlympicGameSeason.WINTER);

    private final OlympicGameSeason season;

    OlympicGame(OlympicGameSeason season) {
        this.season = season;
    }

    public OlympicGameSeason getSeason() {
        return season;
    }
}
