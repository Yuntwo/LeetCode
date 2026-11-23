//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 3358 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
// 这种写法对于n等于链表长度的情况，对于链表长度为n的情况没有前驱节点处理不了
// func removeNthFromEnd(head *ListNode, n int) *ListNode {
// 	if head.Next == nil && n == 1{
// 		return nil
//     }
// 	node := head
// 	for i := 0; i < n; i++ {
// 		node = node.Next
// 	}
//     preNode := head
// 	// 链表长度为n的情况会空指针异常
// 	for node.Next != nil {
// 		node = node.Next
// 		preNode = preNode.Next
//     }
// 	preNode.Next = preNode.Next.Next
// 	return head
// }
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	// 必须得有个dummy节点
	preNode := &ListNode{Next: head}
	preNodePer := preNode
	node := head
	for i := 0; i < n - 1; i++ {
		node = node.Next
	}
	for node.Next != nil {
		node = node.Next
		preNode = preNode.Next
    }
	preNode.Next = preNode.Next.Next
	return preNodePer.Next
}
//leetcode submit region end(Prohibit modification and deletion)
