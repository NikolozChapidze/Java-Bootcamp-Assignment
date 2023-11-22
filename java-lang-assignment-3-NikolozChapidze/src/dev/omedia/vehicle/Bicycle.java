package dev.omedia.vehicle;

public class Bicycle {
    private int fontGear;
    private int rareGear;
    private final int maxFontGear;
    private final int maxRareGear;
    private final int minGaer;

    public Bicycle() {
        this.minGaer  = 1;
        this.fontGear = this.minGaer ;
        this.rareGear = this.minGaer;
        this.maxFontGear = 3;
        this.maxRareGear = 9;
    }

    public void increaseFrontGear(){
        if(fontGear < maxFontGear){
            fontGear++;
        }
    }

    public void decreaseFrontGear(){
        if(fontGear > minGaer){
            fontGear--;
        }
    }

    public void increaseRareGear(){
        if(rareGear < maxRareGear){
            rareGear++;
        }
    }

    public void decreaseRareGear(){
        if(rareGear > minGaer){
            rareGear--;
        }
    }

    public int calculateSpeed(){
        return fontGear*rareGear;
    }

    public int getFontGear() {
        return fontGear;
    }

    public int getRareGear() {
        return rareGear;
    }

    public int getMaxFontGear() {
        return maxFontGear;
    }

    public int getMaxRareGear() {
        return maxRareGear;
    }

    public int getMinGaer() {
        return minGaer;
    }
}
