package neetcode150.binarySearch;

public class SearchInRotatedArray_33 {
    // Smallest element in array
    static int smallestElementBinarySearch(int[] nums) {
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
        int smallestIndex = smallestElementBinarySearch(nums);
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
        int rot = smallestElementBinarySearch(nums);
        int low = 0;
        int high = nums.length - 1;
        int n = nums.length;

        // The usual binary search and accounting for rotation.
        while (low <= high) {

            int mid = (low + high) / 2;
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
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 2;
        System.out.println(search(nums, target));
        System.out.println(searchUsingModuloIndex(nums, target));
    }
}
