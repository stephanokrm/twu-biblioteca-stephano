package com.twu.biblioteca.foundation;

import java.io.Console;
import java.io.InputStream;
import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input(InputStream in) {
        scanner = new Scanner(in);
        scanner.useDelimiter(System.lineSeparator());
    }

    public String askQuestion() {
        return scanner.next();
    }

    public char[] askPassword() {
        Console console = System.console();

        if (console == null) {
            return scanner.next().toCharArray();
        }

        return console.readPassword();
    }
}
