package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
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

public class CheckoutAMovieOptionTest extends InteractsWithConsole {
    private Menu menu;
    private List<Movie> movies;
    private CheckoutAMovieOption checkoutAMovieOption;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        MovieRepository movieRepository = mock(MovieRepository.class);
        CheckoutRepository checkoutRepository = new CheckoutRepository();
        CheckoutMovieService checkoutService = new CheckoutMovieService(checkoutRepository);
        MovieService movieService = new MovieService(movieRepository, checkoutService);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userService);

        movies = new ArrayList<>();

        checkoutAMovieOption = new CheckoutAMovieOption(console, movieService, checkoutService, authService);

        menu = new Menu(console, authService);
        menu.addOption(checkoutAMovieOption);

        doReturn(movies).when(movieRepository).all();
    }

    @Test
    public void showCheckoutAMovieOption() {
        menu.open();

        verify(console).doWrite("5. Checkout a Movie");
    }

    @Test
    public void runCheckoutAMovieOption() throws Exception {
        Movie movie = new Movie("Name", 2020, "Director", 10);

        movies.add(movie);

        option(checkoutAMovieOption)
                .expectsOutput("Checkout a Movie")
                .expectsQuestion("Enter the movie name: ", "Name")
                .execute();
    }
}
