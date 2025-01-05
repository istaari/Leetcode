package leetcode.binarySearch;

public class SearchInsertPosition {


    int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length; // Full Length

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target <= nums[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }



    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 8;
        System.out.println(new SearchInsertPosition().searchInsert(nums, target));
    }

}
