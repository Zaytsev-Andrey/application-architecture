package ru.geekbrains.decorator;

public class MilkCoffee implements Coffee {

    private final Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.70;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", milk";
    }
}
