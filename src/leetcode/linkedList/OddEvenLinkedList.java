package leetcode.linkedList;

public class OddEvenLinkedList {


    public ListNode oddEvenList(ListNode head) {

        ListNode oddDummy = new ListNode(0);
        ListNode evenDummy = new ListNode(0);

        ListNode oddHead = oddDummy;
        ListNode evenHead = evenDummy;

        boolean isOdd = true;

        while (head != null) {

            if (isOdd) {
                oddHead.next = head;
                oddHead = head;

                isOdd = false;

            } else {
                evenHead.next = head;
                evenHead = head;

                isOdd = true;
            }

            head = head.next;
        }

        evenHead.next = null;
        oddHead.next = evenDummy.next;

        return oddDummy.next;
    }


}
