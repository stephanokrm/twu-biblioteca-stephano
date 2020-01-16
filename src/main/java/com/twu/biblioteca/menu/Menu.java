package com.twu.biblioteca.menu;

import com.twu.biblioteca.BookList;

import java.io.PrintStream;

public class Menu {
    private PrintStream printStream;

    public Menu(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void show() {
        printStream.println(BookList.LABEL);
    }
}
