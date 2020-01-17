package com.twu.biblioteca.menu.option;

public abstract class MenuOption {
    private int number;
    private String label;

    public MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public abstract void show();

    @Override
    public String toString() {
        return String.format("%d. %s", number, label);
    }
}
