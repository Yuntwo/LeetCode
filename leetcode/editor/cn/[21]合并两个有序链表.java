//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表 👍 2279 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 注意特例首先判断
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l1 != null && l2 == null) {
            return l1;
        }
        // write code here
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = p1;
        ListNode temp = p1;
        // 注意这只是其中一种情况的后续讨论
        if (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                head = p1;
                p1 = p1.next;
                head.next = null;
            } else {
                head = p2;
                p2 = p2.next;
                head.next = null;
            }
        }
        // 注意尾插法要保留头结点
        ListNode resHead = head;
        while (p1 != null && p2 != null) {
            // 升序排升序采用尾插法
            if (p1.val < p2.val) {
                temp = p1.next;
                head.next = p1;
                head = p1;
                p1 = temp;
            } else {
                temp = p2.next;
                head.next = p2;
                head = p2;
                p2 = temp;
            }
        }
        if (p1 == null) {
            while (p2 != null) {
                temp = p2.next;
                head.next = p2;
                head = p2;
                p2 = temp;
            }
        } else {
            while (p1 != null) {
                temp = p1.next;
                head.next = p1;
                head = p1;
                p1 = temp;
            }
        }
        return resHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
