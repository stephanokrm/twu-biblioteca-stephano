package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepository implements Repository<Book> {
    private static final List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("The Nickel Boys", "Colson Whitehead", 2019),
            new Book("The Testaments", "Margaret Atwood", 2019),
            new Book("The Topeka School", "Ben Lerner", 2019)
    ));

    public List<Book> all() {
        return books;
    }

    public Book whereTitle(String title) {
        return new Book("Title", "Author", 2020);
    }
}
