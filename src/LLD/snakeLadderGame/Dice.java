package LLD.snakeLadderGame;

import java.util.Random;

public class Dice {

    private static final Dice INSTANCE = new Dice();
    private static final int size = 6;
    private final Random random;

    private Dice() {
        this.random = new Random();
    }

    public static Dice getInstance() {
        return INSTANCE;
    }

    public int roll() {
        return random.nextInt(size) + 1;
    }

}
