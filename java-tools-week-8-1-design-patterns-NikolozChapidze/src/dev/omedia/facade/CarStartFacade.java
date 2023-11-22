package dev.omedia.facade;

public class CarStartFacade {
    FuelInjector fuelInjector = new FuelInjector();
    Starter starter = new Starter();
    Engine engine = new Engine();
    public  void startCar(){
        fuelInjector.inject();
        starter.start();
        engine.start();
    }

    public  void stopCar(){
        starter.stop();
        engine.stop();
        fuelInjector.stop();
    }
}
