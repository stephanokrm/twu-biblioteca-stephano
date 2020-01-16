package com.twu.biblioteca.menu;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuTest {
    private PrintStream printStream;
    private Menu menu;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        menu = new Menu(printStream);
    }

    @Test
    public void shouldShowMenuOptions() {
        menu.show();

        verify(printStream).println("1. List of Books");
    }
}
