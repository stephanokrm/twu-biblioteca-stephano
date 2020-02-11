package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.service.AuthService;

public class MyInformationOption extends MenuOption {
    private static final int NUMBER = 8;
    private static final boolean GUARDED = true;
    private static final String LABEL = "My Information";

    private final AuthService authService;

    public MyInformationOption(Console console, AuthService authService) {
        super(NUMBER, LABEL, console, GUARDED);

        this.authService = authService;
    }

    @Override
    public void show() {
        console.doWrite(authService.getUser().toString());
    }
}
