//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// 
//L0 → L1 → … → Ln - 1 → Ln
// 
//
// 请将其重新排列后变为： 
//
// 
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
//
// Related Topics 栈 递归 链表 双指针 👍 1701 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reorderList(head *ListNode)  {
	// 这个判断是必须的，避免空指针异常，且无需处理
	if head == nil || head.Next == nil  {
		return
	}
	fast, slow := head, head
	// 这样才能在偶数个节点时，slow指向前半段末尾，奇数个节点时，slow指向中间节点
	// 也可以理解为fast要走的下一个节点不能为nil且fast.Next不能有空指针异常
	for fast.Next != nil && fast.Next.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next
    }
	// 最终slow会始终指向后半段的pre
	// 反转链表
	// 不用头插法，直接用迭代法，因为这里不连接，把前后半段要断开
	// for tail := slow.Next; tail.Next != nil; tail = tail.Next {
	// 	newTail := tail.Next
	// 	tail.Next = slow.Next
	// 	slow.Next = tail
	// 	tail = newTail
    // }
	second := slow.Next
	slow.Next = nil

	var pre *ListNode
	for second != nil {
		newSecond := second.Next
		second.Next = pre
		pre = second
		second = newSecond
	}

	first, second := head, pre
	// 判断second就够了
	// 合并就是把second插入到first后面
	// first, second是待合并的节点，不是两条子链表的头节点
	for second != nil {
		newFirst := first.Next
		first.Next = second
		newSecond := second.Next
		// 因为是要把second插到first后面，所以second.Next的指针必须指回first，否则就会成鱼骨状
		second.Next = newFirst
		first = newFirst
		second = newSecond
	}
}
//leetcode submit region end(Prohibit modification and deletion)
