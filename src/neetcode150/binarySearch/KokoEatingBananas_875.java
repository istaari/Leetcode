package neetcode150.binarySearch;

public class KokoEatingBananas_875 {

    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000000 + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEatAll(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static boolean canEatAll(int[] piles, int speed, int h) {
        long hours = 0;
        for (int pile : piles) {
            int time = (int) Math.ceil((double) pile / speed);
            hours = hours + time;
            //if (pile % speed != 0) hours++;
        }
        return hours <= h;
    }


    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(minEatingSpeed(piles, h)); // 4

        piles = new int[]{30, 11, 23, 4, 20};
        h = 5;
        System.out.println(minEatingSpeed(piles, h)); // 30
    }

}
