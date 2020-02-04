package com.twu.biblioteca.model;

import com.twu.biblioteca.domain.menu.option.MenuOption;

import java.io.PrintStream;

public class MenuOptionStub extends MenuOption {
    public static final int NUMBER = 100;
    public static final String LABEL = "STUB";

    public MenuOptionStub(PrintStream out) {
        super(NUMBER, LABEL, out);
    }

    @Override
    public void show() {
    }
}
