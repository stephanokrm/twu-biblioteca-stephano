package com.twu.biblioteca.menu.option;

import com.twu.biblioteca.Book;
import com.twu.biblioteca.menu.Menu;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListOfBooksOptionTest {
    private Menu menu;
    private PrintStream printStream;
    private ListOfBooksOption listOfBooksOption;
    private List<Book> books;
    private List<MenuOption> menuOptions;

    @Before
    public void setUp() {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
        listOfBooksOption = new ListOfBooksOption(printStream, books);
        menuOptions = new ArrayList<>();
        menu = new Menu(printStream, menuOptions);
    }

    @Test
    public void shouldListAllLibraryBooks() {
        Book book = new Book("Book 1", "Person 1", 2020);
        books.add(book);

        book = new Book("Book 2", "Person 2", 2020);
        books.add(book);

        listOfBooksOption.show();

        verify(printStream).println("Title: Book 1 | Author: Person 1 | Published Year: 2020\nTitle: Book 2 | Author: Person 2 | Published Year: 2020");
    }

    @Test
    public void shouldShowListOfBooksOption() {
        MenuOption listOfBooksOption = new ListOfBooksOption(printStream, books);

        menuOptions.add(listOfBooksOption);

        menu.open();

        verify(printStream).println("1. List of Books");
    }

    @Test
    public void shouldEnterListOfBooksFromMenu() {
        MenuOption menuOption = new ListOfBooksOption(printStream, books);

        menuOptions.add(menuOption);

        menu.run(1);

        verify(printStream).println("- Books");
    }
}
