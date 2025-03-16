//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 2³¹ - 1
// 0 <= amount <= 10⁴
//
//
// Related Topics 广度优先搜索 数组 动态规划 👍 2221 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 注意amount==0比较特殊，也是为后面迭代不超过索引划界限
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        // 注意这里的差异初始化很重要
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = -1;
        }
        for (int coin : coins) {
            // 注意这个条件是必须的
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }
        for (int coin : coins) {
            // 由于coin可以重用，所以这里从小到大就相当于在重用
            for (int i = 1; i <= amount; i++) {
                if (i - coin > 0 && dp[i - coin] != -1) {
                    // 注意这里必须分类讨论，动态规划的难点其实就在于不同条件的状态转移
                    if (dp[i] == -1) {
                        dp[i] = dp[i - coin] + 1;
                    } else {
                        dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                    }
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = new int[]{1, 2, 5};
//        int count = 11;
//        int[] nums = new int[]{2};
//        int count = 3;
        int[] nums = new int[]{2};
        int count = 1;
        int res = solution.coinChange(nums, count);
        System.out.println(res);
    }
}

/**
 * Success2
 * 这是以amount为外部遍历，以coin为内部遍历的
 */
//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        int max = amount + 1;
//        int[] dp = new int[amount + 1];
//        Arrays.fill(dp, max);
//        dp[0] = 0;
//        for (int i = 1; i <= amount; i++) {
//            for (int j = 0; j < coins.length; j++) {
//                if (coins[j] <= i) {
//                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
//                }
//            }
//        }
//        return dp[amount] > amount ? -1 : dp[amount];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] nums = new int[]{1, 2, 5};
////        int count = 11;
//        int[] nums = new int[]{2};
//        int count = 3;
//        int res = solution.coinChange(nums, count);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 先coin再target
 * 这种方式貌似效率高一丢丢
 */
//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        int[] nums = new int[amount + 1];
//        // 这两种初始化方式都行，后面都是配套的
////        Arrays.fill(nums, -1);
//        // 用-1还是不行
//        // 注意这种最差情况还要差一层初始化方法很重要，直接决定了后面的迭代结果
//        Arrays.fill(nums, amount + 1);
//        // 这种方案是一定存在的，它很重要
//        // 对于任何一个动态规划问题，起点总是很重要
//        nums[0] = 0;
//        for (int i = 0; i < coins.length; i++) {
//            for (int j = coins[i]; j < amount + 1; j++) {
//                // 注意初始化达到非0的amount至少需要一个coin
//                // 但因为是找最小，所以初始化一定要用最差的情况，这样才能被更少的情况覆盖
//                // 不管给了怎样的硬币，最差的情况一定是j个面值为1的硬币组成的
//                // 注意这样初始化的话就不方便讲真的这种最差情况区分开，所以还要再远一步
////                nums[j] = j + 1;
//                // 只选当前硬币和之前和为0的方案一定存在
//                // 注意由于达到之前某种和的方案不存在，所以这个时候不能用初始值0+1来得到之前某种和加当前硬币的方案
//                // 所以要对初始值做一些处理
//                // 注意这样还是不行
////                if (nums[j - coins[i]] != j - coins[i] + 1) {
//                // 其实也不可以不要外面这个控制条件，因为一定不会取到那种情况
////                if (nums[j - coins[i]] != amount + 1) {
////                    nums[j] = Math.min(nums[j], nums[j - coins[i]] + 1);
////                }
//                nums[j] = Math.min(nums[j], nums[j - coins[i]] + 1);
//            }
//        }
//        return nums[amount] > amount ? -1 : nums[amount];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{2, 5, 10, 1};
//        int count = 27;
////        int[] nums = new int[]{1, 2, 5};
////        int count = 11;
////        int[] nums = new int[]{2};
////        int count = 3;
//        int res = solution.coinChange(nums, count);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)


