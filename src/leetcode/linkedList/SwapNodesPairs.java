package leetcode.linkedList;

public class SwapNodesPairs {

    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode prev = head;
        ListNode curr = head.next;

        ListNode dummy = new ListNode(0);
        ListNode dummyHead = dummy;

        while (prev != null && curr != null) {

            prev.next = curr.next;
            curr.next = prev;

            dummyHead.next = curr;
            dummyHead = prev;

            prev = prev.next;
            if (prev != null) {
                curr = prev.next;
            }
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        // Creating each node
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println(swapPairs(head));

    }


}
