package com.twu.biblioteca.domain;

import com.twu.biblioteca.foundation.Console;

public class Welcome {
    public static final String MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private final Console console;

    public Welcome(Console console) {
        this.console = console;
    }

    public void show() {
        console.doWrite(MESSAGE);
    }
}
