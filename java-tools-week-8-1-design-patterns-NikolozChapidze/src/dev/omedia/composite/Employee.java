package dev.omedia.composite;

public abstract class Employee {
    private String name;
    private int salary;
    private String position;

    public Employee(String name, int salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public abstract void printInfo();
}

