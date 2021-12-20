package ru.geekbrains.strategy;

public class QuickSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] dataset) {
        System.out.print("Sorting using quick sort");
        return dataset;
    }
}
