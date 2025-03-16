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
// Related Topics 哈希表 链表 双指针 👍 110 👎 0


import java.util.HashSet;

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
 * 快慢指针做法，需要一定的数学推导
 * 但是感觉空间效率没有提高很多啊
 * 注意null的访问控制
 */
public class Solution {

    /**
     * Success2
     * 另一种循环写法，没有什么效率提升
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    /**
     * Success1
     */
//    public ListNode detectCycle(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//        ListNode p1 = head.next;
//        if (head.next == null) {
//            return null;
//        }
//        ListNode p2 = head.next.next;
//        while (p1 != p2) {
//            if (p1 == null) {
//                return null;
//            }
//            p1 = p1.next;
//            if (p2 == null || p2.next == null) {
//                return null;
//            }
//            p2 = p2.next.next;
//        }
//        p2 = head;
//        while (p1 != p2) {
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        return p1;
//    }
}

/**
 * Success1
 * HashSet空间复杂度O(n)解法，不需要双指针
 * 注意HashSet的模板是LisNode，因为ListNode的val可能重复
 */
//public class Solution {
//    public ListNode detectCycle(ListNode head) {
//        HashSet<ListNode> hashSet = new HashSet<>();
//        ListNode p = head;
//        while (p != null) {
//            if (hashSet.contains(p)) {
//                return p;
//            } else {
//                hashSet.add(p);
//            }
//            // 使用while循环一定不要忘记手动改变循环变量
//            p = p.next;
//        }
//        return null;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
