package com.twu.biblioteca.model;

public class Book implements Model {
    private String title;
    private String author;
    private int year;
    private boolean available = true;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(String title, String author, int year, boolean available) {
        this(title, author, year);
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Author: %s | Published Year: %d", title, author, year);
    }
}
