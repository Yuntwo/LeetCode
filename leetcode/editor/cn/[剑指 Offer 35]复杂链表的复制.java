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
// Related Topics 哈希表 链表 👍 544 👎 0


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
/**
 * 方法一：hash表空间换时间
 */
//class Solution {
//    public Node copyRandomList(Node head) {
//        if (head == null) {
//            return null;
//        }
//        Node p1 = head;
//        Node newHead = new Node(head.val);
//        Node p2 = newHead;
//        // 注意这个hashMap这个数据结构结构很怪，你申明成什么数据类型它就存成了什么数据类型，虽然不知道它的底层是怎么实现的
////        HashMap<Object, Node> hashMap = new HashMap<Object, Node>();
//        // 注意如果是用Object的hashCode()作为key的话，当node的random为null就没法存了，除非判断一下
//        // 但是key存成hashCode貌似没有节省空间
//        HashMap<Integer, Node> hashMap = new HashMap<Integer, Node>();
//        while (p1.next != null) {
////            hashMap.put(p1, p2);
//            hashMap.put(p1.hashCode(), p2);
//            p2.next = new Node(p1.next.val);
//            p1 = p1.next;
//            p2 = p2.next;
//        }
////        hashMap.put(p1, p2);
//        hashMap.put(p1.hashCode(), p2);
//        Node s1 = head;
//        Node s2 = newHead;
//        while (s1 != null) {
//            // 注意这里是random
////            s2.random = hashMap.get(s1.random);
//            if (s1.random != null) {
//                s2.random = hashMap.get(s1.random.hashCode());
//            }
//            s1 = s1.next;
//            s2 = s2.next;
//        }
//        return newHead;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        Node head = new Node(7);
//        Node p = head;
//        p.next = new Node(13);
//        p = p.next;
//        p.next = new Node(11);
//        p = p.next;
//        p.next = new Node(10);
//        p = p.next;
//        p.next = new Node(1);
//        p.next.random = head;
//        p = head.next;
//        p.random = head;
//        p = p.next;
//        p.next.random = p;
//        p.random = p.next.next;
/**
 * 方法二：合并拆分法
 * 其实就是我新生成的链表的random与原链表的random的对应关系怎么确认
 * 方法一是额外用了hash表进行绑定
 * 方法二将新生成的链表插入到原链表中建立绑定关系，就不需要额外的存储空间了
 * 这种绑定关系你可以自定义嘛，插到原链表之后多少都可以
 * 这样应该对了，但是似乎提交不允许修改原链表
 */
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node p1 = head;
        Node newHead = new Node(head.val);
        Node p2 = newHead;
        // temp1 存储没有合并前的p1的next
        Node temp1 = p1.next;
        // 合并
        p1.next = p2;
        // 注意也要改变新链表的指向旧链表，在生成新链表时就直接合并进去
        p2.next = temp1;
        p1 = temp1;
        while (p1 != null) {
            p2 = new Node(p1.val);
            temp1 = p1.next;
            p1.next = p2;
            p2.next = temp1;
            p1 = temp1;
        }
        // 建立random关系
        p1 = head;
        p2 = newHead;
        while (p1 != null) {
            // 注意这里s1.random可能为null，就没有next
            if (p1.random != null) {
                p2.random = p1.random.next;
            }
            // 注意这时候是移两步了
            p1 = p1.next.next;
            // 如果p1为null，p2.next == p1也是null，不能访问它的next了
            if (p1 != null) {
                p2 = p2.next.next;
            }
        }
        // 拆分链表
        // 注意这种拆法没有恢复原链表，不能通过，你还是要把原链表恢复了
//        p2 = newHead;
//        // 注意是p2.next也即原链表的下一结点不为null存在，才有其后面的新链表的结点
//        while (p2.next != null) {
//            p2.next = p2.next.next;
//            p2 = p2.next;
//        }
        p1 = head;
        // 注意是p2.next也即原链表的下一结点不为null存在，才有其后面的新链表的结点
        while (p1.next.next != null) {
            temp1 = p1.next;
            // 修改原链表
            p1.next = p1.next.next;
            if (p1.next != null) {
                p1 = p1.next;
                temp1.next = temp1.next.next;
            }
        }
        return newHead;
    }

    public static void main(String[] args) {
        TestSolution solution = new TestSolution();
        Node head = new Node(7);
        Node p = head;
        p.next = new Node(13);
        p = p.next;
        p.next = new Node(11);
        p = p.next;
        p.next = new Node(10);
        p = p.next;
        p.next = new Node(1);
        p.next.random = head;
        p = head.next;
        p.random = head;
        p = p.next;
        p.next.random = p;
        p.random = p.next.next;
        solution.copyRandomList(head);
    }
}
//        solution.copyRandomList(head);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
