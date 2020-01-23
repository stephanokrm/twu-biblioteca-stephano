package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.service.BookService;

import java.io.PrintStream;

public class ReturnABookOption extends MenuOption {
    public static final int NUMBER = 3;

    private Question question;

    public ReturnABookOption(PrintStream out, Question question, BookService bookService) {
        super(NUMBER, "Return a Book");

        this.question = question;
    }

    @Override
    public void show() {
        question.askForString("Enter the book title: ");
    }
}
