package leetcode.binarySearch;


/**
 * LeetCode Problem 35: Search Insert Position
 *
 * Given a sorted array of distinct integers and a target value, return the index
 * if the target is found. If not, return the index where it would be if it were
 * inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 */
public class SearchInsertPosition {


    public int searchInsert(int[] nums, int target) {
        int low = 0;
        // Initialize high to nums.length to handle the case where the target
        // should be inserted at the very end.
        int high = nums.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (target <= nums[mid]) {
                // The target is in the left half, or this is the insertion point.
                high = mid;
            } else {
                // The target is in the right half.
                low = mid + 1;
            }
        }
        
        // When the loop terminates, `low` is the insertion point.
        return low;
    }

    public static void main(String[] args) {
        SearchInsertPosition solution = new SearchInsertPosition();
        int[] nums = {1, 3, 5, 6};
        
        int target1 = 5;
        System.out.println("Input: [1,3,5,6], target = 5. Output: " + solution.searchInsert(nums, target1)); // Expected: 2

        int target2 = 2;
        System.out.println("Input: [1,3,5,6], target = 2. Output: " + solution.searchInsert(nums, target2)); // Expected: 1

        int target3 = 7;
        System.out.println("Input: [1,3,5,6], target = 7. Output: " + solution.searchInsert(nums, target3)); // Expected: 4
    }
}
