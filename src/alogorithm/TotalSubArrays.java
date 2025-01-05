package alogorithm;

@SuppressWarnings("all")
public class TotalSubArrays {

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 3};
        // Output the result
        System.out.println("Sum of all subarrays: " + new TotalSubArrays().sumOfAllSubarrays(arr));
    }

    int sumOfAllSubarrays(int[] arr) {
        int n = arr.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int subarrayCount = (i + 1) * (n - i);
            sum += arr[i] * subarrayCount;
        }

        return sum;
    }


}
