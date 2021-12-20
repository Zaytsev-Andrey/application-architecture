package ru.geekbrains.observer;

public class JobSeeker implements Observer {

    private final String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void onJobPosted(JobPost jobPost) {
        System.out.printf("Hi %s! New job posted: %s%n", name, jobPost.getTitle());
    }
}
