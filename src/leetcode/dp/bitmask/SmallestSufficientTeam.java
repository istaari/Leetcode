package leetcode.dp.bitmask;

import java.util.*;

public class SmallestSufficientTeam {

    /**
     * STATE: dp[mask] = The smallest list of people indices covering these skills.
     * BASE CASE: dp[0] = empty list (no skills, no people).
     * TRANSITION: If (existing team + this person) is smaller than current
     * dp[nextMask], update.
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int nStates = 1 << n;

        // Map the req_skills to integer
        Map<String, Integer> skillsMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillsMap.put(req_skills[i], i);
        }
      
        
        
    }

    public static void main(String[] args) {
        SmallestSufficientTeam solver = new SmallestSufficientTeam();
        String[] skills = { "java", "nodejs", "reactjs" };
        List<List<String>> people = Arrays.asList(
                Arrays.asList("java"),
                Arrays.asList("nodejs"),
                Arrays.asList("nodejs", "reactjs"));
        System.out.println("Smallest Team Indices: " + Arrays.toString(solver.smallestSufficientTeam(skills, people)));
    }
}
