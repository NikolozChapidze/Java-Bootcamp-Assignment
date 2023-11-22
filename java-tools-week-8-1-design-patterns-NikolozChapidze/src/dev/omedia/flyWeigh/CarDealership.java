package dev.omedia.flyWeigh;

import java.util.ArrayList;
import java.util.List;

public class CarDealership {
    private List<Car> cars = new ArrayList<>();

    public void buyForDealership(String name, int year, String engineName, int manufactureYear, String type){
        Engine engine = CarFactory.getEngine(engineName,manufactureYear,type);
        Car car = new Car(name,year,engine);
        cars.add(car);
    }
}
