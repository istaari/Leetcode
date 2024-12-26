package LLD.textEditor;

public class TextEditorDemo {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextEditorManager manager = new TextEditorManager(editor);

        editor.type("Hello, ");
        manager.save();

        editor.type("world!");
        manager.save();

        System.out.println("Current Content: " + editor.getContent()); // Output: Hello, world!

        manager.undo();
        System.out.println("After Undo: " + editor.getContent()); // Output: Hello,

        manager.redo();
        System.out.println("After Redo: " + editor.getContent()); // Output: Hello, world!
    }
}
