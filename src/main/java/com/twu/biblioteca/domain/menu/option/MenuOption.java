package com.twu.biblioteca.domain.menu.option;

import java.io.PrintStream;

public abstract class MenuOption {
    protected final PrintStream out;
    private final int number;
    private final String label;
    private final boolean guarded;

    public MenuOption(int number, String label, PrintStream out) {
        this(number, label, out, false);
    }

    public MenuOption(int number, String label, PrintStream out, boolean guarded) {
        this.number = number;
        this.label = label;
        this.out = out;
        this.guarded = guarded;
    }

    public int getNumber() {
        return number;
    }

    public void open() throws Exception {
        out.printf("\n%s%n", label);

        show();
    }

    public abstract void show() throws Exception;

    @Override
    public String toString() {
        return String.format("%d. %s", number, label);
    }

    public boolean isGuarded() {
        return guarded;
    }
}
