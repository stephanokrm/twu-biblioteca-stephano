package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest extends TestCase {
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
    public void getBookByTitle() {
        List<Book> books = new ArrayList<>();

        Book book1 = new Book("Book 1", "Author", 2020);
        books.add(book1);

        Book book2 = new Book("Book 2", "Author", 2020);
        books.add(book2);

        when(bookRepository.all()).thenReturn(books);

        assertThat(bookService.getBookByTitle("Book 1"), equalTo(book1));
    }
}