package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.CheckoutBookService;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;
    public static final boolean GUARDED = true;
    public static final String LABEL = "Checkout a Book";

    private final BookService bookService;
    private final CheckoutBookService checkoutService;
    private final AuthService authService;

    public CheckoutABookOption(
            Console console,
            BookService bookService,
            CheckoutBookService checkoutService,
            AuthService authService
    ) {
        super(NUMBER, LABEL, console, GUARDED);

        this.bookService = bookService;
        this.checkoutService = checkoutService;
        this.authService = authService;
    }

    @Override
    public void show() throws BookNotAvailableException {
        String title = console.askQuestion("Enter the book title: ");
        Book book = bookService.getBookByTitle(title);
        checkoutService.checkoutBook(book, authService.getUser());
        console.doWrite("Thank you! Enjoy the book");
    }
}
