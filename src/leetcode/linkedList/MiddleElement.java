package leetcode.linkedList;

public class MiddleElement {


    public static int middle(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            //1. When length is odd, slow will point to the middle element
            //2. When length is even, slow will point to the second middle element
            slow = slow.next;
            // 1. When length is odd, fast will point to the last element
            // 2. When length is even, fast will point to null
            fast = fast.next.next;
        }

        return (slow != null) ? slow.data : Integer.MIN_VALUE;
    }

}
