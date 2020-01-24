package com.twu.biblioteca.model;

public class Movie implements Model {
    private String name;
    private int year;
    private String director;
    private int rating;
    private boolean available;

    public Movie(String name, int year, String director, int rating, boolean available) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    private String getMovieRating() {
        return rating == 0 ? "Unrated" : Integer.toString(rating);
    }

    @Override
    public String toString() {
        String movieRating = getMovieRating();

        return String.format("Name: %s | Year: %d | Director: %s | Rating: %s", name, year, director, movieRating);
    }
}
