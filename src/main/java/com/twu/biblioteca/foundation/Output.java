package com.twu.biblioteca.foundation;

import java.io.PrintStream;

public class Output {
    private final PrintStream out;

    public Output(PrintStream out) {
        this.out = out;
    }

    public void doWrite(String message) {
        out.println(message);
    }
}
