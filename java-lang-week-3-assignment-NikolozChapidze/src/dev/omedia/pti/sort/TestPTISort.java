package dev.omedia.pti.sort;

import dev.omedia.pti.Vehicle;

import java.util.ArrayList;
import java.util.Collection;

public class TestPTISort {
    public static void test(){
        ArrayList<Vehicle> vehicles = new ArrayList<>(){};
        for (int i = 0; i < 5; i++) {
            vehicles.add(new Vehicle());
        }
        VehicleSorter vehicleSorter = new VehicleSorter(vehicles);

        System.out.println("Seat");
        vehicleSorter.sortBySeatsASC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");

        vehicleSorter.sortBySeatsDESC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");

        System.out.println("Volume");
        vehicleSorter.sortByEngineVolumeASC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");

        vehicleSorter.sortByEngineVolumeDESC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");

        System.out.println("Emission");
        vehicleSorter.sortByEngineEmissionASC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");

        vehicleSorter.sortByEngineEmissionDESC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");


        System.out.println("Braking");
        vehicleSorter.sortByBrakingEfficiencyASC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");

        vehicleSorter.sortByBrakingEfficiencyDESC();
        vehicles.forEach(System.out::println);
        System.out.println("---------------------------");



        vehicleSorter.sortBySeatsOrThenBrakingEfficiencyOrThenEngineEmissionBy(false,false,true);
        vehicles.forEach(System.out::println);
    }
}
