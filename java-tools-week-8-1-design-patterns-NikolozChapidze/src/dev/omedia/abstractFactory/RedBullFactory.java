package dev.omedia.abstractFactory;

public class RedBullFactory implements F1Factory{
    @Override
    public Car createCar() {
        return new RedBullCar();
    }
}
