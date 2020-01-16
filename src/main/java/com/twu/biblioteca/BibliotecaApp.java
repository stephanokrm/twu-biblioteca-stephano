package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    public static void main(String[] args) {
        Welcome welcome = new Welcome(System.out);
        welcome.show();

        List<Book> books = new ArrayList<>();
        BookList bookList = new BookList(System.out, books);
        bookList.show();
    }
}
