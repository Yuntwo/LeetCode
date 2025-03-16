//不使用任何库函数，设计一个 跳表 。
//
// 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思
//想与链表相似。
//
// 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
//
// Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
//
// 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(
//n))，空间复杂度是 O(n)。
//
// 了解更多 : https://en.wikipedia.org/wiki/Skip_list
//
// 在本题中，你的设计应该要包含这些函数：
//
//
// bool search(int target) : 返回target是否存在于跳表中。
// void add(int num): 插入一个元素到跳表。
// bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
//
//
//
// 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
//
//
//
// 示例 1:
//
//
//输入
//["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase",
// "search"]
//[[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
//输出
//[null, null, null, null, false, null, true, false, true, false]
//
//解释
//Skiplist skiplist = new Skiplist();
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // 返回 false
//skiplist.add(4);
//skiplist.search(1);   // 返回 true
//skiplist.erase(0);    // 返回 false，0 不在跳表中
//skiplist.erase(1);    // 返回 true
//skiplist.search(1);   // 返回 false，1 已被擦除
//
//
//
//
// 提示:
//
//
// 0 <= num, target <= 2 * 10⁴
// 调用search, add, erase操作次数不大于 5 * 10⁴
//
//
// Related Topics 设计 链表 👍 260 👎 0




//leetcode submit region begin(Prohibit modification and deletion)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Success3
 * 官方题解
 */
//class Skiplist {
//    static final int MAX_LEVEL = 32;
//    static final double P_FACTOR = 0.25;
//    private SkiplistNode head;
//    private int level;
//    private Random random;
//
//    public Skiplist() {
//        this.head = new SkiplistNode(-1, MAX_LEVEL);
//        this.level = 0;
//        this.random = new Random();
//    }
//
//    public boolean search(int target) {
//        SkiplistNode curr = this.head;
//        for (int i = level - 1; i >= 0; i--) {
//            /* 找到第 i 层小于且最接近 target 的元素*/
//            while (curr.forward[i] != null && curr.forward[i].val < target) {
//                curr = curr.forward[i];
//            }
//        }
//        curr = curr.forward[0];
//        /* 检测当前元素的值是否等于 target */
//        if (curr != null && curr.val == target) {
//            return true;
//        }
//        return false;
//    }
//
//    public void add(int num) {
//        SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
//        Arrays.fill(update, head);
//        SkiplistNode curr = this.head;
//        for (int i = level - 1; i >= 0; i--) {
//            /* 找到第 i 层小于且最接近 num 的元素*/
//            while (curr.forward[i] != null && curr.forward[i].val < num) {
//                curr = curr.forward[i];
//            }
//            update[i] = curr;
//        }
//        int lv = randomLevel();
//        level = Math.max(level, lv);
//        SkiplistNode newNode = new SkiplistNode(num, lv);
//        for (int i = 0; i < lv; i++) {
//            /* 对第 i 层的状态进行更新，将当前元素的 forward 指向新的节点 */
//            newNode.forward[i] = update[i].forward[i];
//            update[i].forward[i] = newNode;
//        }
//    }
//
//    public boolean erase(int num) {
//        SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
//        SkiplistNode curr = this.head;
//        for (int i = level - 1; i >= 0; i--) {
//            /* 找到第 i 层小于且最接近 num 的元素*/
//            while (curr.forward[i] != null && curr.forward[i].val < num) {
//                curr = curr.forward[i];
//            }
//            update[i] = curr;
//        }
//        curr = curr.forward[0];
//        /* 如果值不存在则返回 false */
//        if (curr == null || curr.val != num) {
//            return false;
//        }
//        for (int i = 0; i < level; i++) {
//            if (update[i].forward[i] != curr) {
//                break;
//            }
//            /* 对第 i 层的状态进行更新，将 forward 指向被删除节点的下一跳 */
//            update[i].forward[i] = curr.forward[i];
//        }
//        /* 更新当前的 level */
//        while (level > 1 && head.forward[level - 1] == null) {
//            level--;
//        }
//        return true;
//    }
//
//    private int randomLevel() {
//        int lv = 1;
//        /* 随机生成 lv */
//        while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) {
//            lv++;
//        }
//        return lv;
//    }
//}
//
//class SkiplistNode {
//    int val;
//    SkiplistNode[] forward;
//
//    public SkiplistNode(int val, int maxLevel) {
//        this.val = val;
//        this.forward = new SkiplistNode[maxLevel];
//    }
//}

