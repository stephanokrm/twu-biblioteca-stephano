package com.twu.biblioteca.domain;

import java.io.PrintStream;

public class Welcome {
    private PrintStream printStream;
    public static final String MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";

    public Welcome(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void show() {
        printStream.println(MESSAGE);
    }
}
