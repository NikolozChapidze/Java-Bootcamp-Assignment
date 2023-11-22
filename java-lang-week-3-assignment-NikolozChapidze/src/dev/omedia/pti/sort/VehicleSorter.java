package dev.omedia.pti.sort;

import dev.omedia.pti.Vehicle;

import java.util.*;

public class VehicleSorter {
    private List<Vehicle> vehicles;

    public VehicleSorter(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> sortByEngineVolumeASC (){
        vehicles.sort(sorterByEngineVolumeASC());
        return vehicles;
    }

    private Comparator<Vehicle> sorterByEngineVolumeASC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getEngine().getVolumeInCC())){
                    return -1;
                }
                if(Objects.isNull(o2.getEngine().getVolumeInCC())){
                    return 1;
                }
                return Integer.compare(o1.getEngine().getVolumeInCC(), o2.getEngine().getVolumeInCC());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        };
    }

    public List<Vehicle> sortByEngineVolumeDESC (){
        vehicles.sort(sorterByEngineVolumeDESC());
        return vehicles;
    }

    private Comparator<Vehicle> sorterByEngineVolumeDESC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getEngine().getVolumeInCC())){
                    return 1;
                }
                if(Objects.isNull(o2.getEngine().getVolumeInCC())){
                    return -1;
                }
                return -1 * Integer.compare(o1.getEngine().getVolumeInCC(), o2.getEngine().getVolumeInCC());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? -1 : 1;
        };
    }

    public List<Vehicle> sortByEngineEmissionASC (){
        vehicles.sort(sorterByEngineEmissionASC());

        return vehicles;
    }

    private Comparator<Vehicle> sorterByEngineEmissionASC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getEngine().getEmission())){
                    return -1;
                }
                if(Objects.isNull(o2.getEngine().getEmission())){
                    return 1;
                }
                return Double.compare(o1.getEngine().getEmission(), o2.getEngine().getEmission());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        };
    }

    public List<Vehicle> sortByEngineEmissionDESC (){
        vehicles.sort(sorterByEngineEmissionDESC());
        return vehicles;
    }

    private Comparator<Vehicle> sorterByEngineEmissionDESC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getEngine().getEmission())){
                    return 1;
                }
                if(Objects.isNull(o2.getEngine().getEmission())){
                    return -1;
                }
                return -1 * Double.compare(o1.getEngine().getEmission(), o2.getEngine().getEmission());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? -1 : 1;
        };
    }

    public List<Vehicle> sortBySeatsASC (){
        vehicles.sort(sorterBySeatsASC());
        return vehicles;
    }

    private Comparator<Vehicle> sorterBySeatsASC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getSeats())){
                    return -1;
                }
                if(Objects.isNull(o2.getSeats())){
                    return 1;
                }
                return Integer.compare(o1.getSeats(), o2.getSeats());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        };
    }

    public List<Vehicle> sortBySeatsDESC (){
        vehicles.sort(sorterBySeatsDESC());
        return vehicles;
    }

    private Comparator<Vehicle> sorterBySeatsDESC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getSeats())){
                    return 1;
                }
                if(Objects.isNull(o2.getSeats())){
                    return -1;
                }
                return -1 * Integer.compare(o1.getSeats(), o2.getSeats());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? -1 : 1;
        };
    }

    public List<Vehicle> sortByBrakingEfficiencyASC (){
        vehicles.sort(sorterByBrakingEfficiencyASC());
        return vehicles;
    }

    private Comparator<Vehicle> sorterByBrakingEfficiencyASC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getBrakingEfficiency())){
                    return -1;
                }
                if(Objects.isNull(o2.getBrakingEfficiency())){
                    return 1;
                }
                return Integer.compare(o1.getBrakingEfficiency(), o2.getBrakingEfficiency());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        };
    }

    public List<Vehicle> sortByBrakingEfficiencyDESC (){
        vehicles.sort(sorterByBrakingEfficiencyDESC());

        return vehicles;
    }

    private Comparator<Vehicle> sorterByBrakingEfficiencyDESC(){
        return (o1, o2) -> {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getBrakingEfficiency())){
                    return 1;
                }
                if(Objects.isNull(o2.getBrakingEfficiency())){
                    return -1;
                }
                return -1 * Integer.compare(o1.getBrakingEfficiency(), o2.getBrakingEfficiency());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? -1 : 1;
        };
    }

    public List<Vehicle> sortBySeatsOrThenBrakingEfficiencyOrThenEngineEmissionBy
            (boolean seatsASC, boolean brakingEfficiencyASC, boolean engineEmissionASC) {

        Comparator<Vehicle> seats = seatsASC ? sorterBySeatsASC() : sorterBySeatsDESC();
        Comparator<Vehicle> breaking = brakingEfficiencyASC ? sorterByBrakingEfficiencyASC()
                                                                : sorterByBrakingEfficiencyDESC();
        Comparator<Vehicle> engine = engineEmissionASC ? sorterByEngineEmissionASC() : sorterByEngineEmissionDESC();

        vehicles.sort((o1, o2) -> {
            if(seats.compare(o1,o2) != 0){
                return seats.compare(o1,o2);
            }
            if(breaking.compare(o1,o2) != 0){
                return breaking.compare(o1,o2);
            }
            return engine.compare(o1,o2);
        });

        return vehicles;
    }



}
