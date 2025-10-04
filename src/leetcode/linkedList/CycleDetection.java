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
