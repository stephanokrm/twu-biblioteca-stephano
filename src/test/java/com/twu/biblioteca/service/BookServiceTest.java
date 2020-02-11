package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.CheckoutRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BookServiceTest extends TestCase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private List<Book> books;
    private BookService bookService;
    private CheckoutRepository checkoutRepository;

    @Override
    public void setUp() {
        super.setUp();

        checkoutRepository = mock(CheckoutRepository.class);

        BookRepository bookRepository = mock(BookRepository.class);
        CheckoutBookService checkoutService = new CheckoutBookService(checkoutRepository);

        bookService = new BookService(bookRepository, checkoutService);
        books = new ArrayList<>();

        doReturn(books).when(bookRepository).all();
    }

    @Test
    public void getAvailableBooks() {
        Book unavailableBook = new Book("Unavailable", "Author", 2020);
        Book availableBook = new Book("Available", "Author", 2020);

        Checkout checkout = new Checkout(unavailableBook, new User("0", "0", "Name", "Email", "Phone"));

        books.add(unavailableBook);
        books.add(availableBook);

        List<Checkout> checkouts = new ArrayList<>();
        checkouts.add(checkout);

        when(checkoutRepository.all()).thenReturn(checkouts);

        assertThat(bookService.getAvailableBooks(), hasItem(availableBook));
        assertThat(bookService.getAvailableBooks(), not(hasItem(unavailableBook)));
    }

    @Test
    public void getUnavailableBooks() {
        Book unavailableBook = new Book("Unavailable", "Author", 2020);
        Book availableBook = new Book("Available", "Author", 2020);

        Checkout checkout = new Checkout(unavailableBook, new User("0", "0", "Name", "Email", "Phone"));

        books.add(unavailableBook);
        books.add(availableBook);

        List<Checkout> checkouts = new ArrayList<>();
        checkouts.add(checkout);

        when(checkoutRepository.all()).thenReturn(checkouts);

        assertThat(bookService.getUnavailableBooks(), hasItem(unavailableBook));
        assertThat(bookService.getUnavailableBooks(), not(hasItem(availableBook)));
    }

    @Test
    public void getBookByTitle() throws BookNotAvailableException {
        Book book1 = new Book("Book 1", "Author", 2020);
        books.add(book1);

        Book book2 = new Book("Book 2", "Author", 2020);
        books.add(book2);

        assertThat(bookService.getBookByTitle("Book 1"), equalTo(book1));
    }

    @Test
    public void throwBookNotAvailableExceptionWhenBookWasNotFound() throws BookNotAvailableException {
        expectedException.expect(BookNotAvailableException.class);
        expectedException.expectMessage(BookNotAvailableException.MESSAGE);

        bookService.getBookByTitle("Book 1");
    }
}
