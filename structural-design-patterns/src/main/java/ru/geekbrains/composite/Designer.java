package ru.geekbrains.composite;

public class Designer implements Employee {

    private final String name;

    private double salary;

    private String[] roles;

    public Designer(String name, double salary) {
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
