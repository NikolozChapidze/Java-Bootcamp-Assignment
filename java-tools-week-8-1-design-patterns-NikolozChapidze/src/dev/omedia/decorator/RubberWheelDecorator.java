package dev.omedia.decorator;

public class RubberWheelDecorator implements Wheel{
    private RubberWheel wrappee;

    public RubberWheelDecorator(RubberWheel wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void roll() {
        wrappee.roll();
    }
}
