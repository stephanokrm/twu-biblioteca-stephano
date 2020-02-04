package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.AuthenticationException;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.service.AuthService;

import java.io.PrintStream;

public class LoginOption extends MenuOption {
    public static final int NUMBER = 6;
    public static final String LABEL = "Login";

    private final Question question;
    private final AuthService authService;

    public LoginOption(PrintStream out, Question question, AuthService authService) {
        super(NUMBER, LABEL, out);

        this.question = question;
        this.authService = authService;
    }

    @Override
    public void show() throws AuthenticationException {
        String libraryNumber = question.askForString("Enter your library number: ");
        String password = question.askForString("Enter your password: ");

        authService.authenticate(libraryNumber, password);
    }
}
