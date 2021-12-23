package ru.geekbrains.chain_of_responsibility;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ChainOfResponsibilityTest {

    private ByteArrayOutputStream out;

    private static Account bank;

    private static Account paypal;

    private static Account bitcoin;

    @BeforeAll
    public static void init() {
        bank = new Bank(100.00);
        paypal = new Paypal(300.00);
        bitcoin = new Bitcoin(1000.00);

        bank.setSuccessor(paypal);
        paypal.setSuccessor(bitcoin);
    }

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void bankTest() {
        bank.pay(50.00);
        StringBuilder payLog = new StringBuilder();
        payLog.append("Paid 50.0 using Bank").append(System.lineSeparator());
        Assertions.assertEquals(payLog.toString(), out.toString());
    }

    @Test void paypalTest() {
        bank.pay(250.00);
        StringBuilder payLog = new StringBuilder();
        payLog.append("Cannot pay using Bank. Proceeding ..").append(System.lineSeparator());
        payLog.append("Paid 250.0 using Paypal").append(System.lineSeparator());
        Assertions.assertEquals(payLog.toString(), out.toString());
    }

    @Test
    public void bitcoinTest() {
        bank.pay(500.00);
        StringBuilder payLog = new StringBuilder();
        payLog.append("Cannot pay using Bank. Proceeding ..").append(System.lineSeparator());
        payLog.append("Cannot pay using Paypal. Proceeding ..").append(System.lineSeparator());
        payLog.append("Paid 500.0 using Bitcoin").append(System.lineSeparator());
        Assertions.assertEquals(payLog.toString(), out.toString());
    }

    @Test
    public void haveNotEnoughBalanceTest() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bank.pay(1500.00);
        });

        Assertions.assertEquals("None of the accounts have enough balance", exception.getMessage());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
