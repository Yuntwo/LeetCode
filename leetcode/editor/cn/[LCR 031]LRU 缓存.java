//
// 运用所掌握的数据结构，设计和实现一个 LRU (Least Recently Used，最近最少使用) 缓存机制 。
//
//
// 实现 LRUCache 类：
//
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
//
// 示例：
//
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
//
//
//
//
// 提示：
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 10000
// 0 <= value <= 10⁵
// 最多调用 2 * 10⁵ 次 get 和 put
//
//
//
//
// 进阶：是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
//
// 注意：本题与主站 146 题相同：https://leetcode-cn.com/problems/lru-cache/
//
// Related Topics 设计 哈希表 链表 双向链表 👍 107 👎 0



//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

import java.util.HashMap;

/**
 * Review1
 */
class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node dumyHead, dumyTail;

    private HashMap<Integer, Node> hashMap;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dumyHead = new Node();
        this.dumyTail = new Node();
        dumyHead.next = dumyTail;
        dumyTail.prev = dumyHead;
        this.hashMap = new HashMap<>();
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            Node node = hashMap.get(key);
            moveToTail(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            Node node = hashMap.get(key);
            node.value = value;
            moveToTail(node);
        } else {
            Node node = new Node(key, value);
            hashMap.put(key, node);
            addToTail(node);
            if (hashMap.size() > capacity) {
                // 注意这里两种存储数据结构要保持数据一致性还是容易漏
                hashMap.remove(dumyHead.next.key);
                remove(dumyHead.next);
            }
        }
    }

    private void addToTail(Node node) {
        dumyTail.prev.next = node;
        node.next = dumyTail;
        node.prev = dumyTail.prev;
        dumyTail.prev = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToTail(Node node) {
        remove(node);
        addToTail(node);
    }

}

/**
 * Success1
 * 注意结点还是要同时存储key和value才能在HashMap移除队列中的头节点时知道是哪一个key
 * 添加冗余的不为null的不相等的head和tail逻辑会好处理很多
 */
//class LRUCache {
//
//    class Node {
//        int key;
//        int value;
//        Node prev;
//        Node next;
//        public Node() {}
//        public Node(int key, int value) {
//            this.key = key;
//            this.value = value;
//        }
//    }
//
//    private Map<Integer, Node> cache = new HashMap<>();
//    private int size;
//    private int capacity;
//    private Node head, tail;
//
//    public LRUCache(int capacity) {
//        this.size = 0;
//        this.capacity = capacity;
//        head = new Node();
//        tail = new Node();
//        head.next = tail;
//        tail.prev = head;
//    }
//
//    public int get(int key) {
//        Node node = cache.get(key);
//        if (node == null) {
//            return -1;
//        }
//        moveToHead(node);
//        return node.value;
//    }
//
//    public void put(int key, int value) {
//        Node node = cache.get(key);
//        if (node == null) {
//            Node newNode = new Node(key, value);
//            cache.put(key, newNode);
//            addToHead(newNode);
//            ++size;
//            if (size > capacity) {
//                Node tail = removeTail();
//                cache.remove(tail.key);
//                --size;
//            }
//        } else {
//            node.value = value;
//            moveToHead(node);
//        }
//    }
//
//    private void addToHead(Node node) {
//        node.prev = head;
//        node.next = head.next;
//        head.next.prev = node;
//        head.next = node;
//    }
//
//    private void removeNode(Node node) {
//        node.prev.next = node.next;
//        node.next.prev = node.prev;
//    }
//
//    private void moveToHead(Node node) {
//        removeNode(node);
//        addToHead(node);
//    }
//
//    private Node removeTail() {
//        Node res = tail.prev;
//        removeNode(res);
//        return res;
//    }
//}

/**
 * Failure2
 * 有些特殊情况处理起来还是很麻烦
 */
