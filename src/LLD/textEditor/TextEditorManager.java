package LLD.textEditor;

import java.util.Stack;

// Caretaker
public class TextEditorManager {

    private final TextEditor textEditor;
    private final Stack<Memento> undoHistory;
    private final Stack<Memento> redoHistory;


    public TextEditorManager(TextEditor editor) {
        this.textEditor = editor;
        this.undoHistory = new Stack<>();
        this.redoHistory = new Stack<>();
    }

    public void save() {
        undoHistory.push(textEditor.save());
        redoHistory.clear();
    }

    public void undo() {
        if (!undoHistory.isEmpty()) {
            redoHistory.push(undoHistory.pop());
            textEditor.restore((!undoHistory.isEmpty()) ? undoHistory.peek() : new Memento(""));
        }
    }

    // Redo operation
    public void redo() {
        if (!redoHistory.isEmpty()) {
            undoHistory.push(redoHistory.pop());
            textEditor.restore(undoHistory.peek());
        }
    }


}
