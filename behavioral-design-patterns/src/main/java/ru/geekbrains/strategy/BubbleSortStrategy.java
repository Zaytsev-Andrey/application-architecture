package ru.geekbrains.strategy;

public class BubbleSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] dataset) {
        System.out.print("Sorting using bubble sort");
        return dataset;
    }
}
