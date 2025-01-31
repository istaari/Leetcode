package leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

public class NumberOfBurgers {
    /*
     * Jumbo Burger: 4 tomato slices and 1 cheese slice.
     * Small Burger: 2 Tomato slices and 1 cheese slice.
     *
     * 4 * Jumbo Burger + 2 * Small Burger = tomatoSlices
     * 2 * Jumbo Burger + 1 * Small Burger = cheeseSlices
     *
     * 4x + 2y = tomatoSlices
     * 2x + y = cheeseSlices
     *
     * From the second equation:
     * y = cheeseSlices - 2x
     *
     * Substitute this into the first equation:
     * 4x + 2(cheeseSlices - 2x) = tomatoSlices
     *
     * Expand:
     * 4x + 2 * cheeseSlices - 4x = tomatoSlices
     *
     * Simplifies to:
     * 2 * cheeseSlices = tomatoSlices
     *
     * Solving for x:
     * 2x = tomatoSlices - 2 * cheeseSlices
     * x = (tomatoSlices - 2 * cheeseSlices) / 2
     *
     * Solving for y:
     * y = (4 * cheeseSlices - tomatoSlices) / 2
     *
     * Validity checks:
     * 1. Both x and y must be non-negative: x >= 0, y >= 0
     * 2. Both x and y must be integers (division must not result in a fraction)
     * 3. TomatoSlices must be even (since every burger requires an even number of tomatoes)
     *
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0)  // Tomato slices must be even
            return new ArrayList<>();

        int totalJumboBurger = (tomatoSlices - 2 * cheeseSlices) / 2;
        int totalSmallBurger = (4 * cheeseSlices - tomatoSlices) / 2;

        if (totalJumboBurger < 0 || totalSmallBurger < 0) // Burgers cannot be negative
            return new ArrayList<>();

        return List.of(totalJumboBurger, totalSmallBurger);
    }


}
