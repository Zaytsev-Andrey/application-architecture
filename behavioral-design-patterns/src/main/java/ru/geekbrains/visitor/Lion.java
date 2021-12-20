package ru.geekbrains.visitor;

public class Lion implements Animal {

    public void roar() {
        System.out.print("Roaaar!");
    }

    @Override
    public void accept(AnimalOperation animalOperation) {
        animalOperation.visitLion(this);
    }
}
