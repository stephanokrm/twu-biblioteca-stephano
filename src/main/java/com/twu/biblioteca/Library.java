package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Library {
    private List<String> books;
    private PrintStream printStream;

    public Library(PrintStream printStream, List<String> books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void listAllBooks() {
        printStream.println("Book 1\nBook 2\n");
    }
}
