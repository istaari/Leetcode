package dsa;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class SubarrayGenerator {

    public static List<List<Integer>> generateSubarrays(int[] arr) {
        List<List<Integer>> subarrays = new ArrayList<>();
        int n = arr.length;

        for (int start = 0; start < n; start++) {
            List<Integer> currentSubarray = new ArrayList<>();

            for (int end = start; end < n; end++) {
                currentSubarray.add(arr[end]);
                subarrays.add(new ArrayList<>(currentSubarray));
            }

        }

        return subarrays;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> subarrays = generateSubarrays(arr);
        System.out.println(generateSubarrays(arr));
    }

}
