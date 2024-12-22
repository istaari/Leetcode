package LLD.snakeLadderGame;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SnakeAndLadderGameDemo {

    public static void main(String[] args) {

        GameManager gameManager = GameManager.getInstance();

        Deque<Player> playerList1 = new LinkedList<>(List.of(
                new Player("Jack"),
                new Player("Alice"),
                new Player("Sam"),
                new Player("Nick")));

        int sessionId1 = gameManager.createSession(playerList1);
        new Thread(() -> gameManager.getSession(sessionId1).play()).start();


        Deque<Player> playerList2 = new LinkedList<>(List.of(
                new Player("John"),
                new Player("Emma"),
                new Player("Sophia")));

        int sessionId2 = gameManager.createSession(playerList2);
        new Thread(() -> gameManager.getSession(sessionId2).play()).start();



        Deque<Player> playerList3 = new LinkedList<>(List.of(
                new Player("Tom"),
                new Player("Jerry")));

        int sessionId3 = gameManager.createSession(playerList3);
        new Thread(() -> gameManager.getSession(sessionId3).play()).start();

    }


}
