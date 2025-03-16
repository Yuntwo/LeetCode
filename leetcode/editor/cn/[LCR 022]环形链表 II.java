//给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，
//pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
//
// 说明：不允许修改给定的链表。
//
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
//
//
// 示例 3：
//
//
//
//
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
//
//
//
//
// 提示：
//
//
// 链表中节点的数目范围在范围 [0, 10⁴] 内
// -10⁵ <= Node.val <= 10⁵
// pos 的值为 -1 或者链表中的一个有效索引
//
//
//
//
// 进阶：是否可以使用 O(1) 空间解决此题？
//
//
//
//
// 注意：本题与主站 142 题相同： https://leetcode-cn.com/problems/linked-list-cycle-ii/
//
// Related Topics 哈希表 链表 双指针 👍 124 👎 0

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
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/**
 * Success2
 * 写得更简洁，有时候可以把循环写成嵌套
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        solution.detectCycle(head);
    }
}

/**
 * Success1
 * 标准快慢指针解法，效率很高了，写得稍微有点冗余
 * 列个公式很好理解了；注意从环的长度这个特殊值考虑行走距离；如果只有环，那么一定是在起点相遇
 * 假设链表头到环入口的距离为 a，环入口到快慢指针相遇点的距离为 b，相遇点到环入口的距离为 c。
 * 当快慢指针在环中相遇时，慢指针走过的距离是 a+b，快指针走过的距离是a+b+c+b=a+2b+c。
 * 由于快指针是慢指针的两倍速，所以有 2(a+b)=a+2b+c，从而得出a=c
 */
//public class Solution {
//    public ListNode detectCycle(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//        ListNode p1 = head.next;
//        if (p1 == null) {
//            return null;
//        }
//        ListNode p2 = head.next.next;
//        while (p2 != null && p2.next != null && p1 != p2) {
//            p1 = p1.next;
//            p2 = p2.next.next;
//        }
//        if (p2 == null || p2.next == null) {
//            return null;
//        }
//        ListNode temp = p2;
//        p1 = head;
//        p2 = p2.next;
//        p1 = p1.next;
//        // 其实这一步p1再走环长度的距离有点多余，相遇点就是环的长度
//        while (p2 != temp) {
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        p2 = head;
//        while (p1 != p2) {
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        return p1;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        ListNode head = new ListNode(3);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(0);
//        head.next.next.next = new ListNode(-4);
//        head.next.next.next.next = head.next;
//        solution.detectCycle(head);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
