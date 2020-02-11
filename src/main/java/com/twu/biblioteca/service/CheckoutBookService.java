package com.twu.biblioteca.service;

import com.twu.biblioteca.exception.BookNotAvailableException;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.CheckoutRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutBookService extends CheckoutService {
    public CheckoutBookService(CheckoutRepository checkoutRepository) {
        super(checkoutRepository);
    }

    public boolean bookIsAvailable(Book book) {
        return workIsAvailable(book);
    }

    public boolean bookIsNotAvailable(Book book) {
        return !bookIsAvailable(book);
    }

    public Checkout checkoutBook(Book book, User user) throws BookNotAvailableException {
        if (bookIsNotAvailable(book)) {
            throw new BookNotAvailableException();
        }

        return checkout(book, user);
    }

    public void returnBook(Book book) throws InvalidBookException {
        if (bookIsAvailable(book)) {
            throw new InvalidBookException();
        }

        returnWork(book);
    }

    public List<Checkout> getAllCheckedOutBooks() {
        return getAllCheckedOutWorks()
                .stream()
                .filter(checkout -> checkout.getWork() instanceof Book)
                .collect(Collectors.toList());
    }
}
