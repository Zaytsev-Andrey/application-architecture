package ru.geekbrains.visitor;

public class Dolphin implements Animal {

    public void speak() {
        System.out.print("Tuut tuttu tuutt!");
    }

    @Override
    public void accept(AnimalOperation animalOperation) {
        animalOperation.visitDolphin(this);
    }
}
