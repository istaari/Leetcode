package leetcode.linkedList;

public class CycleDetection {

    public static Boolean cycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // If slow and fast meet at the same node, there is a cycle
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    // Return the node where the cycle begins. If there is no cycle, return null.
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }

        if (fast == null || fast.next == null) return null; // No cycle

        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


    public static void main(String[] args) {
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node2;
        System.out.println("cycle  : " + cycle(node1));
    }
}
