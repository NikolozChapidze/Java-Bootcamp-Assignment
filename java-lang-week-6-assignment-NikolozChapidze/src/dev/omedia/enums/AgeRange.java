package dev.omedia.enums;

public enum AgeRange {
    ZERO_TWENTYFIVE(1,"0-25"),
    TWENTYFIVE_FORTY(2,"25-40"),
    FORTY_SIXTY(3,"40-60"),
    SIXTY_EIGHTY(4,"60-80"),
    EIGHTY_REST(5,"80-X");

    private final int index;
    private final String range;

    AgeRange(int index, String range) {
        this.index = index;
        this.range = range;
    }

    public String getRange() {
        return range;
    }

    public int getIndex() {
        return index;
    }

    public static AgeRange getAgeRange(int age){
        if(age > 80){
            return EIGHTY_REST;
        }
        if(age > 60){
            return SIXTY_EIGHTY;
        }
        if(age > 40){
            return FORTY_SIXTY;
        }
        if(age > 25){
            return TWENTYFIVE_FORTY;
        }
        return ZERO_TWENTYFIVE;
    }
}
