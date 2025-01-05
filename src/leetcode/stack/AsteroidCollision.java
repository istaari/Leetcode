package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;


@SuppressWarnings("all")
public class AsteroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean exploded = false;

            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                int top = stack.peek();
                if (Math.abs(top) < Math.abs(asteroid)) {
                    stack.pop(); // Top asteroid explodes
                } else if (Math.abs(top) == Math.abs(asteroid)) {
                    stack.pop(); // Both asteroids explode
                    exploded = true;
                    break;
                } else {
                    exploded = true; // Current asteroid explodes
                    break;
                }
            }

            if (!exploded) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) {
            result[index--] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-2, -1, 1, 2};
        System.out.println(Arrays.toString(asteroidCollision(arr)));
    }


}
