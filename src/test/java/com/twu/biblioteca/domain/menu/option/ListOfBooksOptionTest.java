package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfBooksOptionTest extends TestCase {
    private Menu menu;
    private BookService bookService;
    private BookRepository bookRepository;
    private ListOfBooksOption listOfBooksOption;

    @Override
    public void setUp() {
        super.setUp();

        bookRepository = mock(BookRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        bookService = new BookService(bookRepository);
        listOfBooksOption = new ListOfBooksOption(out, bookService);
        UserService userService = new UserService(userRepository);

        menu = new Menu(out, new AuthService(userService));
        menu.addOption(listOfBooksOption);
    }

    @Test
    public void listAvailableBooks() {
        List<Book> books = new ArrayList<>();

        Book book = new Book("Book 1", "Person 1", 2020, true);
        books.add(book);

        book = new Book("Book 2", "Person 2", 2020, false);
        books.add(book);

        book = new Book("Book 3", "Person 3", 2020, true);
        books.add(book);

        when(bookRepository.all()).thenReturn(books);

        listOfBooksOption.show();

        verify(out).println("Title: Book 1 | Author: Person 1 | Published Year: 2020\nTitle: Book 3 | Author: Person 3 | Published Year: 2020");
    }

    @Test
    public void showListOfBooksOption() {
        menu.open();

        verify(out).println("1. List of Books");
    }

    @Test
    public void enterListOfBooksFromMenu() throws Exception {
        menu.run(1);

        verify(out).printf("\n%s%n", "List of Books");
    }
}
