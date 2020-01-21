package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfBooksOptionTest extends TestCase {
    private Menu menu;
    private ListOfBooksOption listOfBooksOption;
    private BookRepository bookRepository;

    @Override
    public void setUp() {
        super.setUp();

        bookRepository = mock(BookRepository.class);
        listOfBooksOption = new ListOfBooksOption(out, bookRepository);
        menu = new Menu(out);
        menu.addOption(listOfBooksOption);
    }

    @Test
    public void listAllBooks() {
        List<Book> books = new ArrayList<>();

        Book book = new Book("Book 1", "Person 1", 2020);
        books.add(book);

        book = new Book("Book 2", "Person 2", 2020);
        books.add(book);

        when(bookRepository.all()).thenReturn(books);

        listOfBooksOption.show();

        verify(out).println("Title: Book 1 | Author: Person 1 | Published Year: 2020\nTitle: Book 2 | Author: Person 2 | Published Year: 2020");
    }

    @Test
    public void showListOfBooksOption() {
        menu.open();

        verify(out).println("1. List of Books");
    }

    @Test
    public void enterListOfBooksFromMenu() throws InvalidMenuOptionException {
        menu.run(1);

        verify(out).println("List of Books");
    }
}
