package leetcode.stack;

import java.util.Stack;

public class SimplifyPath {


    public String simplifyPath(String path) {

        String[] pathString = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String currentPath : pathString) {

            if (currentPath.equals(".") || currentPath.isEmpty()) {
                continue;
            } else if (currentPath.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(currentPath);
            }

        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();

        for (String s : stack) {
            result.append("/").append(s);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();

        // Test Case 1
        String path1 = "/home/";
        String expected1 = "/home";
        String result1 = sp.simplifyPath(path1);
        System.out.println("Input: " + path1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: " + expected1);
        System.out.println("Test Case 1 " + (result1.equals(expected1) ? "Passed" : "Failed"));
        System.out.println();

        // Test Case 2
        String path2 = "/home//foo/";
        String expected2 = "/home/foo";
        String result2 = sp.simplifyPath(path2);
        System.out.println("Input: " + path2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: " + expected2);
        System.out.println("Test Case 2 " + (result2.equals(expected2) ? "Passed" : "Failed"));
        System.out.println();

        // Test Case 3
        String path3 = "/home/user/Documents/../Pictures";
        String expected3 = "/home/user/Pictures";
        String result3 = sp.simplifyPath(path3);
        System.out.println("Input: " + path3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: " + expected3);
        System.out.println("Test Case 3 " + (result3.equals(expected3) ? "Passed" : "Failed"));
        System.out.println();

        // Test Case 4
        String path4 = "/../";
        String expected4 = "/";
        String result4 = sp.simplifyPath(path4);
        System.out.println("Input: " + path4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: " + expected4);
        System.out.println("Test Case 4 " + (result4.equals(expected4) ? "Passed" : "Failed"));
        System.out.println();

        // Test Case 5
        String path5 = "/.../a/../b/c/../d/./";
        String expected5 = "/.../b/d";
        String result5 = sp.simplifyPath(path5);
        System.out.println("Input: " + path5);
        System.out.println("Output: " + result5);
        System.out.println("Expected: " + expected5);
        System.out.println("Test Case 5 " + (result5.equals(expected5) ? "Passed" : "Failed"));
    }



}
