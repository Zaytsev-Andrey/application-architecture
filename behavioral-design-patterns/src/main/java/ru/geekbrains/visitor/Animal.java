package ru.geekbrains.visitor;

public interface Animal {

    void accept(AnimalOperation animalOperation);
}
