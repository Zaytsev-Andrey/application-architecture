package ru.geekbrains.memento;

public class Editor {

    private String content = "";

    public void type(String words) {
        content += " " + words;
    }

    public String getContent() {
        return content;
    }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public void restore(EditorMemento editorMemento) {
        content = editorMemento.getContent();
    }
}
