package com.twu.biblioteca.foundation;

public class Console {
    private final Input input;
    private final Output output;

    public Console(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    public void doWrite(String message) {
        output.doWrite(message);
    }

    public String askQuestion(String question) {
        doWrite(question);

        return input.askQuestion();
    }
}
