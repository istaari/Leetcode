package leetcode.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums); // Sort the array
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int left = j + 1, right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];

                    // If the sum matches the target, add the quadruplet
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        // Skip duplicates for the 'left' pointer
                        while (left < right && nums[left - 1] == nums[left]) {
                            left++;
                        }

                    } else if (sum < target) { // If the sum is less than target, move the 'left' pointer
                        left++;
                    } else { // If the sum is greater than target, move the 'right' pointer
                        right--;
                    }
                }

                // Skip duplicate nums[j]
                while (j + 1 < n && nums[j] == nums[j + 1]) {
                    j++;
                }
            }

            // Skip duplicate nums[i]
            while (i + 1 < n && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return ans;

    }

    public static List<List<Integer>> fourSumBruteForce(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {

            for (int j = i + 1; j < n; j++) {

                for (int k = j + 1; k < n; k++) {

                    for (int l = k + 1; l < n; l++) {

                        long sum = (long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l];

                        if (target == sum) {
                            result.add(List.of(nums[i], nums[j], nums[k], nums[l]));
                        }

                        while (l < n - 1 && nums[l] == nums[l + 1]) l++;
                    }

                    while (k < n - 1 && nums[k] == nums[k + 1]) k++;
                }

                while (j < n - 1 && nums[j] == nums[j + 1]) j++;
            }

            while (i < n - 1 && nums[i] == nums[i + 1]) i++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        System.out.println(fourSum(nums, target)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    }
}
