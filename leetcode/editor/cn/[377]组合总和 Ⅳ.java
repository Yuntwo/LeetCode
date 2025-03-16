//给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
//
// 题目数据保证答案符合 32 位整数范围。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3], target = 4
//输出：7
//解释：
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//请注意，顺序不同的序列被视作不同的组合。
//
//
// 示例 2：
//
//
//输入：nums = [9], target = 3
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 1000
// nums 中的所有元素 互不相同
// 1 <= target <= 1000
//
//
//
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
//
// Related Topics 数组 动态规划 👍 723 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 注意题目要求，实际上求的是排列数，顺序不同是不同的情况
 * 注意这里只需要调换一下循环顺序就可以得到排列的含义，对于每一个target，接下来每一个num和之前的i-num得到target配合能够得到的排列
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        int res = solution.combinationSum4(nums, target);
        System.out.println(res);
    }
}

/**
 * Success1
 * 调换一下循环的顺序，对于不同的target，以选取不同的硬币开头(当然前提是小于target)
 * 这样自然就能得到排列了
 * 否则以上面那种循环顺序，拿硬币的相对顺序始终是相对固定，区别只是在于拿不拿、拿多少对于能组成target数的影响而已
 */
//class Solution {
//    public int combinationSum4(int[] nums, int target) {
//        int[] coms = new int[target + 1];
//        coms[0] = 1;
//        for (int i = 1; i < target + 1; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (nums[j] <= i) {
//                    coms[i] += coms[i - nums[j]];
//                }
//            }
//        }
//        return coms[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{1, 2, 3};
//        int target = 4;
//        int res = solution.combinationSum4(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 注意这也是每个元素可以用无数次
 * 注意零钱兑换问题中求达成某个target的硬币组合数和最少硬币数的区别
 * 更多的是相关dp数组内涵以及初始化的区别
 * 要注意它这里要考虑不同的排列顺序
 * 注意这样嵌套循环的话
 */
//class Solution {
//    public int combinationSum4(int[] nums, int target) {
//        int[] coms = new int[target + 1];
//        coms[0] = 1;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = nums[i]; j < target + 1; j++) {
//                coms[j] += coms[j - nums[i]];
//            }
//        }
//        return coms[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{1, 2, 3};
//        int target = 4;
//        int res = solution.combinationSum4(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
