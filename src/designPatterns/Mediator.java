package designPatterns;


import java.util.ArrayList;
import java.util.List;



/*
 * Mediator Design Pattern
 *
 * Definition:
 * The Mediator Pattern reduces the complexity of communication between multiple objects or components
 * by centralizing their interactions in a mediator object.
 *
 * Real-World Analogy:
 * Air Traffic Control (ATC): Pilots don't communicate directly with each other. Instead, they communicate
 * via an air traffic controller, who coordinates all flights.
 */


// Mediator interface
interface ChatMediator {
    void sendMessage(String message, User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }
}

// Colleague class
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);

    public abstract void receive(String message);
}

// Concrete Colleague
class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}

// Usage
public class Mediator {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");

        ((ChatRoom) chatRoom).addUser(user1);
        ((ChatRoom) chatRoom).addUser(user2);
        ((ChatRoom) chatRoom).addUser(user3);

        user1.send("Hello, everyone!");
    }

}
