package codeforces.string;

import java.util.Scanner;

public class WayTooLongWords {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String word = scanner.nextLine();
            int len = word.length();

            if (len > 10) {
                System.out.println(String.valueOf(word.charAt(0)) + (len - 2) + word.charAt(len - 1));
            } else {
                System.out.println(word);
            }

        }
    }
}


