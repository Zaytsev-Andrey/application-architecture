package ru.geekbrains.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StrategyTest {

    private ByteArrayOutputStream out;

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void BubbleSortStrategyTest() {
        SortStrategy bubbleSortStrategy = new BubbleSortStrategy();
        Sorter sorter = new Sorter(bubbleSortStrategy);

        int[] dataset = new int[] {1, 3, 2};
        sorter.sort(dataset);
        Assertions.assertEquals("Sorting using bubble sort", out.toString());
    }

    @Test
    public void QuickSortStrategyTest() {
        SortStrategy quickSortStrategy = new QuickSortStrategy();
        Sorter sorter = new Sorter(quickSortStrategy);

        int[] dataset = new int[] {1, 3, 2};
        sorter.sort(dataset);
        Assertions.assertEquals("Sorting using quick sort", out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
