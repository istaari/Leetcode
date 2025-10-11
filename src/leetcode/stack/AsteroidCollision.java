package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {

    /**
     * We are given an array `asteroids` of integers representing asteroids in a row.
     * For each asteroid, the absolute value represents its size, and the sign represents its direction
     * (positive meaning right, negative meaning left). All asteroids are moving at the same speed.
     * <p>
     * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
     * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
     *
     * @param asteroids An array of integers representing the asteroids.
     * @return An array representing the state of asteroids after all collisions.
     * @example For asteroids = [5, 10, -5]:
     * - The -5 and 10 collide. Since |10| > |-5|, the -5 asteroid explodes.
     * - The 5 and 10 never collide as they move in the same direction.
     * - The final state is [5, 10].
     * @example For asteroids = [10, 2, -5]:
     * - The -5 and 2 collide. Since |-5| > |2|, the 2 explodes.
     * - The stack becomes [10], and the current asteroid is still -5.
     * - Now, -5 and 10 collide. Since |10| > |-5|, the -5 explodes.
     * - The final state is [10].
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        // Iterate through each asteroid in the input array.
        for (int asteroid : asteroids) {
            boolean exploded = false;

            // A collision occurs only if the stack has a right-moving asteroid (positive)
            // and the current asteroid is a left-moving one (negative).
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                int top = stack.peek();

                // Case 1: The asteroid on the stack is smaller. It explodes.
                if (Math.abs(top) < Math.abs(asteroid)) {
                    stack.pop(); // The top asteroid is destroyed.
                    // The loop continues to check if the current asteroid collides with the new top.
                }
                // Case 2: Both asteroids are the same size. They both explode.
                else if (Math.abs(top) == Math.abs(asteroid)) {
                    stack.pop();      // The top asteroid is destroyed.
                    exploded = true;  // The current asteroid is also destroyed.
                    break;            // The current asteroid is gone, so stop checking for collisions.
                }
                // Case 3: The asteroid on the stack is larger. The current asteroid explodes.
                else {
                    exploded = true; // The current asteroid is destroyed.
                    break;           // The top asteroid survives, stop checking for collisions.
                }
            }

            // If the current asteroid was not destroyed in any collision, add it to the stack.
            if (!exploded) {
                stack.push(asteroid);
            }
        }

        // Convert the stack to an array for the final output.
        int[] result = new int[stack.size()];
        // We fill the array from the end because stack.pop() gives us the last elements first.
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    /**
     * Main method to test the functionality with a few examples.
     */
    public static void main(String[] args) {
        int[] arr1 = {5, 10, -5};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(arr1))); // Expected: [5, 10]

        int[] arr2 = {-2, -1, 1, 2};
        System.out.println("\nInput: " + Arrays.toString(arr2));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(arr2))); // Expected: [-2, -1, 1, 2]
    }
}