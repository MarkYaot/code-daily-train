package main

type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	l3 := &ListNode{}
	l3.Next = nil
	l3.Val = 0
	current := l3
	carry := 0

	for l1 != nil && l2 != nil {
		node := ListNode{}
		node.Next = nil
		node.Val = 0
		current.Next = &node
		current = current.Next
		sum := l1.Val + l2.Val + carry
		current.Val = sum % 10
		carry = sum / 10
		l1 = l1.Next
		l2 = l2.Next
	}

	for l1 != nil {
		node := ListNode{}
		node.Next = nil
		node.Val = 0
		current.Next = &node
		current = current.Next
		sum := l1.Val + carry
		current.Val = sum / 10
		carry = sum % 10
		l1 = l1.Next
	}

	for l2 != nil {
		node := ListNode{}
		node.Next = nil
		node.Val = 0
		current.Next = &node
		current = current.Next
		sum := l2.Val + carry
		current.Val = sum / 10
		carry = sum % 10
		l2 = l2.Next
	}

	if carry > 0 {
		current.Next = &ListNode{}
		current = current.Next
		current.Val = 1
	}
	return l3.Next
}
