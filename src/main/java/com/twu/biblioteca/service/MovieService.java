package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    private final MovieRepository movieRepository;
    private final CheckoutMovieService checkoutService;

    public MovieService(MovieRepository movieRepository, CheckoutMovieService checkoutService) {
        this.movieRepository = movieRepository;
        this.checkoutService = checkoutService;
    }

    public List<Movie> getAvailableMovies() {
        return this.movieRepository
                .all()
                .stream()
                .filter(checkoutService::movieIsAvailable)
                .collect(Collectors.toList());
    }

    public Movie getMovieByName(String name) {
        return this.movieRepository
                .all()
                .stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }
}
