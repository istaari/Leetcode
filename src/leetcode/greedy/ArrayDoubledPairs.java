package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ArrayDoubledPairs {

    public static boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        // Step 1: Count frequency of each number
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //Step 2: Sort by absolute value using boxed stream
        Integer[] s = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.comparingInt(Math::abs))
                .toArray(Integer[]::new);

        // Step 3: Try to pair each number with its double
        for (int x : s) {
            if (map.get(x) == 0)
                continue;

            int doubleX = 2 * x;

            if (map.getOrDefault(doubleX, 0) < map.get(x)) {
                return false;
            }
            map.put(doubleX, map.get(doubleX) - map.get(x));
            map.put(x, 0);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 0, 0, 8, 1 };
        // arr = new int[] {1, 2, 4, 16, 8, 4};
        System.out.println(canReorderDoubled(arr));
    }

}
