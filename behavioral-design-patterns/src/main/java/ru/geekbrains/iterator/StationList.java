package ru.geekbrains.iterator;

import java.util.Iterator;

public class StationList implements Iterable<RadioStation> {

    private final RadioStation[] stations;

    public StationList() {
        this.stations = new RadioStation[] {
                new RadioStation(87.5),
                new RadioStation(99.8),
                new RadioStation(101.3),
                new RadioStation(105.2),
                new RadioStation(107.1)
        };
    }

    @Override
    public Iterator<RadioStation> iterator() {
        return new Iterator<>() {

            private int counter;

            @Override
            public boolean hasNext() {
                return counter < stations.length;
            }

            @Override
            public RadioStation next() {
                RadioStation station = stations[counter];
                counter++;
                return station;
            }
        };
    }
}
