package leetcode.linkedList;

import java.util.Objects;

/**
 * LeetCode Problem 83: Remove Duplicates from Sorted List
 *
 * Given the head of a sorted linked list, delete all duplicates such that each
 * element appears only once. Return the linked list sorted as well.
 *
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 */
public class RemoveDuplicates {

    public static Node deleteDuplicates(Node head) {
        // A sentinel (or dummy) node is used to simplify the logic of building
        // the new list. We don't need to handle the head as a special case.
        Node dummy = new Node(Integer.MIN_VALUE);

        Node curr = dummy;
        Node temp = head;

        while (temp != null) {

            if (curr.data != temp.data) {
                curr.next = temp;
                curr = curr.next;
            }

            temp = temp.next;
        }

        // It's crucial to terminate the list. The last unique node might
        // have originally pointed to a duplicate. We must sever that link.
        curr.next = null;

        return dummy.next;
    }
}
