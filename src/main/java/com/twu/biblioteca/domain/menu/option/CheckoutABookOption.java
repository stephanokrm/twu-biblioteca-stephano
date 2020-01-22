package com.twu.biblioteca.domain.menu.option;

import java.io.PrintStream;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;

    private PrintStream out;

    public CheckoutABookOption(PrintStream out) {
        super(NUMBER, "Checkout a Book");

        this.out = out;
    }

    @Override
    public void show() {

    }
}
