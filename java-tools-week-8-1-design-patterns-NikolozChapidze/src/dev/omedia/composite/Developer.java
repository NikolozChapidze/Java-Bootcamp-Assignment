package dev.omedia.composite;

public class Developer extends Employee{
    private String devLevel;

    public Developer(String name, int salary, String position, String devLevel) {
        super(name, salary, position);
        this.devLevel = devLevel;
    }

    @Override
    public void printInfo() {
        System.out.println("Developer{" +
                                "devLevel=" + devLevel + '\'' +
                                "salary= " + getSalary()+'\'' +
                                "position= " + getPosition()+'\'' +
                                "name= " + getName()+'\'' +
                            '}');
    }
}
