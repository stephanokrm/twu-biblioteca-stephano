package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.CheckoutRepository;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MovieServiceTest extends TestCase {
    private List<Movie> movies;
    private MovieService movieService;
    private CheckoutRepository checkoutRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Override
    public void setUp() {
        super.setUp();

        checkoutRepository = mock(CheckoutRepository.class);

        MovieRepository movieRepository = mock(MovieRepository.class);
        CheckoutMovieService checkoutService = new CheckoutMovieService(checkoutRepository);

        movies = new ArrayList<>();
        movieService = new MovieService(movieRepository, checkoutService);

        doReturn(movies).when(movieRepository).all();
    }

    @Test
    public void getAvailableMovies() {
        Movie unavailableMovie = new Movie("Movie 1", 2020, "Director 1", 10);
        Movie availableMovie = new Movie("Movie 2", 2020, "Director 2", 5);

        Checkout checkout = new Checkout(unavailableMovie, new User("0", "0", "Name", "Email", "Phone"));

        movies.add(unavailableMovie);
        movies.add(availableMovie);

        List<Checkout> checkouts = new ArrayList<>();
        checkouts.add(checkout);

        when(checkoutRepository.all()).thenReturn(checkouts);

        assertThat(movieService.getAvailableMovies(), hasItem(availableMovie));
        assertThat(movieService.getAvailableMovies(), not(hasItem(unavailableMovie)));
    }

    @Test
    public void getMovieByTitle() {
        Movie movie1 = new Movie("Movie 1", 2020, "Director 1", 10);
        movies.add(movie1);

        Movie movie2 = new Movie("Movie 2", 2020, "Director 2", 10);
        movies.add(movie2);

        assertThat(movieService.getMovieByName("Movie 1"), equalTo(movie1));
    }
}
