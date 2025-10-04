package dsa.setSequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {


    private  void generateSubsequences(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[index]);  // Include current element
        generateSubsequences(nums, index + 1, current, result);
        current.remove(current.size() - 1);  // Backtrack and exclude current element
        generateSubsequences(nums, index + 1, current, result);
    }


    public void generateSubsequencesBitwise(int[] nums, List<List<Integer>> result) {
        int n = nums.length;

        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> s = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    s.add(nums[i]);
                }
            }
            result.add(s);
        }
    }


    public static void main(String[] args) {
        Subsequence subsequence = new Subsequence();
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = {1, 2};
        //subsequence.generateSubsequencesBitwise(nums, result);
        subsequence.generateSubsequences(nums, 0, new ArrayList<>(), result);
        System.out.println(result);
    }

}