//class LRUCache {
//
//    class DeNode {
//
//        public int key;
//
//        public int val;
//
//        public DeNode pre;
//
//        public DeNode next;
//
//        public DeNode(int key, int val, DeNode pre, DeNode next) {
//            this.key = key;
//            this.val = val;
//            this.pre = pre;
//            this.next = next;
//        }
//    }
//
//
//    private DeNode head, tail;
//
//    private HashMap<Integer, DeNode> hashMap;
//
//    private int capacity;
//
//    private int count = 0;
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        this.hashMap = new HashMap<>();
//    }
//
//    public int get(int key) {
//        if (hashMap.containsKey(key)) {
//            DeNode toInsert = hashMap.get(key);
//            moveToTail(toInsert);
//            return toInsert.val;
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        DeNode toInsert;
//        if (hashMap.containsKey(key)) {
//            toInsert = hashMap.get(key);
//            toInsert.val = value;
//            moveToTail(toInsert);
//        } else {
//            toInsert = new DeNode(key, value, null, null);
//            if (hashMap.size() == capacity) {
//                hashMap.remove(head.key);
//                removeHead();
//            }
//            addToTail(toInsert);
//            hashMap.put(key, toInsert);
//        }
//    }
//
//    private void addToTail(DeNode toInsert) {
//        if (tail == null) {
//            tail = toInsert;
//            head = toInsert;
//            return;
//        }
//        tail.next = toInsert;
//        toInsert.pre = tail;
//        tail = toInsert;
//    }
//
//    private void moveToTail(DeNode toInsert) {
//        if (toInsert == tail) {
//            return;
//        }
//        // 插入到链表末尾
//        if (toInsert.pre != null) {
//            // 截断两头
//            toInsert.pre.next = toInsert.next;
//            toInsert.pre = toInsert.pre.pre;
//        } else {
//            // 注意这种情况需要说明toInsert本身是head需要变head
//            if (toInsert == head) {
//                head = toInsert.next;
//            }
//        }
//        // 分情况截断
//        if (toInsert.next != null) {
//            // 截断两头
//            toInsert.next.pre = toInsert.pre;
//            toInsert.next = toInsert.next.next;
//        }
//        toInsert.pre = tail;
//        tail.next = toInsert;
//        tail = toInsert;
//    }
//
//    private void removeHead() {
//        if (head.next == null) {
//            head = null;
//            tail = null;
//            return;
//        }
//        head = head.next;
//    }
//
//    public static void main(String[] args) {
//        // case1
////        LRUCache lruCache = new LRUCache(2);
////        lruCache.put(1, 1);
////        lruCache.put(2, 2);
////        lruCache.get(1);
////        lruCache.put(3, 3);
////        lruCache.get(2);
////        lruCache.put(4, 4);
////        lruCache.get(1);
//        // case2
////        LRUCache lruCache = new LRUCache(2);
////        lruCache.put(1, 0);
////        lruCache.put(2, 2);
////        lruCache.get(1);
////        lruCache.put(3, 3);
////        lruCache.get(2);
////        lruCache.put(4, 4);
////        lruCache.get(1);
////        lruCache.get(3);
////        lruCache.get(4);
//        // case3
//        LRUCache lruCache = new LRUCache(1);
//        lruCache.put(2, 1);
//        lruCache.get(2);
//        lruCache.put(3, 2);
//        lruCache.get(2);
//        lruCache.get(3);
//    }
//}

/**
 * Failure1
 * 单纯用一个toTail来处理逻辑太混乱了
 */
//class LRUCache {
//
//    class DeNode {
//
//        public int val;
//
//        public DeNode pre;
//
//        public DeNode next;
//
//        public DeNode(int val, DeNode pre, DeNode next) {
//            this.val = val;
//            this.pre = pre;
//            this.next = next;
//        }
//    }
//
//
//    private DeNode head, tail;
//
//    private HashMap<Integer, DeNode> hashMap;
//
//    private int capacity;
//
//    private int count = 0;
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        this.hashMap = new HashMap<>();
//    }
//
//    public int get(int key) {
//        if (hashMap.containsKey(key)) {
//            DeNode toInsert = hashMap.get(key);
//            toTail(toInsert);
//            return toInsert.val;
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        DeNode toInsert;
//        if (hashMap.containsKey(key)) {
//            toInsert = hashMap.get(key);
//            toInsert.val = value;
//        } else {
//            toInsert = new DeNode(value, null, null);
//            if (hashMap.size() == capacity) {
//                hashMap.remove(head.val);
//                removeHead();
//            }
//            hashMap.put(key, toInsert);
//        }
//        if (head == null && tail == null) {
//            head = toInsert;
//            tail = toInsert;
//            return;
//        }
//        toTail(toInsert);
//    }
//
//    private void toTail(DeNode toInsert) {
//        // 插入到链表末尾
//        if (toInsert.pre != null) {
//            // 截断两头
//            toInsert.pre.next = toInsert.next;
//            toInsert.pre = toInsert.pre.pre;
//        } else {
//            // 注意这种情况需要变head
//            head = toInsert.next;
//        }
//        // 分情况截断
//        if (toInsert.next != null) {
//            // 截断两头
//            toInsert.next.pre = toInsert.pre;
//            toInsert.next = toInsert.next.next;
//        }
//        toInsert.pre = tail;
//        tail.next = toInsert;
//        tail = toInsert;
//    }
//
//    private void removeHead() {
//        head = head.next;
//        head.pre = null;
//    }
//
//    public static void main(String[] args) {
//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1, 1);
//        lruCache.put(2, 2);
//        lruCache.get(1);
//        lruCache.put(3, 3);
//        lruCache.get(2);
//        lruCache.put(4, 4);
//        lruCache.get(1);
//    }
//}

/**
 * Success1
 * 用可索引中间元素的队列，只操作头尾结点不够，时间复杂度为O(n)，效率也很低
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//class LRUCache {
//
//    private HashMap<Integer, Integer> hashMap;
//
//    private List<Integer> list;
//
//    private int capacity;
//
//    public LRUCache(int capacity) {
//        this.hashMap = new HashMap<>();
//        this.list = new LinkedList<>();
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        if (!hashMap.containsKey(key)) {
//            return -1;
//        } else {
//            // 注意必须使用包装类型，否则会被误认为是索引
//            list.remove((Integer) key);
//            list.add(key);
//            return hashMap.get(key);
//        }
//    }
//
//    public void put(int key, int value) {
//        if (!hashMap.containsKey(key)) {
//            if (hashMap.size() == capacity) {
//                hashMap.remove(list.get(0));
//                // 注意这里直接是remove索引为0
//                list.remove(0);
//            }
//            list.add(key);
//            hashMap.put(key, value);
//        } else {
//            list.remove((Integer) key);
//            list.add(key);
//            hashMap.put(key, value);
//        }
//    }
//
//    public static void main(String[] args) {
//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1, 1);
//        lruCache.put(2, 2);
//        lruCache.get(1);
//        lruCache.put(3, 3);
//        lruCache.get(2);
//        lruCache.put(4, 4);
//        lruCache.get(1);
//    }
//}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
