package leetcode.binarySearch;

@SuppressWarnings("SpellCheckingInspection")
public class SearchInRotatedArray2 {

    public static int smallestElementIndex(int[] nums) {
        int low = 0;
        int high = nums.length - 1;


        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {
                high = mid;
            }
            /* When nums[mid] equals nums[high], we need to check both halves */
            else {
                // If a high element is less than its previous element,
                // we found the minimum
                if (high > 0 && nums[high] < nums[high - 1]) {
                    low = high;
                    break;
                }
                // Otherwise, decrease high and keep searching
                high--;
            }
        }

        return low;
    }

    public static boolean search(int[] nums, int target) {
        int rotatedPoint = smallestElementIndex(nums);

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int realMid = (mid + rotatedPoint) % nums.length;

            if (nums[realMid] == target) {
                return true;
            } else if (target < nums[realMid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1};
        int target = 2;
        System.out.println(search(nums, target));
    }


}
