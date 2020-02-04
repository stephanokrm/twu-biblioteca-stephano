package com.twu.biblioteca.model;

import com.twu.biblioteca.domain.menu.option.MenuOption;

public class MenuOptionStub extends MenuOption {
    public static final int NUMBER = 100;
    public static final String LABEL = "STUB";

    public MenuOptionStub() {
        super(NUMBER, LABEL);
    }

    @Override
    public void show() {
    }
}