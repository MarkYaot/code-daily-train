package org.czm.leetcode;

import org.czm.leetcode.structs.ListNode;

public class LeetCode2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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

    public static void main(String[] args) {
        //342 + 465 = 364
        ListNode p = new ListNode(2);
        p.next = new ListNode(4);
        p.next.next = new ListNode(3);

        ListNode q = new ListNode(5);
        q.next = new ListNode(6);
        q.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(p, q);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
