package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;


// Question: Given an array of integers `heights` representing the histogram's bar height
// where the width of each bar is 1, return the area of the largest rectangle in the histogram.
//
// Example:
// Input: heights = [2, 1, 5, 6, 2, 3]
// Output: 10
// Explanation: The largest rectangle is shown in the diagram. It has an area of 10 (height 5, width 2).
// ]
//
// The core idea of the optimized solution is:
// For each bar `heights[i]`, we assume it is the shortest bar in our target rectangle.
// Then, we just need to find how far this rectangle can extend to the left and to the right.
// The rectangle can extend as long as it doesn't hit a bar that is shorter than `heights[i]`.
public class LargestRectangleHistogram {

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;

        // `left[i]` will store the index of the first bar to the left of `i` that is SHORTER than `heights[i]`.
        int[] left = new int[n];
        // `right[i]` will store the index of the first bar to the right of `i` that is SHORTER than `heights[i]`.
        int[] right = new int[n];

        // Default boundaries: -1 for the left and n for the right.
        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();

        // --- Pass 1: Calculate Left Boundaries ---
        // We iterate from left to right to find the "previous smaller element" for each bar.
        for (int i = 0; i < n; i++) {
            // While the stack is not empty and the bar at the stack's top is taller than or equal to the current bar...
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // ...it can't be a left boundary, so we pop it.
                stack.pop();
            }
            if (!stack.isEmpty()) {
                // The new stack top is the first shorter bar to the left.
                left[i] = stack.peek();
            }
            // Add the current bar's index to the stack for future comparisons.
            stack.push(i);
        }

        stack.clear();

        // --- Pass 2: Calculate Right Boundaries ---
        // We iterate from right to left to find the "next smaller element" for each bar.
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                right[i] = stack.peek();
            }
            stack.push(i);
        }

        // --- Pass 3: Calculate Areas ---
        // Now that we have the boundaries for each bar, we can calculate the area.
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            // The width is the distance between the right and left boundaries.
            int width = right[i] - left[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }


    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest area is: " + largestRectangleArea(heights)); // Expected: 10
    }

}