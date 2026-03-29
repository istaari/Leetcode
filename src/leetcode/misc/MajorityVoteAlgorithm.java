package leetcode.misc;

/**
 * Boyer-Moore Majority Vote Algorithm
 *
 * Finds the majority element in an array — the element that appears more than ⌊n/2⌋ times.
 *
 * ==================== ALGORITHM STEPS ====================
 *
 * Phase 1 — Find a Candidate:
 *   - Maintain a candidate and a counter (initially 0).
 *   - For each element:
 *       • If counter == 0, set the current element as the new candidate.
 *       • If element == candidate, increment counter.
 *       • Otherwise, decrement counter (cancels one occurrence of candidate with one non-candidate).
 *   - Intuition: Majority element survives all cancellations because it appears > n/2 times.
 *
 * Phase 2 — Verify the Candidate:
 *   - Count actual occurrences of the candidate.
 *   - Return the candidate only if its count > n/2.
 *   - This step is necessary because Phase 1 can produce a wrong candidate
 *     when no true majority element exists (e.g., [1, 2, 3]).
 *
 * ==================== COMPLEXITY ====================
 *   Time:  O(n) — two linear passes
 *   Space: O(1) — only a counter and a candidate variable
 */
public class MajorityVoteAlgorithm {

    /**
     * Finds the majority element that appears more than ⌊n/2⌋ times.
     *
     * @param arr the input array
     * @return the majority element, or -1 if none exists
     */
    public static int findMajorityElement(int[] arr) {
        // Phase 1: Find a candidate using Boyer-Moore voting
        int candidate = findCandidate(arr);

        // Phase 2: Verify the candidate actually has majority count
        if (isMajority(arr, candidate)) {
            return candidate;
        }
        return -1;
    }

    /**
     * Phase 1 — Identifies a candidate for the majority element.
     * <p>
     * Pairs up different elements and cancels them out.
     * The last surviving candidate is the potential majority element.
     *
     * @param arr the input array
     * @return the candidate element
     */
    private static int findCandidate(int[] arr) {
        int candidate = 0;
        int count = 0;

        for (int value : arr) {
            // When the count drops to 0, pick a new candidate
            if (count == 0) {
                candidate = value;
            }
            // Same as candidate → reinforce; different → cancel out
            count += (value == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * Phase 2 — Verifies whether the candidate actually appears more than ⌊n/2⌋ times.
     *
     * @param arr       the input array
     * @param candidate the candidate from Phase 1
     * @return true if the candidate is the majority element
     */
    private static boolean isMajority(int[] arr, int candidate) {
        int count = 0;
        for (int value : arr) {
            if (value == candidate) {
                count++;
            }
        }
        return count > arr.length / 2;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 1};
        System.out.println("The majority element is: " + findMajorityElement(arr));
    }
}
