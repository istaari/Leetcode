package alogorithm.linkedList;

import java.util.NoSuchElementException;


@SuppressWarnings("all")
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    public int size;


    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adding a new node at the beginning of the list
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Adding a new node at the end of the list
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Remove the first node of the list
    public int removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        int removedData = head.data;
        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return removedData;
    }

    // Remove the last node of the list
    public int removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        int removedData = tail.data;
        if (tail.prev == null) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return removedData;
    }

    public int removeAtPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }

        if (position == 0) {
            return removeFirst();
        }

        if (position == size - 1) {
            return removeLast();
        }

        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        int removedData = current.data;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;

        return removedData;
    }



    public static void main(String[] args){
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addFirst(1);
        dll.addFirst(2);
        dll.addLast(3);
        dll.addLast(4);
        dll.removeFirst();
        dll.removeLast();
        dll.removeAtPosition(1);
        System.out.println(dll.size);
    }

}

