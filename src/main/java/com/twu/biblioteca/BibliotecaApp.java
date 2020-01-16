package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    public static void main(String[] args) {
        Welcome welcome = new Welcome(System.out);
        welcome.show();

        List<String> books = new ArrayList<>();
        Library library = new Library(System.out, books);
        BookList bookList = new BookList(System.out, library);
        bookList.show();
    }
}
