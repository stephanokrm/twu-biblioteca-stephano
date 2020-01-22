package com.twu.biblioteca.foundation;

import com.twu.biblioteca.domain.Welcome;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.domain.menu.option.CheckoutABookOption;
import com.twu.biblioteca.domain.menu.option.ExitOption;
import com.twu.biblioteca.domain.menu.option.ListOfBooksOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;
import com.twu.biblioteca.repository.BookRepository;

import java.io.PrintStream;
import java.util.InputMismatchException;

public class Application {
    private final static String VERSION = "1.6";
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
        BookRepository bookRepository = new BookRepository();
        Menu menu = new Menu(out);
        menu.addOption(new ListOfBooksOption(out, bookRepository));
        menu.addOption(new CheckoutABookOption(out, question, bookRepository));
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

    private void chooseOption(Menu menu) throws InvalidMenuOptionException, InputMismatchException {
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
