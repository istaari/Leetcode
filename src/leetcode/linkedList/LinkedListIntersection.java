package leetcode.linkedList;


public class LinkedListIntersection {

    // https://leetcode.com/problems/intersection-of-two-linked-lists/description/
    // Exchange the headA and headB to account for the length difference
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode A = headA;
        ListNode B = headB;

        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }

        return A;
    }


    public static void main(String[] args) {

        // Creating the intersecting part of the list [8 -> 4 -> 5]
        ListNode intersectingNode = new ListNode(8);
        intersectingNode.next = new ListNode(4);
        intersectingNode.next.next = new ListNode(5);

        // Creating List A: [4 -> 1 -> 8 -> 4 -> 5]
        ListNode listA = new ListNode(4);
        listA.next = new ListNode(1);
        listA.next.next = intersectingNode;  // Points to the intersection at node 8

        // Creating List B: [5 -> 6 -> 1 -> 8 -> 4 -> 5]
        ListNode listB = new ListNode(5);
        listB.next = new ListNode(6);
        listB.next.next = new ListNode(1);
        listB.next.next.next = intersectingNode;  // Points to the intersection at node 8


        System.out.println(getIntersectionNode(listA, listB));
    }

}
