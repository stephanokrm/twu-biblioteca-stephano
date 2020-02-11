package com.twu.biblioteca.domain;

import com.twu.biblioteca.InteractsWithConsole;
import org.junit.Test;

import static org.mockito.Mockito.verify;

public class WelcomeTest extends InteractsWithConsole {
    @Test
    public void showWelcomeMessage() {
        Welcome welcome = new Welcome(console);
        welcome.show();

        verify(console).doWrite("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }
}
