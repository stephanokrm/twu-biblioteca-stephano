package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.service.MovieService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CheckoutAMovieOptionTest extends TestCase {
    private Menu menu;
    private Question question;
    private MovieRepository movieRepository;

    @Override
    public void setUp() {
        super.setUp();

        question = mock(Question.class);
        movieRepository = mock(MovieRepository.class);
        MovieService movieService = new MovieService(movieRepository);
        CheckoutAMovieOption checkoutAMovieOption = new CheckoutAMovieOption(out, question, movieService);
        menu = new Menu(out);
        menu.addOption(checkoutAMovieOption);
    }

    @Test
    public void showCheckoutAMovieOption() {
        menu.open();

        verify(out).println("5. Checkout a Movie");
    }

    @Test
    public void askForMovieName() throws Exception {
        Movie movie = new Movie("Name", 2020, "Director", 10, true);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);

        when(question.askForString("Enter the movie name: ")).thenReturn("Name");
        when(movieRepository.all()).thenReturn(movies);

        menu.run(CheckoutAMovieOption.NUMBER);

        verify(question).askForString("Enter the movie name: ");
    }
}
