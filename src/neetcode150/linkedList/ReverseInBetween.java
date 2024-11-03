package neetcode150.linkedList;

public class ReverseInBetween {

    // https://leetcode.com/problems/reverse-linked-list-ii/

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int left = 2;
        int right = 4;

        System.out.println(reverseBetween(head, 2, 5));
    }


}
