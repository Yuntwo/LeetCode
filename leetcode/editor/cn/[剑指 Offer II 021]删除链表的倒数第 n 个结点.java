//给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
//
//
// 示例 1：
//
//
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
// 进阶：能尝试使用一趟扫描实现吗？
//
//
//
//
// 注意：本题与主站 19 题相同： https://leetcode-cn.com/problems/remove-nth-node-from-end-
//of-list/
//
// Related Topics 链表 双指针 👍 77 👎 0

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
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

/**
 * 注意如果删除的是头结点，返回的头结点就变了
 */

/**
 * Success1
 * 一次扫描，时间效率最高
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 注意这个只有一个节点删完的特殊情况返回null
        if (head.next == null && n == 1) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 1; i < n; i++) {
            p2 = p2.next;
        }
        // 注意这个删除的是头结点返回新的头结点的特殊情况
        if (p2.next == null) {
            return p1.next;
        }
        while (p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
