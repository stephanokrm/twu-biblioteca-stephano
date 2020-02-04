package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.MovieService;

import java.io.PrintStream;

public class ListOfMoviesOption extends MenuOption {
    private final static int NUMBER = 4;
    private final static String LABEL = "List of Movies";

    private final MovieService movieService;

    public ListOfMoviesOption(PrintStream out, MovieService movieService) {
        super(NUMBER, LABEL, out);

        this.movieService = movieService;
    }

    @Override
    public void show() {
        movieService.getAvailableMovies()
                .stream()
                .map(Movie::toString)
                .reduce((accumulator, movie) -> String.format("%s\n%s", accumulator, movie))
                .ifPresent(out::println);
    }
}
