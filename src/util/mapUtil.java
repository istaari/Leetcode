package util;

import java.util.*;

public class mapUtil {

    static void main(String[] args) {

        //--------------TreeMap---------------------//
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "one");
        treeMap.put(2, "two");
        treeMap.put(3, "three");
        treeMap.put(4, "four");

        System.out.println("First Key " + treeMap.firstKey());
        System.out.println("Last Key " + treeMap.lastKey());
        System.out.println("Floor Key " + treeMap.floorKey(5));
        System.out.println("Ceil Key " + treeMap.ceilingKey(0));


        //--------------HashMap---------------------//
        Map<String, Integer> hashMap = new HashMap<>();

        hashMap.put("A", 10);
        Integer value = hashMap.putIfAbsent("A", 20); // Won't add because "A" already exists
        System.out.println("map.putIfAbsent(\"A\", 20) : " + value);

        value = hashMap.putIfAbsent("B", 30); // Will add "B" because it doesn't exist and returns null
        System.out.println("map.putIfAbsent(\"A\", 30) : " + value);


        Map<String, List<Integer>> hashMap2 = new HashMap<>();
        List<Integer> value1 = hashMap2.computeIfAbsent("A", key -> new ArrayList<>(List.of(1, 2, 3)));
        System.out.println("value1 : " + value1);
        List<Integer> value2 = hashMap2.computeIfAbsent("A", key -> new ArrayList<>());
        System.out.println("value2 : " + value2);


        Map<String, List<Integer>> scores = new HashMap<>();
        // 1. Manually put a list in for "Alice"
        scores.put("Alice", new ArrayList<>(List.of(80, 85)));
        // 2. Use computeIfPresent to add a new score
        // This works because "Alice" is already in the map.
        scores.computeIfPresent("Alice", (key, existingList) -> {
            existingList.add(90);
            return existingList;
        });
        // 3. Try to use computeIfPresent for "Bob"
        // This will do NOTHING because "Bob" doesn't exist yet.
        scores.computeIfPresent("Bob", (key, existingList) -> {
            existingList.add(100);
            return existingList;
        });
        System.out.println("Alice's scores: " + scores.get("Alice")); // [80, 85, 90]
        System.out.println("Bob's scores: " + scores.get("Bob"));     // null
    }

}
