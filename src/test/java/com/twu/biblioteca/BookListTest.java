package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BookListTest {
    private PrintStream printStream;
    private BookList bookList;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bookList = new BookList(printStream);
    }

    @Test
    public void shouldShowBookListLabel() {
        bookList.show();

        verify(printStream).println("Books\n");
    }
}
