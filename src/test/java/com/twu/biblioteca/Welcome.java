package com.twu.biblioteca;

import java.io.PrintStream;

public class Welcome {
    private PrintStream printStream;

    public Welcome(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void show() {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }
}
