package leetcode.linkedList;


/**
 * LeetCode Problem 328: Odd Even Linked List
 *
 * Given the head of a singly linked list, group all the nodes with odd indices
 * together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, the second node is even, and so on.
 *
 * Note that the relative order within both the odd and even groups should remain
 * as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Example 2:
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 */
public class OddEvenLinkedList {

    @SuppressWarnings("all")
    public ListNode oddEvenList(ListNode head) {
        // Handle edge cases where the list has 0, 1, or 2 nodes.
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddTail = oddHead;
        ListNode evenTail = evenHead;

        // We iterate through the rest of the list, starting from the third node.
        ListNode current = head.next.next;
        boolean isOdd = true; // The third node is an odd-indexed node.

        while (current != null) {
            if (isOdd) {
                // Append the current node to the end of the odd list.
                oddTail.next = current;
                oddTail = current;
            } else {
                // Append the current node to the end of the even list.
                evenTail.next = current;
                evenTail = current;
            }
            // Move to the next node and flip the flag.
            current = current.next;
            isOdd = !isOdd;
        }

        // After the loop, we need to terminate the even list properly.
        evenTail.next = null;
        // Link the tail of the odd list to the head of the even list.
        oddTail.next = evenHead;

        // The head of the reordered list is the original head (which is also oddHead).
        return oddHead;
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
        // Create a test list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original list:");
        printList(head);

        OddEvenLinkedList solution = new OddEvenLinkedList();
        ListNode reorderedHead = solution.oddEvenList(head);

        System.out.println("Reordered list:");
        printList(reorderedHead); // Expected: 1 -> 3 -> 5 -> 2 -> 4 -> null
    }

}
