package com.twu.biblioteca;

import java.io.PrintStream;

public class BookList {
    private PrintStream printStream;

    public BookList(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void show() {
        printStream.println("Books:\n");
    }
}
