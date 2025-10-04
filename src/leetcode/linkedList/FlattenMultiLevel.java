package leetcode.linkedList;

/**
 * LeetCode Problem 430: Flatten a Multilevel Doubly Linked List
 *
 * You are given a doubly linked list, which contains nodes that have a next pointer,
 * a previous pointer, and an additional child pointer. This child pointer may or may
 * not point to a separate doubly linked list, also containing these special nodes.
 * These child lists may have one or more children of their own, and so on, to
 * produce a multilevel data structure.
 *
 * Given the head of the first level of the list, flatten the list so that all
 * the nodes appear in a single-level, doubly linked list. The nodes should be ordered
 * according to a preorder traversal of the multilevel structure.
 *
 * After flattening, the child pointers should all be set to null.
 *
 * Example:
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 *
 * Conceptual Structure:
 * 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL
 *
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 */

public class FlattenMultiLevel {

    // Definition for a Node.
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        // Constructor for easier testing
        public Node(int _val) {
            this.val = _val;
        }
    }

    private Node flattenAndConnect(Node head, Node rest) {
        // Base Case: If the current list is empty, the result is just the rest of the list.
        if (head == null) {
            return rest;
        }

        // 1. (Post-order) First, recursively flatten the `head.next` list.
        // This call establishes the link between the flattened `next` list and the `rest`.
        Node flattenedNext = flattenAndConnect(head.next, rest);

        // 2. (Post-order) Then, recursively flatten the `head.child` list.
        // The key is that we pass `flattenedNext` as the "rest" for the child list.
        // This connects the tail of the flattened child list to the head of the flattened next list.
        Node flattenedChild = flattenAndConnect(head.child, flattenedNext);

        // 3. Now, connect the current `head` to the start of its processed child list.
        head.next = flattenedChild;
        if (flattenedChild != null) {
            flattenedChild.prev = head; // Set the crucial back-pointer
        }

        // 4. Clear the child pointer as required by the problem.
        head.child = null;

        return head;
    }

    @SuppressWarnings("all")
    Node flatten(Node head) {
        flattenAndConnect(head, null);
        return head;
    }

    public static void main(String[] args) {
        // Building the example list is complex, let's build a simpler one:
        // 1 -- 2 -- 3 -- 4
        //      |
        //      5 -- 6
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;

        head.next.child = new Node(5);
        head.next.child.next = new Node(6);
        head.next.child.next.prev = head.next.child;

        FlattenMultiLevel solution = new FlattenMultiLevel();
        solution.flatten(head);

        System.out.println("Flattened:");
        Node current = head;
        while (current != null) {
            System.out.print(current.val + " <-> ");
            current = current.next;
        }

        System.out.println("null");
    }

}

