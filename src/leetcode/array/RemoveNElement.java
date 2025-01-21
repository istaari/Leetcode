package leetcode.array;

public class RemoveNElement {


    public static int removeElement(int[] nums, int val) {
        int n = nums.length;
        int i = 0; // Use i to place elements which should not be removed
        int j = 0;

        while (j < n) {

            if (nums[j] != val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }

            j++;
        }

        return i;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int val = 4;
        System.out.println(removeElement(nums, val));
    }
}
