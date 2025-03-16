//给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
//
//
//
// 示例 1:
//
//
//输入: nums = [10,5,2,6], k = 100
//输出: 8
//解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
//
//
// 示例 2:
//
//
//输入: nums = [1,2,3], k = 0
//输出: 0
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 3 * 10⁴
// 1 <= nums[i] <= 1000
// 0 <= k <= 10⁶
//
//
//
//
//
// 注意：本题与主站 713 题相同：https://leetcode-cn.com/problems/subarray-product-less-than-
//k/
//
// Related Topics 数组 滑动窗口 👍 137 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 代码优美、高效
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int sum = 1;
        int res = 0;
        while (end < nums.length) {
            sum *= nums[end];
            // 注意这里要控制start的边界
            while (sum >= k && start <= end) {
                sum /= nums[start];
                // 注意加了之后start的含义还是将要除去的包含在sum内的索引
                start++;
            }
            // 注意这里start的含义是包括在sum里将要除去的索引，是包括在内的所以要加1
            // 虽然start可能越界=end+1，但是这样理解并不影响逻辑的正确性
            res += (end - start + 1);
            // 这里end变为将要乘以的索引，还没有包括在内
            end++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
