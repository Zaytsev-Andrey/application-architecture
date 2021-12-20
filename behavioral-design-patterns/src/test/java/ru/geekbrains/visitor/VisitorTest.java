package ru.geekbrains.visitor;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VisitorTest {

    private ByteArrayOutputStream out;

    private static AnimalOperation animalOperation;

    @BeforeAll
    public static void init() {
        animalOperation = new Speak();
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void monkeyVisitTest() {
        Monkey monkey = new Monkey();
        monkey.accept(animalOperation);
        Assertions.assertEquals("Ooh oo aa aa!", out.toString());
    }

    @Test
    public void lionVisitTest() {
        Lion lion = new Lion();
        lion.accept(animalOperation);
        Assertions.assertEquals("Roaaar!", out.toString());
    }

    @Test
    public void dolphinVisitTest() {
        Dolphin dolphin = new Dolphin();
        dolphin.accept(animalOperation);
        Assertions.assertEquals("Tuut tuttu tuutt!", out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
