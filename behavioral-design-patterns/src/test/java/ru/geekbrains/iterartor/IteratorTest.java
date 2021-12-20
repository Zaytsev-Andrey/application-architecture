package ru.geekbrains.iterartor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.iterator.RadioStation;
import ru.geekbrains.iterator.StationList;

import java.util.Arrays;
import java.util.List;

public class IteratorTest {

    @Test
    public void stationListTest() {
        StationList stations = new StationList();

        List<RadioStation> expectedStations = Arrays.asList(
                new RadioStation(87.5),
                new RadioStation(99.8),
                new RadioStation(101.3),
                new RadioStation(105.2),
                new RadioStation(107.1)
        );

        Assertions.assertIterableEquals(expectedStations, stations);
    }
}
