package leetcode.array;

public class RemoveDuplicates {


    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

    public static int removeDuplicates(int[] nums) {

        return 1;

    }


    // https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/

    public static int removeDuplicates2(int[] nums) {
        return 0;
    }


    public static void main(String[] args) {

        // Test cases for removeDuplicates (each element only once)
        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int newLength1 = removeDuplicates(nums1);
        System.out.println("removeDuplicates:");
        System.out.println("New Length: " + newLength1);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println("\n");


        // Additional test case for removeDuplicates
        int[] nums2 = {1, 1, 2};
        int newLength2 = removeDuplicates(nums2);
        System.out.println("New Length: " + newLength2);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println("\n");


        // Test cases for removeDuplicates2 (each element at most twice)
        int[] nums3 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int newLength3 = removeDuplicates2(nums3);
        System.out.println("removeDuplicates2:");
        System.out.println("New Length: " + newLength3);
        System.out.print("Array after removing duplicates (at most twice): ");
        for (int i = 0; i < newLength3; i++) {
            System.out.print(nums3[i] + " ");
        }
        System.out.println("\n");


        // Additional test case for removeDuplicates2
        int[] nums4 = {1, 1, 1, 2, 2, 3};
        int newLength4 = removeDuplicates2(nums4);
        System.out.println("New Length: " + newLength4);
        System.out.print("Array after removing duplicates (at most twice): ");
        for (int i = 0; i < newLength4; i++) {
            System.out.print(nums4[i] + " ");
        }

        System.out.println("\n");

    }

}
