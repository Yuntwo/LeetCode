//给你一个链表的头节点 head ，判断链表中是否有环。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
//
// 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
// 示例 3：
//
//
//
//
//输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围是 [0, 10⁴]
// -10⁵ <= Node.val <= 10⁵
// pos 为 -1 或者链表中的一个 有效索引 。
//
//
//
//
// 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
// Related Topics 哈希表 链表 双指针 👍 1474 👎 0

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */

/**
 * Review1
 */
class Solution {
    public boolean hasCycle(ListNode head) {
        // 注意边界条件一定要有
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        // 注意涉及到next.next的判断一定要先判断next
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                // 注意有环返回true，没环返回false
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(3);
        ListNode p = head;
        p.next = new ListNode(2);
        ListNode rear = p.next;
        p = p.next;
        p.next = new ListNode(0);
        p = p.next;
        p.next = new ListNode(-4);
        p = p.next;
        p.next = rear;
        boolean res = solution.hasCycle(head);
        System.out.println(res);
    }
}

/**
 * Success1
 */
//class Solution {
//    public boolean hasCycle(ListNode head) {
//        if (head == null || head.next == null) {
//            return false;
//        }
//        // 注意在初始化这里就要差异化地走
//        ListNode p1 = head;
//        ListNode p2 = head.next;
//        // 注意这里的判断null条件不一样，p2要判断两个，否则会有为null的情况则无法访问
//        while (p1 != null && p2 != null && p2.next != null) {
//            if (p1 == p2) {
//                return true;
//            }
//            p1 = p1.next;
//            p2 = p2.next.next;
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        TestSolution solution = new TestSolution();
//        ListNode head = new ListNode(3);
//        ListNode p = head;
//        p.next = new ListNode(2);
//        ListNode rear = p.next;
//        p = p.next;
//        p.next = new ListNode(0);
//        p = p.next;
//        p.next = new ListNode(-4);
//        p = p.next;
//        p.next = rear;
//        boolean res = solution.hasCycle(head);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
