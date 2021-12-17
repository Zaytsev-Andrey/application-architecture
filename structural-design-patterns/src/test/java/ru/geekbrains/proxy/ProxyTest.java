package ru.geekbrains.proxy;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProxyTest {

    private ByteArrayOutputStream out;

    private static SecuredDoor securedDoor;

    @BeforeAll
    public static void init() {
        securedDoor = new SecuredDoor(new LabDoor());
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void validOpenDorTest() {
        securedDoor.open("Pa$$word");
        Assertions.assertEquals("Opening lab door", out.toString());
    }

    @Test
    public void invalidOpenDoorTest() {
        securedDoor.open("invalid");
        Assertions.assertEquals("Big no! It ain't possible.", out.toString());
    }

    @Test
    public void closeDoorTest() {
        securedDoor.close();
        Assertions.assertEquals("Closing the lab door", out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }

}
