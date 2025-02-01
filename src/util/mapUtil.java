package util;

import java.util.*;

public class mapUtil {

    public static void main(String[] args) {

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
        List<Integer> value2 = hashMap2.computeIfAbsent("A", key -> new ArrayList<>());
        System.out.println("value2 : " + value2);


        Map<String, List<Integer>> hashMap3 = new HashMap<>();
        List<Integer> value3 = hashMap3.computeIfPresent("A", (key, value0) -> new ArrayList<>());
        System.out.println("computeIfPresent : " + value3); // return null
        List<Integer> value4 = hashMap3.computeIfPresent("C", (key, value0) -> new ArrayList<>());

    }

}
