//给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：[2,1]
//
//
// 示例 3：
//
//
//输入：head = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围是 [0, 5000]
// -5000 <= Node.val <= 5000
//
//
//
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
//
//
//
//
// 注意：本题与主站 206 题相同： https://leetcode-cn.com/problems/reverse-linked-list/
//
// Related Topics 递归 链表 👍 125 👎 0

import java.util.List;

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
 * 迭代解法，时间效率已经很高了
 */
//class Solution {
//    public ListNode reverseList(ListNode head) {
//        ListNode h = null;
//        ListNode next = head;
//        while (next != null) {
//            ListNode temp = next.next;
//            next.next = h;
//            h = next;
//            next = temp;
//        }
//        return h;
//    }
//}
/**
 * Success2
 * 不知道递归写法是不是这样，但是感觉像个假递归，或许是太简单了吧，迭代就能解决就没必要用递归了
 * 这个递归思路的难点不在于直接用递归得到子结果，这个都是我们假设已经得到了，而在于如何利用返回的子结果进行上层处理
 */
//class Solution {
//    public ListNode reverseList(ListNode head) {
//        ListNode res = reverse(null, head);
//        return res;
//    }
//
//    private ListNode reverse(ListNode head, ListNode headNext) {
//        if (headNext == null) {
//            return head;
//        }
//        ListNode temp = headNext.next;
//        headNext.next = head;
//        // res都是一样的，但其实要逐层返回
//        ListNode res = reverse(headNext, temp);
//        return res;
//    }
//}

/**
 * Success3
 * 递归简化写法，你一定要明白在递归过程中哪些值实际上是在递归返回过程中改变的
 * 你在整体思考的时候要看做一个黑盒，但是在细节考虑的时候要明白递归返回过程，你得对子结果的结构进行设计定义/观察实际的情况
 * 确实是很妙
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // 注意head==null的情况
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = reverseList(head.next);
        // 这是实际在反转链表
        head.next.next = head;
        // 这里在子递归的头部会有一个环，让它暂时指向null，如果后续还有父递归的话自然会改变它的值，否则就是尾节点，直接就指向null了
        // 因为你只是知道个子递归结果的大概，具体有些细节结构你不知道或者说不定义清楚的话，你就没有办法处理
        head.next = null;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
