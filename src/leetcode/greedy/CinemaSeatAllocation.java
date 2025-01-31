package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

public class CinemaSeatAllocation {

    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedMap = new HashMap<>();
        // Represent reserved seats in bit mask
        for (int[] reservedSeat : reservedSeats) {
            int row = reservedSeat[0];
            int col = reservedSeat[1];

            reservedMap.putIfAbsent(row, 0);
            int mask = reservedMap.get(row); // Initial Mask is 0
            mask |= 1 << (col - 1);
            reservedMap.put(row, mask);
        }

        int leftBlock = 0;
        leftBlock |= 1 << (2 - 1);
        leftBlock |= 1 << (3 - 1);
        leftBlock |= 1 << (4 - 1);
        leftBlock |= 1 << (5 - 1); // 30 - 11110

        int middleBlock = 0;
        middleBlock |= 1 << (4 - 1);
        middleBlock |= 1 << (5 - 1);
        middleBlock |= 1 << (6 - 1);
        middleBlock |= 1 << (7 - 1); // 120 - 1111000


        int rightBlock = 0;
        rightBlock |= 1 << (6 - 1);
        rightBlock |= 1 << (7 - 1);
        rightBlock |= 1 << (8 - 1);
        rightBlock |= 1 << (9 - 1); // 480 - 111100000

        int result = 2 * (n - reservedMap.size());

        for (int row : reservedMap.keySet()) {
            int rowMask = reservedMap.get(row);
            boolean canSitLeft = (rowMask & leftBlock) == 0;
            boolean canSitMiddle = (rowMask & middleBlock) == 0;
            boolean canSitRight = (rowMask & rightBlock) == 0;

            if (canSitLeft && canSitRight) {
                result += 2;
            } else if (canSitLeft || canSitMiddle || canSitRight) {
                result += 1;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int n = 3;
        int[][] reservedSeats = {{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        System.out.println(maxNumberOfFamilies(n, reservedSeats));
    }
}
