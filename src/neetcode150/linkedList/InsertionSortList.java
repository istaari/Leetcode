package neetcode150.linkedList;

public class InsertionSortList {

    public static ListNode insertionSortList(ListNode head) {

        if (head == null) return null;

        ListNode dummy = new ListNode(0);

        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            ListNode prev = dummy;

            while (prev.next != null && current.val > prev.next.val) {
                prev = prev.next;
            }

            current.next = prev.next;
            prev.next = current;

            current = next;
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        // Create nodes
        ListNode head = new ListNode(-1); // First node
        ListNode second = new ListNode(5);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(0);

        // Append nodes to form the linked list
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null; // End the list (no cycle for now)

        System.out.println(insertionSortList(head));
    }

}
