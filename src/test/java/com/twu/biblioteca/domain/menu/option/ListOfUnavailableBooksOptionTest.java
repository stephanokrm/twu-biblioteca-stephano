package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.CheckoutRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.CheckoutBookService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfUnavailableBooksOptionTest extends InteractsWithConsole {
    private Menu menu;
    private List<Checkout> checkouts;
    private ListOfUnavailableBooksOption listOfUnavailableBooksOption;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        CheckoutRepository checkoutRepository = mock(CheckoutRepository.class);
        CheckoutBookService checkoutService = new CheckoutBookService(checkoutRepository);
        UserService userService = new UserService(userRepository);

        listOfUnavailableBooksOption = new ListOfUnavailableBooksOption(console, checkoutService);

        checkouts = new ArrayList<>();

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(listOfUnavailableBooksOption);

        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void showListOfUnavailableBooksOption() {
        menu.open();

        verify(console).doWrite("7. List of Unavailable Books");
    }

    @Test
    public void runListOfUnavailableBooksOption() throws Exception {
        User user = new User("0", "0", "Name", "Email", "Phone");
        Book book = new Book("Book 1", "Person 1", 2020);
        Checkout checkout = new Checkout(book, user);

        checkouts.add(checkout);

        Movie movie = new Movie("Movie 2", 2020, "Person 2", 10);

        checkout = new Checkout(movie, user);

        checkouts.add(checkout);

        option(listOfUnavailableBooksOption)
                .expectsOutput("List of Unavailable Books")
                .expectsOutput("Book: Book 1 | Renter Library Number: 0\nMovie: Movie 2 | Renter Library Number: 0")
                .execute();
    }
}
