package com.twu.biblioteca.domain.menu.option;

import java.io.PrintStream;

public class CheckoutABookOption extends MenuOption {
    public static final int NUMBER = 2;
    public static final String LABEL = "Checkout a Book";

    private PrintStream out;

    public CheckoutABookOption(PrintStream out) {
        super(NUMBER, LABEL);

        this.out = out;
    }

    @Override
    public void show() {

    }
}
