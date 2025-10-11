package leetcode.stack.monotonic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CarFleet {

    static class Car implements Comparable<Car> {
        int pos;
        int speed;

        public Car(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }

        @Override
        public int compareTo(Car other) {
            // Sort cars based on their starting position in ascending order.
            return Integer.compare(this.pos, other.pos);
        }
    }

    /**
     * There are `n` cars going to the same destination along a one-lane road. The destination is `target` miles away.
     * You are given two integer arrays `position` and `speed`, both of length `n`.
     * A car fleet is a non-empty set of cars driving at the same position and same speed.
     * A car can catch up to another car ahead of it, but it cannot pass it. Once they meet, they drive together
     * at the speed of the slower car.
     * <p>
     * Return the number of car fleets that will arrive at the destination.
     *
     * @param target The distance to the destination.
     * @param position An array of initial positions for each car.
     * @param speed An array of speeds for each car.
     * @return The total number of car fleets that will arrive.
     * @example For target = 12, position = {10, 8, 0, 5, 3}, speed = {2, 4, 1, 1, 3}:
     * - The car at 10 (speed 2) arrives in (12-10)/2 = 1 hour.
     * - The car at 8 (speed 4) arrives in (12-8)/4 = 1 hour. These two form a fleet.
     * - The car at 5 (speed 1) arrives in (12-5)/1 = 7 hours. This is a new fleet.
     * - The car at 3 (speed 3) arrives in (12-3)/3 = 3 hours. It catches the fleet at position 5.
     * - The car at 0 (speed 1) arrives in (12-0)/1 = 12 hours. This is a final fleet.
     * - Result: 3 fleets.
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) {
            return 0;
        }

        // Step 1: Combine position and speed into a list of Car objects.
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cars.add(new Car(position[i], speed[i]));
        }

        // Step 2: Sort the cars by their starting position.
        // This allows us to process them in the order they appear on the road.
        Collections.sort(cars);

        // A stack to store the arrival times of the fleets.
        // The stack will maintain the arrival times of fleets in increasing order from bottom to top.
        Stack<Float> stack = new Stack<>();

        // Step 3: Iterate through the cars from closest to the start line to closest to the target.
        for (Car car : cars) {
            // Calculate the time it would take this car to reach the target if it were alone.
            float time = (float) (target - car.pos) / car.speed;

            // Step 4: Check if this car forms a new fleet or joins an existing one.
            while (!stack.isEmpty() && stack.peek() <= time) {
                stack.pop();
            }

            // After merging with any possible fleets ahead, the current car's arrival time represents
            // the new fleet leader's time. Push it onto the stack.
            stack.push(time);
        }

        // The final size of the stack is the number of distinct fleets.
        return stack.size();
    }

    /**
     * Main method for testing the carFleet function.
     */
    public static void main(String[] args) {
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};

        // Expected output: 3
        System.out.println(new CarFleet().carFleet(target, position, speed));
    }
}