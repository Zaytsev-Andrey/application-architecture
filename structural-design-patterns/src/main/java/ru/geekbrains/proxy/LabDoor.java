package ru.geekbrains.proxy;

public class LabDoor implements Door {

    @Override
    public void open() {
        System.out.print("Opening lab door");
    }

    @Override
    public void close() {
        System.out.print("Closing the lab door");
    }
}
