package neetcode150.linkedList;


public class SortList {

    public static ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode prev = null;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(slow);

        ListNode newHead = merge(l1, l2);

        return newHead;
    }


    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode dummyTail = dummyHead;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                dummyTail.next = l1;
                dummyTail = l1;
                l1 = l1.next;

            } else {
                dummyTail.next = l2;
                dummyTail = l2;
                l2 = l2.next;
            }

        }

        if (l1 != null) {
            dummyTail.next = l1;
        }

        if (l2 != null) {
            dummyTail.next = l2;
        }

        return dummyHead.next;
    }


    public static ListNode sortList(ListNode head) {
       return mergeSort(head);
    }

    public static void main(String[] args) {
        // Manually create the linked list for input: [4,2,1,3]
        ListNode head = new ListNode(4); // First node
        head.next = new ListNode(2);    // Second node
        head.next.next = new ListNode(1); // Third node
        head.next.next.next = new ListNode(3); // Fourth node

        ListNode sortedList = sortList(head);
        System.out.print(sortedList);
    }
}
