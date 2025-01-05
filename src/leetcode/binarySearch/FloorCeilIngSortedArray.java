package leetcode.binarySearch;

public class FloorCeilIngSortedArray {

    public static int findFloor(int[] arr, long x) {

        int low = 0;
        int high = arr.length - 1;
        int floorIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (x <= arr[mid]) {
                high = mid - 1;
            } else {
                floorIndex = mid;
                low = mid + 1;
            }

        }

        return arr[floorIndex];
    }

    public static int findCeil(int[] arr, long x) {

        int low = 0;
        int high = arr.length - 1;
        int ceilIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (x <= arr[mid]) {
                ceilIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return arr[ceilIndex];
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 8, 10, 11, 12, 19};
        int x = 0;
        System.out.println("floor " + findFloor(nums, x));
        System.out.println("ceil " + findCeil(nums, x));
    }

}
