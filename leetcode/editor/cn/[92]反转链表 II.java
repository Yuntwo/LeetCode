//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [5], left = 1, right = 1
//输出：[5]
//
//
//
//
// 提示：
//
//
// 链表中节点数目为 n
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
//
//
//
//
// 进阶： 你可以使用一趟扫描完成反转吗？
//
// Related Topics 链表 👍 1524 👎 0

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
 * Review1
 */
class Solution {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        int count = right - left;
        ListNode dummy = new ListNode(-1, head);
        ListNode preHead = dummy;
        for (int i = 0; i < left - 1; i++) {
            preHead = preHead.next;
        }
        ListNode tail = preHead.next;
        for (int i = 0; i < count; i++) {
            ListNode toInsert = tail.next;
            tail.next = toInsert.next;
            toInsert.next = preHead.next;
            preHead.next = toInsert;
        }
        // 如果是插入到了最前面的话就不是原来的head了
        return dummy.next;
    }

}

/**
 * Success3
 * 递归解法
 * 注意递归的范围，凡是在递归里面的一定是有行为改变的，否则它就不用递归了
 * 就算是递归思路，也不要用倒置的结构去思考，而就是用实际连接的链表结构(插入的思路)去看它，倒置那种空间太混乱了
 */
//class Solution {
//
//    ListNode newHead = null;
//
//    int length = 0;
//
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode dummyHead = new ListNode(0, head);
//        ListNode p = dummyHead;
//        for (int i = 0; i < left - 1; i++) {
//            p = p.next;
//        }
//        ListNode g = p;
//        reg(p.next, right - left + 1);
//        g.next = newHead;
//        return dummyHead.next;
//    }
//
//    private void reg(ListNode p, int len) {
//        length++;
//        // right位置的就是new head
//        // right位置的下一位置就是tail
//        if (length == len) {
//            newHead = p;
//            return;
//        }
//        reg(p.next, len);
//        // 注意这样递归之后p.next的下一位置就是tail了，只需要将p插入多p.next之后即可
//        // 注意一定要先递归再插入，这样p.next的下一位置才指向tail
//        // 注意p.next.next就是tail，这个结构很特殊
//        ListNode temp = p.next.next;
//        p.next.next = p;
//        p.next = temp;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode res = solution.reverseBetween(head, 2, 4);
//        System.out.println(res);
//    }
//
//}

/**
 * Success2
 * 穿针引线头插法
 * 注意虚拟头结点的用法
 */
//class Solution {
//
//    /**
//     * Success2
//     * 简化循环方法
//     * 有时候用while确实不太习惯
//     */
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode dummyHead = new ListNode(0, head);
//        ListNode g = dummyHead;
//        // 注意对于循环来说，最后跳出循环时的状态一定是不满足循环条件的，一般都是先一个状态，所以要小心控制
//        // 其实这里还就可以理解为循环次数
//        for (int i = 0; i < left - 1; i++) {
//            g = g.next;
//        }
//        // 跳出循环时g指向left的前一个，p指向left
//        // 但此时p只是指向，还没有操作
//        ListNode p = g.next;
//        // 有时候循环还是看操作次数
//        // 注意反转n个节点，只需要头插n-1个节点
//        for (int i = 0; i < right - left; i++) {
//            // 把要插入的节点拿出来
//            ListNode toInsert = p.next;
//            p.next = toInsert.next;
//            // 插入新节点，注意也是三部曲
////            ListNode temp = g.next;
////            g.next = toInsert;
////            toInsert.next = temp;
//            // 可以简化
//            toInsert.next = g.next;
//            g.next = toInsert;
//        }
//        return dummyHead.next;
//    }
//
//    /**
//     * Success1
//     */
////    public ListNode reverseBetween(ListNode head, int left, int right) {
////        ListNode dummyHead = new ListNode(0, head);
////        int index = 0;
////        ListNode g = dummyHead;
////        // 注意对于循环来说，最后跳出循环时的状态一定是不满足循环条件的，一般都是先一个状态，所以要小心控制
////        // 其实这里还就可以理解为循环次数
////        while (index < left - 1) {
////            g = g.next;
////            index++;
////        }
////        // 跳出循环时g指向left的前一个，p指向left
////        // 但此时p只是指向，还没有操作
////        ListNode p = g.next;
////        // 有时候循环还是看操作次数
////        // 注意反转n个节点，只需要头插n-1个节点
////        for (int i = 0; i < right - left; i++) {
////            // 把要插入的节点拿出来
////            ListNode toInsert = p.next;
////            p.next = toInsert.next;
////            // 插入新节点，注意也是三部曲
//////            ListNode temp = g.next;
//////            g.next = toInsert;
//////            toInsert.next = temp;
////            // 可以简化
////            toInsert.next = g.next;
////            g.next = toInsert;
////        }
////        return dummyHead.next;
////    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode res = solution.reverseBetween(head, 2, 4);
//        System.out.println(res);
//    }
//
//}

