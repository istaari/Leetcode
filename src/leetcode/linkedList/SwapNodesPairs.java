package leetcode.linkedList;

/**
 * LeetCode Problem 24: Swap Nodes in Pairs
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 */
public class SwapNodesPairs {

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // `prevNode` points to the node *before* the pair we are about to swap.
        // Initially, this is the dummy node.
        ListNode prevNode = dummy;

        while (head != null && head.next != null) {
            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // --- The Swap ---
            // 1. The node before the pair (`prevNode`) should now point to the second node.
            prevNode.next = secondNode;

            // 2. The first node's `next` should point to the node *after* the second node.
            firstNode.next = secondNode.next;

            // 3. The second node's `next` should point back to the first node.
            secondNode.next = firstNode;

            // --- Prepare for the Next Iteration ---
            // The `prevNode` for the next pair is now the `firstNode` of the current pair.
            prevNode = firstNode;

            // The `head` for the next pair is the node that was originally after the second node.
            head = firstNode.next;
        }

        // The new head of the list is the node right after the dummy node.
        return dummy.next;
    }


    // Helper method to print the list for verification
    public static void printList(ListNode head) {
        System.out.print("List: ");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        // Creating the linked list: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println("Original list:");
        printList(head);

        ListNode swappedHead = swapPairs(head);

        System.out.println("List after swapping pairs:");
        printList(swappedHead); // Expected: 2 -> 1 -> 4 -> 3 -> null
    }
}

