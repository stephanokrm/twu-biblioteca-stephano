package com.twu.biblioteca;

import com.twu.biblioteca.domain.menu.option.MenuOption;
import com.twu.biblioteca.foundation.Console;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public abstract class InteractsWithConsole extends TestCase {
    @Mock
    protected Console console;
    private List<String> expectedOutput = new ArrayList<>();
    private Map<String, String> expectedQuestions = new HashMap<>();

    public InteractsWithConsole expectsQuestion(String question, String answer) {
        expectedQuestions.put(question, answer);

        return this;
    }

    public InteractsWithConsole expectsOutput(String output) {
        expectedOutput.add(output);

        return this;
    }

    public void execute(MenuOption option) throws Exception {
        run(option);
    }

    private void run(MenuOption option) throws Exception {
        mockConsoleInput();
        mockConsoleOutput();

        option.open();

        assertConsoleInput();
        assertConsoleOutput();
    }

    private void assertConsoleInput() {
        expectedQuestions.forEach((query, answer) -> verify(console).askQuestion(query));
    }

    private void assertConsoleOutput() {
        expectedOutput.forEach(output -> verify(console).doWrite(output));
    }

    private void mockConsoleOutput() {
        expectedOutput.forEach(output -> doNothing().when(console).doWrite(output));
    }

    private void mockConsoleInput() {
        expectedQuestions.forEach((query, answer) -> doReturn(answer).when(console).askQuestion(query));
    }
}
