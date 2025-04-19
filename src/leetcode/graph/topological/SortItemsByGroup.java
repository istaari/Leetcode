package leetcode.graph.topological;

import java.util.*;

// Step 1: Assign unique group IDs to ungrouped items
// Step 2: Build item-level and group-level graphs
// Step 3: Topological sort groups
// Step 4: For each group, topological sort its items
public class SortItemsByGroup {
    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
         return new int[0];
    }

    public static void main(String[] args){
       int n = 8;
       int m = 2;
       int[] group = new int[]{ -1, -1, 1, 0, 0, 1, 0, -1 };
       List<List<Integer>> beforeItems = new ArrayList<>();
       beforeItems.add(new ArrayList<>()); // []
       beforeItems.add(Arrays.asList(6));  // [6]
       beforeItems.add(Arrays.asList(5));  // [5]
       beforeItems.add(Arrays.asList(6));  // [6]
       beforeItems.add(Arrays.asList(3, 6)); // [3, 6]
       beforeItems.add(new ArrayList<>()); // []
       beforeItems.add(new ArrayList<>()); // []
       beforeItems.add(new ArrayList<>()); // [] 

       System.out.println(sortItems(n, m, group, beforeItems)); 
    }
}