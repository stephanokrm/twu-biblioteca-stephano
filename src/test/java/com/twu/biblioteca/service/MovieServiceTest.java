package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieServiceTest extends TestCase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MovieRepository movieRepository;
    private MovieService movieService;

    @Override
    public void setUp() {
        super.setUp();

        movieRepository = mock(MovieRepository.class);
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void getAvailableMovies() {
        Movie unavailableMovie = new Movie("Movie 1", 2020, "Director 1", 10, false);
        Movie availableMovie = new Movie("Movie 2", 2020, "Director 2", 5, true);

        List<Movie> movies = new ArrayList<>();
        movies.add(unavailableMovie);
        movies.add(availableMovie);

        when(movieRepository.all()).thenReturn(movies);

        assertThat(movieService.getAvailableMovies(), not(hasItem(unavailableMovie)));
    }
}
