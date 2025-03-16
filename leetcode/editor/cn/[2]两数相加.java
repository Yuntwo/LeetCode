//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学 👍 8200 👎 0
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
// 注意这里题目应该是头对齐然后从头计算直接在链尾加数
// 真真麻烦死
// 时间效率还是很高的
//class Solution {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode p1 = l1;
//        ListNode p2 = l2;
//        int len1 = 1;
//        int len2 = 1;
//        while (p1.next != null) {
//            len1++;
//            p1 = p1.next;
//        }
//        while (p2.next != null) {
//            len2++;
//            p2 = p2.next;
//        }
//        ListNode res = new ListNode(0, new ListNode(0));
//        ListNode pRes = res;
//        ListNode zero = new ListNode(0);
//        int lenMax = Math.max(len1, len2);
//        for (int i = 0; i <= lenMax - 1; i++) {
//            // 每一次都要重置
//            p1 = l1;
//            p2 = l2;
//            if (i < len1) {
//                for (int j = 1; j <= i; j++) {
//                    p1 = p1.next;
//                }
//            } else {
//                p1 = zero;
//            }
//            if (i < len2) {
//                for (int j = 1; j <= i; j++) {
//                    p2 = p2.next;
//                }
//            } else {
//                p2 = zero;
//            }
//            int temp = p1.val + p2.val + pRes.next.val;
//            int low = temp % 10;
//            int high = temp / 10;
//            // 注意头结点的逻辑还不太一样
//            // 感觉这里逻辑判断写得有点麻烦
//            if (i == 0) {
//                // 注意如果是头结点还只有一位还高位是0的话，省去这个0
//                if (lenMax == 1 && high == 0) {
//                    res = new ListNode(low);
//                    pRes = res;
//                } else {
//                    res = new ListNode(low, new ListNode(high));
//                    pRes = res;
//                }
//                // 注意最后一个如果高位进位是0的话，省去这个0
//            } else if (i == lenMax - 1 && high == 0) {
//                ListNode now = new ListNode(low);
//                pRes.next = now;
//                pRes = pRes.next;
//            } else {
//                // 注意这里的链表指针关系，直接赋值的或实际上就是指向新的节点了，会丢失之前的值
//                ListNode now = new ListNode(low, new ListNode(high));
//                pRes.next = now;
//                pRes = pRes.next;
//            }
//        }
//
//        return res;
//    }
//
//    public static void main(String[] args) {
//        // example1
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//        // example2
////        ListNode l1 = new ListNode(0);
////        ListNode l2 = new ListNode(0);
//
//        Solution solution = new Solution();
//        ListNode res = solution.addTwoNumbers(l1, l2);
//        System.out.println(res);
//    }
//}
// 思想一样，优化写法
// 注意可以直接用l1、l2这两个变量，并没有改变l1、l2指向的原链表
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resPre = new ListNode(0);
        ListNode cur = resPre;
        // 注意这里直接用一个变量来保存当前计算节点对下一位的进位，进不用ListNode来存了，这样还会指向新的计算节点，浪费内存
        // 所以要存成全局变量
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x1 = l1 == null ? 0 : l1.val;
            int x2 = l2 == null ? 0 : l2.val;
            // 注意加上进位
            int sum = x1 + x2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            // 下一次循环的移动
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        // 注意还剩最后两个结点相加之后的进位没有处理
        // 如果进位是0就不生成新的节点了
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return resPre.next;
    }

    public static void main(String[] args) {
        // example1
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        // example2
//        ListNode l1 = new ListNode(0);
//        ListNode l2 = new ListNode(0);

        Solution solution = new Solution();
        ListNode res = solution.addTwoNumbers(l1, l2);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
