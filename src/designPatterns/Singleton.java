package designPatterns;


/**
 * <h2>Problem:</h2>
 * <p>
 * You need to ensure that a class has only one instance and provide a global point of access to it.
 * </p>
 *
 * <h2>Solution:</h2>
 * <p>
 * The Singleton pattern restricts the instantiation of a class to one <em>single</em> instance.
 * This is useful in situations where exactly one object is needed to coordinate actions across the system.
 * </p>
 *
 * <h3>When to use:</h3>
 * <ul>
 *   <li><strong>Logging:</strong> Ensure that all log messages are written by a single instance of a logger.</li>
 *   <li><strong>Configuration management:</strong> Centralize the configuration settings of the application in a single instance.</li>
 *   <li><strong>Database connections:</strong> Manage a single connection pool or database access object.</li>
 * </ul>
 *
 * <h3>Example usage:</h3>
 * <pre>
 * Singleton singleton = Singleton.getInstance();
 * </pre>
 *
 * <p>This class demonstrates the Singleton design pattern.</p>
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
