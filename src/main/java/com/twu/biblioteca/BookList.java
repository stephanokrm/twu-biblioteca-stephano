package com.twu.biblioteca;

import java.io.PrintStream;

public class BookList {
    private PrintStream printStream;
    public static final String MESSAGE = "Books\n";

    public BookList(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void show() {
        printStream.println(MESSAGE);
    }
}
