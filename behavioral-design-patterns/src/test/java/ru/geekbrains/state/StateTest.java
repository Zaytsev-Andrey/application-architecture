package ru.geekbrains.state;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StateTest {

    private ByteArrayOutputStream out;

    private TextEditor textEditor;

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        textEditor = new TextEditor(new DefaultText());
    }

    @Test
    public void defaultTextTest() {
        textEditor.type("Test");
        Assertions.assertEquals("Test", out.toString());
    }

    @Test
    public void upperTextTest() {
        textEditor.setState(new UpperCase());
        textEditor.type("Test");
        Assertions.assertEquals("TEST", out.toString());
    }

    @Test
    public void lowerTextTest() {
        textEditor.setState(new LowerCase());
        textEditor.type("Test");
        Assertions.assertEquals("test", out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
