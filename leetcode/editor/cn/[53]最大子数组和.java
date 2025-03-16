//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 子数组 是数组中的一个连续部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：1
//
//
// 示例 3：
//
//
//输入：nums = [5,4,-1,7,8]
//输出：23
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
//
// Related Topics 数组 分治 动态规划 👍 6773 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success3
 * 前缀和的思路
 * 本质上都是子问题，关键在于怎么去发现定义子问题
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int minPreSum = 0;
        int preSum = 0;
        for (int x : nums) {
            preSum += x; // 当前的前缀和
            ans = Math.max(ans, preSum - minPreSum); // 减去前缀和的最小值
            minPreSum = Math.min(minPreSum, preSum); // 维护前缀和的最小值
        }
        return ans;
    }
}

/**
 * Success2
 * 动态规划，思路和贪心算法是一样的，关键是dp的内涵定义清楚
 * 你只需要定义是以i结尾的最大值即可，不需要知道起始是谁
 */
//class Solution {
//    public int maxSubArray(int[] nums) {
//        // dp[i] 定义为数组nums 中以num[i] 结尾的最大连续子串和
//        int[] dp = new int[nums.length + 1];
//        // 这是全局值
//        int res = Integer.MIN_VALUE;
//        for (int i = 1; i <= nums.length; i++) {
//            // 因为以i-1结尾的最大连续子串和可能为负数，所以有可能不带前面的子串结果反而更大
//            dp[i] = Math.max(nums[i - 1] + dp[i - 1], nums[i - 1]);
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(solution.maxSubArray(nums));
//    }
//}

/**
 * Success1
 * 贪心算法
 */
//class Solution {
//    public int maxSubArray(int[] nums) {
//        int ans = nums[0];
//        int sum = 0;
//        for(int num: nums) {
//            // 前面的和对结果有增益，加上
//            if(sum > 0) {
//                sum += num;
//                // 前面的和对结果没有增益，直接舍弃
//            } else {
//                sum = num;
//            }
//            ans = Math.max(ans, sum);
//        }
//        return ans;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
