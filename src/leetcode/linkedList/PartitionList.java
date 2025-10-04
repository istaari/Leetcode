package leetcode.linkedList;

/**
 * LeetCode Problem 86: Partition List
 *
 * Given the head of a linked list and a value x, partition it such that all
 * nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 *
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 */
public class PartitionList {


    public static ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode(-1);
        ListNode dummyLarge = new ListNode(-1);

        ListNode small = dummySmall;
        ListNode large = dummyLarge;

        // `current` will iterate through the original input list.
        ListNode current = head;

        while (current != null) {
            // Based on the node's value, we append it to the appropriate list.
            if (current.val < x) {
                // Add the current node to the end of the "small" list.
                small.next = current;
                small = small.next; // Move the small tail pointer forward.
            } else {
                // Add the current node to the end of the "large" list.
                large.next = current;
                large = large.next; // Move the large tail pointer forward.
            }
            // Move to the next node in the original list.
            current = current.next;
        }

        // Important: terminate the "large" list to avoid potential cycles.
        large.next = null;
        // Connect the "small" list with the "large" list.
        small.next = dummyLarge.next;

        // The head of our final partitioned list is `dummySmall.next`.
        return dummySmall.next;
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
        // Manually creating the linked list: 1 -> 4 -> 3 -> 2 -> 5 -> 2 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        int x = 3;

        System.out.println("Original list:");
        printList(head);

        ListNode partitionedHead = partition(head, x);

        System.out.println("Partitioned list (x = " + x + "):");
        printList(partitionedHead); // Expected: 1 -> 2 -> 2 -> 4 -> 3 -> 5 -> null
    }
}
