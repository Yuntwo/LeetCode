//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
//
//
// 示例 2：
//
//
//
//
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
//
//提示：
//
//
// 链表中的节点数目为 n
// 1 <= k <= n <= 5000
// 0 <= Node.val <= 1000
//
//
//
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
//
//
//
//
// Related Topics 递归 链表 👍 2226 👎 0

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
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * Review2
 * 递归写法，时间效率也是100%
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode tail = head;
        int count = k;
        while (count > 1 && head.next != null) {
            head = head.next;
            count--;
        }
        if (count > 1) {
            return tail;
        }
        ListNode cur = tail;
        while (cur != head) {
            ListNode nextCur = cur.next;
            ListNode temp = head.next;
            head.next = cur;
            cur.next = temp;
            cur = nextCur;
        }
        tail.next = reverseKGroup(tail.next, k);
        return head;
    }

}

/**
 * Success2
 * 这样时间效率也是100%，因为判断非null那里的效率和先数一遍可能不相上下
 * 思路不复杂，就是逻辑细节不好把握
 */
//class Solution {
//    public ListNode reverseKGroup(ListNode head, int k) {
//        ListNode tail = new ListNode(-1, head);
//        ListNode toInsert = head;
//        ListNode newHead = head;
//        for (int i = 0; i < k - 1; i++) {
//            newHead = newHead.next;
//        }
//        ListNode res = newHead;
//        while (newHead != null) {
//            // 新旧段落的连接先设置好
//            tail.next = newHead;
//            tail = toInsert;
//            ListNode toInsertTemp = newHead.next;
//            while (toInsert!= newHead) {
//                ListNode temp = toInsert.next;
//                toInsert.next = newHead.next;
//                newHead.next = toInsert;
//                toInsert = temp;
//            }
//            toInsert = toInsertTemp;
//            // 寻找下一段的newHead
//            newHead = toInsert;
//            for (int i = 0; i < k - 1; i++) {
//                // 刚好k的倍数不剩了(这种情况易漏造成非空访问)、或者不足k个就返回了
//                if (newHead == null || newHead.next == null) {
//                    return res;
//                }
//                newHead = newHead.next;
//            }
//        }
//        return res;
//    }
//}

/**
 * Review1
 * 注意理解指针和实际对象的区别，在链表的题中如果这个关系没有理清楚那么狠容易出错
 * 你在用图表示的时候实际的节点用实体表示，指针就是名字，千万不能搞混了
 */
//class Solution {
//    public ListNode reverseKGroup(ListNode head, int k) {
//        if (head.next == null) {
//            return head;
//        }
//        int num = 1;
//        ListNode p = head;
//        while (p.next != null) {
//            p = p.next;
//            num++;
//        }
//        ListNode dummy = new ListNode(-1, head);
//        ListNode preHead = dummy;
//        // 此时preHead和dummy都指向的是实际的节点new ListNode(-1, head)
//        while (num >= k) {
//            for (int i = 0; i < k - 1; i++) {
//                // 不知道这里插入和交换一样也会形成一个闭环
//                // 因为这里这个节点还会用，所以必须用一个额外的指针指向它
//                ListNode temp = dummy.next;
//                dummy.next = head.next;
//                head.next = dummy.next.next;
//                dummy.next.next = temp;
//            }
//            num -= k;
//            // 我这里想要的下一个head的前一个节点实际上就是当前的head，也不需要new一个对象来专门指向
//            dummy = head;
//            head = head.next;
//        }
//        return preHead.next;
//    }
//}

/**
 * Success1
 * 时间效率100%
 */
//class Solution {
//    public ListNode reverseKGroup(ListNode head, int k) {
//        if (head == null || k == 1) {
//            return head;
//        }
//        int count = 0;
//
//        ListNode dummy = new ListNode(0, head);
//        // 这里的preHead是每一小k段的preHead，dummy是整个链表的pre
//        // 注意这里的preHead只是一个指针，只要preHead.next是下一段的头就行了，不管preHead指向的具体是哪一个
//        ListNode preHead = dummy;
//        ListNode cur = head;
//        ListNode next;
//
//        while (cur != null) {
//            cur = cur.next;
//            count++;
//        }
//
//        while (count >= k) {
//            // 其实就是上一次cur的next
//            cur = preHead.next;
//            next = cur.next;
//            // 注意每一次内层循环，cur都不用动，因为它一定是这一节的tail
//            // 这里的反转链表实际上就是用头插法
//            // 循环完cur.next还是正常的链接，只不过有k个了，不需要继续头插了
//            for (int i = 1; i < k; i++) {
//                cur.next = next.next;
//                next.next = preHead.next;
//                preHead.next = next;
//                next = cur.next;
//            }
//            preHead = cur;
//            count -= k;
//        }
//
//        return dummy.next;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
