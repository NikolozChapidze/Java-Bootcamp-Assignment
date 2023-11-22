package dev.omedia.abstractFactory;

public class MercedesFactory implements F1Factory{
    @Override
    public Car createCar() {
        return new MercedesCar();
    }
}
