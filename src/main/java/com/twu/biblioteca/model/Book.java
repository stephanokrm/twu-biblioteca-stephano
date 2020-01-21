package com.twu.biblioteca.model;

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Author: %s | Published Year: %d", title, author, year);
    }
}
