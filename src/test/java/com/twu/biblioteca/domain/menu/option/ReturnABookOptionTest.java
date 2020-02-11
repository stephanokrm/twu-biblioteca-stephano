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

public class ReturnABookOptionTest extends InteractsWithConsole {
    private Menu menu;
    private ReturnABookOption returnABookOption;

    @Mock
    private BookRepository bookRepository;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userService);
        BookService bookService = new BookService(bookRepository);

        returnABookOption = new ReturnABookOption(console, bookService);

        authService.actingAs(new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000"));

        menu = new Menu(console, authService);
        menu.addOption(returnABookOption);
    }

    @Test
    public void showReturnABookOption() {
        menu.open();

        verify(console).doWrite("3. Return a Book");
    }

    @Test
    public void runReturnABookOption() throws Exception {
        Book book = new Book("Title", "Author", 2020, false);

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.all()).thenReturn(books);

        option(returnABookOption)
                .expectsOutput("Return a Book")
                .expectsQuestion("Enter the book title: ", "Title")
                .expectsOutput("Thank you for returning the book")
                .execute();
    }
}
