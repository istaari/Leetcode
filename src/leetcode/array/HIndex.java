package leetcode.array;

import java.util.Arrays;

public class HIndex {

    public static int hIndexB(int[] citations) {
        int left = 0;
        int right = citations.length - 1;

        int n = citations.length;

        int max = 0;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (citations[mid] >= n - mid) {
                right = mid - 1;
                max = n - mid;

            } else {
                left = mid + 1;
            }

        }

        return max;
    }


    public static int hIndex(int[] citations) {
        Arrays.sort(citations); // Sort in ascending order

        // Reverse the array
        for (int i = 0; i < citations.length / 2; i++) {
            int temp = citations[i];
            citations[i] = citations[citations.length - i - 1];
            citations[citations.length - i - 1] = temp;
        }

        int max = 0;
        for (int i = 0; i < citations.length; i++) {
            if (i + 1 <= citations[i]) {
                max = Math.max(i + 1, max);
            } else {
                break;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(hIndexB(new int[]{0,1,3,5,6}));
        //System.out.println(hIndexB(new int[]{0, 1}));
        //System.out.println(hIndexB(new int[]{1,2,100}));
    }

}
