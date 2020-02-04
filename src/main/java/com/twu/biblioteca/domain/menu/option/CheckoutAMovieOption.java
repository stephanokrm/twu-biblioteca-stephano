package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.service.MovieService;

import java.io.PrintStream;

public class CheckoutAMovieOption extends MenuOption {
    public static final int NUMBER = 5;

    private final Question question;
    private final MovieService movieService;

    public CheckoutAMovieOption(PrintStream out, Question question, MovieService movieService) {
        super(NUMBER, "Checkout a Movie", out);

        this.question = question;
        this.movieService = movieService;
    }

    @Override
    public void show() {
        String name = question.askForString("Enter the movie name: ");
        Movie movie = movieService.getMovieByName(name);
        movieService.checkOutMovie(movie);
    }
}