/**
 * Review3
 * 完美
 * 不管是add、erase、search，都是从最高层出发，一定会落到最底层
 */
class SkipNode {

    public int val;

    public SkipNode[] next;

    public SkipNode(int val, int maxLevel) {
        this.val = val;
        // 注意因为这里是以0开始的索引，所以在初始化数组时要+1
        this.next = new SkipNode[maxLevel + 1];
    }
}

class Skiplist {

    private int MAX_LEVEL = 31;

    // 不是越低越好，也不是越高越好
    private double PROB = 0.25;

    private SkipNode dummy;

    private int realLevel;

    private Random random;

    public Skiplist() {
        dummy = new SkipNode(-1, MAX_LEVEL);
        realLevel = -1;
        random = new Random();
    }

    private int getRandomLevel() {
        int res = 0;
        while (random.nextDouble() < PROB && res < MAX_LEVEL) {
            res++;
        }
        return res;
    }

    public boolean search(int target) {
        SkipNode curNode = dummy;
        int curLevel = realLevel;
        while (curLevel >= 0) {
            while (curNode.next[curLevel] != null && curNode.next[curLevel].val < target) {
                curNode = curNode.next[curLevel];
            }
            if (curNode.next[curLevel] != null && curNode.next[curLevel].val == target) {
                return true;
            }
            // 这里curLevel--都一定不要忘记了，否则就是死循环
            curLevel--;
        }
        return false;
    }

    public void add(int num) {
        int randomLevel = getRandomLevel();
        // 效率低，当然任何randomLevel的生成方法对于跳表而言都是上疏下密，因为在上层生成的结点必然会在下层建立联系。这是上层太密的话就影响效率
//        int randomLevel = random.nextInt(MAX_LEVEL + 1);
        realLevel = Math.max(randomLevel, realLevel);
        int curLevel = realLevel;
        SkipNode preNode = dummy;
        SkipNode newNode = new SkipNode(num, randomLevel);
        while (curLevel >= 0) {
            while (preNode.next[curLevel] != null && preNode.next[curLevel].val < num) {
                preNode = preNode.next[curLevel];
            }
            if (curLevel <= randomLevel) {
                SkipNode temp = preNode.next[curLevel];
                preNode.next[curLevel] = newNode;
                newNode.next[curLevel] = temp;
            }
            curLevel--;
        }
    }

    public boolean erase(int num) {
        int curLevel = realLevel;
        SkipNode preNode = dummy;
        boolean res = false;
        while (curLevel >= 0) {
            while (preNode.next[curLevel] != null && preNode.next[curLevel].val < num) {
                preNode = preNode.next[curLevel];
            }
            if (preNode.next[curLevel] != null && preNode.next[curLevel].val == num) {
                res = true;
                preNode.next[curLevel] = preNode.next[curLevel].next[curLevel];
            }
            curLevel--;
        }
        return res;
    }

}

/**
 * Review2
 * 基本上搞懂了，关键在于要从realLevel搜
 */
