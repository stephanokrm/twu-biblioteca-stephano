package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class ReturnABookOption extends MenuOption {
    public static final int NUMBER = 3;

    private Question question;
    private BookService bookService;

    public ReturnABookOption(PrintStream out, Question question, BookService bookService) {
        super(NUMBER, "Return a Book");

        this.question = question;
        this.bookService = bookService;
    }

    @Override
    public void show() throws BookNotAvailableException {
        String title = question.askForString("Enter the book title: ");
        Book book = bookService.getBookByTitle(title);
        bookService.returnBook(book);
    }
}
