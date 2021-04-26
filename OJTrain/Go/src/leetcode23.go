package main

func mergeKLists(lists []*ListNode) *ListNode {
	l := &ListNode{}
	l = nil
	for _, list := range lists {
		l = mergeTwoLists(l, list)
	}
	return l
}