//class Node {
//
//    /**
//     * @param val
//     * @param maxLevel, begin with 0
//     */
//    public Node(int val, int maxLevel) {
//        // 这里不指明this的话赋值无效
//        this.val = val;
//        this.next = new Node[maxLevel + 1];
//    }
//
//    public int val;
//
//    public Node[] next;
//
//}
//
//class Skiplist {
//
//    /**
//     * Begin with 0
//     */
//    private int MAX_LEVEL = 31;
//
//    private double PROBABILTY = 0.25;
//
//    private Random random = new Random();
//
//    /**
//     * 每次寻找要从实际建的高度寻找，不能从MAX_LEVEL，否则没有到它的话查找都是null
//     */
//    private int realLevel = -1;
//
//    private Node dummy = new Node(-1, MAX_LEVEL);
//
//    public Skiplist() {
//
//    }
//
//    /**
//     * 以PROBABILTY的概率向上爬level
//     * @return
//     */
//    private int getRandomLevel() {
//        int res = 0;
//        while (random.nextDouble() < PROBABILTY && res < MAX_LEVEL) {
//            res++;
//        }
//        return res;
//    }
//
//    public boolean search(int target) {
////        int curLevel;
//        int curLevel = realLevel;
//        Node curNode = dummy;
//        // 这样占的内存会稍稍多一点
//        while (curLevel >= 0) {
//            while (curNode.next[curLevel] != null && curNode.next[curLevel].val < target) {
//                curNode = curNode.next[curLevel];
//            }
//            if (curNode.next[curLevel] != null && curNode.next[curLevel].val == target) {
//                return true;
//            }
//            curLevel--;
//        }
////         这样一直搜到底层在某种情况下快一丢丢
//        // 主要是if的比较次数减少了，但是一定要搜到底这个次数增加了
////        for (curLevel = realLevel; curLevel >= 0; curLevel--) {
////            while (curNode.next[curLevel] != null && curNode.next[curLevel].val < target) {
////                curNode = curNode.next[curLevel];
////            }
////        }
////        if (curNode.next[0] != null && curNode.next[0].val == target) {
////            return true;
////        }
//        return false;
//    }
//
////    public void add(int num) {
////        int curLevel = getRandomLevel();
////        realLevel = Math.max(curLevel, realLevel);
////        Node newNode = new Node(num, MAX_LEVEL);
////        Node pre = dummy;
////        while (curLevel >= 0) {
////            while (pre.next[curLevel] != null && pre.next[curLevel].val < num) {
////                pre = pre.next[curLevel];
////            }
////            Node temp = pre.next[curLevel];
////            pre.next[curLevel] = newNode;
////            newNode.next[curLevel] = temp;
////            curLevel--;
////        }
////    }
//
//    public void add(int num) {
//        int randomLevel = getRandomLevel();
//        realLevel = Math.max(randomLevel, realLevel);
//        int curLevel = realLevel;
//        // 小小的改动，大大的提升；不会越界因为之后在curLevel <= randomLevel时才会访问newNode的后继结点
//        // Q:虽然从逻辑上来说多余的都不会访问，但是可能是物理上数组小的话查找起来也更快？
//        Node newNode = new Node(num, randomLevel);
////        Node newNode = new Node(num, MAX_LEVEL);
//        Node pre = dummy;
//        // 注意这里很关键，任何时候的包括search、erase和add时候的查找都要从最高的realLevel查找，否则无法发挥跳表的最佳功效
//        while (curLevel >= 0) {
//            while (pre.next[curLevel] != null && pre.next[curLevel].val < num) {
//                pre = pre.next[curLevel];
//            }
//            // 搜的时候从最顶搜，但是只在randomLevel及以下插入
//            if (curLevel <= randomLevel) {
//                Node temp = pre.next[curLevel];
//                pre.next[curLevel] = newNode;
//                newNode.next[curLevel] = temp;
//            }
//            curLevel--;
//        }
//    }
//
//    /**
//     * Erase should level by level because need to know every pre node
//     *
//     * @param num
//     * @return
//     */
//    public boolean erase(int num) {
//        int curLevel = realLevel;
//        Node pre = dummy;
//        boolean res = false;
//        while (curLevel >= 0) {
//            while (pre.next[curLevel] != null && pre.next[curLevel].val < num) {
//                pre = pre.next[curLevel];
//            }
//            if (pre.next[curLevel] != null && pre.next[curLevel].val == num) {
//                pre.next[curLevel] = pre.next[curLevel].next[curLevel];
//                res = true;
//            }
//            curLevel--;
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Skiplist skiplist = new Skiplist();
//        skiplist.add(1);
//        skiplist.add(2);
//        skiplist.add(3);
//        System.out.println(skiplist.search(0));
//        skiplist.add(4);
//        System.out.println(skiplist.search(1));
//        skiplist.erase(0);
//        skiplist.erase(1);
//        System.out.println(skiplist.search(1));
//    }
//
//}

/**
 * Review1
 * 不知道这样改为什么还是不行
 */
