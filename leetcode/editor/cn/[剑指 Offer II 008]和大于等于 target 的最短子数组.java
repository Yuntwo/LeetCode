//给定一个含有 n 个正整数的数组和一个正整数 target 。
//
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长
//度。如果不存在符合条件的子数组，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：target = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
//
//
// 示例 2：
//
//
//输入：target = 4, nums = [1,4,4]
//输出：1
//
//
// 示例 3：
//
//
//输入：target = 11, nums = [1,1,1,1,1,1,1,1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= target <= 10⁹
// 1 <= nums.length <= 10⁵
// 1 <= nums[i] <= 10⁵
//
//
//
//
// 进阶：
//
//
// 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
//
//
//
//
//
// 注意：本题与主站 209 题相同：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
//
//
// Related Topics 数组 二分查找 前缀和 滑动窗口 👍 113 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Success1
 * 双指针
 * 注意看清题目，是sum大于等于target
 * 注意在缩小值的时候，只缩小前面就可以了，不需要缩小后面，因为缩小后面的情况一定是之面的滑动窗口涵盖了的
 */
class Solution {

    /**
     * Success2
     * 换种写法
     */
    public int minSubArrayLen(int target, int[] nums){
        int res = Integer.MAX_VALUE;
        // 注意这里start的含义是将要减去的值索引，是虚拟的
        int start = 0;
        // 注意这里end的含义是将要加上的值索引
        int end = 0;
        // 注意根据start和end的含义，这里的sum是0
        int sum = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                // 注意只要是sum>=target了，就是end加上的满足条件了，这里的end是已经加上的，start是将要减去的，但是这里没有(必要)减去，所以要+1
                res = Math.min(res, end - start + 1);
                // 这里减去是为了寻找下一个，为了迭代，含义暂时变为已经减去的
                sum -= nums[start];
                // start++，start含义又变回将要减去的
                start++;
            }
            // 注意这里的循环逻辑是以end都要++为主线的
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * Success1
     */
//    public int minSubArrayLen(int target, int[] nums) {
//        int res = Integer.MAX_VALUE;
//        int i = 0;
//        int j = 0;
//        int sum = nums[0];
//        while (i < nums.length && j < nums.length) {
//            if (sum >= target) {
//                res = Math.min(res, j - i + 1);
//                // 注意在找到一个满足条件的时候，还是要继续更新变量寻找下一个，同时也便于跳出最终循环
//                sum -= nums[i];
//                i++;
//            } else {
//                j++;
//                // 注意控制边界条件
//                if (j == nums.length) {
//                    break;
//                }
//                sum += nums[j];
//            }
//        }
//        // 注意这里不满足条件时返回0
//        return res == Integer.MAX_VALUE ? 0 : res;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
