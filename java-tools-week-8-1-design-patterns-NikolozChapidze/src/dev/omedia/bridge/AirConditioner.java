package dev.omedia.bridge;

public class AirConditioner implements CoolingDevice{
    private boolean isOn = false;
    private boolean isSwinging = false;
    private int speed = 0;

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public boolean isSwinging() {
        return isSwinging;
    }

    @Override
    public void start() {
        isOn = true;
    }

    @Override
    public void stop() {
        isOn = false;
    }

    @Override
    public void startSwing() {
        isSwinging = true;
    }

    @Override
    public void stopSwing() {
        isSwinging = false;
    }

    @Override
    public void setSpeed(int speed) {
        if(speed > 8){
            this.speed = 8;
        }else if(speed < 0){
            this.speed = 0;
        }else{
            this.speed = speed;
        }
    }

    @Override+
    public int getSpeed() {
        return speed;
    }
}
