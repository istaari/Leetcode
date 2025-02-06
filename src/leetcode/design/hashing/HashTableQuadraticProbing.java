package leetcode.design.hashing;

public class HashTableQuadraticProbing {

    private static final String DELETED = "DELETED";
    private final String[] table;
    private final int size;

    public HashTableQuadraticProbing(int size) {
        this.size = size;
        this.table = new String[size];
    }

    public int hash(String key) {
        return key.length() % size;
    }


    // Quadratic probing insert method
    private void insertQuadraticProbing(String key) {
        int index = hash(key);
        int i = 0;
        // Hash Function: (index + i * i) % size
        while (table[(index + i * i) % size] != null && !table[(index + i * i) % size].equals(DELETED)) {
            i++;
        }
        table[(index + i * i) % size] = key;
    }


    private void removeQuadraticProbing(String key) {
        int index = hash(key);
        int i = 0;
        while (table[(index + i * i) % size] != null) {
            if (table[(index + i * i) % size].equals(key)) {
                table[(index + i * i) % size] = DELETED;
                break;
            }
            i++;
        }
    }


}
