package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.repository.BookRepository;

import java.io.PrintStream;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;
    public static final String LABEL = "Checkout a Book";

    private PrintStream out;
    private Question question;

    public CheckoutABookOption(PrintStream out, Question question, BookRepository bookRepository) {
        super(NUMBER, LABEL);

        this.out = out;
        this.question = question;
    }

    @Override
    public void show() {
        question.askForString("Enter the book title: ");
    }
}
