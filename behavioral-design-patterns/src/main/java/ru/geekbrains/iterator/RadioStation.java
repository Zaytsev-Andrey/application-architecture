package ru.geekbrains.iterator;

public class RadioStation {

    private final double frequency;

    public RadioStation(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof RadioStation)) {
            return false;
        }

        RadioStation otherStation = (RadioStation) obj;

        return this.frequency == otherStation.frequency;
    }
}
