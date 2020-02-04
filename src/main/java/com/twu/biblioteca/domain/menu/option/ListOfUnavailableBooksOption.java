package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class ListOfUnavailableBooksOption extends MenuOption {
    private static final int NUMBER = 7;
    private static final String LABEL = "List of Unavailable Books";

    private final BookService bookService;

    public ListOfUnavailableBooksOption(PrintStream out, BookService bookService) {
        super(NUMBER, LABEL, out);

        this.bookService = bookService;
    }

    @Override
    public void show() {
        out.println(LABEL);

        bookService.getUnavailableBooks()
                .stream()
                .map(Book::toString)
                .reduce((accumulator, book) -> String.format("%s\n%s", accumulator, book))
                .ifPresent(out::println);
    }
}
