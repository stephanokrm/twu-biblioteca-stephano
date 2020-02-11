package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Checkout;
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

public class ListOfBooksOptionTest extends InteractsWithConsole {
    private Menu menu;
    private List<Book> books;
    private ListOfBooksOption listOfBooksOption;

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

        books = new ArrayList<>();
        menu = new Menu(console, new AuthService(userService));
        listOfBooksOption = new ListOfBooksOption(console, bookService);

        menu.addOption(listOfBooksOption);

        doReturn(books).when(bookRepository).all();
        doReturn(checkouts).when(checkoutRepository).all();
    }

    @Test
    public void showListOfBooksOption() {
        menu.open();

        verify(console).doWrite("1. List of Books");
    }

    @Test
    public void runListOfBooksOption() throws Exception {
        Book book = new Book("Book 1", "Person 1", 2020);
        books.add(book);

        book = new Book("Book 3", "Person 3", 2020);
        books.add(book);

        option(listOfBooksOption)
                .expectsOutput("List of Books")
                .expectsOutput("Title: Book 1 | Author: Person 1 | Published Year: 2020\nTitle: Book 3 | Author: Person 3 | Published Year: 2020")
                .execute();
    }
}
