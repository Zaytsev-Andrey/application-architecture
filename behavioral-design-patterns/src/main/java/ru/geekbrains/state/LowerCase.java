package ru.geekbrains.state;

public class LowerCase implements WritingState {

    @Override
    public void write(String words) {
        System.out.print(words.toLowerCase());
    }
}
