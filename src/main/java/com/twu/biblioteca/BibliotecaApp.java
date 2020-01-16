package com.twu.biblioteca;

public class BibliotecaApp {
    public static void main(String[] args) {
        Welcome welcome = new Welcome(System.out);
        welcome.show();

        BookList bookList = new BookList(System.out);
        bookList.show();
    }
}
