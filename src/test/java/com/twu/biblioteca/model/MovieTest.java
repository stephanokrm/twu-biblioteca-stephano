package com.twu.biblioteca.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MovieTest {
    @Test
    public void showUnratedWhenMovieIsNotRated() {
        Movie movie = new Movie("Movie", 2020, "Director", 0, true);

        assertThat(movie.toString(), containsString("Rating: Unrated"));
    }
}
