package com.twu.biblioteca.model;

public class Book extends Work {
    private final String author;

    public Book(String title, String author, Integer year) {
        super(title, year);

        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Author: %s | Published Year: %d", getTitle(), author, getYear());
    }
}
