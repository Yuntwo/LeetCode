//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
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
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
//
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 1469 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Reivew1
 * 注意Priority要存Entry而不能只存Integer
 * 注意如果是topK的话只能全部遍历之后再找，不能一次遍历完成，否则key会重复，因为每次遍历可能value变了
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            // 注意参数顺序
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//            hashMap.put(num, hashMap.getOrDefault(0, num) + 1);
        }
        // 默认是最小堆
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
//            if (minHeap.size() < k || entry.getValue() > minHeap.peek().getValue()) {
//                minHeap.offer(entry);
//            }
            // 可以直接offer不比较
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll().getKey();
        }
        return res;
    }
}

/**
 * Failure1
 */
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
////        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
//        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//            if (minHeap.size() < k || hashMap.get(num) > minHeap.peek().getValue()) {
//                minHeap.add(new AbstractMap.SimpleEntry<>(num, hashMap.get(num)));
//
//            }
//            if (minHeap.size() > k) {
//                minHeap.poll();
//            }
//        }
//        int[] res = new int[k];
//        for (int i = 0; i < k; i++) {
//            res[i] = minHeap.poll().getKey();
//        }
//        return res;
//    }
//}

/**
 * Success1
 * 是我的实现方式不对吗，感觉效率没有特别高
 * 快速排序，时间复杂度为O(N)的解法
 * 注意Java似乎不像Python那样支持原生的多维数组(从这一点上来看Python有其独特的数学特性优势)
 * 也没有啦，好像Java也支持二维数组，而且基本数据类型和对象数据类型都支持
 */
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//        }
//        // 可以有多种实现，但是这种原生二维数组结构应该是最省空间的
//        // 但是如果直接引用HashMap中的EntrySet的话(不知道有没有实际生成其他对象)应该更省空间吧
//        Iterator<Map.Entry<Integer, Integer>> iterator = hashMap.entrySet().iterator();
//        int[][] freq = new int[hashMap.size()][2];
//        for (int i = 0; i < hashMap.size(); i++) {
//            Map.Entry<Integer, Integer> entry = iterator.next();
//            freq[i][0] = entry.getKey();
//            freq[i][1] = entry.getValue();
//        }
//        this.k = k;
//        quickSort(freq, 0, freq.length - 1);
//        int[] res = new int[k];
//        for (int i = 0; i < k; i++) {
//            res[i] = freq[i][0];
//        }
//        return res;
//    }
//
//    private int k;
//
//    /**
//     * 注意这里的快排要从大到小
//     */
//    private void quickSort(int[][] freq, int l, int r) {
//        // 注意这里传进来的l、r应该是可以直接用的，都是引用类型，只不过是直接改变那个变量的值而已
//        int left = l;
//        int right = r;
//        int[] pivot = freq[left];
//        while (left < right) {
//            // 注意快速排序中如果选定左边第一个为标定点的话，要先从右边最后一个循环，这样索引占位才能充分利用
//            while (freq[right][1] <= pivot[1] && left < right) {
//                right--;
//            }
//            freq[left] = freq[right];
//            while (freq[left][1] > pivot[1] && left < right) {
//                left++;
//            }
//            freq[right] = freq[left];
//        }
//        freq[left] = pivot;
//        // 不用再排了
//        if (left == k - 1) {
//            return;
//        // 只需要排左边就够了
//        } else if (left >= k - 1) {
//            quickSort(freq, l, left - 1);
//        // 只需要排右边就够了
//        } else {
//            quickSort(freq, left + 1, r);
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int k = 2;
//        Solution solution = new Solution();
//        int[] res = solution.topKFrequent(nums, k);
//        System.out.println(res);
//    }
//}
/**
 * Success2
 * 题解写法，虽然也是快速排序，但是和Success1的递归思路和寻找思路还是不一样的，这个时间复杂度肯定是更低，递归更浅
 * 当然思路不是那么直接，很巧妙
 * 但是它也不是双一百效率
 * 理解起来还稍微绕了一点
 */
//class Solution {
//    public int[] topKFrequent(int[] nums, int k) {
//        int n = nums.length;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//        }
//        List<int[]> arr = new ArrayList<>();
//        // 注意除了数组类型，Set类型也是可以直接用这种for语法遍历的
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            int key = entry.getKey();
//            // 注意value简写为val就好了
//            int val = entry.getValue();
//            arr.add(new int[]{key, val});
//        }
//        int[] ret = new int[k];
//        qSort(arr, 0, arr.size() - 1, ret, 0, k);
//        return ret;
//    }
//
//    /**
//     * @param retIndex 是答案中继续添加元素的位置，这里直接传递参数没有问题，也不需要返回它的相关信息什么的
//     */
//    private void qSort(List<int[]> value, int start, int end, int[] ret, int retIndex, int k) {
//        /*不要这一步也行，只是说增加随机性可能会更高效*/
//        // 随机选取一个start到end间的索引，随机值的话比固定选第一个值可能有更好的效果
//        int picked = (int) (Math.random() * (end - start + 1)) + start;
//        // 将这个随机选取的索引位置的值交换到第一个位置
//        // List集合交换操作很方便
//        Collections.swap(value, start, picked);
//
//        /*快速排序的正餐*/
//        // 注意这里的pivot就是直接要比较的E类型，不需要它的索引
//        int pivot = value.get(start)[1];
//        // 注意这里由于要进行额外的判断，所以不能直接修改start和end的值，而是要用额外的变量
//        int index = start;
//        // 注意这是快速排序比较调整的另一种写法
//        // 不是很懂这里的思路，但是效率应该也不会比那种写法差
//        for (int i = start + 1; i <= end; i++) {
//            if (value.get(i)[1] >= pivot) {
//                // 注意这里index的含义是>=pivot的索引的最后一个值
//                // 由于这里是直接swap的所以不存在会有值被覆盖什么的
//                Collections.swap(value, index + 1, i);
//                index++;
//            }
//        }
//        // 比较n和k的关系
//        // 这是>=pivot(及pivot的右边)的长度
//        int lLen = index - start + 1;
//        // 答案在左半边
//        if (k < lLen) {
//            // 将index位置上的数换到第一个位置，换哪个都行，因为start位置的数是<pivot的
//            Collections.swap(value, start, index);
//            // 只需要递归左边
//            qSort(value, start, index - 1, ret, retIndex, k);
//        // 答案是左右两边的结合
//        } else {
//            // 左边的全部放到答案中
//            for (int i = start; i <= index; i++) {
//                ret[retIndex++] = value.get(i)[0];
//            }
//            // 注意如果lLen==k的话就不会继续递归下面的了，就直接返回了，这样写比单独开一个k==llen的分支写法更简洁
//            // 还要继续寻找右边的分支
//            if (k > lLen) {
//                // 注意要寻找的长度不是k了，要减去已经找到的
//                qSort(value, index + 1, end, ret, retIndex, k - lLen);
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
//        int k = 2;
//        Solution solution = new Solution();
//        int[] res = solution.topKFrequent(nums, k);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
