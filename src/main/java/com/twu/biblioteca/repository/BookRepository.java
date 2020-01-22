package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository implements Repository<Book> {
    public List<Book> all() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Nickel Boys", "Colson Whitehead", 2019));
        books.add(new Book("The Testaments", "Margaret Atwood", 2019));
        books.add(new Book("The Topeka School", "Ben Lerner", 2019));

        return books;
    }
}
