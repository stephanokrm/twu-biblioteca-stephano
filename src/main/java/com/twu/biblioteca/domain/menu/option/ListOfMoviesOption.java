package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.MovieService;

import java.io.PrintStream;

public class ListOfMoviesOption extends MenuOption {
    private final static String LABEL = "List of Movies";
    private final PrintStream out;
    private final MovieService movieService;

    public ListOfMoviesOption(PrintStream out, MovieService movieService) {
        super(4, LABEL);
        this.out = out;
        this.movieService = movieService;
    }

    @Override
    public void show() throws Exception {
        out.println(LABEL);

        movieService.getAvailableMovies()
                .stream()
                .map(Movie::toString)
                .reduce((accumulator, movie) -> String.format("%s\n%s", accumulator, movie))
                .ifPresent(out::println);
    }
}
