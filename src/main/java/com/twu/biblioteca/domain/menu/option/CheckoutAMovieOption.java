package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.CheckoutMovieService;
import com.twu.biblioteca.service.MovieService;

public class CheckoutAMovieOption extends MenuOption {
    public static final int NUMBER = 5;
    public static final String LABEL = "Checkout a Movie";
    public static final boolean GUARDER = true;

    private final MovieService movieService;
    private final CheckoutMovieService checkoutService;
    private final AuthService authService;

    public CheckoutAMovieOption(
            Console console,
            MovieService movieService,
            CheckoutMovieService checkoutService,
            AuthService authService
    ) {
        super(NUMBER, LABEL, console, GUARDER);

        this.movieService = movieService;
        this.checkoutService = checkoutService;
        this.authService = authService;
    }

    @Override
    public void show() {
        String name = console.askQuestion("Enter the movie name: ");
        Movie movie = movieService.getMovieByName(name);
        checkoutService.checkoutMovie(movie, authService.getUser());
    }
}
