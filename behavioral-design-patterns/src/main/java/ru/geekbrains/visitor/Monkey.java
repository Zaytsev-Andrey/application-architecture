package ru.geekbrains.visitor;

public class Monkey implements Animal {

    public void shout() {
        System.out.print("Ooh oo aa aa!");
    }

    @Override
    public void accept(AnimalOperation animalOperation) {
        animalOperation.visitMonkey(this);
    }
}
