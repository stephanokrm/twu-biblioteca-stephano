package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.CheckoutRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CheckoutMovieServiceTest extends TestCase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private List<Checkout> checkouts;
    private CheckoutMovieService checkoutMovieService;

    @Override
    public void setUp() {
        super.setUp();

        CheckoutRepository checkoutRepository = mock(CheckoutRepository.class);

        checkouts = new ArrayList<>();
        checkoutMovieService = new CheckoutMovieService(checkoutRepository);

        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void checkoutMovie() {
        Movie book = new Movie("Available", 2020, "Author", 10);
        User user = new User("0", "0", "Name", "Email", "Phone");

        Checkout checkout = checkoutMovieService.checkoutMovie(book, user);

        checkouts.add(checkout);

        assertThat(checkout.getWork(), is(equalTo(book)));
        assertThat(checkout.getUser(), is(equalTo(user)));
        assertThat(checkoutMovieService.movieIsAvailable(book), is(false));
        assertThat(checkoutMovieService.movieIsNotAvailable(book), is(true));
    }

    @Test
    public void returnMovie() {
        Movie book = new Movie("Available", 2020, "Author", 10);
        User user = new User("0", "0", "Name", "Email", "Phone");

        Checkout checkout = new Checkout(book, user);

        checkouts.add(checkout);

        checkoutMovieService.returnMovie(book);

        checkouts.remove(checkout);

        assertThat(checkoutMovieService.movieIsAvailable(book), is(true));
        assertThat(checkoutMovieService.movieIsNotAvailable(book), is(false));
    }
}
