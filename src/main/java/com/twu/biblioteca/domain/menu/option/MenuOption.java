package com.twu.biblioteca.domain.menu.option;

public abstract class MenuOption {
    private int number;
    private String label;
    private boolean guarded;

    public MenuOption(int number, String label) {
        this(number, label, false);
    }

    public MenuOption(int number, String label, boolean guarded) {
        this.number = number;
        this.label = label;
        this.guarded = guarded;
    }

    public int getNumber() {
        return number;
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
