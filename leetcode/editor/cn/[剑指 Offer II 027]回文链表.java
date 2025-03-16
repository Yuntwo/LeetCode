import java.util.ArrayList;
import java.util.Stack;

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
// Related Topics 栈 递归 链表 双指针 👍 118 👎 0
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

class Solution {

    /**
     * Success2
     * 用将要到的位置的前一个结点做判断，判断更少，代码更简洁，不用分类讨论
     * 最好先用最少的访问解决问题，这样更高效，往往也更不容易出错
     */
//    public boolean isPalindrome(ListNode head) {
//        ListNode fast = head;
//        ListNode slow = head;
//        head = null;
////        while (fast != null && fast.next != null) {
//        // 注意while可以直接对对象就行判null
//        while (fast && fast.next) {
//            fast = fast.next.next;
//            // 在slow往后移一步的同时反转前面的链表
//            // 注意反转的是当前slow的之前所有，也即此次循环结束之后slow的前一个位置
//            // 注意这里的四步是完整的闭环
//            ListNode temp = slow.next;
//            slow.next = head;
//            head = slow;
//            slow = temp;
//        }
//        // 通过fast指针的位置可以判断链表长度的奇偶性，调整后半部分的开头
//        // 如果链表长度为奇数，快指针会停在最后一个节点，需要将慢指针向前移动一步，跳过中间的节点
//        // 注意不能用fast.next == null，尽管这对于奇数的情况是等价的；但是对于偶数的情况fast停留在null的位置会引起NullPointerException
//        if (fast != null) {
//            slow = slow.next;
//        }
//        // head是反转前半部分的开头，slow是未反转后半部分的开头
//        while (slow != null) {
//            if (slow.val != head.val) {
//                return false;
//            }
//            slow = slow.next;
//            head = head.next;
//        }
//        return true;
//
//    }

    /**
     * Failure1
     * 用将要到达的位置做判断，这样要分很多情况讨论，代码不简洁
     * 最主要的是由于过度判断少走了一步，会导致在偶数情况下不能完全反转至一半
     */
    public boolean isPalindrome(ListNode head) {
        // 一个结点的要单独处理
        if (head.next == null) {
            return true;
        }
        // 两个结点的要单独处理
        if (head.next.next == null) {
            if (head.val != head.next.val) {
                return false;
            } else {
                return true;
            }
        }
        ListNode fast = head;
        ListNode slow = head;
        head = null;
        // 注意fast.next要放在前面判断
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            // 在slow往后移一步的同时反转前面的链表
            // 注意这里的四步是完整的闭环
            ListNode temp = slow.next;
            slow.next = head;
            head = slow;
            slow = temp;
        }
        // 通过fast指针的位置可以判断链表长度的奇偶性，从未调整前半段的开始
        if (fast.next == null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != head.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;

    }

    /**
     * Failure1
     * 需要一个额外的指针来存储slow的下一个节点，这是后半段比较的开始
     */
//    public boolean isPalindrome(ListNode head) {
//        ListNode fast = head;
//        ListNode slow = head;
//        ListNode newHead = null;
//        // 注意fast.next要放在前面判断
//        while (fast.next != null && fast.next.next != null) {
//            fast = fast.next.next;
//            // 在slow往后移一步的同时反转前面的链表
//            // 注意这里的四步是完整的闭环
//            ListNode temp = slow.next;
//            slow.next = newHead;
//            newHead = slow;
//            slow = temp;
//        }
//        // 通过fast指针的位置可以判断链表长度的奇偶性，从未调整前半段的开始
//        if (fast.next == null) {
//            slow = slow.next;
//        }
//
//    }

}

/**
 * Success2
 * O(n)的时间复杂度，要O(n)的空间复杂度
 * 虽然是反转链表的思路，但是由于递归将使用堆栈空间，所以时间复杂度不是O(1)
 */
