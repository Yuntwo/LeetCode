//给定一个已排序的链表的头
// head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,1,2]
//输出：[1,2]
//
//
// 示例 2：
//
//
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序 排列
//
//
// Related Topics 链表 👍 961 👎 0
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
    public ListNode deleteDuplicates(ListNode head) {
        // 注意判null是一定要有的，不只是为了直接返回，而且是你后面要访问p.next那么你p一定要是null才行
        // 这是通用的
        if (head == null) {
            return head;
        }
        ListNode p = head;
        // 注意控制p.next不一定保证p不会为null，这种条件只能保证你在循环体中移动了一步
        // 如果在循环体中你的p移动了两步及以上的话，这个条件就不能保证p!=null了，那么你要另外判断p
        while (p.next != null) {
            // 注意这种情况下不应该移动p!!!
            // 这种逻辑移动也方便多了
            if (p.next.val == p.val) {
                p.next = p.next.next;
                continue;
            }
            p = p.next;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
