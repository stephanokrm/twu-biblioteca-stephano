package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.foundation.Question;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CheckoutABookOptionTest extends TestCase {
    private Menu menu;
    private Question question;
    private BookRepository bookRepository;

    @Override
    public void setUp() {
        super.setUp();

        question = mock(Question.class);
        bookRepository = mock(BookRepository.class);

        UserRepository userRepository = mock(UserRepository.class);
        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository);
        AuthService authService = new AuthService(userService);
        CheckoutABookOption checkoutABookOption = new CheckoutABookOption(out, question, bookService, authService);

        authService.actingAs(new User("0", "0"));
        menu = new Menu(out, authService);
        menu.addOption(checkoutABookOption);
    }

    @Test
    public void showCheckoutABookOption() {
        menu.open();

        verify(out).println("2. Checkout a Book");
    }

    @Test
    public void askForBookTitle() throws Exception {
        Book book = new Book("Title", "Author", 2020);

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(question.askForString("Enter the book title: ")).thenReturn("Title");
        when(bookRepository.all()).thenReturn(books);

        menu.run(CheckoutABookOption.NUMBER);

        verify(question).askForString("Enter the book title: ");
    }

    @Test
    public void notifyWhenSuccessfullyCheckOutBook() throws Exception {
        Book book = new Book("Title", "Author", 2020, true);

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(question.askForString("Enter the book title: ")).thenReturn("Title");
        when(bookRepository.all()).thenReturn(books);

        menu.run(CheckoutABookOption.NUMBER);

        verify(out).println("Thank you! Enjoy the book");
    }
}
