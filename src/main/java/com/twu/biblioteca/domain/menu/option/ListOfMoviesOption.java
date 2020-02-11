package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.MovieService;

public class ListOfMoviesOption extends MenuOption {
    private final static int NUMBER = 4;
    private final static String LABEL = "List of Movies";

    private final MovieService movieService;

    public ListOfMoviesOption(Console console, MovieService movieService) {
        super(NUMBER, LABEL, console);

        this.movieService = movieService;
    }

    @Override
    public void show() {
        movieService
                .getAvailableMovies()
                .stream()
                .map(Movie::toString)
                .reduce((accumulator, movie) -> String.format("%s\n%s", accumulator, movie))
                .ifPresent(console::doWrite);
    }
}
