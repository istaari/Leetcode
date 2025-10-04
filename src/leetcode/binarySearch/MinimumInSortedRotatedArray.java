package leetcode.binarySearch;

public class MinimumInSortedRotatedArray {

    public static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] <= nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 1, 2};
        System.out.println(findMin(nums)); // 1
    }

}
