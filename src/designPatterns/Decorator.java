package designPatterns;


interface Coffee {
    String getDescription();

    double getCost();
}


class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple coffee";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription();
    }

    public double getCost() {
        return coffee.getCost();
    }
}


/**
 * <h2>Problem:</h2>
 * <p>
 * You want to add new functionality to an object without altering its structure.
 * Modifying the core structure would affect other parts of the system.
 * </p>
 *
 * <h2>Solution:</h2>
 * <p>
 * The Decorator pattern allows behavior to be added to individual objects dynamically
 * without affecting the behavior of other objects of the same class. It is a structural pattern
 * that provides a flexible alternative to subclassing for extending functionality.
 * </p>
 *
 * <h3>When to use:</h3>
 * <ul>
 *   <li><strong>Runtime feature addition:</strong> When you need to add responsibilities to objects at runtime without altering their class.</li>
 *   <li><strong>Pizza ordering systems:</strong> Adding toppings to a base pizza without creating subclasses for every topping combination.</li>
 *   <li><strong>Graphical User Interfaces (GUIs):</strong> Dynamically adding features to a window (like scrollbars or borders) without changing the window class.</li>
 * </ul>
 *
 * <h3>Example usage:</h3>
 * <pre>
 * Coffee basicCoffee = new BasicCoffee();
 * Coffee milkCoffee = new MilkDecorator(basicCoffee);
 * System.out.println(milkCoffee.getDescription());  // Outputs: Coffee, with milk
 * System.out.println(milkCoffee.getCost());  // Adds milk cost to base coffee price
 * </pre>
 *
 * <p>This class demonstrates the Decorator design pattern.</p>
 */
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }
}


public class Decorator {

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription()); // Simple coffee, with milk
        System.out.println(coffee.getCost());        // 6.5
    }
}
