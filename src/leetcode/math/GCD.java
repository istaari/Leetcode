package leetcode.math;

public class GCD {

    public static int GCDRecursive(int a, int b) {

        if (b == 0) return a;

        return GCDRecursive(b, a % b);
    }

    public static int GCDIterative(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }


    public static void main(String[] args) {
        int a = 98;
        int b = 56;
        System.out.println(GCDRecursive(a, b));
        System.out.println(GCDIterative(a, b));
    }

}
