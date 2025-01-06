public class Solution {

import java.util.*;

public class MonotonicStackExample {

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Previous Smallest: " + Arrays.toString(findPreviousSmallest(arr)));
        System.out.println("Next Smallest: " + Arrays.toString(findNextSmallest(arr)));
        System.out.println("Previous Largest: " + Arrays.toString(findPreviousLargest(arr)));
        System.out.println("Next Largest: " + Arrays.toString(findNextLargest(arr)));
    }

    // Method to find Previous Smallest Element
    int[] findPreviousSmallest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return result;
    }

    // Method to find Next Smallest Element
    int[] findNextSmallest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    int[] findPreviousLargest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    int[] findNextLargest(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }
    
}



}


