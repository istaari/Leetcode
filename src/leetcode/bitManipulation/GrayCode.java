package leetcode.bitManipulation;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    public static List<Integer> grayCode(int n) {
        n = 1 << n; // Math.pow(2, n)
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int grayCode = i ^ (i >> 1);
            result.add(grayCode);
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(grayCode(2));
    }

}
