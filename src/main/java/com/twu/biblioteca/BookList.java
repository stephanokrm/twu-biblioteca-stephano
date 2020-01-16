package com.twu.biblioteca;

import java.io.PrintStream;

public class BookList {
    private PrintStream printStream;
    private Library library;
    public static final String MESSAGE = "Books\n";

    public BookList(PrintStream printStream, Library library) {
        this.printStream = printStream;
        this.library = library;
    }

    public void show() {
        printStream.println(MESSAGE);
        library.listAllBooks();
    }
}
