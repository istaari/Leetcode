package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;


public class MonotonicStackTemplate {

    public void findPreviousNextSmallerValue(int[] arr) {
        int n = arr.length;
        int[] prevSmaller = new int[n];
        int[] nextSmaller = new int[n];
        Arrays.fill(prevSmaller, -1);
        Arrays.fill(nextSmaller, -1);

        // The stack must store INDICES, not values, to know which position to update in the nextSmaller array.
        // It maintains indices for a monotonically increasing sequence of values.
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // The current element arr[i] is the "Next Smaller" for any elements on the stack that are greater than it.
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int pastIndex = stack.pop();
                nextSmaller[pastIndex] = arr[i];
            }

            // After clearing the stack, what remains on top is the first element to the left that is smaller.
            // This is the "Previous Smaller" for the current element arr[i].
            if (!stack.isEmpty()) {
                prevSmaller[i] = arr[stack.peek()];
            }

            // Push the current INDEX onto the stack.
            stack.push(i);
        }

        System.out.println("--- Smaller Elements ---");
        System.out.println("Input Array:      " + Arrays.toString(arr));
        System.out.println("Previous Smaller: " + Arrays.toString(prevSmaller));
        System.out.println("Next Smaller:     " + Arrays.toString(nextSmaller));
    }


    public void findPreviousNextGreaterValue(int[] arr) {
        int n = arr.length;
        int[] prevGreater = new int[n];
        int[] nextGreater = new int[n];
        Arrays.fill(prevGreater, -1);
        Arrays.fill(nextGreater, -1);

        // This stack stores INDICES for a monotonically decreasing sequence of values.
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // The current element arr[i] is the "Next Greater" for any elements on the stack that are smaller than it.
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int pastIndex = stack.pop();
                nextGreater[pastIndex] = arr[i];
            }

            // After clearing the stack, what remains on top is the first element to the left that is greater.
            // This is the "Previous Greater" for the current element arr[i].
            if (!stack.isEmpty()) {
                prevGreater[i] = arr[stack.peek()];
            }

            // Push the current INDEX onto the stack.
            stack.push(i);
        }

        System.out.println("\n--- Greater Elements ---");
        System.out.println("Input Array:      " + Arrays.toString(arr));
        System.out.println("Previous Greater: " + Arrays.toString(prevGreater));
        System.out.println("Next Greater:     " + Arrays.toString(nextGreater));
    }


    public static void main(String[] args) {
        MonotonicStackTemplate template = new MonotonicStackTemplate();
        int[] arr = {4, 8, 5, 2, 25};

        template.findPreviousNextSmallerValue(arr);
        /*
         Expected Output:
         --- Smaller Elements ---
         Input Array:      [4, 8, 5, 2, 25]
         Previous Smaller: [-1, 4, 4, -1, 2]
         Next Smaller:     [2, 5, 2, -1, -1]
        */

        template.findPreviousNextGreaterValue(arr);
        /*
         Expected Output:
         --- Greater Elements ---
         Input Array:      [4, 8, 5, 2, 25]
         Previous Greater: [-1, -1, 8, 5, -1]
         Next Greater:     [8, 25, 25, 25, -1]
        */
    }
}

