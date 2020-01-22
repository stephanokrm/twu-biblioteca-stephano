package com.twu.biblioteca.foundation;

import com.twu.biblioteca.domain.Welcome;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.domain.menu.option.ExitOption;
import com.twu.biblioteca.exception.InvalidMenuOptionException;

import java.io.PrintStream;
import java.util.InputMismatchException;

public class Application {
    private final static String VERSION = "1.4";
    private Question question;
    private PrintStream out;
    private boolean booted = false;
    private boolean running = false;

    public Application(Question question, PrintStream out) {
        this.question = question;
        this.out = out;
    }

    public String version() {
        return VERSION;
    }

    public void abort(String message) throws RuntimeException {
        throw new RuntimeException(message);
    }

    public void boot() {
        this.booted = true;
    }

    public boolean isBooted() {
        return booted;
    }

    public void run() {
        running = true;

        showWelcome();
        showMenu();
    }

    private void showMenu() {
        Menu menu = new Menu(out);
        menu.boot();

        do {
            menu.open();

            try {
                chooseOption(menu);
            } catch (InputMismatchException exception) {
                handleError(InvalidMenuOptionException.MESSAGE);
            } catch (Exception exception) {
                handleError(exception.getMessage());
            }
        } while (isRunning());
    }

    private void handleError(String message) {
        out.println(message);
        question.clear();
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
