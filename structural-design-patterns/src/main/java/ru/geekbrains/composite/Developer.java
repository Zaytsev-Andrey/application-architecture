package ru.geekbrains.composite;

public class Developer implements Employee {

    private final String name;

    private double salary;

    private String[] roles;

    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String[] getRoles() {
        return roles;
    }
}
