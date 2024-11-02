package leetcode.linkedList;

public class PartitionList {

    public static ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode(-1);
        ListNode dummyLarge = new ListNode(-1);

        ListNode small = dummySmall;
        ListNode large = dummyLarge;

        ListNode current = head;

        while (current != null) {

            if (current.val < x) {
                small.next = current;
                small = current;

            } else {
                large.next = current;
                large = current;

            }

            current = current.next;
        }

        large.next = null;
        small.next = dummyLarge.next;

        return dummySmall.next;

    }

    public static void main(String[] args) {
        // Manually creating the linked list: 1 -> 4 -> 3 -> 2 -> 5 -> 2 -> null
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        // Linking the nodes
        node1.next = node2; // 1 -> 4
        node2.next = node3; // 4 -> 3
        node3.next = node4; // 3 -> 2
        node4.next = node5; // 2 -> 5
        node5.next = node6; // 5 -> 2

        // Define x
        int x = 3;

        System.out.println(partition(node1, x));
        System.out.println("wdqw");

    }


}

