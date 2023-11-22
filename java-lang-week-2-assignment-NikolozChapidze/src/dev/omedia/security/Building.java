package dev.omedia.security;

import java.util.Arrays;

public class Building {
    private String location;
    private String name;
    private Gate[] gates;
    private int peopleAmount = 0;

    public Building(int gatesAmount) {
        gates = new Gate[gatesAmount];
        for (int i = 0; i < gatesAmount; i++) {
            gates[i] = new Gate();
            gates[i].setBuilding(this);
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeopleAmount(int peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public int retrieveAmountOfPeopleInTheBuilding(){
        return peopleAmount;
    }

    public Gate getGateByIndex(int index){
        return gates[index];
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        Building temp = (Building)obj;
        return temp.location.equals(this.location) && temp.name.equals(this.name) && temp.gates.length == this.gates.length && temp.peopleAmount == this.peopleAmount;
    }

    @Override
    public String toString() {
        return  "location = " + location + "\n" +
                "name = " + name + "\n" +
                "gates = " + Arrays.toString(gates) + "\n" +
                "peopleAmount = " + peopleAmount;
    }
}
