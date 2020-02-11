package com.twu.biblioteca.model;

public class Work implements Model {
    private final String title;
    private final Integer year;

    public Work(String title, Integer year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }
}
