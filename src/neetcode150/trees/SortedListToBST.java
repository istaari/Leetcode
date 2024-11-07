package neetcode150.trees;

import leetcode.linkedList.ListNode;

public class SortedListToBST {


    /*

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public static TreeNode toBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) return null;

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head, slow);
        thead.right = toBST(slow.next, tail);
        return thead;
    }

     */


    public static TreeNode sortedListToBST(ListNode head) {

        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null) {
            prev.next = null;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST((prev == null) ? null : head);
        root.right = sortedListToBST(slow.next);


        return root;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(-10);

        ListNode node1 = new ListNode(-3);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(9);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        sortedListToBST(head);
    }


}
