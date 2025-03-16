//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。
//
// 返回 滑动窗口中的最大值 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3     3 -1
// 1 [3  -1  -3] 5  3  6  7       3     3 -1 -3
// 1  3 [-1  -3  5] 3  6  7       5     5
// 1  3  -1 [-3  5  3] 6  7       5     5 3
// 1  3  -1  -3 [5  3  6] 7       6     6
// 1  3  -1  -3  5 [3  6  7]      7     7
//
//
// 示例 2：
//
//
//输入：nums = [1], k = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
// 1 <= k <= nums.length
//
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 2586 👎 0


/**
 * 类似题型
 */
//给定一个整数数组 ums 和一个整数 k, k 表示滑动窗口的大小。你需要找出每个滑动窗口中的最大值与最小值的差，并返回这些差的最大值。
//你需要处理Java的标准输入输出
//输入描述：
//数组的长度为 n,1 <=n <=10000, 数组中的每个元素范围为[-10000,10000]，滑动窗口大小 k 的范围为[1,n]。
//输出描述：
//例如，给你 nums= [1,3, -1, -3,5,3,6,7] 和 k=3, 每个窗口中的最大值与最小值的差分别为[2,4,6,8]，所以返回 8。
//示例 1
//输入: nums= [1,3, -1, -3,5,3,6,7], k=3 输出复制 8

//这道题的思路和此题类似，只不过是分别维护一个最大值队列和一个最小值队列然后分别取最大值和最小值
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String inputLine = scanner.nextLine();
//        String[] parts = inputLine.split(", k=");
//        String numsStr = parts[0].substring(parts[0].indexOf("[") + 1, parts[0].indexOf("]"));
//        int[] nums = Arrays.stream(numsStr.split(",")).mapToInt(Integer::parseInt).toArray();
//        int k = Integer.parseInt(parts[1]);
//
//        int result = findMaxDiff(nums, k);
//        System.out.println(result);
//    }
//
//    public static int findMaxDiff(int[] nums, int k) {
//        if (nums == null || nums.length == 0 || k <= 0) {
//            return -1;
//        }
//        Deque<Integer> maxQueue = new ArrayDeque<>();
//        Deque<Integer> minQueue = new ArrayDeque<>();
//        int maxDiff = Integer.MIN_VALUE;
//        for (int i = 0; i < nums.length; i++) {
//            while (!maxQueue.isEmpty() && nums[i] > nums[maxQueue.getLast()]) {
//                maxQueue.removeLast();
//            }
//            while (!minQueue.isEmpty() && nums[i] < nums[minQueue.getLast()]) {
//                minQueue.removeLast();
//            }
//            maxQueue.addLast(i);
//            minQueue.addLast(i);
//
//            // Remove indices out of the current sliding window from the front of the queue
//            if (!maxQueue.isEmpty() && i - k == maxQueue.getFirst()) {
//                maxQueue.removeFirst();
//            }
//            if (!minQueue.isEmpty() && i - k == minQueue.getFirst()) {
//                minQueue.removeFirst();
//            }
//
//            // Get the max diff
//            if (i >= k - 1) {
//                maxDiff = Math.max(maxDiff, nums[maxQueue.getFirst()] - nums[minQueue.getFirst()]);
//            }
//        }
//        return maxDiff;
//    }
//}




//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Review2
 * 滑动窗口的时间效应也比较巧妙，不只是递增队列的性质
 */
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        // 具体k的作用隐式体现在数组窗口差值中而不是队列长度中
        int[] res = new int[nums.length - k + 1];
        int i = 0;
        while (i < nums.length) {
            // 移除，注意这里要移除的元素是nums[i-k]并且要限定索引
            // 注意当要移除的元素不是最大时不需要做任何操作，因为最大的元素后来加入时就已经把它给踢出去了
            if (!deque.isEmpty() && i >= k && nums[i - k] == deque.peekFirst()) {
                deque.pollFirst();
            }
            // 添加
            // 注意不用担心被踢出的已有元素会成为候选的最大，因为在当前将要被加入的较大元素被踢出之前它们就会被踢出，不会成为候选
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                deque.pollLast();
            }
            deque.addLast(nums[i]);
            // 计算结果
            if (i >= (k - 1)) {
                res[i - k + 1] = deque.peekFirst();
            }
            i++;
        }
        return res;
    }

}

/**
 * Review1
 */
//class Solution {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.add(nums[0]);
//        // 这里队列初始化倒是不需要过多地限制
//        for (int i = 1; i < k; i++) {
//            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
//                queue.pollLast();
//            }
//            queue.add(nums[i]);
//        }
//        int[] res = new int[nums.length - k + 1];
//        res[0] = queue.peekFirst();
//        for (int i = k; i < nums.length; i++) {
//            // 队列长度超出k不要紧，关键是要弹出窗口的那个如果是最大的就要从队列中移出
//            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
//                queue.pollLast();
//            }
//            queue.add(nums[i]);
//            if (nums[i - k] == queue.peekFirst()) {
//                queue.pollFirst();
//            }
//            res[i - k + 1] = queue.peekFirst();
//        }
//        return res;
//    }
//}

/**
 * Success1
 * 还是最大堆的思路，但是不需要保存固定数量的元素，小于的只需要移除就行了，因为一定会有最大的在前面保证会得到结果
 * 而且双端队列不需要像堆那样移动元素
 */
//class Solution {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int start = 0;
//        int end = k - 1;
//        ArrayDeque<Integer> window = new ArrayDeque<>(k);
//        window.add(nums[start]);
//        for (int i = start + 1; i <= end; i++) {
//            // 注意这里是peekLast来保证递减而不是只比最大的PeekFirst最小就行了，这样不能保证递减
//            while (!window.isEmpty() && nums[i] > window.peekLast()) {
//                window.pollLast();
//            }
//            window.add(nums[i]);
//        }
//        int[] res = new int[nums.length - k + 1];
//        res[0] = window.peek();
//        for (int count = 1; count <= nums.length - k; count++) {
//            if (nums[start] == window.peekFirst()) {
//                window.poll();
//            }
//            start++;
//            end++;
//            while (!window.isEmpty() && nums[end] > window.peekLast()) {
//                window.pollLast();
//            }
//            window.add(nums[end]);
//            res[count] = window.peek();
//        }
//        return res;
//    }
//}

/**
 * Failure1
 * Priority模拟最大堆，但是移除时时间复杂度为O(n)，所以整体复杂度为O(n*n)，很慢
 */
//class Solution {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int start = 0;
//        int end = k - 1;
//        PriorityQueue<Integer> window = new PriorityQueue<>(k, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        for (int i = start; i <= end; i++) {
//            window.add(nums[i]);
//        }
//        int[] res = new int[nums.length - k + 1];
//        res[0] = window.peek();
//        for (int count = 1; count <= nums.length - k; count++) {
//            window.remove(nums[start]);
//            start++;
//            end++;
//            window.add(nums[end]);
//            res[count] = window.peek();
//        }
//        return res;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
