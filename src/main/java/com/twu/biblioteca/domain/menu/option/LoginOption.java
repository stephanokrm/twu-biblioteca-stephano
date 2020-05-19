package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.AuthenticationException;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.service.AuthService;

public class LoginOption extends MenuOption {
    public static final int NUMBER = 6;
    public static final String LABEL = "Login";

    private final AuthService authService;

    public LoginOption(Console console, AuthService authService) {
        super(NUMBER, LABEL, console);

        this.authService = authService;
    }

    @Override
    public void show() throws AuthenticationException {
        String libraryNumber = console.askQuestion("Enter your library number: ");
        String password = console.askPassword("Enter your password: ");

        authService.authenticate(libraryNumber, password);
    }
}
