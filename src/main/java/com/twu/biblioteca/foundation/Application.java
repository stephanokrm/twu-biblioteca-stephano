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

        Welcome welcome = new Welcome(out);
        welcome.show();

        Menu menu = new Menu(out);
        menu.boot();

        int option;

        do {
            menu.open();

            try {
                option = question.askForInteger("Enter an option: ");

                menu.run(option);

                if (option == ExitOption.NUMBER) {
                    running = false;
                }
            } catch (InputMismatchException exception) {
                out.println(InvalidMenuOptionException.MESSAGE);
            } catch (Exception exception) {
                out.println(exception.getMessage());
            }

            question.clear();
        } while (isRunning());
    }

    public boolean isRunning() {
        return running;
    }
}
