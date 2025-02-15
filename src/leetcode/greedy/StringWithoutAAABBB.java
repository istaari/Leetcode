package leetcode.greedy;

public class StringWithoutAAABBB {

    public static String strWithout3a3b(int a, int b) {
        StringBuilder result = new StringBuilder();

        while (a > 0 || b > 0) {
            if (a > b) {

                if (a > 1) {
                    result.append("aa");
                    a -= 2;
                } else {
                    result.append("a");
                    a--;
                }

                if (b > 0) {
                    result.append("b");
                    b--;
                }

            } else if (b > a) {

                if (b > 1) {
                    result.append("bb");
                    b -= 2;
                } else {
                    result.append("b");
                    b--;
                }
                if (a > 0) {
                    result.append("a");
                    a--;
                }

            } else {

                result.append("a");
                a--;
                result.append("b");
                b--;

            }
        }

        return result.toString();
    }

    public static String strWithout3a3bOptimized(int A, int B) {
        // Create a StringBuilder with an initial capacity of A + B
        StringBuilder res = new StringBuilder(A + B);

        char a = 'a', b = 'b'; // Default characters: 'a' and 'b'
        int i = A, j = B; // i represent count of 'a', j represents count of 'b'

        // If B > A, swap 'a' and 'b' to always ensure 'a' is the more frequent character
        if (B > A) {
            a = 'b';
            b = 'a';
            i = B;
            j = A;
        }

        // Build the result string while 'i' (more frequent letter count) is greater than 0
        while (i-- > 0) {
            res.append(a); // Add the more frequent letter

            // If 'i' is still greater than 'j', add one more 'a' to avoid "bbb"
            if (i > j) {
                res.append(a);
                --i; // Decrease 'i' since we added an extra 'a'
            }

            // If there are still 'b's left, add one to maintain balance
            if (j-- > 0) {
                res.append(b);
            }
        }

        return res.toString();
    }


    public static void main(String[] args) {
        int a = 4;
        int b = 4;
        System.out.println(strWithout3a3b(a, b));

    }


}

