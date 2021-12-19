package ru.geekbrains.decorator;

public class WhipCoffee implements Coffee {

    private final Coffee coffee;

    public WhipCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.40;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", whip";
    }
}
