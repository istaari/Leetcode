package leetcode.array;

public class RemoveDuplicates {


    /**
     * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/"> Link </a>
     **/
    public static int removeDuplicates(int[] nums) {

        int i = 1;
        int j = 1;
        int n = nums.length;

        while (j < n) {

            if (nums[i - 1] != nums[j]) {
                nums[i] = nums[j];
                i++;
            }

            j++;
        }

        return i;
    }


    /**
     * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/">Link </a>
     **/
    public static int removeDuplicates_2(int[] nums) {
        int i = 2;
        int j = 2;
        int n = nums.length;

        while (j < n) {

            if (nums[i - 2] != nums[j]) {
                nums[i] = nums[j];
                i++;
            }

            j++;
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};

        System.out.println(removeDuplicates(nums));
        System.out.println(removeDuplicates_2(nums));
    }


}
