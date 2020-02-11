package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.CheckoutBookService;

public class ReturnABookOption extends MenuOption {
    public static final int NUMBER = 3;
    public static final boolean GUARDED = true;
    public static final String LABEL = "Return a Book";

    private final BookService bookService;
    private final CheckoutBookService checkoutService;

    public ReturnABookOption(Console console, BookService bookService, CheckoutBookService checkoutService) {
        super(NUMBER, LABEL, console, GUARDED);

        this.bookService = bookService;
        this.checkoutService = checkoutService;
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

        checkoutService.returnBook(book);
        console.doWrite("Thank you for returning the book");
    }
}
