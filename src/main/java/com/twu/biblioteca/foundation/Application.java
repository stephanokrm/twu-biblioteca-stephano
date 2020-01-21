package com.twu.biblioteca.foundation;

import com.twu.biblioteca.domain.Welcome;
import com.twu.biblioteca.domain.menu.Menu;

import java.io.PrintStream;

public class Application {
    private final static String VERSION = "1.4";
    private Question question;
    private PrintStream out;
    private boolean booted = false;

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
        Welcome welcome = new Welcome(out);
        welcome.show();

        Menu menu = new Menu(out);
        menu.boot();
        menu.open();

        int option = question.ask("Enter an option: ");

        menu.run(option);
    }
}
