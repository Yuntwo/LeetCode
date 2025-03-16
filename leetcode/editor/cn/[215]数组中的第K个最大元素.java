//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//
// 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
//
//
//
// 示例 1:
//
//
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
//
//
// 示例 2:
//
//
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4
//
//
//
// 提示：
//
//
// 1 <= k <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
//
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1946 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1, k);
        // 注意是第k大
        return nums[nums.length - k];
    }

    private int quickSort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return 0;
        }
        int pivotPos = partition(nums, start, end);
        // 改进一下，找到第k大的位置就不继续递归，而是直接返回了
        if (pivotPos == nums.length - k) {
            return nums[pivotPos];
        }
        quickSort(nums, start, pivotPos - 1, k);
        quickSort(nums, pivotPos + 1, end, k);
        return 0;
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            // 注意一定要带上等于
            while (start < end && nums[end] >= pivot) end--;
            nums[start] = nums[end];
            // 注意一定要带上等于
            while (start < end && nums[start] <= pivot) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int res = solution.findKthLargest(nums, 4);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
