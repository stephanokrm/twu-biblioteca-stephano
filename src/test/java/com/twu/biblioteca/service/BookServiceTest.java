package com.twu.biblioteca.service;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
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
}
