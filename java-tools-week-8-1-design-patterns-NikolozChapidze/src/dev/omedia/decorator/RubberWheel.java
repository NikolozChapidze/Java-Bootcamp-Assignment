package dev.omedia.decorator;

public class RubberWheel implements Wheel {
    private int speed = 0;
    private boolean goingForward = false;
    private boolean isMoving = false;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            this.speed = 0;
        } else {
            this.speed = speed;
        }
    }

    public boolean isGoingForward() {
        return goingForward;
    }

    public void setGoingForward(boolean goingForward) {
        this.goingForward = goingForward;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void stopMoving() {
        isMoving = false;
        speed = 0;
    }

    public void startMoving() {
        if (!isMoving) {
            isMoving = true;
            goingForward = true;
            speed = 5;
        }
    }

    @Override
    public void roll() {
        startMoving();
    }
}
