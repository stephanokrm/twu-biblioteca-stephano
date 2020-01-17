package com.twu.biblioteca.menu.option;

import com.twu.biblioteca.Book;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class ListOfBooksOption extends MenuOption {
    private PrintStream printStream;
    private List<Book> books;

    public ListOfBooksOption(PrintStream printStream, List<Book> books) {
        super(1, "List of Books");

        this.printStream = printStream;
        this.books = books;
    }

    public void show() {
        List<String> booksAsStringList = books.stream().map(Book::toString).collect(Collectors.toList());
        String booksAsString = String.join("\n", booksAsStringList);

        printStream.println("- Books");
        printStream.println(booksAsString);
    }
}
