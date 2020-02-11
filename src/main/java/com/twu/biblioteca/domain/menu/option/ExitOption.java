package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;

public class ExitOption extends MenuOption {
    public static final int NUMBER = 0;
    public static final String LABEL = "Exit";

    public ExitOption(Console console) {
        super(NUMBER, LABEL, console);
    }

    @Override
    public void show() {
    }
}
