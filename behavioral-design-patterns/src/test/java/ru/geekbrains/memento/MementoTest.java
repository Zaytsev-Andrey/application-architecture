package ru.geekbrains.memento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MementoTest {

    private Editor editor;

    @BeforeEach
    public void init() {
        editor = new Editor();
    }

    @Test
    public void saveAndRestoreStateTest() {
        editor.type("This is the first sentence.");
        Assertions.assertEquals(" This is the first sentence.", editor.getContent());
        EditorMemento editorMemento = editor.save();
        editor.type("This is second.");
        Assertions.assertEquals(" This is the first sentence. This is second.", editor.getContent());
        editor.restore(editorMemento);
        Assertions.assertEquals(" This is the first sentence.", editor.getContent());
    }
}
