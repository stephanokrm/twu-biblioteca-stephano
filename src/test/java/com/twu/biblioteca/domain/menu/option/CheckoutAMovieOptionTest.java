package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CheckoutAMovieOptionTest extends InteractsWithConsole {
    private Menu menu;
    private CheckoutAMovieOption checkoutAMovieOption;

    @Mock
    private MovieRepository movieRepository;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        MovieService movieService = new MovieService(movieRepository);
        UserService userService = new UserService(userRepository);

        checkoutAMovieOption = new CheckoutAMovieOption(console, movieService);

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(checkoutAMovieOption);
    }

    @Test
    public void showCheckoutAMovieOption() {
        menu.open();

        verify(console).doWrite("5. Checkout a Movie");
    }

    @Test
    public void runCheckoutAMovieOption() throws Exception {
        Movie movie = new Movie("Name", 2020, "Director", 10, true);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);

        when(movieRepository.all()).thenReturn(movies);

        expectsOutput("Checkout a Movie")
                .expectsQuestion("Enter the movie name: ", "Name")
                .execute(checkoutAMovieOption);
    }
}
