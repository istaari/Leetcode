package leetcode.linkedList;

public class ReverseLinkedList {

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = null; // points to the previous node
        Node curr = head; // points to the current node
        Node next = null; // points to the next node

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next; // At the end of the loop, curr will be null and prev will be the new head
        }

        return prev;
    }

    public static Node recursiveReverse(Node head) {
        // Base case: if the list is empty or has only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive step: reverse the rest of the list
        Node newHead = recursiveReverse(head.next);

        // Make adjustments to reverse the current pair of nodes
        head.next.next = head;
        head.next = null;

        // Return the new head of the reversed list
        return newHead;
    }

}
