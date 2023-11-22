package dev.omedia.facade;

public class FuelInjector {
    FuelPump fuelPump = new FuelPump();

    public void inject(){
        fuelPump.pumpFuel();
        System.out.println("started injecting fuel");
    }

    public void stop(){
        System.out.println("stopped fuel injecting");
    }
}
