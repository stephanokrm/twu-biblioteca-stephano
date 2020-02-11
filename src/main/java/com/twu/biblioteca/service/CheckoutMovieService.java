package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.CheckoutRepository;

public class CheckoutMovieService extends CheckoutService {
    public CheckoutMovieService(CheckoutRepository checkoutRepository) {
        super(checkoutRepository);
    }

    public boolean movieIsAvailable(Movie movie) {
        return workIsAvailable(movie);
    }

    public boolean movieIsNotAvailable(Movie movie) {
        return !movieIsAvailable(movie);
    }

    public Checkout checkoutMovie(Movie movie, User user) {
        return checkout(movie, user);
    }

    public void returnMovie(Movie movie) {
        returnWork(movie);
    }
}
