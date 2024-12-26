package LLD.snakeLadderGame;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GameManager {

    private static final ConcurrentHashMap<Integer, SnakeAndLadderGame> sessionMap;
    private static final AtomicInteger sessionIdCounter;
    private static volatile GameManager Instance;

    static {
        sessionIdCounter = new AtomicInteger(0);
        sessionMap = new ConcurrentHashMap<>();
    }

    private GameManager() {
    }

    // Double Locking
    public static GameManager getInstance() {
        if (Instance == null) {
            synchronized (GameManager.class) {
                if (Instance == null) {
                    Instance = new GameManager();
                }
            }
        }

        return Instance;
    }

    // Create a new session and return the session ID
    public int createSession(Deque<Player> players) {
        int key = sessionIdCounter.incrementAndGet();
        sessionMap.put(key, new SnakeAndLadderGame(players));
        return key;
    }

    // // Retrieve a session based on the session ID
    public SnakeAndLadderGame getSession(Integer sessionId) {
        return sessionMap.get(sessionId);
    }

}
