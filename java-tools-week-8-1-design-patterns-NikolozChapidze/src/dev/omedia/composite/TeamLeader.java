package dev.omedia.composite;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamLeader extends Employee{
    private int workExperienceAsTeamLead;
    private ArrayList<Employee> developerTeam = new ArrayList<>();

    public TeamLeader(String name, int salary, String position, int workExperienceAsTeamLead) {
        super(name, salary, position);
        this.workExperienceAsTeamLead = workExperienceAsTeamLead;
    }

    public void addDeveloper(Developer developer){
        developerTeam.add(developer);
    }

    public void removeDeveloper(Developer developer){
        developerTeam.remove(developer);
    }

    public void addDevelopers(Developer... developers){
        developerTeam.addAll(Arrays.asList(developers));
    }

    public int getWorkExperienceAsTeamLead() {
        return workExperienceAsTeamLead;
    }

    public ArrayList<Employee> getDeveloperTeam() {
        return developerTeam;
    }

    @Override
    public void printInfo() {
        System.out.println("TeamLead{" +
                "salary= " + getSalary()+'\'' +
                "position= " + getPosition()+'\'' +
                "name= " + getName()+'\'' +
                "workExperienceAsTeamLead= " + getWorkExperienceAsTeamLead()+'\'' +
                "group" + getDeveloperTeam()+
                '}');
    }
}
