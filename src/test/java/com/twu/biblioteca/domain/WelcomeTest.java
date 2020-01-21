package com.twu.biblioteca.domain;

import com.twu.biblioteca.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.verify;

public class WelcomeTest extends TestCase {
    private Welcome welcome;

    @Override
    public void setUp() {
        super.setUp();

        welcome = new Welcome(out);
    }

    @Test
    public void shouldShowWelcomeMessage() {
        welcome.show();

        verify(out).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }
}
