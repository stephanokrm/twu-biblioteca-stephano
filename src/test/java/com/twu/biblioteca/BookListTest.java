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
    private Library library;

    @Before
    public void setUp() {
        List<String> books = new ArrayList<>();

        printStream = mock(PrintStream.class);
        library = new Library(printStream, books);
        bookList = new BookList(printStream, library);
    }

    @Test
    public void shouldShowBookListLabel() {
        bookList.show();

        verify(printStream).println("Books\n");
    }
}
