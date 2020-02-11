package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
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

public class ListOfUnavailableBooksOptionTest extends InteractsWithConsole {
    private Menu menu;
    private ListOfUnavailableBooksOption listOfUnavailableBooksOption;

    @Mock
    private BookRepository bookRepository;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository);

        listOfUnavailableBooksOption = new ListOfUnavailableBooksOption(console, bookService);

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(listOfUnavailableBooksOption);
    }

    @Test
    public void showListOfUnavailableBooksOption() {
        menu.open();

        verify(console).doWrite("7. List of Unavailable Books");
    }

    @Test
    public void runListOfUnavailableBooksOption() throws Exception {
        List<Book> books = new ArrayList<>();

        Book book = new Book("Book 1", "Person 1", 2020, false);
        book.setRenter(new User("xxx-xxxx", "0", "Name", "email@gmail.com", "(00) 00000-0000"));
        books.add(book);

        book = new Book("Book 2", "Person 2", 2020, true);
        books.add(book);

        book = new Book("Book 3", "Person 3", 2020, false);
        book.setRenter(new User("xxx-xxxx", "0", "Name", "email@gmail.com", "(00) 00000-0000"));
        books.add(book);

        when(bookRepository.all()).thenReturn(books);

        expectsOutput("List of Unavailable Books")
                .expectsOutput("Title: Book 1 | Author: Person 1 | Published Year: 2020 | Renter Library Number: xxx-xxxx\nTitle: Book 3 | Author: Person 3 | Published Year: 2020 | Renter Library Number: xxx-xxxx")
                .execute(listOfUnavailableBooksOption);
    }
}
