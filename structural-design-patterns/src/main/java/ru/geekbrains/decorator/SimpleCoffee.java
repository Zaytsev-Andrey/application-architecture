package ru.geekbrains.decorator;

public class SimpleCoffee implements Coffee {

    @Override
    public double getCost() {
        return 1.20;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}
