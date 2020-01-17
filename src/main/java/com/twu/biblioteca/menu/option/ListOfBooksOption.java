package com.twu.biblioteca.menu.option;

import com.twu.biblioteca.Book;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class ListOfBooksOption {
    private PrintStream printStream;
    private List<Book> books;

    public static final String LABEL = "1. List of Books";

    public ListOfBooksOption(PrintStream printStream, List<Book> books) {
        this.printStream = printStream;
        this.books = books;
    }

    public void show() {
        List<String> booksAsStringList = books.stream().map(Book::toString).collect(Collectors.toList());
        String booksAsString = String.join("\n", booksAsStringList);

        printStream.println(booksAsString);
    }
}
