package dev.omedia.flyWeigh;

public class Engine {
    private String name;
    private int manufactureYear;
    private String type;

    public Engine(String name, int manufactureYear, String type) {
        this.name = name;
        this.manufactureYear = manufactureYear;
        this.type = type;
    }

    public void startWorking(){
        System.out.println("engine started");
    }
}
