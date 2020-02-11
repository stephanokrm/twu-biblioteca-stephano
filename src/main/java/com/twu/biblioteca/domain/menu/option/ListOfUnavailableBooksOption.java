package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

public class ListOfUnavailableBooksOption extends MenuOption {
    private static final int NUMBER = 7;
    private static final String LABEL = "List of Unavailable Books";

    private final BookService bookService;

    public ListOfUnavailableBooksOption(Console console, BookService bookService) {
        super(NUMBER, LABEL, console);

        this.bookService = bookService;
    }

    @Override
    public void show() {
        bookService.getUnavailableBooks()
                .stream()
                .map(Book::toString)
                .reduce((accumulator, book) -> String.format("%s\n%s", accumulator, book))
                .ifPresent(console::doWrite);
    }
}
