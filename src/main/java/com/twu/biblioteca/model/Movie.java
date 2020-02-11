package com.twu.biblioteca.model;

public class Movie extends Work {
    private final Integer rating;
    private final String director;

    public Movie(String name, int year, String director, Integer rating) {
        super(name, year);

        this.director = director;
        this.rating = rating;
    }

    private String getMovieRating() {
        return rating == 0 ? "Unrated" : Integer.toString(rating);
    }

    @Override
    public String toString() {
        String movieRating = getMovieRating();

        return String.format("Name: %s | Year: %d | Director: %s | Rating: %s", getTitle(), getYear(), director, movieRating);
    }
}
