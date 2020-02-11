package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;
    public static final boolean GUARDED = true;
    public static final String LABEL = "Checkout a Book";

    private final BookService bookService;
    private final AuthService authService;

    public CheckoutABookOption(Console console, BookService bookService, AuthService authService) {
        super(NUMBER, LABEL, console, GUARDED);

        this.bookService = bookService;
        this.authService = authService;
    }

    @Override
    public void show() throws BookNotAvailableException {
        String title = console.askQuestion("Enter the book title: ");
        Book book = bookService.getBookByTitle(title);
        bookService.checkOutBook(book, authService.getUser());
        console.doWrite("Thank you! Enjoy the book");
    }
}
