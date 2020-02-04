package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        return this.bookRepository.all()
                .stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> getUnavailableBooks() {
        return this.bookRepository
                .all()
                .stream()
                .filter(Book::isNotAvailable)
                .collect(Collectors.toList());
    }

    public Book getBookByTitle(String title) throws BookNotAvailableException {
        return this.bookRepository.all()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(BookNotAvailableException::new);
    }

    public void checkOutBook(Book book, User user) throws BookNotAvailableException {
        if (book.isNotAvailable()) {
            throw new BookNotAvailableException();
        }

        book.setAvailable(false);
        book.setRenter(user);
    }

    public void returnBook(Book book) throws InvalidBookException {
        if (book.isAvailable()) {
            throw new InvalidBookException();
        }

        book.setAvailable(true);
    }
}
