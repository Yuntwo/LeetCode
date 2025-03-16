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
// Related Topics 链表 双指针 👍 2949 👎 0

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
 * Success1
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 注意因为要找的是倒数n+1个结点，所以如果刚好是删除的头结点就会出现访问空的情况，所以需要构造虚拟的dummy结点
        // 但这并不影响快慢指针的相对步差
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        ListNode res = dummy;
        // 其实就是找到倒数n+1个结点，所以先走n+1步，注意倒数第几个就是先走那么多到null，算的是差值
        for (int i = 0; i <= n; i++) {
            cur = cur.next;
        }
        while (cur != null) {
            cur = cur.next;
            res = res.next;
        }
        res.next = res.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        solution.removeNthFromEnd(head, 2);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