//class Node {
//    public int val;
//
//    public Node[] next;
//
//    Node(int val, int maxLevel) {
//        this.val = val;
//        this.next = new Node[maxLevel + 1];
//    }
//
//}
//
//class Skiplist {
//
//    private static final int MAX_LEVEL = 31;
//
//    private static final double P_FACTOR = 0.25;
//
//    private int trueLevel;
//
//    private Node dummy;
//
//    private Random random;
//
//    public Skiplist() {
//        trueLevel = -1;
//        dummy = new Node(-1, MAX_LEVEL);
//        random = new Random();
//    }
//
//    public boolean search(int target) {
//        Node cur = dummy;
//        for (int i = trueLevel; i >= 0; i--) {
//            while (cur.next[i] != null && cur.next[i].val <= target) {
//                cur = cur.next[i];
////                if (cur.val == target) {
////                    return true;
////                }
//            }
//        }
//        if (cur != null && cur.val == target) {
//            return true;
//        }
//        return false;
//    }
//
//    public void add(int num) {
//        Node cur = dummy;
//        Node newNode = new Node(num, MAX_LEVEL);
////        int randomLevel = (int) (Math.random() * MAX_LEVEL);
//        int randomLevel = getRandomLevel();
//        if (randomLevel > trueLevel) {
//            trueLevel = randomLevel;
//        }
//        Node[] update = new Node[MAX_LEVEL + 1];
//        Arrays.fill(update, dummy);
//        for (int i = randomLevel; i >= 0; i--) {
//            while (cur.next[i] != null && cur.next[i].val <= num) {
//                cur = cur.next[i];
//            }
//            update[i] = cur;
//        }
//        for (int i = randomLevel; i >= 0; i--) {
//            newNode.next[i] = update[i].next[i];
//            update[i].next[i] = newNode;
//        }
//    }
//
//    public boolean erase(int num) {
//        boolean res = false;
//        Node cur = dummy;
//        for (int i = trueLevel; i >= 0; i--) {
//            while (cur.next[i] != null && cur.next[i].val < num) {
//                cur = cur.next[i];
//            }
//            if (cur.next[i] != null && cur.next[i].val == num) {
//                res = true;
//                cur.next[i] = cur.next[i].next[i];
//                // 注意这里的条件判断要在删除之后
//                if (cur == dummy && cur.next[i] == null) {
//                    trueLevel--;
//                }
//            }
//        }
//        return res;
//    }
//
//    private int getRandomLevel() {
//        int res = 0;
//        while (res < MAX_LEVEL && random.nextDouble() < P_FACTOR) {
//            res++;
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Skiplist skiplist = new Skiplist();
//        skiplist.add(1);
//        skiplist.add(2);
//        skiplist.add(3);
//        System.out.println(skiplist.search(0));
//        skiplist.add(4);
//        System.out.println(skiplist.search(1));
//        skiplist.add(5);
//        System.out.println(skiplist.search(3));
//        System.out.println(skiplist.search(6));
//    }
//}

/**
 * Success2
 * 应该是正确实现了跳表的思想的
 * 但是实际上随机插入层级用(int) (Math.random() * MAX_HEIGHT)反而比randomLevel = getRandomLevel()高效
 * 因为边插入边更新使得行为反常且效率低，虽然不知道为什么
 */
