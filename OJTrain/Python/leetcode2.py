class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        carry = 0
        l3 = ListNode()
        current = l3
        while l1 and l2:
            current.next = ListNode()
            current = current.next
            num = l1.val + l2.val + carry
            current.val = num % 10
            carry = int(num / 10)
            l1 = l1.next
            l2 = l2.next

        while l1:
            current.next = ListNode()
            current = current.next
            num = l1.val + carry
            current.val = num % 10
            carry = int(num / 10)
            l1 = l1.next

        while l2:
            current.next = ListNode()
            current = current.next
            num = l2.val + carry
            current.val = num % 10
            carry = int(num / 10)
            l2 = l2.next

        if carry > 0:
            current.next = ListNode()
            current = current.next
            current.val = 1
        return l3.next
