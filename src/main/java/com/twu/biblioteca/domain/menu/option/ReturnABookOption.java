package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class ReturnABookOption extends MenuOption {
    public static final int NUMBER = 3;
    public static final String LABEL = "Return a Book";

    private PrintStream out;
    private Question question;
    private BookService bookService;

    public ReturnABookOption(PrintStream out, Question question, BookService bookService) {
        super(NUMBER, LABEL);

        this.out = out;
        this.question = question;
        this.bookService = bookService;
    }

    @Override
    public void show() throws InvalidBookException {
        String title = question.askForString("Enter the book title: ");
        Book book;

        try {
            book = bookService.getBookByTitle(title);
        } catch (BookNotAvailableException exception) {
            throw new InvalidBookException();
        }

        bookService.returnBook(book);
        out.println("Thank you for returning the book");
    }
}
