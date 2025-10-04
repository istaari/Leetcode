package leetcode.binarySearch;

public class SearchInRotatedArray {

    // Smallest element in array
    static int findSmallestElement(int[] nums) {
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
        return low;
    }

    static int search(int[] nums, int target) {
        // Find the minimum element in the array
        int smallestIndex = findSmallestElement(nums);
        int low = 0;
        int high = nums.length - 1;

        // Separate the array by defining the new low and high, based on the target
        if (target >= nums[smallestIndex] && target <= nums[high]) {
            low = smallestIndex;
        } else {
            high = smallestIndex - 1;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    static int searchUsingModuloIndex(int[] nums, int target) {
        int rot = findSmallestElement(nums);
        int low = 0;
        int high = nums.length - 1;
        int n = nums.length;

        while (low <= high) {
            int mid = (low + high) / 2;

            // This formula translates the index from our "virtual" sorted array
            // (represented by 'mid') to the actual physical index in the rotated 'nums'  array.
            //
            // Example Walkthrough:
            // - Array: [4, 5, 6, 7, 0, 1, 2], and its rotation point `rot` is 4 (the index of '0').
            // - Our binary search's conceptual index is `mid = 3`.
            // - Calculation: realIndex = (mid + rot) % n = (3 + 4) % 7 = 0.
            //
            // This means the value that *should* be at index 3 in a sorted array
            // is actually located at index 0 in our rotated array. (And indeed, nums[0] is 4).
            int realMidValue = (mid + rot) % n;

            if (nums[realMidValue] == target) {
                return realMidValue;
            }
            if (nums[realMidValue] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Without rotation - {0, 1, 2, 4, 5, 6, 7};
        // rotation point: 4
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 2;
        System.out.println(search(nums, target));
        System.out.println(searchUsingModuloIndex(nums, target));
    }
}
