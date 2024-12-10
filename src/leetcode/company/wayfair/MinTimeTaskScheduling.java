package leetcode.company.wayfair;

import java.util.*;

public class MinTimeTaskScheduling {

    public static int getMinTime(List<Integer> taskMemory, List<Integer> taskType, int maxMemory) {
        Map<Integer, List<Integer>> taskGroup = new HashMap<>();
        int n = taskType.size();

        for (int i = 0; i < n; i++) {
            taskGroup.computeIfAbsent(taskType.get(i), k -> new ArrayList<>()).add(taskMemory.get(i));
        }

        int totalTime = 0;
        for (List<Integer> tasks : taskGroup.values()) {
            tasks.sort(Collections.reverseOrder());

            int time = 0;
            while (tasks.size() >= 2) {

                if (tasks.get(0) + tasks.get(1) <= maxMemory) {
                    time++;
                    tasks.removeFirst();
                    tasks.removeFirst();
                } else {
                    time++;
                    tasks.removeFirst();
                }
            }
            totalTime += time + tasks.size();
        }

        return totalTime;
    }


    public static void main(String[] args) {
        List<Integer> taskMemory = new ArrayList<>(List.of(1, 4, 5, 2, 3));
        List<Integer> taskType = new ArrayList<>(List.of(1, 2, 1, 3, 4));
        int maxMemory = 6;

        System.out.println(getMinTime(taskMemory, taskType, maxMemory));
    }

}
