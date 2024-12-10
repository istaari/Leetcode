package designPatterns;


import java.util.ArrayList;
import java.util.List;


/*
 * Composite Design Pattern
 *
 * Definition:
 * The Composite Pattern allows you to compose objects into tree structures and work with them as if
 * they were individual objects.
 *
 * Real-World Analogy:
 * Company Hierarchy: An organization has a tree-like structure where managers can have subordinates,
 * and subordinates can also be managers for others.
 */


// Component
interface FileComponent {
    void display();
}

// Leaf
class File implements FileComponent {
    private final String name;

    public File(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileComponent {
    private final String name;
    private final List<FileComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileComponent component) {
        components.add(component);
    }

    public void remove(FileComponent component) {
        components.remove(component);
    }

    public void display() {
        System.out.println("Directory: " + name);
        for (FileComponent component : components) {
            component.display();
        }
    }
}

// Usage
public class Composite {
    public static void main(String[] args) {
        FileComponent file1 = new File("Document.txt");
        FileComponent file2 = new File("Photo.jpg");
        FileComponent file3 = new File("Presentation.ppt");

        Directory folder = new Directory("MyFolder");
        folder.add(file1);
        folder.add(file2);
        folder.add(file3);

        folder.display();
    }
}

