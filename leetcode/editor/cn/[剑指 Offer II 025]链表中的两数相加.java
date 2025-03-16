//给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 可以假设除了数字 0 之外，这两个数字都不会以零开头。
//
//
//
// 示例1：
//
//
//
//
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
//
//
// 示例2：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
//
//
// 示例3：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 链表的长度范围为 [1, 100]
// 0 <= node.val <= 9
// 输入数据保证链表代表的数字无前导 0
//
//
//
//
// 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。
//
//
//
//
// 注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/
//
// Related Topics 栈 链表 数学 👍 94 👎 0
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
 * 效率蛮低的
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = stack.pop();
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            int carry = sum / 10;
            int remainder = sum % 10;
            stack.push(remainder);
            stack.push(carry);
        }
        // 注意stack的栈顶不一定是0，取决于之前的进位
        int top = stack.pop();
        ListNode head;
        // 如果栈顶为0，分情况讨论
        if (top == 0) {
            if (stack.isEmpty()) {
                // 注意为0的情况不是返回null
                return new ListNode(0);
            } else {
                // 还有其他的数字那么就要舍弃开头的0
                head = new ListNode(stack.pop());
            }
        // 如果栈顶不为0，那么直接将它作为head
        } else {
            head = new ListNode(top);
        }
        // 注意尾插法，这里headPre和tail的定义不冲突
        ListNode tail = head;
        while (!stack.isEmpty()) {
            tail.next = new ListNode(stack.pop());
            tail = tail.next;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
