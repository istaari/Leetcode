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

        // Map the req_skills to integer
        Map<String, Integer> skillsMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillsMap.put(req_skills[i], i);
        }

        Map<Integer, List<Integer>> dp = new HashMap<>();
        dp.put(0, new ArrayList<>());

        for (int i = 0; i < people.size(); i++) {

            int peopleSkillMask = 0;
            List<String> curr = people.get(i);
            for (int j = 0; j < curr.size(); i++) {
                String currentSkill = curr.get(j);
                peopleSkillMask |= (1 << skillsMap.get(currentSkill));
            }

            if (peopleSkillMask == 0) {
                continue;
            }

            for (Map.Entry<Integer, List<Integer>> entry : new HashMap<>(dp).entrySet()) {
                int combinedMask = entry.getKey() | peopleSkillMask;

                // If we found a new skill combination OR a shorter path to an existing
                // combination
                if (!dp.containsKey(combinedMask) || dp.get(combinedMask).size() > entry.getValue().size() + 1) {
                    List<Integer> newTeam = new ArrayList<>(entry.getValue());
                    newTeam.add(i);
                    dp.put(combinedMask, newTeam);
                }
            }
        }

        // The answer is the team associated with the "all bits set" mask
        List<Integer> resultList = dp.get((1 << n) - 1);
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
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
