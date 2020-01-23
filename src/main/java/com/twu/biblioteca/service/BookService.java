package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
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

    public Book getBookByTitle(String title) {
        return this.bookRepository.all()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .get();
    }
}
