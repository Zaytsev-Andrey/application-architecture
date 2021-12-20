package ru.geekbrains.state;

public class DefaultText implements WritingState {

    @Override
    public void write(String words) {
        System.out.print(words);
    }
}
