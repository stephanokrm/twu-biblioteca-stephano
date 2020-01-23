package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;
    public static final String LABEL = "Checkout a Book";

    private PrintStream out;
    private Question question;
    private BookService bookService;

    public CheckoutABookOption(PrintStream out, Question question, BookService bookService) {
        super(NUMBER, LABEL);

        this.out = out;
        this.question = question;
        this.bookService = bookService;
    }

    @Override
    public void show() {
        String title = question.askForString("Enter the book title: ");
        Book book = bookService.getBookByTitle(title);
        bookService.checkOutBook(book);
    }
}
