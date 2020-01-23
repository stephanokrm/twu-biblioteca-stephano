package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieRepository implements Repository<Movie> {
    private static final List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie("The Irishman", 2019, "Martin Scorsese", 10, true),
            new Movie("Parasite", 2019, "Bong Joon-ho", 9, true),
            new Movie("Marriage Story", 2019, "Noah Baumbach", 8, true)
    ));

    public List<Movie> all() {
        return movies;
    }
}
