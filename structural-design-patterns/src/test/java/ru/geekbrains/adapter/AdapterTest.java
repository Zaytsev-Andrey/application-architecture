package ru.geekbrains.adapter;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AdapterTest {

    private ByteArrayOutputStream out;

    private static Hunter hunter;

    @BeforeAll
    public static void init() {
        hunter = new Hunter();
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void AfricanLionTest() {
        Lion africanLion = new AfricanLion();
        hunter.hunt(africanLion);
        Assertions.assertEquals("African Lion Roars!", out.toString());
    }

    @Test
    public void AsianLionTest() {
        Lion asianLion = new AsianLion();
        hunter.hunt(asianLion);
        Assertions.assertEquals("Asian Lion Roars!", out.toString());
    }

    @Test
    public void WildDogTest() {
        WildDog wildDog = new WildDog();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(wildDog);
        hunter.hunt(wildDogAdapter);
        Assertions.assertEquals("Wild Dog Barks!", out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
