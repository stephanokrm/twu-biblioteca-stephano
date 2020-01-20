package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BibliotecaAppTest {
    private final InputStream originalIn = System.in;
    private PrintStream out;
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        String option = "0";
        ByteArrayInputStream in = new ByteArrayInputStream(option.getBytes());
        System.setIn(in);

        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void shouldShowTheWelcomeMessageWhenIStartTheApplication() {
        BibliotecaApp.main(null);

        verify(out).println(Welcome.MESSAGE);
    }

    @Test
    public void shouldShowTheMenuAfterTheWelcomeMessageAppears() {
        BibliotecaApp.main(null);

        verify(out).println(Welcome.MESSAGE);
        verify(out).println("- Menu");
    }
}
