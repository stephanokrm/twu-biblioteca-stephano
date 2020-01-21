package com.twu.biblioteca.foundation;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Question {
    private final Scanner scanner;
    private final PrintStream out;

    public Question(PrintStream out, InputStream in) {
        this.out = out;
        scanner = new Scanner(in);
    }

    public int ask(String message) {
        out.println(message);

        return scanner.nextInt();
    }
}
