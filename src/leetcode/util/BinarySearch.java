package leetcode.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BinarySearch {


    public static void searchAndPrint(List<Integer> list, int key) {
        int index = Collections.binarySearch(list, key);

        if (index >= 0) {
            System.out.printf("Key %3d: Found at index %d\n", key, index);
        } else {
            // If not found, calculate the theoretical insertion point
            int insertionPoint = -(index + 1);
            System.out.printf("Key %3d: Not found. Would be inserted at index %d (returned %d)\n", key, insertionPoint, index);
        }
    }


    public static void main(String[] args) {
        List<Integer> cIndices = new ArrayList<>(List.of(99, 3, 42, 10, 57, 25));
        Collections.sort(cIndices); // Sorts to: [3, 10, 25, 42, 57, 99]

        System.out.println("Searching in sorted list: " + cIndices);
        System.out.println("-------------------------------------");

        // 2. Search for multiple keys (the 'i' in your request)
        searchAndPrint(cIndices, 42); // Exists
        searchAndPrint(cIndices, 30); // Doesn't exist, would be in the middle
        searchAndPrint(cIndices, 1);  // Doesn't exist, would be at the start
        searchAndPrint(cIndices, 100); // Doesn't exist, would be at the end
    }


}
