package main

func swapPairs(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	if head.Next == nil {
		return head
	}
	fir := &ListNode{}
	sec := &ListNode{}
	p1 := fir
	p2 := sec
	cur := head
	num := 1

	for cur != nil {
		node := &ListNode{}
		node.Val = cur.Val
		node.Next = nil
		if num%2 == 1 {
			p1.Next = node
			p1 = p1.Next
		} else {
			p2.Next = node
			p2 = p2.Next
		}
		num += 1
		cur = cur.Next
	}

	result := &ListNode{}
	p1 = fir.Next
	p2 = sec.Next
	p := result
	for {
		if p2 == nil {
			return result.Next
		}
		p.Next = p2
		p = p.Next
		p2 = p2.Next

		if p1 == nil {
			continue
		}
		p.Next = p1
		p = p.Next
		p1 = p1.Next
	}
}
