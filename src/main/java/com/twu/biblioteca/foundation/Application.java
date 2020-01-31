package com.twu.biblioteca.foundation;

import com.twu.biblioteca.domain.Welcome;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.domain.menu.option.*;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

import java.io.PrintStream;
import java.util.InputMismatchException;

public class Application {
    private final static String VERSION = "2.1";
    private Question question;
    private PrintStream out;
    private boolean running = false;

    public Application(Question question, PrintStream out) {
        this.question = question;
        this.out = out;
    }

    public String version() {
        return VERSION;
    }

    public void run() {
        running = true;

        showWelcome();
        showMenu();
    }

    private void showMenu() {
        BookService bookService = new BookService(new BookRepository());
        MovieService movieService = new MovieService(new MovieRepository());

        Menu menu = new Menu(out);
        menu.addOption(new ListOfBooksOption(out, bookService));
        menu.addOption(new CheckoutABookOption(out, question, bookService));
        menu.addOption(new ReturnABookOption(out, question, bookService));
        menu.addOption(new ListOfMoviesOption(out, movieService));
        menu.addOption(new CheckoutAMovieOption(out, question, movieService));
        menu.addOption(new ExitOption(out));

        do {
            menu.open();

            try {
                chooseOption(menu);
            } catch (InputMismatchException exception) {
                handleError(InvalidMenuOptionException.MESSAGE);
                question.clear();
            } catch (Exception exception) {
                handleError(exception.getMessage());
            }
        } while (isRunning());
    }

    private void handleError(String message) {
        out.println(message);
    }

    private void chooseOption(Menu menu) throws Exception {
        int option = question.askForInteger("Enter an option: ");

        menu.run(option);

        if (option == ExitOption.NUMBER) {
            running = false;
        }
    }

    private void showWelcome() {
        Welcome welcome = new Welcome(out);
        welcome.show();
    }

    public boolean isRunning() {
        return running;
    }
}
