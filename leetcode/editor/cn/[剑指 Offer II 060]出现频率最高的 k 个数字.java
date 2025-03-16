//给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
//
//
// 示例 2:
//
//
//输入: nums = [1], k = 1
//输出: [1]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// k 的取值范围是 [1, 数组中不相同的元素的个数]
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
//
//
//
//
// 进阶：所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
//
//
//
//
// 注意：本题与主站 347 题相同：https://leetcode-cn.com/problems/top-k-frequent-elements/
//
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 50 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
// PriorityQueue实际上实现了小顶堆
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
//        // 重写比较器变成大顶堆
//        PriorityQueue<Map.Entry<Integer, Integer>> MaxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return o2.getValue() - o1.getValue();
//            }
//        });
//        // This is for initial comparation
//        MaxHeap.offer(new AbstractMap.SimpleEntry<Integer, Integer>(0, 0));
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//            if (hashMap.get(num) > hashMap.get(MaxHeap.peek())) {
//                // No need to poll, it will adjust itself, or there may not be enough node
////                queue.poll();
//                MaxHeap.offer(num);
//            }
//        }
//        int[] res = new int[k];
//        for (int i = 0; i < k; i++) {
//            res[k] = MaxHeap.poll();
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int k = 2;
//        int[] res = solution.topKFrequent(nums, k);
//        System.out.println(res);
//    }
//}
// 感觉这和那个第k大有点像，但是记不起来了

/**
 * Failure1
 */
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        int keyOfMaxVal = 0;
//        int maxVal = 0;
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//            if (hashMap.get(num) > maxVal) {
//                keyOfMaxVal = num;
//                maxVal = hashMap.get(num);
//            }
//        }
//        // Max decrease
//        // reserve one more to adjust
//        int[] res = new int[k];
//        res[0] = keyOfMaxVal;
//        // 注意遍历HashMap的方法
//        // We should first fill the max
//        Iterator<Integer> iterator = hashMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            int key = iterator.next();
//            // if it has been resolved
//            if (key == keyOfMaxVal) {
//                continue;
//            }
//            int value = hashMap.get(key);
//            // temp is the from place to replace
//            int temp = k;
//            for (int i = 0; i < k; i++) {
//                // attention here we should include =
//                if (res[i] != 0 && value <= hashMap.get(res[i])) {
//                    temp = i + 1;
//                }
//            }
//            // can replace
//            if (temp != k) {
//                for (int i = k - 1; i > temp; i--) {
//                    res[i] = res[i - 1];
//                }
//                res[temp] = key;
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
//        int k = 2;
//        int[] res = solution.topKFrequent(nums, k);
//        System.out.println(res);
//    }
//}

/**
 * Failure2
 * 注意还是得用小顶堆
 * 堆中还是存储的最大的数，但是要把最大中的最小给挤出去，所以要用小顶堆
 * 注意可以外部控制堆的大小
 */
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//        }
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//        Iterator<Integer> iterator = hashMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            int key = iterator.next();
//            if (minHeap.size() < k) {
//                minHeap.offer(key);
//            } else {
//                if (hashMap.get(key) > hashMap.get(minHeap.peek())) {
//                    minHeap.poll();
//                    minHeap.offer(key);
//                }
//            }
//        }
//        int[] res = new int[k];
//        for (int i = 0; i < k; i++) {
//            res[i] = minHeap.poll();
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
//        int k = 2;
//        int[] res = solution.topKFrequent(nums, k);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 注意heap中还是得存键值对的形式，否则始终对应不上
 * 键值对其实用数组就可以，应该更节省空间吧
 * 当然数组存键值对只能是同一类型的
 */
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//        }
//        // 这是构造了一个具有初始容量、重写比较数据结构的小顶堆
//        // 但是不管限不限制初始容量，最开始加值的时候还是要比较与k的大小分类讨论，因为没有值
//        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, (a, b) -> a.getValue() - b.getValue());
//        for (Map.Entry<Integer, Integer> num : hashMap.entrySet()) {
//            if (minHeap.size() < k) {
//                minHeap.offer(num);
//            } else {
//                if (num.getValue() > minHeap.peek().getValue()) {
//                    minHeap.poll();
//                    minHeap.offer(num);
//                }
//            }
//        }
//        int[] res = new int[k];
//        for (int i = 0; i < k; i++) {
//            res[i] = minHeap.poll().getKey();
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
//        int k = 2;
//        int[] res = solution.topKFrequent(nums, k);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 用数组会稍微更节省空间
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        // 这是构造了一个具有初始容量、重写比较数据结构的小顶堆
        /*Q:但是这个初始容量似乎并没有什么实际用处，没有相关的底层操作，更多地是提示作用，还需要自己手动控制容量删除元素，这对于
        * k大/小问题来说很关键*/
        // 但是不管限不限制初始容量，最开始加值的时候还是要比较与k的大小分类讨论，因为没有值
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, (a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> num : hashMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(new int[]{num.getKey(), num.getValue()});
            } else {
                if (num.getValue() > minHeap.peek()[1]) {
                    minHeap.poll();
                    minHeap.offer(new int[]{num.getKey(), num.getValue()});
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        int[] res = solution.topKFrequent(nums, k);
        System.out.println(res);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
