package com.twu.biblioteca;

import com.twu.biblioteca.foundation.Application;
import com.twu.biblioteca.foundation.Console;
import com.twu.biblioteca.foundation.Input;
import com.twu.biblioteca.foundation.Output;

public class BibliotecaApp {
    public static void main(String[] args) {
        Output output = new Output(System.out);

        try {
            Input input = new Input(System.in);
            Console console = new Console(output, input);
            Application application = new Application(console);
            application.run();
        } catch (Exception exception) {
            output.doWrite(exception.getMessage());
        }
    }
}
