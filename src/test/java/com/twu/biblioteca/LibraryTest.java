package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LibraryTest {
    private Library library;
    private List<String> books;
    private PrintStream printStream;

    @Before
    public void setUp() {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
        library = new Library(printStream, books);
    }

    @Test
    public void shouldListAllLibraryBooks() {
        books.add("Book 1");
        books.add("Book 2");

        library.listAllBooks();

        verify(printStream).println("Book 1\nBook 2\n");
    }
}
