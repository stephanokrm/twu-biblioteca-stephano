package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.exception.InvalidBookException;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Checkout;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.CheckoutRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.CheckoutBookService;
import com.twu.biblioteca.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReturnABookOptionTest extends InteractsWithConsole {
    private Menu menu;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private User user;
    private List<Book> books;
    private ReturnABookOption returnABookOption;
    private List<Checkout> checkouts;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        BookRepository bookRepository = mock(BookRepository.class);
        CheckoutRepository checkoutRepository = mock(CheckoutRepository.class);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userService);
        CheckoutBookService checkoutService = new CheckoutBookService(checkoutRepository);
        BookService bookService = new BookService(bookRepository, checkoutService);

        returnABookOption = new ReturnABookOption(console, bookService, checkoutService);

        books = new ArrayList<>();
        checkouts = new ArrayList<>();

        user = new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000");

        authService.actingAs(user);

        menu = new Menu(console, authService);
        menu.addOption(returnABookOption);

        doReturn(books).when(bookRepository).all();
        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void showReturnABookOption() {
        menu.open();

        verify(console).doWrite("3. Return a Book");
    }

    @Test
    public void runReturnABookOption() throws Exception {
        Book book = new Book("Title", "Author", 2020);
        Checkout checkout = new Checkout(book, user);

        books.add(book);

        checkouts.add(checkout);

        option(returnABookOption)
                .expectsOutput("Return a Book")
                .expectsQuestion("Enter the book title: ", "Title")
                .expectsOutput("Thank you for returning the book")
                .execute();
    }

    @Test
    public void throwInvalidBookException() throws Exception {
        expectedException.expect(InvalidBookException.class);
        expectedException.expectMessage(InvalidBookException.MESSAGE);

        doReturn("Title").when(console).askQuestion("Enter the book title: ");

        returnABookOption.show();
    }
}
