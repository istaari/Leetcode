package designPatterns;


interface Animal {
    void speak();
}


class Dog implements Animal {
    public void speak() {
        System.out.println("Woof");
    }
}


class Cat implements Animal {
    public void speak() {
        System.out.println("Meow");
    }
}


/**
 * <h2>Problem:</h2>
 * <p>
 * You want to create objects without exposing the instantiation logic to the client and want the flexibility
 * of creating different types of objects based on some input.
 * </p>
 *
 * <h2>Solution:</h2>
 * <p>
 * The Factory pattern provides an interface for creating objects in a superclass, but allows subclasses to alter
 * the type of objects that will be created. It delegates the object creation logic to subclasses based on some input.
 * </p>
 *
 * <h3>When to use:</h3>
 * <ul>
 *   <li><strong>UI Components:</strong> When you want to delegate the creation of different types of UI components like buttons, checkboxes, etc.</li>
 *   <li><strong>Game characters:</strong> When the game needs to create different character types based on the player's selection.</li>
 *   <li><strong>Data parsers:</strong> When the type of data being processed changes dynamically (e.g., XML vs JSON parsers).</li>
 * </ul>
 *
 * <h3>Example usage:</h3>
 * <pre>
 * AnimalFactory factory = new AnimalFactory();
 * Animal animal = factory.createAnimal("Dog");
 * </pre>
 *
 * <p>This class demonstrates the Factory design pattern.</p>
 */
class AnimalFactory {
    public Animal createAnimal(String type) {
        if (type.equals("Dog")) {
            return new Dog();
        } else if (type.equals("Cat")) {
            return new Cat();
        }
        return null;
    }
}


public class Factory {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal dog = animalFactory.createAnimal("Dog");
        dog.speak();
    }
}
