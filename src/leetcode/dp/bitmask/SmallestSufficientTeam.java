package leetcode.dp.bitmask;

import java.util.*;

/**
 * 1125. Smallest Sufficient Team
 * https://leetcode.com/problems/smallest-sufficient-team/
 *
 * In a project, you have a list of required skills req_skills, and a list of people.
 * The i-th person people[i] contains a list of skills that person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill,
 * there is at least one person in the team who has that skill.
 * Return any sufficient team of the smallest possible size.
 *
 * Example 1:
 *   Input: req_skills = ["java","nodejs","reactjs"],
 *          people = [["java"],["nodejs"],["nodejs","reactjs"]]
 *   Output: [0,2]
 *
 * Example 2:
 *   Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"],
 *          people = [["algorithms","math","java"],["algorithms","math","reactjs"],
 *                    ["java","csharp","aws"],["reactjs","csharp"],
 *                    ["csharp","math"],["aws","java"]]
 *   Output: [1,2]
 *
 * Constraints:
 *   1 <= req_skills.length <= 16
 *   1 <= people.length <= 60
 *   1 <= people[i].length <= 16
 *
 * ---
 * Approach: Bitmask DP
 *
 * Since req_skills.length <= 16, we represent each skill set as a bitmask.
 * dp[mask] = smallest list of people whose combined skills equal this mask.
 *
 * For each person, we iterate over all existing reachable masks and try
 * combining the person's skill mask. If the combined mask yields a smaller
 * team than currently known, we update it.
 *
 * Time:  O(2^n * p) where n = number of skills, p = number of people.
 * Space: O(2^n)     for the dp map.
 */
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
