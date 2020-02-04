package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest extends TestCase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BookRepository bookRepository;
    private BookService bookService;

    @Override
    public void setUp() {
        super.setUp();

        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void getAvailableBooks() {
        Book unavailableBook = new Book("Unavailable", "Author", 2020, false);
        Book availableBook = new Book("Available", "Author", 2020);

        List<Book> books = new ArrayList<>();
        books.add(unavailableBook);
        books.add(availableBook);

        when(bookRepository.all()).thenReturn(books);

        assertThat(bookService.getAvailableBooks(), not(hasItem(unavailableBook)));
    }

    @Test
    public void getUnavailableBooks() {
        Book unavailableBook = new Book("Unavailable", "Author", 2020, false);
        Book availableBook = new Book("Available", "Author", 2020);

        List<Book> books = new ArrayList<>();
        books.add(unavailableBook);
        books.add(availableBook);

        when(bookRepository.all()).thenReturn(books);

        assertThat(bookService.getUnavailableBooks(), not(hasItem(availableBook)));
    }

    @Test
    public void getBookByTitle() throws BookNotAvailableException {
        List<Book> books = new ArrayList<>();

        Book book1 = new Book("Book 1", "Author", 2020);
        books.add(book1);

        Book book2 = new Book("Book 2", "Author", 2020);
        books.add(book2);

        when(bookRepository.all()).thenReturn(books);

        assertThat(bookService.getBookByTitle("Book 1"), equalTo(book1));
    }

    @Test
    public void checkOutBook() throws BookNotAvailableException {
        Book book = new Book("Book 1", "Author", 2020, true);
        User user = new User("xxx-xxxx", "0");

        bookService.checkOutBook(book, user);

        assertThat(book.isAvailable(), is(false));
    }

    @Test
    public void bookHasUserAfterCheckOut() throws BookNotAvailableException {
        Book book = new Book("Book 1", "Author", 2020, true);
        User user = new User("xxx-xxxx", "0");

        bookService.checkOutBook(book, user);

        assertThat(book.getRenter(), is(equalTo(user)));
    }

    @Test
    public void returnBook() throws InvalidBookException {
        Book book = new Book("Book 1", "Author", 2020, false);

        bookService.returnBook(book);

        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void throwExceptionWhenBookIsUnavailable() throws BookNotAvailableException {
        expectedException.expect(BookNotAvailableException.class);
        expectedException.expectMessage(BookNotAvailableException.MESSAGE);

        Book book = new Book("Book 1", "Author", 2020, false);
        User user = new User("xxx-xxxx", "0");

        bookService.checkOutBook(book, user);
    }

    @Test
    public void throwExceptionWhenReturningAvailableBook() throws InvalidBookException {
        expectedException.expect(InvalidBookException.class);
        expectedException.expectMessage(InvalidBookException.MESSAGE);

        Book book = new Book("Book 1", "Author", 2020, true);

        bookService.returnBook(book);
    }

    @Test
    public void throwExceptionWhenBookIsNotFound() throws BookNotAvailableException {
        expectedException.expect(BookNotAvailableException.class);
        expectedException.expectMessage(BookNotAvailableException.MESSAGE);

        List<Book> books = new ArrayList<>();

        when(bookRepository.all()).thenReturn(books);

        bookService.getBookByTitle("Book 1");
    }
}
