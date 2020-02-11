package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Checkout;
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

public class CheckoutBookServiceTest extends TestCase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private List<Checkout> checkouts;
    private CheckoutBookService checkoutBookService;

    @Override
    public void setUp() {
        super.setUp();

        CheckoutRepository checkoutRepository = mock(CheckoutRepository.class);

        checkouts = new ArrayList<>();
        checkoutBookService = new CheckoutBookService(checkoutRepository);

        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void checkoutBook() throws BookNotAvailableException {
        Book book = new Book("Available", "Author", 2020);
        User user = new User("0", "0", "Name", "Email", "Phone");

        Checkout checkout = checkoutBookService.checkoutBook(book, user);

        checkouts.add(checkout);

        assertThat(checkout.getWork(), is(equalTo(book)));
        assertThat(checkout.getUser(), is(equalTo(user)));
        assertThat(checkoutBookService.bookIsAvailable(book), is(false));
        assertThat(checkoutBookService.bookIsNotAvailable(book), is(true));
    }

    @Test
    public void returnBook() throws InvalidBookException {
        Book book = new Book("Available", "Author", 2020);
        User user = new User("0", "0", "Name", "Email", "Phone");

        Checkout checkout = new Checkout(book, user);

        checkouts.add(checkout);

        checkoutBookService.returnBook(book);

        checkouts.remove(checkout);

        assertThat(checkoutBookService.bookIsAvailable(book), is(true));
        assertThat(checkoutBookService.bookIsNotAvailable(book), is(false));
    }

    @Test
    public void throwBookNotAvailableExceptionWhenBookIsAlreadyCheckedOut() throws BookNotAvailableException {
        expectedException.expect(BookNotAvailableException.class);
        expectedException.expectMessage(BookNotAvailableException.MESSAGE);

        Book book = new Book("Available", "Author", 2020);
        User user = new User("0", "0", "Name", "Email", "Phone");

        Checkout checkout = new Checkout(book, user);

        checkouts.add(checkout);

        checkoutBookService.checkoutBook(book, user);
    }

    @Test
    public void throwInvalidBookExceptionWhenBookIsAlreadyReturned() throws InvalidBookException {
        expectedException.expect(InvalidBookException.class);
        expectedException.expectMessage(InvalidBookException.MESSAGE);

        Book book = new Book("Available", "Author", 2020);

        checkoutBookService.returnBook(book);
    }
}
