//给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
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
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
//
// 示例 2：
//
//
//
//
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
//
//
// 示例 3：
//
//
//输入：head = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内
// -10⁵ <= Node.val <= 10⁵
//
//
//
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
//
//
//
// 注意：本题与主站 148 题相同：https://leetcode-cn.com/problems/sort-list/
//
// Related Topics 链表 双指针 分治 排序 归并排序 👍 105 👎 0

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
 * 不知道它出链表的数据结构难道是有什么额外的优势吗，感觉链表操作确实也挺有意思的
 * 链表比起数组的优势就在于不需要占用额外的空间
 * 链表操作起来还是有点麻烦，要是数组归并排序就方便多了
 * Q: 感觉是不是分治其实就可以理解为递归划分了，能分治就一定能递归，是类似的概念
 */
class Solution {
    public ListNode sortList(ListNode head) {
        // Attention the null is not solved by mergerSort
        if (head == null) {
            return head;
        }
        return mergerSort(head);
    }

    /**
     * Return the half pre!!! node of a link list for even
     * Return the half node of a link list for odd
     */
    private ListNode findHalf(ListNode head) {
        ListNode oneStep = head;
        ListNode doubleStep = head;
        while (doubleStep.next != null && doubleStep.next.next != null) {
            oneStep = oneStep.next;
            doubleStep = doubleStep.next.next;
        }
        return oneStep;
    }

    /**
     * Attention: the new head may change after sort
     */
    private ListNode mergerSort(ListNode head) {
        /* step1: partition */
        // If there is only one node in list, then no partition, bottle return
        if (head.next == null) {
            return head;
        }
        ListNode half = findHalf(head);
        // partition 1 may 1 more node than partition 2 for odd
        ListNode head1 = head;
        ListNode head2 = half.next;
        // cut two partitions off
        half.next = null;
        /* step2: regression sort */
        head1 = mergerSort(head1);
        head2 = mergerSort(head2);
        /* step3: merge two sorted partition, this is not regression */
        return merge(head1, head2);
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead;
        ListNode p1;
        ListNode p2;
        if (head1.val <= head2.val) {
            newHead = head1;
            p1 = head1;
            p2 = head2;
        } else {
            newHead = head2;
            p1 = head2;
            p2 = head1;
        }
        ListNode temp;
        // Attention here we should start with the less head
        // here after every loop, p1 is always the less head
        while (p1 != null && p2 != null) {
            // add list2 to list1
            while (p1.next != null && p1.next.val <= p2.val) {
                p1 = p1.next;
            }
            temp = p1.next;
            p1.next = p2;
            p1 = temp;
            // add list1 to list2
            // this is a possible mid state
            if (p1 == null) {
                break;
            }
            while (p2.next != null && p2.next.val <= p1.val) {
                p2 = p2.next;
            }
            temp = p2.next;
            p2.next = p1;
            p2 = temp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2));
        head.next.next = new ListNode(1, new ListNode(3));
        Solution solution = new Solution();
        ListNode res = solution.sortList(head);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
