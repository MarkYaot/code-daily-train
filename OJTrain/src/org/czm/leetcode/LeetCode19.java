package org.czm.leetcode;


import org.czm.leetcode.structs.ListNode;

public class LeetCode19 {
    public static void main(String[] args) {
        //test(10);
        test(1);
    }

    private static void test(int n) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = 1; i < n; i++) {
            p.next = new ListNode(i, null);
            p = p.next;
        }
        head = removeNthFromEnd(head, 1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
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
}
