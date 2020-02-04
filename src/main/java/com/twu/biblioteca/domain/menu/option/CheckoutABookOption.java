package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;
    public static final boolean GUARDED = true;
    public static final String LABEL = "Checkout a Book";

    private final Question question;
    private final BookService bookService;
    private final AuthService authService;

    public CheckoutABookOption(PrintStream out, Question question, BookService bookService, AuthService authService) {
        super(NUMBER, LABEL, out, GUARDED);

        this.question = question;
        this.bookService = bookService;
        this.authService = authService;
    }

    @Override
    public void show() throws BookNotAvailableException {
        String title = question.askForString("Enter the book title: ");
        Book book = bookService.getBookByTitle(title);
        bookService.checkOutBook(book, authService.getUser());
        out.println("Thank you! Enjoy the book");
    }
}
