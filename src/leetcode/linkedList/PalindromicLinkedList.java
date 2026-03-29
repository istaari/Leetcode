package leetcode.linkedList;

/**
 * LeetCode Problem 234: Palindrome Linked List
 *
 * Given the head of a singly linked list, return true if it is a palindrome
 * or false otherwise.
 *
 * A palindrome is a sequence that reads the same forwards and backwards.
 *
 * Can you do it in O(n) time and O(1) space?
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 */
public class PalindromicLinkedList {


    public static boolean isPalindrome(Node head) {
        // Edge case: an empty or single-node list is a palindrome.
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle of the linked list.
        // This loop places `slow` at the middle (odd) or upper-middle (even) node.
        Node slow = head;
        Node fast = head;
        Node prev_of_slow = head; // We need a pointer to the node before slow to sever the list.

        while (fast != null && fast.next != null) {
            prev_of_slow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }


        // After the loop:
        // - For odd lists, `fast` is at the last node. `slow` is at the middle.
        // - For even lists, `fast` is null. `slow` is at the start of the second half.

        Node secondHalfHead;

        // Step 2: Sever the first half and identify the head of the second half.
        if (fast != null) { // Odd number of nodes, `slow` is the middle node.
            secondHalfHead = slow.next; // The second half starts after the middle.
            slow.next = null; // The middle node has no next in the first half.
        } else { // Even number of nodes, `slow` is the start of the second half.
            secondHalfHead = slow;
            prev_of_slow.next = null; // Sever the link from the first half.
        }

        // Step 3: Reverse the second half of the linked list.
        secondHalfHead = reverseList(secondHalfHead);

        // Step 4: Compare the first half with the reversed second half.
        Node firstHalfPointer = head;
        Node secondHalfPointer = secondHalfHead;
        boolean isPalindrome = true;

        while (firstHalfPointer != null && secondHalfPointer != null) {
            if (firstHalfPointer.data != secondHalfPointer.data) {
                isPalindrome = false;
                break;
            }
            firstHalfPointer = firstHalfPointer.next;
            secondHalfPointer = secondHalfPointer.next;
        }

        return isPalindrome;
    }

    private static Node reverseList(Node head) {
        Node previous = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    // Helper method to print the list for verification
    public static void printList(Node head) {
        System.out.print("List: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        // Test Case 1: Palindrome with even nodes
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(2);
        head1.next.next.next = new Node(1);
        System.out.println("Testing list:");
        printList(head1);
        System.out.println("Is Palindrome? " + isPalindrome(head1)); // Expected: true

        // Test Case 2: Palindrome with odd nodes
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(2);
        head2.next.next.next.next = new Node(1);
        System.out.println("\nTesting list:");
        printList(head2);
        System.out.println("Is Palindrome? " + isPalindrome(head2)); // Expected: true

        // Test Case 3: Not a palindrome
        Node head3 = new Node(1);
        head3.next = new Node(2);
        System.out.println("\nTesting list:");
        printList(head3);
        System.out.println("Is Palindrome? " + isPalindrome(head3)); // Expected: false
    }
}

