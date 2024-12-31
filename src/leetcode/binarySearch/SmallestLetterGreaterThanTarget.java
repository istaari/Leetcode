package leetcode.binarySearch;

public class SmallestLetterGreaterThanTarget {

    public static char nextGreatestLetter(char[] letters, char target) {
        char result = letters[0];

        int l = 0;
        int h = letters.length - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (target < letters[mid]) {
                result = letters[mid];
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        char[] letters = {'x', 'x', 'y', 'z'};
        char target = 'z';

        System.out.println(nextGreatestLetter(letters, target));
    }
}
