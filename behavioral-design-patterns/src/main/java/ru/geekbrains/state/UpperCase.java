package ru.geekbrains.state;

public class UpperCase implements WritingState {

    @Override
    public void write(String words) {
        System.out.print(words.toUpperCase());
    }
}
