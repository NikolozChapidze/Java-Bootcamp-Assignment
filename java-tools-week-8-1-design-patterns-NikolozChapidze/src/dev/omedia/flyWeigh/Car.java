package dev.omedia.flyWeigh;

public class Car {
    private String name;
    private int year;
    private Engine engine;

    public Car(String name, int year, Engine engine) {
        this.name = name;
        this.year = year;
        this.engine = engine;
    }

    public void start(){
        engine.startWorking();
    }
}
