package dev.omedia.decorator;

public class MotorcycleDecorator extends RubberWheelDecorator{
    private int engineCC;
    private boolean isAutomatic;
    private String markOfEnigine;

    public MotorcycleDecorator(RubberWheel wrappee, int engineCC, boolean isAutomatic, String markOfEnigine) {
        super(wrappee);
        this.engineCC = engineCC;
        this.isAutomatic = isAutomatic;
        this.markOfEnigine = markOfEnigine;
    }

    public int getEngineCC() {
        return engineCC;
    }

    public void setEngineCC(int engineCC) {
        this.engineCC = engineCC;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public String getMarkOfEnigine() {
        return markOfEnigine;
    }

    public void setMarkOfEnigine(String markOfEnigine) {
        this.markOfEnigine = markOfEnigine;
    }

    @Override
    public void roll() {
        super.roll();
    }
}
