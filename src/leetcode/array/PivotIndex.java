package leetcode.array;

public class PivotIndex {

    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] postfix = new int[n];

        int pre = 0;
        int post = 0;

        for (int i = 0; i < n; i++) {
            prefix[i] = pre;
            pre += nums[i];

            postfix[n - 1 - i] = post;
            post += nums[n - 1 - i];
        }

        for (int i = 0; i < n; i++) {
            if (prefix[i] == postfix[i]) return i;
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 7, 3, 6, 5, 6};
        // a = new int[]{2, 1, -1};
        System.out.println(pivotIndex(a));
    }

}
