package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.service.AuthService;

import java.io.PrintStream;

public class MyInformationOption extends MenuOption {
    private static final int NUMBER = 8;
    private static final boolean GUARDED = true;
    private static final String LABEL = "My Information";

    private final AuthService authService;

    public MyInformationOption(PrintStream out, AuthService authService) {
        super(NUMBER, LABEL, out, GUARDED);

        this.authService = authService;
    }

    @Override
    public void show() {
        out.println(authService.getUser().toString());
    }
}
