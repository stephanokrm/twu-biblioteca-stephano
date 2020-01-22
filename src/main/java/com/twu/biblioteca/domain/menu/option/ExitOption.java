package com.twu.biblioteca.domain.menu.option;

import java.io.PrintStream;

public class ExitOption extends MenuOption {
    public static final int NUMBER = 0;
    public static final String LABEL = "Exit";

    private PrintStream out;

    public ExitOption(PrintStream printStream) {
        super(NUMBER, LABEL);

        this.out = printStream;
    }

    @Override
    public void show() {
        out.println("Closing the application...");
    }
}
