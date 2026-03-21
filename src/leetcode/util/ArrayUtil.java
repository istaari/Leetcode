package leetcode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayUtil {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Fill with default values
        Arrays.fill(array, 0);
        // Sort in ascending order
        Arrays.sort(array);
        // Copied array
        int[] copied = Arrays.copyOf(array, array.length);

        // Convert a arrayList to array
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 2, 3));
        int[] listArray = list.stream().mapToInt(a -> a).toArray();

        // Convert String[] to int[]
        String[] stringArray = {"1", "2", "3", "4", "5"};
        int[] intArray = Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();

        // Sort array in descending order
        // Convert int[] to Integer[] to use Collections.reverseOrder()
        Integer[] array1dInteger = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Arrays.sort(array1dInteger, Collections.reverseOrder());
        // Convert back to int[] if needed
        array = Arrays.stream(array1dInteger).mapToInt(Integer::intValue).toArray();
    }

}
