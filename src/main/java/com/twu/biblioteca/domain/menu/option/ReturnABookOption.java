package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

public class ReturnABookOption extends MenuOption {
    public static final int NUMBER = 3;
    public static final boolean GUARDED = true;
    public static final String LABEL = "Return a Book";

    private BookService bookService;

    public ReturnABookOption(Console console, BookService bookService) {
        super(NUMBER, LABEL, console, GUARDED);

        this.bookService = bookService;
    }

    @Override
    public void show() throws InvalidBookException {
        String title = console.askQuestion("Enter the book title: ");
        Book book;

        try {
            book = bookService.getBookByTitle(title);
        } catch (BookNotAvailableException exception) {
            throw new InvalidBookException();
        }

        bookService.returnBook(book);
        console.doWrite("Thank you for returning the book");
    }
}
