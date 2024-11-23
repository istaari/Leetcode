package leetcode.sampling;

import leetcode.linkedList.ListNode;

import java.util.Random;

public class LinkedListRandomNode {

    ListNode head;
    Random random;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int index = 1;
        int result = -1;
        ListNode current = head;

        while (current != null) {

            if (random.nextInt(index) == 0) {
                result = current.val;
            }

            index++;
            current = current.next;
        }

        return result;
    }


}
