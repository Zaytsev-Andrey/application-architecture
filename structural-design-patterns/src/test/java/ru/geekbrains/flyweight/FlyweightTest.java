package ru.geekbrains.flyweight;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FlyweightTest {

    private ByteArrayOutputStream out;

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void takeOrderTest() {
        TeaMaker teaMaker = new TeaMaker();
        TeaShop teaShop = new TeaShop(teaMaker);

        teaShop.takeOrder("less sugar", 1);
        teaShop.takeOrder("more milk", 2);
        teaShop.takeOrder("without sugar", 5);

        teaShop.serve();

        StringBuilder takeOrderLog = new StringBuilder();
        takeOrderLog.append("Serving tea to table# 1").append(System.lineSeparator());
        takeOrderLog.append("Serving tea to table# 2").append(System.lineSeparator());
        takeOrderLog.append("Serving tea to table# 5").append(System.lineSeparator());

        Assertions.assertEquals(takeOrderLog.toString(), out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
