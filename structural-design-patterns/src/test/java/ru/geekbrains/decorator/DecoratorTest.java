package ru.geekbrains.decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DecoratorTest {

    private static Coffee simpleCoffee;

    @BeforeAll
    public static void init() {
        simpleCoffee = new SimpleCoffee();
    }

    @Test
    public void SimpleCoffeeTest() {
        Assertions.assertEquals(1.20, simpleCoffee.getCost());
        Assertions.assertEquals("Simple coffee", simpleCoffee.getDescription());
    }

    @Test
    public void MilkCoffeeTest() {
        Coffee milkCoffee = new MilkCoffee(simpleCoffee);
        Assertions.assertEquals(1.90, milkCoffee.getCost());
        Assertions.assertEquals("Simple coffee, milk", milkCoffee.getDescription());
    }

    @Test
    public void WhipCoffeeTest() {
        Coffee whipCoffee = new WhipCoffee(simpleCoffee);
        Assertions.assertEquals(1.60, whipCoffee.getCost());
        Assertions.assertEquals("Simple coffee, whip", whipCoffee.getDescription());
    }

    @Test
    public void VanillaCoffeeTest() {
        Coffee vanillaCoffee = new VanillaCoffee(simpleCoffee);
        Assertions.assertEquals(1.70, vanillaCoffee.getCost());
        Assertions.assertEquals("Simple coffee, vanilla", vanillaCoffee.getDescription());
    }

    @Test
    public void FullCoffeeTest() {
        Coffee fullCoffee = new WhipCoffee(
                new VanillaCoffee(
                        new MilkCoffee(simpleCoffee)
                )
        );
        Assertions.assertEquals(2.80, fullCoffee.getCost());
        Assertions.assertEquals("Simple coffee, milk, vanilla, whip", fullCoffee.getDescription());
    }
}
