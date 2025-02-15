package leetcode.dp.subarray;

public class MaximumProductSubarray {


    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxProd = nums[0]; // Maximum product so far
        int minProd = nums[0]; // Minimum product so far
        int result = nums[0];  // Final result


        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Update maxProd and minProd
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);

            result = Math.max(result, maxProd);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {2, 3, -2, 4};
        System.out.println(maxProduct(nums1));

        // Test case 2
        int[] nums2 = {-2, 0, -1};
        System.out.println(maxProduct(nums2));
    }
}
