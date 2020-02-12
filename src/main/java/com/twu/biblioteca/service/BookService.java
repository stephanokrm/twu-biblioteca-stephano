package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository;
    private final CheckoutBookService checkoutService;

    public BookService(BookRepository bookRepository, CheckoutBookService checkoutService) {
        this.bookRepository = bookRepository;
        this.checkoutService = checkoutService;
    }

    public List<Book> getAvailableBooks() {
        return this.bookRepository
                .all()
                .stream()
                .filter(checkoutService::bookIsAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> getUnavailableBooks() {
        return this.bookRepository
                .all()
                .stream()
                .filter(checkoutService::bookIsNotAvailable)
                .collect(Collectors.toList());
    }

    public Book getBookByTitle(String title) throws BookNotAvailableException {
        return this.bookRepository
                .all()
                .stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }
}
