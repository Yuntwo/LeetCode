//给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//
// L0 → L1 → … → Ln-1 → Ln 请将其重新排列后变为：
//
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例 1:
//
//
//
//
//输入: head = [1,2,3,4]
//输出: [1,4,2,3]
//
// 示例 2:
//
//
//
//
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3]
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
//
//
//
// 注意：本题与主站 143 题相同：https://leetcode-cn.com/problems/reorder-list/
//
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
// Related Topics 栈 递归 链表 双指针 👍 118 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 效率很低
 * 感觉需要考虑到链表长度是奇数还是偶数，行为有些不同
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode insertAfter = head;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode tailBefore;
        // 注意第一个是偶数长度链表的条件，第二个是奇数长度链表的条件，必须分类判断
        // 奇偶长度不同只是判断条件不同，核心逻辑与长度的奇偶性无关，这就还比较简单了
        while (insertAfter.next != tail && insertAfter != tail) {
            tailBefore = insertAfter;
            while (tailBefore.next != tail) {
                tailBefore = tailBefore.next;
            }
            ListNode temp = insertAfter.next;
            insertAfter.next = tail;
            tail.next = temp;
            insertAfter = temp;
            // 避免成环
            tailBefore.next = null;
            // 不用找tail了，找tailBefore就行
            tail = tailBefore;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        solution.reorderList(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
