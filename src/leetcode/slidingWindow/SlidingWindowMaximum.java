package leetcode.slidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Check if the element exceeds the maximum size in deque
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.removeFirst();
            }

            // Create a decreasing monotonic queue
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            if (i >= k - 1 && !deque.isEmpty()) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {7, 2, 4};
        int k = 2;

        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }


}
