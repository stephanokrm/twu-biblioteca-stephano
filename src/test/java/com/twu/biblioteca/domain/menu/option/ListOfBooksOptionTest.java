package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfBooksOptionTest extends InteractsWithConsole {
    private Menu menu;
    private ListOfBooksOption listOfBooksOption;

    @Mock
    private BookRepository bookRepository;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository);

        listOfBooksOption = new ListOfBooksOption(console, bookService);

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(listOfBooksOption);
    }

    @Test
    public void showListOfBooksOption() {
        menu.open();

        verify(console).doWrite("1. List of Books");
    }

    @Test
    public void runListOfBooksOption() throws Exception {
        List<Book> books = new ArrayList<>();

        Book book = new Book("Book 1", "Person 1", 2020, true);
        books.add(book);

        book = new Book("Book 2", "Person 2", 2020, false);
        books.add(book);

        book = new Book("Book 3", "Person 3", 2020, true);
        books.add(book);

        when(bookRepository.all()).thenReturn(books);

        expectsOutput("List of Books")
                .expectsOutput("Title: Book 1 | Author: Person 1 | Published Year: 2020\nTitle: Book 3 | Author: Person 3 | Published Year: 2020")
                .execute(listOfBooksOption);
    }
}
