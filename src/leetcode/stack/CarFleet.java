package leetcode.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CarFleet {

    class Car implements Comparable<Car> {

        int pos;
        int speed;

        public Car(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }

        @Override
        public int compareTo(Car car) {
            return Integer.compare(this.pos, car.pos);
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {

        List<Car> cars = new ArrayList<>();
        int n = position.length;

        for (int i = 0; i < n; i++) {
            cars.add(new Car(position[i], speed[i]));
        }

        Collections.sort(cars);
        Stack<Float> stack = new Stack<>();

        for (Car car : cars) {
            float time = (float) (target - car.pos) / car.speed;

            while (!stack.isEmpty() && stack.peek() <= time) {
                stack.pop();
            }

            stack.push(time);
        }

        return stack.size();
    }


    public static void main(String[] args) {
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};


       System.out.println(new CarFleet().carFleet(target, position, speed));
    }

}
