package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaAppTest {
    private PrintStream printStream;
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        System.setOut(printStream);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldWelcomeMessageWhenIStartTheApplication() {
        BibliotecaApp.main(null);

        verify(printStream).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }
}
