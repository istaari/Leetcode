package codeforces.string;

import java.util.Scanner;

public class BitPlus {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = 0;

        scanner.nextLine(); //consumes the newline after reading int

        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            if (s.charAt(0) == '+' || s.charAt(s.length() - 1) == '+') x++;
            if (s.charAt(0) == '-' || s.charAt(s.length() - 1) == '-') x--;

        }

        System.out.println(x);
    }

}
