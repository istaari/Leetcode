package leetcode.linkedList;

public class RemoveLinkedListElements {


    // https://leetcode.com/problems/remove-linked-list-elements/description/
    // Solved using dummy nodes
    // Can be solved by shrinking the node like curr.next = curr.next.next, before doing this first skip the matched value with node
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (head != null) {

            if (head.val != val) {
                tail.next = head;
                tail = head;
            }

            head = head.next;
        }

        tail.next = null;

        return dummy.next;
    }


    public static void main(String[] args) {
        // Manually creating the linked list: [1, 2, 6, 3, 4, 5, 6]
        // Create the head node
        ListNode head = new ListNode(1);
        // Create the rest of the nodes and link them
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        int val = 6;

        System.out.println(removeElements(head, val));
        System.out.println("Test");
    }

}
