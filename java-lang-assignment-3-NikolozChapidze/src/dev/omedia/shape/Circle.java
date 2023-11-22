package dev.omedia.shape;

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double circumference(){
        return 2*radius*Math.PI;
    }

    public double area(){
        return radius*radius*Math.PI;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
