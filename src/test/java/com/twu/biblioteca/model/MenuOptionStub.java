package com.twu.biblioteca.model;

import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.foundation.Console;

public class MenuOptionStub extends MenuOption {
    public static final int NUMBER = 100;
    public static final String LABEL = "STUB";
    public static boolean GUARDED = false;

    public MenuOptionStub(Console console) {
        super(NUMBER, LABEL, console, GUARDED);
    }

    @Override
    public void show() {
    }
}
