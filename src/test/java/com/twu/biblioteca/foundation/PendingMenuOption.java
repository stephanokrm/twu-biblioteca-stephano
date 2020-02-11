package com.twu.biblioteca.foundation;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.option.MenuOption;

import static org.mockito.Mockito.*;

public class PendingMenuOption {
    private final InteractsWithConsole test;
    private final Console console;
    private final MenuOption option;

    public PendingMenuOption(InteractsWithConsole test, Console console, MenuOption option) {
        this.test = test;
        this.console = console;
        this.option = option;
    }

    public PendingMenuOption expectsQuestion(String question, String answer) {
        test.expectedQuestions.put(question, answer);

        return this;
    }

    public PendingMenuOption expectsOutput(String output) {
        test.expectedOutput.add(output);

        return this;
    }

    public void execute() throws Exception {
        run();
    }

    private void run() throws Exception {
        mockConsoleInput();
        mockConsoleOutput();

        option.open();

        assertConsoleInput();
        assertConsoleOutput();
    }

    private void assertConsoleInput() {
        test.expectedQuestions.forEach((query, answer) -> verify(console).askQuestion(query));
    }

    private void assertConsoleOutput() {
        test.expectedOutput.forEach(output -> verify(console).doWrite(output));
    }

    private void mockConsoleOutput() {
        test.expectedOutput.forEach(output -> doNothing().when(console).doWrite(output));
    }

    private void mockConsoleInput() {
        test.expectedQuestions.forEach((query, answer) -> doReturn(answer).when(console).askQuestion(query));
    }
}