//class Skiplist {
//
//    class Node {
//
//        public int val;
//
//        public Node[] next;
//
//        public Node(int val) {
//            this.val = val;
//            next = new Node[MAX_HEIGHT + 1];
//        }
//
//    }
//
//    private static final int MAX_HEIGHT = 31;
//
//    private static final double P_FACTOR = 0.25;
//
//    private int trueHeight = -1;
//
//    private Node dummy;
//
//    public Skiplist() {
//        dummy = new Node(-1);
//    }
//
//    /**
//     * 实际上最简单的逻辑就是一层一层地跳，一层一层地向后遍历
//     * 注意逻辑要这样完备地思考完备地写，至于实际情况是怎样的它自然不满足条件就会略过，不会做多余的工作，你从一开始就不能从特殊情况去思考
//     * @param target
//     * @return
//     */
//    public boolean search(int target) {
//        int curLevel = trueHeight;
//        Node curNode = dummy;
//        while (curLevel >= 0) {
//            while (curNode.next[curLevel] != null && target > curNode.next[curLevel].val) {
//                curNode = curNode.next[curLevel];
//            }
//            // 注意这个if语句应该放在内层while的后面，因为while循环后的位置才能充分判断是否有if的可能，否则判断不充分会遗漏情况
//            // 而且如果是等于的情况也不需要继续向下跳了，所以这个判断逻辑应该在当前层，只有不满足才需要继续向下跳
//            if (curNode.next[curLevel] != null && curNode.next[curLevel].val == target) {
//                return true;
//            }
//            curLevel--;
//        }
//        return false;
//    }
//
//    /**
//     * Failure2
//     * 注意对于每一个search来说，不一定会遍历到最底层
//     * 但我这样实际上是只是跳到第一个合适的层级，然后就是在当前层级继续遍历，如果找不到没有继续向下跳了寻找细粒度的层级，这样实际上是不完备的
//     * @param target
//     * @return
//     */
////    public boolean search(int target) {
////        int curLevel = trueHeight;
////        Node curNode = dummy;
////        while (curLevel >= 0 && curNode.next[curLevel] != null && target < curNode.next[curLevel].val) {
////            curLevel--;
////        }
////        while (curLevel >= 0 && curNode.next[curLevel] != null && target >= curNode.next[curLevel].val) {
////            if (curNode.next[curLevel].val == target) {
////                return true;
////            }
////            curNode = curNode.next[curLevel];
////        }
////        return false;
////    }
//
//    /**
//     * Failure1
//     * 注意如果这样写的话，思路是对的，但是代码实现有问题
//     * 当外层就是需要比较的那一层时就不会进入到当层向后逐个结点比较的循环中，相当于这种情况的遍历就提前结束了
//     * @param target
//     * @return
//     */
////    public boolean search(int target) {
////        int curLevel = trueHeight;
////        Node curNode = dummy;
////        while (curLevel >= 0 && curNode.next[curLevel] != null && target <= curNode.next[curLevel].val) {
////            if (curNode.next[curLevel].val == target) {
////                return true;
////            }
////            curLevel--;
////            while (curLevel >= 0 && curNode.next[curLevel] != null && target > curNode.next[curLevel].val) {
////                curNode = curNode.next[curLevel];
////            }
////        }
////        return false;
////    }
//
//    /**
//     * 注意对于每一个add来说，一定会在其所有层下面建立连接关系
//     * 也就是说add每层的连接关系是完全的，search用到的连接不是完全的，见好就收
//     * @param num
//     */
//    public void add(int num) {
//        // 注意类型转换的优先级高于*
////        int randomLevel = (int) (Math.random() * MAX_HEIGHT);
//        int randomLevel = getRandomLevel();
//        // 注意这句不要漏了，否则search的起始搜索层就毫无变化
//        trueHeight = Math.max(trueHeight, randomLevel);
//        Node newNode = new Node(num);
//        Node curNode = dummy;
//        for (int i = randomLevel; i >= 0; i--) {
//            while (curNode.next[i] != null && num > curNode.next[i].val) {
//                curNode = curNode.next[i];
//            }
//            // 注意这里是curNode的下一个
//            Node temp = curNode.next[i];
//            curNode.next[i] = newNode;
//            newNode.next[i] = temp;
//        }
//    }
//
//    /**
//     * 注意无论是添加还是删除，始终都有一个搜索的过程，所以还是要从上向下搜索以最大程度地利用跳表的结构
//     * @param num
//     * @return
//     */
//    public boolean erase(int num) {
//        boolean res = false;
//        Node curNode = dummy;
//        for (int i = trueHeight; i >= 0; i--) {
//            while (curNode.next[i] != null && num > curNode.next[i].val) {
//                curNode = curNode.next[i];
//            }
//            // 注意这儿的逻辑处理，既然卡到这儿了，只有这种情况才需要删除且处理结果为true
//            if (curNode.next[i] != null && curNode.next[i].val == num) {
//                res = true;
//                curNode.next[i] = curNode.next[i].next[i];
//            }
//        }
//        // 在循环结束后更新 trueHeight
//        while (trueHeight >= 0 && dummy.next[trueHeight] == null) {
//            trueHeight--;
//        }
//
//        return res;
//    }
//
////    private int getRandomLevel() {
////        int res = 0;
////        while (Math.random() < P_FACTOR && res < MAX_HEIGHT) {
////            res++;
////        }
////        return res;
////    }
//
//    private Random random = new Random();
//
//    private int getRandomLevel() {
//        int lv = 0;
//        /* 随机生成 lv */
//        while (random.nextDouble() < P_FACTOR && lv < MAX_HEIGHT) {
//            lv++;
//        }
//        return lv;
//    }
//
//    public static void main(String[] args) {
//        Skiplist skiplist = new Skiplist();
//        skiplist.add(1);
//        skiplist.add(2);
//        skiplist.add(3);
//        System.out.println(skiplist.search(0));
//        skiplist.add(4);
//        System.out.println(skiplist.search(1));
//        skiplist.add(5);
//        System.out.println(skiplist.search(3));
//        System.out.println(skiplist.search(6));
//    }
//}

