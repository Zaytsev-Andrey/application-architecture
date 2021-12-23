package ru.geekbrains.observer;

public interface Observable {

    void registerJobSeeker(Observer observer);

    void removeJobSeeker(Observer observer);

    void notifyJobSeekers(JobPost jobPost);
}
