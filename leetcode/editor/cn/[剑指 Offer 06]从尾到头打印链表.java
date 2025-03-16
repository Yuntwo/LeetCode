//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
//
//
// 示例 1：
//
// 输入：head = [1,3,2]
//输出：[2,3,1]
//
//
//
// 限制：
//
// 0 <= 链表长度 <= 10000
// Related Topics 栈 递归 链表 双指针 👍 230 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
/**
 * 不改变链表结构的条件
 * */
// 用栈，而且据说是Stack这种数据类型最好不要用
//class Solution {
//    public int[] reversePrint(ListNode head) {
//        int length = 0;
//        // 注意这里要指明泛型
//        Stack<Integer> stack = new Stack<Integer>();
//        // 注意java隐藏了指针的概念
//        ListNode p = head;
//        while (p != null) {
//            length++;
//            stack.push(p.val);
//            p = p.next;
//        }
//        int[] res = new int[length];
//        for (int i = 0; i < length; i++) {
//            res[i] = stack.pop();
//        }
//        return res;
//    }
//}
// 用LinkedList快一点 yes
// 注意java中集合的概念与数学中集合的概念并不完全对等，不同的类有不同的定义
//class Solution {
//    public int[] reversePrint(ListNode head) {
//        int length = 0;
//        // 注意这里要指明泛型
//        LinkedList<Integer> stack = new LinkedList<Integer>();
//        // 注意java隐藏了指针的概念
//        ListNode p = head;
//        while (p != null) {
//            length++;
//            stack.push(p.val);
//            p = p.next;
//        }
//        int[] res = new int[length];
//        for (int i = 0; i < length; i++) {
//            res[i] = stack.pop();
//        }
//        return res;
//    }
//}
// 用递归实现，似乎这种结构不太好返回 no
//class Solution {
//    public int[] reversePrint(ListNode head) {
//        if (head != null) {
//            if (head.next != null) {
//                reversePrint(head.next);
//            }
//        }
//    }
//}
// 反正都要返回到数组中，直接遍历两遍即可。
// 和栈本质上是一样的，都遍历了两遍。只不过这种结构更快
// 在空间复杂度上没有太大提升
//class Solution {
//    public int[] reversePrint(ListNode head) {
//        //先获取链表长度，创建对应长度数组
//        ListNode currNode = head;
//        int len = 0;
//        while (currNode != null) {
//            len++;
//            currNode = currNode.next;
//        }
//        int[] result = new int[len];
//
//        //再次遍历链表，将值倒序填充至结果数组
//        currNode = head;
//        while (currNode != null) {
//            result[len - 1] = currNode.val;
//            len--;
//            currNode = currNode.next;
//        }
//        return result;
//    }
//}
/**
 * 可以修改原链表的条件
 * */
// 也很快，内存与上一种方法占的是差不多的，主要是返回逆序数组的话占的空间都差不多
class Solution {
    public int[] reversePrint(ListNode head) {
        int length = 0;
        ListNode headNew = null;
        ListNode headReverted = null;
        ListNode headNow = head;
        ListNode headToRevert = null;
        while (headNow != null) {
            length++;
            headToRevert = headNow.next;
            if (headToRevert == null) {
                headNew = headNow;
            }
            headNow.next = headReverted;
            headReverted = headNow;
            headNow = headToRevert;
        }
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = headNew.val;
            headNew = headNew.next;
        }
        return res;
    }
}
//
//leetcode submit region end(Prohibit modification and deletion)
