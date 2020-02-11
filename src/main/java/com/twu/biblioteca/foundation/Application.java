package com.twu.biblioteca.foundation;

import com.twu.biblioteca.domain.Welcome;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.domain.menu.option.*;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;

public class Application {
    private final Console console;
    private boolean running = false;

    public Application(Console console) {
        this.console = console;
    }

    public void run() {
        running = true;

        showWelcome();
        showMenu();
    }

    private void showMenu() {
        BookService bookService = new BookService(new BookRepository());
        MovieService movieService = new MovieService(new MovieRepository());
        UserService userService = new UserService(new UserRepository());
        AuthService authService = new AuthService(userService);

        Menu menu = new Menu(console, authService);
        menu.addOption(new ListOfBooksOption(console, bookService));
        menu.addOption(new CheckoutABookOption(console, bookService, authService));
        menu.addOption(new ReturnABookOption(console, bookService));
        menu.addOption(new ListOfMoviesOption(console, movieService));
        menu.addOption(new CheckoutAMovieOption(console, movieService));
        menu.addOption(new LoginOption(console, authService));
        menu.addOption(new ListOfUnavailableBooksOption(console, bookService));
        menu.addOption(new MyInformationOption(console, authService));
        menu.addOption(new ExitOption(console));

        do {
            menu.open();

            try {
                chooseOption(menu);
            } catch (NumberFormatException exception) {
                handleError(InvalidMenuOptionException.MESSAGE);
            } catch (Exception exception) {
                handleError(exception.getMessage());
            }
        } while (isRunning());
    }

    private void handleError(String message) {
        console.doWrite(message == null ? "Unexpected Error" : message);
    }

    private void chooseOption(Menu menu) throws Exception {
        String answer = console.askQuestion("Enter an option: ");

        int option = Integer.parseInt(answer);

        menu.run(option);

        if (option == ExitOption.NUMBER) {
            running = false;
        }
    }

    private void showWelcome() {
        Welcome welcome = new Welcome(console);
        welcome.show();
    }

    public boolean isRunning() {
        return running;
    }
}
