package leetcode;

public class LeetCode2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);

        ListNode p1 = l1, p2 = l2, p3 = result;
        while (p2.next != null && p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1.next != null) {
            while (p1.next != null) {
                p2.next = new ListNode(0);
                p2 = p2.next;
                p1 = p1.next;
            }
        }
        if (p2.next != null) {
            while (p2.next != null) {
                p1.next = new ListNode(0);
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        int carry = 0;
        p1 = l1;
        p2 = l2;
        p3 = result;

        while (p1 != null) {
            int num = p1.val + p2.val + carry;
            carry = 0;
            if (num >= 10) {
                carry = 1;
                num = num % 10;
            }
            p3.next = new ListNode(num);
            p3 = p3.next;
            p1 = p1.next;
            p2 = p2.next;
        }

        if (carry == 1) {
            p3.next = new ListNode(1);
        }
        return result.next;
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