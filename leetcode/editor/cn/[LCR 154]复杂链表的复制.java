//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。
//
//
//
// 示例 1：
//
//
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
//
//
// 示例 2：
//
//
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
//
//
// 示例 3：
//
//
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
//
//
// 示例 4：
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
//
//
//
//
// 提示：
//
//
// -10000 <= Node.val <= 10000
// Node.random 为空（null）或指向链表中的节点。
// 节点数目不超过 1000 。
//
//
//
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-
//pointer/
//
//
//
// Related Topics 哈希表 链表 👍 786 👎 0

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/**
 * Success2
 * 用HashMap来存储原链表与复制链表的映射关系，时间效率肯定100%，空间换时间
 */
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node newHead = new Node(head.val);
        Node cur = newHead;
        // 注意不要少了这个映射关系
        hashMap.put(head, newHead);
        while (head != null) {
            // 为null的情况默认不需要处理了
            if (head.next != null) {
                if (!hashMap.containsKey(head.next)) {
                    hashMap.put(head.next, new Node(head.next.val));
                }
                cur.next = hashMap.get(head.next);
            }
            if (head.random != null) {
                if (!hashMap.containsKey(head.random)) {
                    hashMap.put(head.random, new Node(head.random.val));
                }
                cur.random = hashMap.get(head.random);
            }
            cur = cur.next;
            head = head.next;
        }
        return newHead;
    }
}

/**
 * Success1
 * 时间效率100%
 * 注意访问next.next这种情况一定要结合实际情况考虑next是否有为null的情况
 * 注意这里比较巧妙的点在于将新生成的复制链表插入到原链表中，这样有助于索引
 */
//class Solution {
//    public Node copyRandomList(Node head) {
//        if (head == null) {
//            return null;
//        }
//        Node cur = head;
//        while (cur != null) {
//            Node newNode = new Node(cur.val);
//
//            newNode.next = cur.next;
//            cur.next = newNode;
//            cur = newNode.next;
//        }
//
//        cur = head;
//        while (cur != null) {
//            // 注意这里判断cur.random != null很有必要，否则cur.random会出现null访问
//            if (cur.random != null) {
//                cur.next.random = cur.random.next;
//            }
//            cur = cur.next.next;
//        }
//
//        Node newHead = head.next;
//        Node next;
//        Node copy;
//        cur = head;
//        while (cur != null) {
//            // 注意原始链表每一个节点后面至少都插入了一个复制节点，所以cur.next一定不为null
//            next = cur.next.next;
//            copy = cur.next;
//
//            // 注意这里判断next是否为null很重要，否则尾结点会出现next为null访问异常
//            copy.next = (next != null) ? next.next : null;
//
//            cur.next = next;
//
//            cur = next;
//        }
//        return newHead;
//
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
