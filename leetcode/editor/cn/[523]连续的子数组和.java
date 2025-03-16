//给你一个整数数组 nums 和一个整数 k ，如果 nums 有一个 好的子数组 返回 true ，否则返回 false：
//
// 一个 好的子数组 是：
//
//
// 长度 至少为 2 ，且
// 子数组元素总和为 k 的倍数。
//
//
// 注意：
//
//
// 子数组 是数组中 连续 的部分。
// 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终 视为 k 的一个倍数。
//
//
//
//
// 示例 1：
//
//
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
//
// 示例 2：
//
//
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//
//
// 示例 3：
//
//
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// 0 <= nums[i] <= 10⁹
// 0 <= sum(nums[i]) <= 2³¹ - 1
// 1 <= k <= 2³¹ - 1
//
//
// Related Topics 数组 哈希表 数学 前缀和 👍 585 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Success1
 * 还是前缀和与子问题的思路，主要是问题不好定义
 * 注意这里要求子数组长度至少是2，所以在添加的时候还有一点tricky
 * *** 注意并非涉及到子数组就是滑动窗口的思路，那样时间复杂度太高
 * *** 当然本质也是动态规划的思路，但是这里时间复杂度降下去了关键是将问题转为了前缀和；否则按照原问题去构造dp时间复杂度还是很高
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 由于这里不是求最大最小的单一值了，所以需要额外的数据结构来存储多种值
        // set存0-i前缀和关于k的余数
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            // 如果两个前缀和的余数相同则其相减的子数组是k的倍数，这很关键
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
