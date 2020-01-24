package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.service.MovieService;

import java.io.PrintStream;

public class CheckoutAMovieOption extends MenuOption {
    public static final int NUMBER = 5;
    private final Question question;

    public CheckoutAMovieOption(PrintStream out, Question question, MovieService movieService) {
        super(NUMBER, "Checkout a Movie");
        this.question = question;
    }

    @Override
    public void show() throws Exception {
        question.askForString("Enter the movie name: ");
    }
}
