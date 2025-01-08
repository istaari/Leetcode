package dsa;

public class MajorityVoteAlgorithm {

    public static int findMajorityElement(int[] arr) {
        int count = 0;
        int candidate = 0;

        for (int value : arr) {
            if (count == 0) {
                candidate = value;
            }
            if (value == candidate) {
                count++;
            } else {
                count--;
            }
        }


        int newCount = 0;
        for (int value : arr) {
            if (value == candidate) {
                newCount++;
            }
        }

        if (newCount > arr.length / 2) {
            return candidate;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 1};
        System.out.println("The majority element is: " + findMajorityElement(arr));
    }
}
