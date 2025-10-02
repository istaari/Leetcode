package leetcode.linkedList;

public class FlattenMultiLevel {

    // Definition for a Node.
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }


    Node flatten(Node head, Node tail) {
        if (head == null) return tail;

        head.next = flatten(head.child, flatten(head.next, tail));

        if (head.next.prev != null) {
            head.next.prev = head;
        }

        head.child = null;
        return head;
    }

    Node flatten(Node head) {
        return flatten(head, null);
    }

}
