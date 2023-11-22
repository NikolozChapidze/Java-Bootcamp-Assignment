package dev.omedia.security;

public class Gate {
    private Building building;

    private int peopleEntered = 0;

    private int peopleLeft = 0;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getPeopleEntered() {
        return peopleEntered;
    }

    public void setPeopleEntered(int peopleEntered) {
        this.peopleEntered = peopleEntered;
    }

    public int getPeopleLeft() {
        return peopleLeft;
    }

    public void setPeopleLeft(int peopleLeft) {
        this.peopleLeft = peopleLeft;
    }

    public void enter(){
        peopleEntered++;
        building.setPeopleAmount(building.retrieveAmountOfPeopleInTheBuilding()+1);
    }

    public void leave(){
        if(building.retrieveAmountOfPeopleInTheBuilding() == 0){
            System.err.println("something went wrong");
            return;
        }
        peopleLeft++;
        building.setPeopleAmount(building.retrieveAmountOfPeopleInTheBuilding()-1);;
    }

    @Override
    public String toString() {
        return "\npeople entered from this gate: " + peopleEntered
                + "; people left from this gate: " + peopleLeft;
    }
}