//class Solution {
//
//    /**
//     * Success2
//     * 找到第一个中点定义的下一个快慢指针一次遍历就够了，这样比较酷
//     */
//    public boolean isPalindrome(ListNode head) {
//        // 注意长度为1的情况要特殊处理，不符合下面的移动条件
//        if (head.next == null) {
//            return true;
//        }
//        // 注意理想的中点是第一半(比例严格大于等于0.5)的末尾，且都是慢(p)指针指向的位置
//        ListNode p = head;
//        ListNode fast = head;
//        // 前者是链表长度为偶数的fast最终情况，后者是链表长度为奇数的fast最终情况
//        while (fast.next != null && fast.next.next != null) {
//            p = p.next;
//            fast = fast.next.next;
//        }
//        // 注意我们需要的开始点是上述中点的下一个
//        p = p.next;
//        ListNode newHead = rev(p);
//        while (newHead != null) {
//            if (head.val != newHead.val) {
//                return false;
//            }
//            newHead = newHead.next;
//            head = head.next;
//        }
//        return true;
//    }
//
//    /**
//     * Success1
//     * 啥办法遍历两次找到中点
//     */
////    public boolean isPalindrome(ListNode head) {
////        // 注意长度为1的情况要特殊处理，不符合下面的移动条件
////        if (head.next == null) {
////            return true;
////        }
////        int length = 0;
////        ListNode p = head;
////        while (p != null) {
////            length++;
////            p = p.next;
////        }
////        p = head;
////        for (int i = 1; i < (length + 1) / 2 + 1; i++) {
////            p = p.next;
////        }
////        ListNode newHead = rev(p);
////        while (newHead != null) {
////            if (head.val != newHead.val) {
////                return false;
////            }
////            newHead = newHead.next;
////            head = head.next;
////        }
////        return true;
////    }
//
//    private ListNode rev(ListNode head) {
//        if (head.next == null) {
//            return head;
//        }
//        ListNode newHead = rev(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//    }
//
//}

/**
 * Success1
 * 用栈，O(n)的时间复杂度和O(n)的空间复杂度
 */
//class Solution {
//
//    /**
//     * Success2
//     * 如果是O(n)的空间复杂度，或许数组复制才是最省空间的
//     */
//    public boolean isPalindrome(ListNode head) {
//        ArrayList<Integer> array = new ArrayList<>();
//        ListNode p = head;
//        while (p != null) {
//            array.add(p.val);
//            p = p.next;
//        }
//        // 注意头尾索引也可以，没必要for
//        int start = 0;
//        int end = array.size() - 1;
//        while (start <= end) {
//            if (array.get(start) != array.get(end)) {
//                return false;
//            }
//            start++;
//            end--;
//        }
//        return true;
//    }
//
//    /**
//     * Success1
//     * 用O(n)的栈空间
//     */
////    public boolean isPalindrome(ListNode head) {
////        Stack<Integer> stack = new Stack<>();
////        ListNode p = head;
////        int length = 0;
////        while (p != null) {
////            length++;
////            stack.push(p.val);
////            p = p.next;
////        }
////        p = head;
////        // 如果是奇数的话，中心位置不需要比较，一定是符合回文的，所以向下取整就够了
////        for (int i = 0; i < length / 2; i++) {
////            if (p.val != stack.pop()) {
////                return false;
////            }
////            p = p.next;
////        }
////        return true;
////    }
//}

/**
 * Failure1
 * 直接暴力每次都循环找对称位置会超时，时间复杂度应该是到了O(n^2)
 */
//class Solution {
//    public boolean isPalindrome(ListNode head) {
//        int length = 0;
//        ListNode p = head;
//        while (p != null) {
//            length++;
//            p = p.next;
//        }
//        p = head;
//        for (int i = 0; i <= (length - 1) / 2; i++) {
//            ListNode rev = p;
//            for (int j = i; j < length - 1 - i; j++) {
//                rev = rev.next;
//            }
//            if (p.val != rev.val) {
//                return false;
//            }
//            p = p.next;
//        }
//        return true;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
