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
// Related Topics 设计 哈希表 链表 双向链表 👍 94 👎 0


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)

class LRUCache {

    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    private LinkedList<Integer> queue = new LinkedList<>();

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            // 注意这种写法，使用包装类可以直接删除对应的元素
            queue.remove((Integer) key);
            queue.add(key);
            return hashMap.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        // 注意这里的put不一定是添加的新key，也有可能是修改原有的key，这样不会超出capacity，要分类讨论，因为删除的位置不同
        if (hashMap.containsKey(key)) {
            queue.remove((Integer) key);
        }
        if (!hashMap.containsKey(key) && hashMap.size() >= capacity) {
            int toRemove = queue.poll();
            hashMap.remove(toRemove);
        }
        queue.add(key);
        hashMap.put(key, value);
    }
}

/**
 * Success1
 * 思路正确，但是用LinkedList的插入删除时间复杂度是O(n)
 * 注意用队列的话，最近访问是要将访问的元素找到从队列中取出并放到队尾
 */
//class LRUCache {
//
//    private HashMap<Integer, Integer> hashMap = new HashMap<>();
//
//    private LinkedList<Integer> queue = new LinkedList<>();
//
//    private int capacity;
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        if (hashMap.containsKey(key)) {
//            queue.remove((Integer) key);
//            queue.add(key);
//            return hashMap.get(key);
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        // 注意这里的put不一定是添加的新key，也有可能是修改原有的key，这样不会超出capacity，要分类讨论，因为删除的位置不同
//        if (hashMap.containsKey(key)) {
//            queue.remove((Integer) key);
//        }
//        if (!hashMap.containsKey(key) && hashMap.size() >= capacity) {
//            int toRemove = queue.poll();
//            hashMap.remove(toRemove);
//        }
//        queue.add(key);
//        hashMap.put(key, value);
//    }
//}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
