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
    private List<String> books;

    @Before
    public void setUp() {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
        bookList = new BookList(printStream, books);
    }

    @Test
    public void shouldListAllLibraryBooks() {
        books.add("Book 1");
        books.add("Book 2");

        bookList.show();

        verify(printStream).println("Books\n");
        verify(printStream).println("Book 1\nBook 2");
    }
}
