package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.io.PrintStream;

public class ListOfBooksOption extends MenuOption {
    public static final int NUMBER = 1;
    public static final String LABEL = "List of Books";

    private PrintStream out;
    private BookRepository bookRepository;

    public ListOfBooksOption(PrintStream printStream, BookRepository bookRepository) {
        super(NUMBER, LABEL);

        this.out = printStream;
        this.bookRepository = bookRepository;
    }

    public void show() {
        out.println(LABEL);

        bookRepository.all()
                .stream()
                .map(Book::toString)
                .reduce((accumulator, book) -> String.format("%s\n%s", accumulator, book))
                .ifPresent(out::println);
    }
}
