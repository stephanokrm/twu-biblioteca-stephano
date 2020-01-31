package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
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

    @Test
    public void getMovieByTitle() {
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie("Movie 1", 2020, "Director 1", 10, false);
        movies.add(movie1);

        Movie movie2 = new Movie("Movie 2", 2020, "Director 2", 10, true);
        movies.add(movie2);

        when(movieRepository.all()).thenReturn(movies);

        assertThat(movieService.getMovieByName("Movie 1"), equalTo(movie1));
    }

    @Test
    public void checkOutMovie() {
        Movie movie = new Movie("Movie 1", 2020, "Director 1", 10, true);

        movieService.checkOutMovie(movie);

        assertThat(movie.isAvailable(), is(false));
    }

}
