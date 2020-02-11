package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.MovieService;

public class CheckoutAMovieOption extends MenuOption {
    public static final int NUMBER = 5;
    public static final String LABEL = "Checkout a Movie";

    private final MovieService movieService;

    public CheckoutAMovieOption(Console console, MovieService movieService) {
        super(NUMBER, LABEL, console);

        this.movieService = movieService;
    }

    @Override
    public void show() {
        String name = console.askQuestion("Enter the movie name: ");
        Movie movie = movieService.getMovieByName(name);
        movieService.checkOutMovie(movie);
    }
}