/**
 * Failure1
 * 错误地实现成了单链表
 */
//class Skiplist {
//
//    class Node {
//        public int value;
//
//        Node[] next;
//
//        public Node(int value, int maxLevel) {
//            this.value = value;
//            this.next = new Node[maxLevel];
//        }
//
//    }
//
//    private Node head;
//
//    /**
//     * 由于没有提供限制SkipList最大level的构造函数接口，所以直接在这个类里用static final定死了
//     */
//    private static final int MAX_SUM_LEVELS = 32;
//
//    /**
//     * 这是实际建的层数，也就是实际最高层，否则每次都从MAX_LEVEL找的话就多余了
//     * 从0开始，免去索引麻烦
//     */
//    private int maxLevel;
//
//
//    public Skiplist() {
//        this.head = new Node(-1, MAX_SUM_LEVELS);
//        this.maxLevel = 0;
//    }
//
//    public boolean search(int target) {
//        Node cur = head;
//        int curLevel = maxLevel;
//        while (curLevel >= 0) {
//            while (curLevel >= 0 && (cur.next[curLevel] == null || target < cur.next[curLevel].value)) {
//                curLevel--;
//            }
//            if (curLevel >= 0 && cur.next[curLevel] != null && target == cur.next[curLevel].value) {
//                return true;
//            }
//            if (curLevel < 0 || cur.next[curLevel] == null) {
//                return false;
//            }
//            cur = cur.next[curLevel];
//        }
//        return false;
//
//    }
//
//    /**
//     * 这里是实现实际上是有问题的，但是由于错误地使用了(int) Math.random() * MAX_SUM_LEVELS，相当于就是个单链表了，错上加错反而正确了
//     * 只不过就没有跳表的性能优势了
//     * 注意如果是一个节点被添加到了某个层作为最高层，那么添加其下所有层的连接关系以保证其他任何层在搜索时都能访问到这个节点，
//     * 注意理解这里某个节点处于某一层其实并不是说有个额外的实体，而是说这一层的连接有它
//     * 跳表所有的节点实际上都只有一个实体，所谓越上面越稀疏、越下面越紧凑并不是说的实体，而是说的连接网，这里的层实际上就是虚拟的连接而非实体
//     * 越上面层的连接越稀疏，越下面层的连接越紧凑
//     * ！！！而且由于下面层的连接更紧凑，所以下面层的连接越细粒度，你一旦往下跳，那么所能访问到的下一层的连接一定是比当前层的连接更小、更细粒度
//     * @param num
//     */
//    public void add(int num) {
//        int randomLevel = (int) Math.random() * MAX_SUM_LEVELS;
//        if (randomLevel > maxLevel) {
//            maxLevel = randomLevel;
//        }
//        Node cur = head;
//        while (cur.next[randomLevel] != null && num > cur.next[randomLevel].value) {
//            cur = cur.next[randomLevel];
//        }
//        // 注意这儿声明next数组的长度都为MAX_SUM_LEVELS比较方便，反正不实际生成对象也没有影响，如果是用maxLevel的注意+1
//        Node newNode = new Node(num, MAX_SUM_LEVELS);
//        for (int i = 0; i <= randomLevel; i++) {
//            if (cur.next[i] != null) {
//                Node temp = cur.next[i];
//                cur.next[i] = newNode;
//                cur.next[i].next[i] = temp;
//            } else {
//                cur.next[i] = newNode;
//            }
//        }
//    }
//
//    public boolean erase(int num) {
//        boolean res = false;
//        for (int i = 0; i <= maxLevel; i++) {
//            Node cur = head;
//            while (cur.next[i] != null && num > cur.next[i].value) {
//                cur = cur.next[i];
//            }
//            if (cur.next[i] != null && num == cur.next[i].value) {
//                cur.next[i] = cur.next[i].next[i];
//                res = true;
//            }
//        }
//        return res;
//    }
//}

