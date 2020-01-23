package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class ListOfBooksOption extends MenuOption {
    public static final int NUMBER = 1;
    public static final String LABEL = "List of Books";

    private PrintStream out;
    private BookService bookService;

    public ListOfBooksOption(PrintStream printStream, BookService bookService) {
        super(NUMBER, LABEL);

        this.out = printStream;
        this.bookService = bookService;
    }

    public void show() {
        out.println(LABEL);

        bookService.getAvailableBooks()
                .stream()
                .map(Book::toString)
                .reduce((accumulator, book) -> String.format("%s\n%s", accumulator, book))
                .ifPresent(out::println);
    }
}
