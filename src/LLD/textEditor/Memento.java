package LLD.textEditor;

// Memento
public class Memento {

    private final String state;

    public Memento(String content) {
        this.state = content;
    }

    public String getState() {
        return state;
    }

}
