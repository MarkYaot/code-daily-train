package leetcode;

public class LeetCode19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            length += 1;
        }

        current = head;
        if (n == length) {
            current = current.next;
            return current;
        }

        for (int i = 1; i <= length; i++) {
            if (i == length - n) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        return head;
    }

    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode() {
        }
    }
}