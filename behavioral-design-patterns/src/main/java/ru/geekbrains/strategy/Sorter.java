package ru.geekbrains.strategy;

public class Sorter {

    private final SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public int[] sort(int[] dataset) {
        return strategy.sort(dataset);
    }
}
