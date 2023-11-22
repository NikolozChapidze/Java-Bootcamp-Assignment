package dev.omedia;

import dev.omedia.security.Building;

public class Main {

    public static void main(String[] args) {
        Building building = new Building(2);
        building.getGateByIndex(0).enter();
        building.getGateByIndex(0).enter();
        building.getGateByIndex(0).enter();
        System.out.println("amount of people in the building: " + building.retrieveAmountOfPeopleInTheBuilding());
        building.getGateByIndex(1).leave();
        building.getGateByIndex(1).leave();
        System.out.println(building);
    }
}
