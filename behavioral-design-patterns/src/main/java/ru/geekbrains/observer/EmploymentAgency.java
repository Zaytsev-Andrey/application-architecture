package ru.geekbrains.observer;

import java.util.LinkedList;
import java.util.List;

public class EmploymentAgency implements Observable {

    private final List<Observer> observers = new LinkedList<>();

    @Override
    public void registerJobSeeker(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeJobSeeker(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyJobSeekers(JobPost jobPost) {
        observers.forEach(observer -> observer.onJobPosted(jobPost));
    }

    public void addJob(JobPost jobPost) {
        notifyJobSeekers(jobPost);
    }
}
