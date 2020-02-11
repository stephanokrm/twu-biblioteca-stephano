package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
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
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CheckoutABookOptionTest extends InteractsWithConsole {
    private Menu menu;
    private List<Book> books;
    private CheckoutABookOption checkoutABookOption;

    @Override
    public void setUp() {
        super.setUp();

        List<Checkout> checkouts = new ArrayList<>();
        UserRepository userRepository = mock(UserRepository.class);
        BookRepository bookRepository = mock(BookRepository.class);
        CheckoutRepository checkoutRepository = mock(CheckoutRepository.class);
        CheckoutBookService checkoutService = new CheckoutBookService(checkoutRepository);
        BookService bookService = new BookService(bookRepository, checkoutService);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userService);

        books = new ArrayList<>();

        checkoutABookOption = new CheckoutABookOption(console, bookService, checkoutService, authService);

        authService.actingAs(new User("0", "0", "Name", "email@gmail.com", "(00) 00000-0000"));

        menu = new Menu(console, authService);
        menu.addOption(checkoutABookOption);

        doReturn(books).when(bookRepository).all();
        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void showCheckoutABookOption() {
        menu.open();

        verify(console).doWrite("2. Checkout a Book");
    }

    @Test
    public void runCheckoutABookOption() throws Exception {
        Book book = new Book("Title", "Author", 2020);

        books.add(book);

        option(checkoutABookOption)
                .expectsOutput("Checkout a Book")
                .expectsQuestion("Enter the book title: ", "Title")
                .expectsOutput("Thank you! Enjoy the book")
                .execute();
    }
}
