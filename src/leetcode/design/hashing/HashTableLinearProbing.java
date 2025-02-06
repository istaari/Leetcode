package leetcode.design.hashing;

public class HashTableLinearProbing {

    private static final String DELETED = "DELETED";
    private final String[] table;
    private final int size;

    public HashTableLinearProbing(int size) {
        this.size = size;
        this.table = new String[size];
    }

    public int hash(String key) {
        return key.length() % size;
    }

    // Linear probing insert method
    private void insertLinearProbing(String key) {
        int index = hash(key);
        int i = 0; // Step size
        // Hash Function: (index + i) % size
        while (table[index + 1] != null && !table[index + 1].equals(DELETED)) {
            i++;
        }

        table[index + 1] = key;
    }

    // Linear probing remove method
    private void removeLinearProbing(String key) {
        int index = hash(key);
        int i = 0; // Step size
        while (table[index + i] != null) {
            if (table[index + i].equals(key)) {
                table[index + i] = DELETED;
                break;
            }
            i++;
        }
    }


}
