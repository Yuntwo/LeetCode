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
// Related Topics 栈 递归 链表 双指针 👍 143 👎 0

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
 * 没有用到双指针，虽然是麻烦了点，但是时间复杂度是O(4N)
 */
class Solution {
    public void reorderList(ListNode head) {
        ListNode headBackup = head;
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        n = (n + 1) / 2;
        ListNode subHead = head;
        for (int i = 0; i < n - 1; i++) {
            subHead = subHead.next;
        }
        // 注意这里要斩断两截，否则会成环
        ListNode temp = subHead.next;
        subHead.next = null;
        subHead = temp;
        while (subHead != null && subHead != tail) {
            ListNode toInsert = subHead;
            subHead = subHead.next;
            toInsert.next = tail.next;
            tail.next = toInsert;
        }
        while (subHead != null) {
            ListNode nextHead = head.next;
            ListNode nextSubHead = subHead.next;
            head.next = subHead;
            subHead.next = nextHead;
            head = nextHead;
            subHead = nextSubHead;
        }
        // 注意最后不能修改原head指针
        head = headBackup;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        solution.reorderList(head);
    }
}

/**
 * Failure2
 * 不好理解，双重while循环时间复杂度为O(n^2)，Time Limit Exceeded
 */
//class Solution {
//    public void reorderList(ListNode head) {
//        if (head == null || head.next == null) {
//            return;
//        }
//
//        ListNode insertAfter = head;
//        ListNode tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//        ListNode tailBefore;
//        while (insertAfter != tail && insertAfter.next != tail) {
//            tailBefore = insertAfter;
//            while (tailBefore.next != tail) {
//                tailBefore = tailBefore.next;
//            }
//            ListNode temp = insertAfter.next;
//            insertAfter.next = tail;
//            tail.next = temp;
//            insertAfter = temp;
//            // 避免成环
//            tailBefore.next = null;
//            // 不用找tail了，找tailBefore就行
//            tail = tailBefore;
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
//        solution.reorderList(head);
//    }
//}

/**
 * Failure1
 * 注意理解题意究竟怎么插的
 */
//class Solution {
//    public void reorderList(ListNode head) {
//        ListNode tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//        while (head.next != tail) {
//            ListNode toInsert = head.next;
//            head.next = head.next.next;
//            toInsert.next = tail.next;
//            tail.next = toInsert;
//        }
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