/**
 * Success1
 */
//class Skiplist {
//
//    private static final int MAX_LEVEL = 32;
//
//    private static final double SKIPLIST_P = 0.25;
//
//    /**
//     * head本身的val不重要，重要的是它的next数组存储了每一层链表的开始节点
//     */
//    private Node head;
//
//    /**
//     * 注意这里Level的表示都是从1开始的
//     * 表示实际建的层数
//     */
//    private int curLevel;
//
//
//
//    public Skiplist() {
//        // 注意这里初始虽然一层也没有，但是head初始化了MAX_LEVEL个next指针
//        // 这是很有必要的，因为后续的curLevel可能增加，但是head索引从一开始就一直需要，在这个过程中head需要索引的层数可能在不断变化
//        // 如果后续动态改变很麻烦，所以最开始就赋最极限的值最方便
//        this.head = new Node(-1, MAX_LEVEL);
//        this.curLevel = 0;
//    }
//
//    public boolean search(int target) {
//        Node cur = this.head;
//        // 注意这里都是从实际建的最高层开始查找，跳表的设计决定了查找一定是从上往下找的
//        for (int i = curLevel - 1; i >= 0; i--) {
//            // 注意这里是小于没有等于，找到每层小于target的边界值
//            while (cur.next[i] != null && cur.next[i].val < target) {
//                cur = cur.next[i];
//            }
//        }
//        // 注意虽然是层层下去简化索引，但是最后一定会落到底层，跳表只是简化了这个底层顺序查找的过程
//        // 这是底层第一个大于等于target的值
//        cur = cur.next[0];
//        return cur != null && cur.val == target;
//
//    }
//
//    /**
//     * Success2
//     * 确定了randomLevel再去找将要插入的那些前驱节点，而不是先找到所有curLevel的将要插入的前驱节点
//     * 但是这样更慢，注意跳表的性质，一定是从最上层往下遍历更快，你从中间的randomLevel开始找反而是更慢了
//     */
//    public void add(int num) {
//        // 存放更新的位置，也就是在各层上将要插入节点的前驱节点
//        Node[] update = new Node[MAX_LEVEL];
//        // 将update所有的值都赋为head
//        Arrays.fill(update, head);
//        Node cur = this.head;
//        int randomLevel = randomLevel();
//        // 把最高层数从当前层数提到可能增长的随机层数
//        this.curLevel = Math.max(this.curLevel, randomLevel);
//        // 注意插入还是从randomLevel而不是curLevel开始，从意义上理解没必要从最高层开始插入，虽然实际上curLevel可能被randomLevel覆盖
//        Node newNode = new Node(num, randomLevel);
//        for (int i = randomLevel - 1; i >= 0; i--) {
//            // 在当前层不断往后寻找合适的前驱节点
//            while (cur.next[i] != null && cur.next[i].val < num) {
//                cur = cur.next[i];
//            }
//            // 存储找到的当前层的前驱节点
//            update[i] = cur;
//        }
//        // 这里从上往下插还是从下网上插性能没有区别
//        for (int i = 0; i < randomLevel; i++) {
//            // 这个逻辑是很好理解的，实际上都是处理的这一层的插入，很普通的那种
//            // 但是将newNode插入到所有层，newNode其实也就有了所有层下一节点的全局信息
//            newNode.next[i] = update[i].next[i];
//            update[i].next[i] = newNode;
//        }
//    }
//
//    /**
//     * Success1
//     * 先找出所有前驱节点，再插入
//     */
////    public void add(int num) {
////        // 存放更新的位置，也就是在各层上将要插入节点的前驱节点
////        Node[] update = new Node[MAX_LEVEL];
////        // 将update所有的值都赋为head
////        Arrays.fill(update, head);
////        Node cur = this.head;
////        // 先把所有层的前驱节点找出来，后续要插入到哪些层再看
////        for (int i = curLevel - 1; i >= 0; i--) {
////            // 在当前层不断往后寻找合适的前驱节点
////            while (cur.next[i] != null && cur.next[i].val < num) {
////                cur = cur.next[i];
////            }
////            // 存储找到的当前层的前驱节点
////            update[i] = cur;
////        }
////        int randomLevel = randomLevel();
////        // 把最高层数从当前层数提到可能增长的随机层数
////        this.curLevel = Math.max(this.curLevel, randomLevel);
////        // 注意插入还是从randomLevel而不是curLevel开始，从意义上理解没必要从最高层开始插入，虽然实际上curLevel可能被randomLevel覆盖
////        Node newNode = new Node(num, randomLevel);
////        // 这里从上往下插还是从下网上插性能没有区别
////        for (int i = 0; i < randomLevel; i++) {
////            // 这个逻辑是很好理解的，实际上都是处理的这一层的插入，很普通的那种
////            // 但是将newNode插入到所有层，newNode其实也就有了所有层下一节点的全局信息
////            newNode.next[i] = update[i].next[i];
////            update[i].next[i] = newNode;
////        }
////    }
//
//    /**
//     * 和查找类似，但是需要修改目标节点的所有前驱关系
//     */
//    public boolean erase(int num) {
//        Node[] update = new Node[MAX_LEVEL];
//        Node cur = this.head;
//        // 和查找过程一样，但是需要额外存储每一层的前驱节点信息
//        for (int i = curLevel - 1; i >= 0; i--) {
//            while (cur.next[i] != null && cur.next[i].val < num) {
//                cur = cur.next[i];
//            }
//            update[i] = cur;
//        }
//        // 和查找一样，最底层的这个数是判断目标
//        cur = cur.next[0];
//        if (cur == null || cur.val != num) {
//            return false;
//        }
//        // 注意删除的时候从下往上比较快
//        // 注意这里的删除也没有释放存储空间什么的，只要能将其从整个跳表能够索引到的范围解除关系即可
//        for (int i = 0; i < curLevel; i++) {
//            // 注意只删到有num的那一层
//            if (update[i].next[i] != cur) {
//                break;
//            }
//            // 有num的层解除关联关系
//            // cur.next[i]修改为null与否关系不大
//            update[i].next[i] = cur.next[i];
//        }
//        // 注意在删除的时候，有可能最上层没有实际元素就只有head，则缩短实际层数
//        while (curLevel > 1 && head.next[curLevel - 1] == null) {
//            curLevel--;
//        }
//        return true;
//    }
//
//    /**
//     * 随机返回一个1-MAX_LEVEL之间的层数，按SKIPLIST_P的概率层层晋升
//     * 则SKIPLIST_P的概率返回2，SKIPLIST_P/2的概率返回3，,SKIPLIST_P/4的概率返回4，以此类推
//     */
//    private int randomLevel() {
//        int level = 1;
//        // 这里其实就是有SKIPLIST_P的概率晋升，和Math.random() > 1 - SKIPLIST_P的概率是等价的
//        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
//            level++;
//        }
//        return level;
//    }
//
//    class Node {
//
//        int val;
//
//        /**
//         * 注意这个next的信息量很大，不仅存了各下层的节点，当前层的后一个节点也存了
//         * 当然一般各上层的节点都是null，没必要存用不上
//         */
//        Node[] next;
//
//        public Node(int val, int maxLevel) {
//            this.val = val;
//            this.next = new Node[maxLevel];
//        }
//    }
//}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */

//leetcode submit region end(Prohibit modification and deletion)
