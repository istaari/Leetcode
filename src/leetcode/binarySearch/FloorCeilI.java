package leetcode.binarySearch;

import java.util.Arrays;

/**
 * Problem: Find Floor and Ceil in a Sorted Array
 *
 * Given a sorted array and a value x, the floor of x is the largest element in the array
 * smaller than or equal to x. The ceiling of x is the smallest element in the array
 * greater than or equal to x.
 *
 * Example:
 * Input: arr = {1, 2, 8, 10, 11, 12, 19}, x = 5
 * Output: Floor = 2, Ceil = 8
 */
public class FloorCeilI {

    public static int findFloor(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= x) {
                // This is a potential floor. Store it and search for a larger one to the right.
                floor = arr[mid];
                low = mid + 1;
            } else {
                // arr[mid] is too large, search in the left half.
                high = mid - 1;
            }
        }
        return floor;
    }

    public static int findCeil(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                // This is a potential ceiling. Store it and search for a smaller one to the
                // left.
                ceil = arr[mid];
                high = mid - 1;
            } else {
                // arr[mid] is too small, search in the right half.
                low = mid + 1;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 8, 10, 11, 12, 19 };

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("--- Finding Floor and Ceil for x = 5 ---");
        System.out.println("Floor: " + findFloor(nums, 5)); // Expected: 2
        System.out.println("Ceil: " + findCeil(nums, 5)); // Expected: 8

        System.out.println("\n--- Finding Floor and Ceil for x = 10 ---");
        System.out.println("Floor: " + findFloor(nums, 10)); // Expected: 10
        System.out.println("Ceil: " + findCeil(nums, 10)); // Expected: 10

        System.out.println("\n--- Finding Floor and Ceil for x = 20 ---");
        System.out.println("Floor: " + findFloor(nums, 20)); // Expected: 19
        System.out.println("Ceil: " + findCeil(nums, 20)); // Expected: -1

        System.out.println("\n--- Finding Floor and Ceil for x = 0 ---");
        System.out.println("Floor: " + findFloor(nums, 0)); // Expected: -1
        System.out.println("Ceil: " + findCeil(nums, 0)); // Expected: 1
    }
}
