package leetcode.string;


public class BullsCows {

    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));

            if (s == g) bulls++;
            else {
                if (count[s] < 0) cows++;
                if (count[g] > 0) cows++;
                count[s]++;
                count[g]--;
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";

        secret = "11";
        guess = "10";

        System.out.println(getHint(secret, guess));
    }
}
