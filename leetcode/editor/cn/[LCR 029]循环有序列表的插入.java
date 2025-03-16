//给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
//
// 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
//
// 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
//
// 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [3,4,1], insertVal = 2
//输出：[3,4,1,2]
//解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后
//，整个列表如上图所示，最后返回节点 3 。
//
//
//
//
// 示例 2：
//
//
//输入：head = [], insertVal = 1
//输出：[1]
//解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
//
//
// 示例 3：
//
//
//输入：head = [1], insertVal = 0
//输出：[1,0]
//
//
//
//
// 提示：
//
//
// 0 <= Number of Nodes <= 5 * 10^4
// -10^6 <= Node.val <= 10^6
// -10^6 <= insertVal <= 10^6
//
//
//
//
//
// 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-
//circular-linked-list/
//
// Related Topics 链表 👍 158 👎 0
class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

/**
 * Success1
 * 效率已经很高了，就是需要注意跳出循环
 * 注意它是循环链表
 */
class Solution {
    public Node insert(Node head, int insertVal) {
        Node toInsertNode = new Node(insertVal);
        if (head == null) {
            // 注意它是循环链表要指向自己
            toInsertNode.next = toInsertNode;
            return toInsertNode;
        }
        Node absTail = head;
        // 注意控制循环
        while (absTail.next != head && absTail.next.val >= absTail.val) {
            absTail = absTail.next;
        }
        // 在相对大的段插入
        // 在相对小的段插入，这个时候就要从后半段开始访问
        Node preToInsert = insertVal >= head.val ? head : absTail;
        while (preToInsert.next.val < insertVal) {
            preToInsert = preToInsert.next;
            // 如果已经是absTail了，那么后面一定还是满足条件的，但是就循环回去了，所以强制break
            if (preToInsert == absTail) {
                break;
            }
        }
        Node temp = preToInsert.next;
        preToInsert.next = toInsertNode;
        toInsertNode.next = temp;
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        Node head = new Node(1, new Node(3, new Node(5)));
//        head.next.next.next = head;
//        System.out.println(solution.insert(head, 6));

//        Node head = new Node(3, new Node(4, new Node(1)));
//        head.next.next.next = head;
//        System.out.println(solution.insert(head, 2));

        Node head = new Node(3, new Node(3, new Node(5)));
        head.next.next.next = head;
        System.out.println(solution.insert(head, 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
