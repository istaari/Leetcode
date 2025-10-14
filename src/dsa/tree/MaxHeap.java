package dsa.tree;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    public final List<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int leftChild(int i) {
        return i * 2 + 1;
    }

    public int rightChild(int i) {
        return i * 2 + 2;
    }


    // Insert Operation
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // After insert heapify up
    public void heapifyUp(int i) {
        while (i > 0 && heap.get(i) > heap.get(parent(i))) {

            // Swap child with parent
            int temp = heap.get(i);
            heap.set(i, heap.get(parent(i)));
            heap.set(parent(i), temp);

            // heapify up next node
            i = parent(i);
        }
    }

    // Extract a max element from heap
    public int extractMax() {
        if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");

        int max = heap.get(0);
        int last = heap.get(heap.size() - 1);

        heap.set(0, last);
        heapifyDown(0);

        return max;
    }


    // After extracting max heapify down
    public void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < heap.size() && heap.get(left) > heap.get(largest)) {
            largest = left;
        }

        if (right < heap.size() && heap.get(right) > heap.get(largest)) {
            largest = right;
        }

        if (i != largest) {
            // Swap child with parent
            int temp = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, temp);

            heapifyDown(largest);
        }
    }


    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(30);
        maxHeap.insert(10);
        maxHeap.insert(15);

        System.out.println("Heap: " + maxHeap.heap);
        System.out.println("Max: " + maxHeap.extractMax());
        System.out.println("Max: " + maxHeap.extractMax());
        System.out.println("Max: " + maxHeap.extractMax());
        System.out.println("Heap: " + maxHeap.heap);
    }

}
