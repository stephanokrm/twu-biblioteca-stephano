package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.service.CheckoutBookService;

public class ListOfUnavailableBooksOption extends MenuOption {
    private static final int NUMBER = 7;
    private static final String LABEL = "List of Unavailable Books";

    private final CheckoutBookService checkoutBookService;

    public ListOfUnavailableBooksOption(Console console, CheckoutBookService checkoutBookService) {
        super(NUMBER, LABEL, console);

        this.checkoutBookService = checkoutBookService;
    }

    @Override
    public void show() {
        checkoutBookService
                .getAllCheckedOutBooks()
                .stream()
                .map(Checkout::toString)
                .reduce((accumulator, book) -> String.format("%s\n%s", accumulator, book))
                .ifPresent(console::doWrite);
    }
}
