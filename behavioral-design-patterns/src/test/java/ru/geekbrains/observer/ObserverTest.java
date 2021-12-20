package ru.geekbrains.observer;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ObserverTest {

    private ByteArrayOutputStream out;

    private static EmploymentAgency agency;

    @BeforeAll
    public static void init() {
        agency = new EmploymentAgency();
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void notifyObserversTest() {
        agency.registerJobSeeker(new JobSeeker("John Doe"));
        agency.registerJobSeeker(new JobSeeker("Jane Doe"));

        StringBuilder notifyLog = new StringBuilder();
        notifyLog.append("Hi John Doe! New job posted: Software Engineer").append(System.lineSeparator());
        notifyLog.append("Hi Jane Doe! New job posted: Software Engineer").append(System.lineSeparator());

        agency.notifyJobSeekers(new JobPost("Software Engineer"));

        Assertions.assertEquals(notifyLog.toString(), out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
