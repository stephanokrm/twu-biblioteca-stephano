package com.twu.biblioteca;

import com.twu.biblioteca.foundation.Application;
import com.twu.biblioteca.foundation.Question;

import java.io.PrintStream;

public class BibliotecaApp {
    public static void main(String[] args) {
        PrintStream out = System.out;
        Question question = new Question(System.out, System.in);
        Application application = new Application(question, out);

        try {
            application.boot();
            application.run();
        } catch (Exception exception) {
            out.println(exception.getMessage());
        }
    }
}
