package dev.omedia.pti;

import java.util.Objects;
import java.util.UUID;

public class Vehicle {
    private final UUID vin;
    private final Engine engine;
    private final Integer seats;
    private final Integer brakingEfficiency;

    {
        vin = UUID.randomUUID();
        engine = new Engine();
        seats = getSeatsNumber();
        brakingEfficiency = (int)(Math.random() * 101);
    }

    private int getSeatsNumber(){
        if(this.engine.getVolumeInCC() <= 2200){
            return switch ((int) (Math.random() * 1000) % 6) {
                case 0 -> 2;
                case 1 -> 4;
                case 2 -> 5;
                default -> 0;
            };
        }
        return switch ((int) (Math.random() * 1000) % 6) {
            case 0 -> 2;
            case 1 -> 4;
            case 2 -> 5;
            case 3 -> 7;
            case 4 -> 8;
            case 5 -> 10;
            default -> 0;
        };
    }

    public UUID getVin() {
        return vin;
    }

    public Engine getEngine() {
        return engine;
    }

    public Integer getSeats() {
        return seats;
    }

    public Integer getBrakingEfficiency() {
        return brakingEfficiency;
    }

    public boolean isMinivan(){
        return seats > 5;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin=" + vin +
                ", engine=" + engine +
                ", seats=" + seats +
                ", brakingEfficiency=" + brakingEfficiency +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin,seats,engine,brakingEfficiency);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Vehicle
                &&  Objects.equals(this.vin, ((Vehicle) obj).vin)
                &&  Objects.equals(this.engine, ((Vehicle) obj).engine)
                &&  Objects.equals(this.seats, ((Vehicle) obj).seats)
                &&  Objects.equals(this.brakingEfficiency, ((Vehicle) obj).brakingEfficiency));
    }
}
