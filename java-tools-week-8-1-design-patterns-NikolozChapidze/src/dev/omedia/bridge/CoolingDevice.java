package dev.omedia.bridge;

public interface CoolingDevice {
    boolean isOn();
    boolean isSwinging();
    void start();
    void stop();
    void startSwing();
    void stopSwing();
    void setSpeed(int speed);
    int getSpeed();
}
