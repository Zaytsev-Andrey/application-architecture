package ru.geekbrains.facade;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FacadeTest {

    private ByteArrayOutputStream out;

    private static ComputerFacade computerFacade;

    @BeforeAll
    public static void init() {
        computerFacade = new ComputerFacade(new Computer());
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void turnOnTest() {
        StringBuilder turnOnLog = new StringBuilder();
        turnOnLog.append("Ouch!").append(System.lineSeparator());
        turnOnLog.append("Beep beep!").append(System.lineSeparator());
        turnOnLog.append("Loading..").append(System.lineSeparator());
        turnOnLog.append("Ready to be used!").append(System.lineSeparator());

        computerFacade.turnOn();
        Assertions.assertEquals(turnOnLog.toString(), out.toString());
    }

    @Test
    public void turnOffTest() {
        StringBuilder turnOnLog = new StringBuilder();
        turnOnLog.append("Bup bup bup buzzzz!").append(System.lineSeparator());
        turnOnLog.append("Haaah!").append(System.lineSeparator());
        turnOnLog.append("Zzzzz").append(System.lineSeparator());

        computerFacade.turnOff();
        Assertions.assertEquals(turnOnLog.toString(), out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
