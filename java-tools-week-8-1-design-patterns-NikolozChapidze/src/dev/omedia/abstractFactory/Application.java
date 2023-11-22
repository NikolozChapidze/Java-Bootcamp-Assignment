package dev.omedia.abstractFactory;

public class Application {
    private Car car;

    public Application(F1Factory f1Factory) {
        this.car = f1Factory.createCar();
    }

    public void win(){
        car.winF1();
    }
}
