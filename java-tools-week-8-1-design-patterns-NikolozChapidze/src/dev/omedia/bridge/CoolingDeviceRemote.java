package dev.omedia.bridge;

public class CoolingDeviceRemote implements Remote{
    protected CoolingDevice coolingDevice;

    public CoolingDeviceRemote(CoolingDevice coolingDevice) {
        this.coolingDevice = coolingDevice;
    }

    @Override
    public void power() {
        if (coolingDevice.isOn()){
            coolingDevice.stop();
        }else{
            coolingDevice.start();
        }
    }

    @Override
    public void swing() {
        if(coolingDevice.isSwinging()){
            coolingDevice.stopSwing();
        }else{
            coolingDevice.startSwing();
        }
    }

    @Override
    public void increaseSpeed() {
        coolingDevice.setSpeed(coolingDevice.getSpeed()+1);
    }

    @Override
    public void decreaseSpeed() {
        coolingDevice.setSpeed(coolingDevice.getSpeed()-1);
    }
}
