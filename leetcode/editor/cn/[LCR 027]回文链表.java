//给定一个链表的 头节点 head ，请判断其是否为回文链表。
//
// 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
//
//
//
// 示例 1：
//
//
//
//
//输入: head = [1,2,3,3,2,1]
//输出: true
//
// 示例 2：
//
//
//
//
//输入: head = [1,2]
//输出: false
//
//
//
//
// 提示：
//
//
// 链表 L 的长度范围为 [1, 10⁵]
// 0 <= node.val <= 9
//
//
//
//
// 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
//
//
//
//
// 注意：本题与主站 234 题相同：https://leetcode-cn.com/problems/palindrome-linked-list/
//
// Related Topics 栈 递归 链表 双指针 👍 135 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
 * Success2
 * 进阶解法
 * 注意反转链表有头插法和尾插法两种，细节有差异；头插法直接换头，尾插法插在新头后面新头不变
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 如果fast.next==null，说明是奇数个节点，slow指向中点
        // 如果fast==null，说明是偶数个节点，slow指向后半段head
        // 反转后半段逻辑更简洁
        ListNode secondHead = null;
        // 这里在中点多反转一个也没有关系，因为前半段也可以引用这个中点、tail没有直接指向null
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = secondHead;
            secondHead = slow;
            slow = temp;
        }
        while (secondHead != null) {
            if (head.val != secondHead.val) {
                return false;
            }
            head = head.next;
            secondHead = secondHead.next;
        }
        return true;
    }
}

/**
 * Success1
 * 时空复杂度为O(n)
 */
//class Solution {
//    public boolean isPalindrome(ListNode head) {
//        Deque<Integer> arr = new LinkedList<>();
//        ListNode node = head;
//        while (node != null) {
//            arr.push(node.val);
//            node = node.next;
//        }
//        node = head;
//        for (int i = 0; i < arr.size(); i++) {
//            if (arr.pop() != node.val) {
//                return false;
//            }
//            node = node.next;
//        }
//        return true;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
