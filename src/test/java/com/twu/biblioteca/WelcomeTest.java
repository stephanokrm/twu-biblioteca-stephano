package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WelcomeTest {
    private PrintStream printStream;
    private Welcome welcome;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        welcome = new Welcome(printStream);
    }

    @Test
    public void shouldWelcomeMessage() {
        welcome.show();

        verify(printStream).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }
}
