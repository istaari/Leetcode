package leetcode.bit;

@SuppressWarnings("all")
public class BitwiseTricks {

    public static void main(String[] args) {
        int x = 1; // 00000000 00000000 00000000 00000001

        // 1 << 31: 10000000 00000000 00000000 00000000
        System.out.println(x << 31); // Integer.MIN_VALUE : -2147483648


        /**
         1 << 32: 00000000 00000000 00000000 00000001

         1 << 32   → 1 << (32 % 32)  = 1 << 0   → 1
         1 << 33   → 1 << (33 % 32)  = 1 << 1   → 2
         1 << 34   → 1 << (34 % 32)  = 1 << 2   → 4
         1 << 35   → 1 << (35 % 32)  = 1 << 3   → 8
         1 << 36   → 1 << (36 % 32)  = 1 << 4   → 16
         1 << 37   → 1 << (37 % 32)  = 1 << 5   → 32
         1 << 43   → 1 << (43 % 32)  = 1 << 11  → 2048
         **/
        System.out.println(x << 32); // Bits shift wraps around
    }

}
