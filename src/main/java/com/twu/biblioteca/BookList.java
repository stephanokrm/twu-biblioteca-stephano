package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class BookList {
    private PrintStream printStream;
    private List<String> books;

    public static final String MESSAGE = "Books\n";

    public BookList(PrintStream printStream, List<String> books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void show() {
        printStream.println(MESSAGE);
        printStream.println(String.join("\n", books));
    }
}
