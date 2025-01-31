package leetcode.greedy;

public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        int twenty = 0;

        for (int note : bills) {
            if (note == 5) {
                five++;
            }

            if (note == 10) {
                if (five <= 0) return false;
                ten++;
                five--;
            }

            if (note == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                    twenty++;
                } else if (five >= 3) {
                    twenty++;
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }


}
