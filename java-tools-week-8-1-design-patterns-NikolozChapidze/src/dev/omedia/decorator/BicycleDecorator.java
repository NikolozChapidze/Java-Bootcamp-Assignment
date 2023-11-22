package dev.omedia.decorator;

public class BicycleDecorator extends RubberWheelDecorator{
    private int numberOfPedals;
    private int yearOfManufacture;
    private String bicucleType;

    public BicycleDecorator(RubberWheel wrappee, int numberOfPedals, int yearOfManufacture, String bicucleType) {
        super(wrappee);
        this.numberOfPedals = numberOfPedals;
        this.yearOfManufacture = yearOfManufacture;
        this.bicucleType = bicucleType;
    }

    public int getNumberOfPedals() {
        return numberOfPedals;
    }

    public void setNumberOfPedals(int numberOfPedals) {
        this.numberOfPedals = numberOfPedals;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getBicucleType() {
        return bicucleType;
    }

    public void setBicucleType(String bicucleType) {
        this.bicucleType = bicucleType;
    }

    public BicycleDecorator(RubberWheel wrappee) {
        super(wrappee);
    }
}
