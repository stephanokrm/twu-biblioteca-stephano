package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BookListTest {
    private PrintStream printStream;
    private BookList bookList;
    private List<Book> books;

    @Before
    public void setUp() {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
        bookList = new BookList(printStream, books);
    }

    @Test
    public void shouldListAllLibraryBooks() {
        Book book = new Book("Book 1", "Person 1", 2020);
        books.add(book);

        book = new Book("Book 2", "Person 2", 2020);
        books.add(book);

        bookList.show();

        verify(printStream).println("Books\n");
        verify(printStream).println("Title: Book 1 | Author: Person 1 | Year: 2020\nTitle: Book 2 | Author: Person 2 | Year: 2020");
    }
}
