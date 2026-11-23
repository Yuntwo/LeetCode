//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。 
//
// 你应当 保留 两个分区中每个节点的初始相对位置。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [2,1], x = 2
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 200] 内 
// -100 <= Node.val <= 100 
// -200 <= x <= 200 
// 
//
// Related Topics 链表 双指针 👍 993 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 这样还是想复杂了，直接两个dummy节点即可，只要dummy节点没有前驱指针就不会被算入链表中
// func partition(head *ListNode, x int) *ListNode {
// 	if head == nil {
// 	return nil
// 	}
// 	preHead := &ListNode{Next: head}
// 	preHeadPer := preHead
// 	// 其实甚至不需要单独找x的位置，就按照大于等于的情况插入就可以了
// 	for head != nil {
// 		if head.Val == x {
// 			break
// 		}
// 		preHead = preHead.Next
// 		head = head.Next
// 	}
// 	// 这样会导致lessTail和greaterTail本身可能不满足条件
// 	lessTail := preHead
// 	greaterTail := head.Next
//
// 	preNode := preHeadPer
// 	node := preHeadPer.Next
// 	for node != nil {
// 		if node.Val < x {
// 			newTail := lessTail.Next
// 			lessTail.Next = node
// 			preNode.Next = node.Next
// 			node.Next = newTail
// 			lessTail = node
// 		} else {
// 			newTail := greaterTail.Next
// 			greaterTail.Next = node
// 			preNode.Next = node.Next
// 			node.Next = newTail
// 			greaterTail = node
// 		}
// 		preNode = preNode.Next
// 		node = preNode.Next
// 	}
// 	return preHeadPer.Next
// }

func partition(head *ListNode, x int) *ListNode {
	if head == nil {
		return nil
	}
	preLessHead, preGreaterHead := &ListNode{}, &ListNode{}
	// 这里的初始化很重要
	lessTail, greaterTail := preLessHead, preGreaterHead
	for head != nil {
		if head.Val < x {
			lessTail.Next = head
			lessTail = head
        } else {
			greaterTail.Next = head
			greaterTail = head
		}
		head = head.Next
    }
	lessTail.Next = preGreaterHead.Next
	greaterTail.Next = nil
	return preLessHead.Next
}