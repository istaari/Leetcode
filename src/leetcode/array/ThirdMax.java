package leetcode.array;

public class ThirdMax {

    public static int thirdMax(int[] nums) {
        // Use nullable Integer objects to correctly handle the Integer.MIN_VALUE edge case
        // and to track if a max has been found yet.
        Integer firstMax = null;
        Integer secondMax = null;
        Integer thirdMax = null;

        for (Integer num : nums) { // Use Integer wrapper to allow comparison with null
            // Skip duplicates
            if (num.equals(firstMax) || num.equals(secondMax) || num.equals(thirdMax)) {
                continue;
            }

            // The cascade update logic
            if (firstMax == null || num > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = num;
            } else if (secondMax == null || num > secondMax) {
                thirdMax = secondMax;
                secondMax = num;
            } else if (thirdMax == null || num > thirdMax) {
                thirdMax = num;
            }
        }

        // If thirdMax is null, it means there were fewer than three distinct numbers.
        // In this case, we must return the absolute maximum (firstMax).
        return thirdMax == null ? firstMax : thirdMax;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 2, 3, 1};
    }

}
