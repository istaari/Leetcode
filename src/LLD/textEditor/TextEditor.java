package LLD.textEditor;


// Originator
public class TextEditor {

    private String content;

    public TextEditor() {
        this.content = "";
    }

    public void type(String words) {
        content += words;
    }

    public String getContent() {
        return content;
    }

    public Memento save() {
        return new Memento(content);
    }

    public void restore(Memento memento) {
        content = memento.getState();
    }

}
