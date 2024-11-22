package leetcode.math;


// BFS Solution : https://leetcode.com/problems/water-and-jug-problem/solutions/83716/java-programmatic-solution-bfs-without-gcd/

public class WaterJugProblem {

    public static int GCD(int a, int b) {
        if (b == 0) return a;

        return GCD(b, a % b);
    }

    public boolean canMeasureWater(int x, int y, int target) {
        if (target > x + y ) return false;

        return target % GCD(x, y) == 0;
    }


}