/**
 * Success1
 * 原地倒置法
 * 代码写得有点冗余
 * 这种情况下貌似只能用迭代了，注意题目给的只是第几个节点，而不是ListNode
 */
//class Solution {
//
//    /**
//     * Success3
//     * 简化循环，总的来说还是用头插法而不是这种原地倒置法的思路更简便易理解，其实最后落实的操作都是差不多
//     */
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode dummyHead = new ListNode(0, head);
//        // 也可以直接把newHead置为left节点，这样直接少反转一个，而且也不用处理尾部连接了，中间变量会少一些
//        ListNode newHead = null;
//        ListNode p = dummyHead;
//        for (int i = 0; i < left - 1; i++) {
//            p = p.next;
//        }
//        ListNode g = p;
//        // 如果newHead从null开始的话，这个中间变量必不可少
//        ListNode tail = p.next;
//        p = p.next;
//        // 注意这里要加1
//        for (int i = 0; i < right - left + 1; i++) {
//            ListNode temp = p.next;
//            p.next = newHead;
//            newHead = p;
//            p = temp;
//        }
//        tail.next = p;
//        g.next = newHead;
//        return dummyHead.next;
//    }
//
//    /**
//     * Success2
//     * 用dummyHead减少分支
//     */
////    public ListNode reverseBetween(ListNode head, int left, int right) {
////        ListNode dummyHead = new ListNode(0, head);
////        ListNode newHead = null;
////        ListNode p = dummyHead;
////        ListNode temp = null;
////        int index = 1;
////        while (index <= left - 1) {
////            p = p.next;
////            index++;
////        }
////        ListNode tail = p.next;
////        ListNode tailPre = p;
////        p = p.next;
////        while (index <= right) {
////            temp = p.next;
////            p.next = newHead;
////            newHead = p;
////            p = temp;
////            index++;
////        }
////        tail.next = p;
////        tailPre.next = newHead;
////        return dummyHead.next;
////    }
//
//    /**
//     * Success1
//     * 没有用虚拟头节点分支比较麻烦
//     */
////    public ListNode reverseBetween(ListNode head, int left, int right) {
////        if (left == 1) {
////            if (head.next == null) {
////                return head;
////            }
////            ListNode newHead = null;
////            ListNode p = head;
////            ListNode temp = null;
////            int index = 1;
////            while (index <= right) {
////                temp = p.next;
////                p.next = newHead;
////                newHead = p;
////                p = temp;
////                index++;
////            }
////            head.next = p;
////            return newHead;
////        } else {
////            ListNode newHead = null;
////            ListNode p = head;
////            ListNode temp = null;
////            int index = 1;
////            ListNode tail = null;
////            ListNode tailPre = null;
////            while (index <= left - 2) {
////                p = p.next;
////                index++;
////            }
////            tail = p.next;
////            tailPre = p;
////            p = p.next;
////            index++;
////            while (index <= right) {
////                temp = p.next;
////                p.next = newHead;
////                newHead = p;
////                p = temp;
////                index++;
////            }
////            tail.next = p;
////            tailPre.next = newHead;
////            return head;
////        }
////    }
//}

/**
 * This is end
 */
//leetcode submit region end(Prohibit modification and deletion)
