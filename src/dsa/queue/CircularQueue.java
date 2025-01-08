package dsa.queue;

public class CircularQueue {

    private final int[] queue;
    private final int capacity;
    private int head;
    private int tail;
    private int size;

    public CircularQueue(int k) {
        capacity = k;
        queue = new int[capacity];
        head = -1;
        tail = -1;
        size = 0;
    }

    //Adding element in circular queue
    public boolean add(int value) {
        if (isFull()) {
            return false;
        }

        if (isEmpty()) {
            head = 0;
        }

        tail = (tail + 1) % capacity;
        queue[tail] = value;
        size++;
        return true;
    }

    //Removing element from circular queue
    public boolean remove() {
        if (isEmpty()) {
            return false;
        }
        // when there is only one element in the queue, reset the pointers
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % capacity;
        }

        size--;
        return true;
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(2);
        System.out.println(circularQueue.add(1)); // return true
        System.out.println(circularQueue.add(2)); // return true
        System.out.println(circularQueue.add(3)); // return false
        System.out.println(circularQueue.peek()); // return 1
        System.out.println(circularQueue.remove()); // return true
        System.out.println(circularQueue.peek());
    }

}




