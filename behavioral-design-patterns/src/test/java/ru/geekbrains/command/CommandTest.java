package ru.geekbrains.command;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandTest {

    private ByteArrayOutputStream out;

    private static TurnOn turnOn;

    private static TurnOff turnOff;

    private static RemoteControl remoteControl;

    @BeforeAll
    public static void init() {
        Bulb bulb = new Bulb();
        turnOn = new TurnOn(bulb);
        turnOff = new TurnOff(bulb);
        remoteControl = new RemoteControl();
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void turnOnTest() {
        remoteControl.submit(turnOn);
        Assertions.assertEquals("Bulb has been lit", out.toString());
    }

    @Test
    public void turnOffTest() {
        remoteControl.submit(turnOff);
        Assertions.assertEquals("Darkness!", out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
