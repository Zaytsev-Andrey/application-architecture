package ru.geekbrains.composite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeTest {

    private Organization organization;

    @BeforeEach
    public void init() {
        organization = new Organization();
    }

    @Test
    public void netSalariesTest() {
        organization.addEmployee(new Developer("Developer", 3.50));
        organization.addEmployee(new Designer("Designer", 2.30));

        Assertions.assertEquals(5.80, organization.getNetSalaries());
    }
}
