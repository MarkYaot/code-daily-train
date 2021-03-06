package main

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	l3 := &ListNode{}
	l3.Val = 0
	l3.Next = nil
	p := l3
	for l1 != nil && l2 != nil {
		if l1.Val < l2.Val {
			p.Next = l1
			l1 = l1.Next
		} else {
			p.Next = l2
			l2 = l2.Next
		}
		p = p.Next
	}

	if l1 != nil {
		p.Next = l1
	}
	if l2 != nil {
		p.Next = l2
	}
	return l3.Next
}
