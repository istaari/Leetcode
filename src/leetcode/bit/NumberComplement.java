package leetcode.bit;

public class NumberComplement {

    public static int findComplement(int num) {
        int mask = ~0;

        while ((num & mask) != 0) {
            mask = mask << 1;
        }

        return ~num & ~mask;
    }

    public static void main(String[] args) {
        int num = 5;
        System.out.println(findComplement(num)); // 2
    }

}
