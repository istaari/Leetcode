package alogorithm.linkedList;

public class Reverse {

    public static Node reverse(Node node) {
        // Base cases
        if (node == null) return null;
        if (node.next == null) {
            // For last node, make it the new head
            node.prev = null;
            return node;
        }

        // Store the next node before we change any pointers
        Node nextNode = node.next;

        // Recursively reverse the rest of the list
        Node newHead = reverse(nextNode);

        nextNode.next = node;
        node.prev = nextNode;
        node.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        // Create two nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        // Connect the nodes
        node1.next = node2;
        node2.prev = node1;

        System.out.println(reverse(node1));

    }


}
