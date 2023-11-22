package dev.omedia.composite;

import java.util.ArrayList;
import java.util.Arrays;

public class Manager extends Employee{

    ArrayList<TeamLeader> teamLeaders = new ArrayList<>();

    public Manager(String name, int salary, String position) {
        super(name, salary, position);
    }

    public void addTeamLeaders(TeamLeader teamLeader){
        teamLeaders.add(teamLeader);
    }

    public void removeTeamLeaders(TeamLeader teamLeader){
        teamLeaders.remove(teamLeader);
    }

    public void addTeamLeaders(TeamLeader... teamLeader){
        teamLeaders.addAll(Arrays.asList(teamLeader));
    }

    public ArrayList<TeamLeader> getTeamLeaders() {
        return teamLeaders;
    }

    @Override
    public void printInfo() {
        System.out.println("Developer{" +
                "salary= " + getSalary()+'\'' +
                "position= " + getPosition()+'\'' +
                "name= " + getName()+'\'' +
                "teamLeaders= "+ getTeamLeaders()+
                '}');
    }
}
