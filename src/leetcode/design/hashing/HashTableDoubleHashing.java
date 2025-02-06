package leetcode.design.hashing;


public class HashTableDoubleHashing {
    private static final String DELETED = "DELETED";
    private final String[] table;
    private final int size;


    public HashTableDoubleHashing(int size) {
        this.size = size;
        this.table = new String[size];
    }


    // Simple hash function
    public int hash(String key) {
        return key.length() % size;
    }

    // Secondary hash function for double hashing
    private int secondaryHash(String key) {
        return 7 - (key.length() % 7);
    }

    // Double hashing insert method
    private void insertDoubleHashing(String key) {
        int index = hash(key);
        int stepSize = secondaryHash(key);
        int i = 0;

        // Hash Function: (index + i * stepSize) % size
        while (table[(index + i * stepSize) % size] != null && !table[(index + i * stepSize) % size].equals(DELETED)) {
            i++;
        }
        table[(index + i * stepSize) % size] = key;
    }


    // Double hashing remove method
    private void removeDoubleHashing(String key) {
        int index = hash(key);
        int stepSize = secondaryHash(key);
        int i = 0;

        while (table[(index + i * stepSize) % size] != null && !table[(index + i * stepSize) % size].equals(key)) {
            i++;
        }

        table[(index + i * stepSize) % size] = DELETED;
    }

}
