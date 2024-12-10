package designPatterns;


import java.util.ArrayList;
import java.util.List;


/**
 * <h2>Problem:</h2>
 * <p>
 * You need to notify multiple objects about state changes in another object, but you don't want those objects
 * to be tightly coupled.
 * </p>
 *
 * <h2>Solution:</h2>
 * <p>
 * The Observer pattern defines a one-to-many dependency between objects so that when one object changes state,
 * all its dependents (observers) are notified. This ensures loose coupling between the subject and observers.
 * </p>
 *
 * <h3>When to use:</h3>
 * <ul>
 *   <li><strong>Real-time event handling:</strong> Systems like messaging platforms where a change (e.g., a new message)
 *   needs to notify many users or components.</li>
 *   <li><strong>User Interfaces:</strong> In GUIs where an event in one component (e.g., button click) should update
 *   multiple components, such as displaying messages, updating labels, etc.</li>
 *   <li><strong>Stock market updates:</strong> When a stock price changes, all registered clients need to be notified
 *   in real-time.</li>
 * </ul>
 *
 * <h3>Example usage:</h3>
 * <pre>
 * Subject subject = new Subject();
 * Observer observer = new ConcreteObserver();
 * subject.addObserver(observer);
 * subject.notifyObservers("State changed!");
 * </pre>
 *
 * <p>This class demonstrates the Observer design pattern.</p>
 */
interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private final String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}


class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}


public class Observer0 {

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new ConcreteObserver("Observer 1"));
        subject.addObserver(new ConcreteObserver("Observer 2"));
        subject.notifyObservers("New event!");
    }

}
