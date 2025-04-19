package leetcode.greedy;

public class SmallestNumberDIString {
    private String result = null;

    // Using backtracking
    private void backtrack(String pattern, String current, boolean[] used, int index) {
        if (result != null) return; // Found smallest already

        if (index == pattern.length() + 1) {
            result = current;
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (used[i]) continue;

            if (index > 0) {
                int prev = current.charAt(index - 1) - '0';
                char constraint = pattern.charAt(index - 1);

                if ((constraint == 'I' && prev >= i) || (constraint == 'D' && prev <= i)) {
                    continue;
                }
            }

            used[i] = true;
            backtrack(pattern, current + i, used, index + 1);
            used[i] = false;
        }
    }

    // Greedy Solution using stack
    public String greedy(String pattern) {
        StringBuilder result = new StringBuilder();
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i <= pattern.length(); i++) {
            // Push current position (i+1) onto stack
            stack.append(i + 1);

            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                result.append(stack.reverse());
                stack = new StringBuilder();
            }
        }

        return result.toString();
    }

    public String smallestNumber(String pattern) {
        boolean[] used = new boolean[10]; // digits 1-9
        backtrack(pattern, "", used, 0);
        return result;
    }


    public static void main(String[] args){
       String pattern = "IIIDIDDD";
       System.out.println(new SmallestNumberDIString().smallestNumber(pattern));
    }
}
