package ru.geekbrains.composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public double getNetSalaries() {
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
    }
}
