package leetcode.array;

public class SortColors {

    public static void sortColors(int[] nums, int n) {

        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {

            if (nums[i] == 0) {
                nums[++n2] = 2;
                nums[++n1] = 1;
                nums[++n0] = 0;

            } else if (nums[i] == 1) {
                nums[++n2] = 2;
                nums[++n1] = 1;

            } else if (nums[i] == 2) {
                nums[++n2] = 2;
            }

        }
    }

    public static void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = nums.length - 1;

        while (white <= blue) {
            int temp = 0;
            if (nums[white] == 0) {
                temp = nums[white];
                nums[white] = nums[red];
                nums[red] = temp;
                white++;
                red++;
            } else if (nums[white] == 1) {
                white++;
            } else {
                temp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = temp;
                blue--;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 0};
        sortColors(nums, nums.length);
        System.out.println(nums);
    }
}
