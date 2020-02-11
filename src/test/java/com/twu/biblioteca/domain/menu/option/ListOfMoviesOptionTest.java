package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.CheckoutRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.CheckoutMovieService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfMoviesOptionTest extends InteractsWithConsole {
    private Menu menu;
    private List<Movie> movies;
    private ListOfMoviesOption listOfMoviesOption;

    @Override
    public void setUp() {
        super.setUp();

        List<Checkout> checkouts = new ArrayList<>();
        UserRepository userRepository = mock(UserRepository.class);
        MovieRepository movieRepository = mock(MovieRepository.class);
        CheckoutRepository checkoutRepository = mock(CheckoutRepository.class);
        CheckoutMovieService checkoutService = new CheckoutMovieService(checkoutRepository);
        MovieService movieService = new MovieService(movieRepository, checkoutService);
        UserService userService = new UserService(userRepository);

        listOfMoviesOption = new ListOfMoviesOption(console, movieService);

        movies = new ArrayList<>();

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(listOfMoviesOption);

        doReturn(movies).when(movieRepository).all();
        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void showListOfMoviesOption() {
        menu.open();

        verify(console).doWrite("4. List of Movies");
    }

    @Test
    public void runListOfMoviesOption() throws Exception {
        Movie movie = new Movie("Movie 1", 2020, "Director 1", 10);
        movies.add(movie);

        movie = new Movie("Movie 3", 2020, "Director 3", 5);
        movies.add(movie);

        option(listOfMoviesOption)
                .expectsOutput("List of Movies")
                .expectsOutput("Name: Movie 1 | Year: 2020 | Director: Director 1 | Rating: 10\nName: Movie 3 | Year: 2020 | Director: Director 3 | Rating: 5")
                .execute();
    }
}
