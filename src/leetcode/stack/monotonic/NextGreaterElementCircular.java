package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementCircular {

    /**
     * Given a circular integer array `nums`, return the next greater number for every element.
     * The next greater number of a number `x` is the first greater number to its traversing-order next in the array,
     * which means you could search circularly to find its next greater number. If it doesn't exist, return -1.
     * <p>
     * This implementation uses a monotonic stack and a single pass (emulated as two passes) to find the solution efficiently.
     *
     * @param arr The input circular integer array.
     * @return An array of the same size where result[i] is the next greater element for arr[i].
     * @example For arr = [1, 2, 1]:
     * - The next greater element for the first 1 is 2.
     * - The next greater element for 2 is -1 (no greater element).
     * - The next greater element for the second 1 is 2 (found by searching circularly).
     * - The result will be [2, -1, 2].
     */
    public static int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, -1); // Default value if no greater element is found.

        // The stack stores indices, maintaining a monotonically decreasing order for the values at those indices.
        Stack<Integer> stack = new Stack<>();

        // We iterate through the array twice to handle the circular nature.
        // The modulo operator (k % n) wraps the index around for the second pass.
        for (int k = 0; k < n * 2; k++) {
            int currentIndex = k % n;
            int currentElement = arr[currentIndex];

            // While the stack is not empty and the current element is greater than
            // the element at the index on top of the stack...
            while (!stack.isEmpty() && arr[stack.peek()] < currentElement) {
                // ...the current element is the "Next Greater Element" for the element at the popped index.
                int poppedIndex = stack.pop();
                nextGreater[poppedIndex] = currentElement;
            }

            // Push the current index onto the stack. We only do this in the first pass
            // to avoid redundant pushes and ensure each index is processed correctly.
            if (k < n) {
                stack.push(currentIndex);
            }
        }

        return nextGreater;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 1};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Next Greater Elements: " + Arrays.toString(nextGreaterElements(nums1))); // Expected: [2, -1, 2]

        System.out.println("---");

        int[] nums2 = {1, 2, 3, 4, 3};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Next Greater Elements: " + Arrays.toString(nextGreaterElements(nums2))); // Expected: [2, 3, 4, -1, 4]
    }
}
