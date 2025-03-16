//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
//
// 假设每一种面额的硬币有无限个。
//
// 题目数据保证结果符合 32 位带符号整数。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
//
//
// 示例 2：
//
//
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
//
//
// 示例 3：
//
//
//输入：amount = 10, coins = [10]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 300
// 1 <= coins[i] <= 5000
// coins 中的所有值 互不相同
// 0 <= amount <= 5000
//
//
// Related Topics 数组 动态规划 👍 965 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 注意因为考虑的是每一个coin对于i的影响，所以不管是相同的多个coin还是不同的coin之间，都没有考虑顺序
 * 最终考虑的只是每种coin的数量，所以是无序的组合数
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 注意这个初始值很重要
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i < amount + 1; i++) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 5};
        int count = 5;
        int res = solution.change(count, nums);
        System.out.println(res);
    }
}

/**
 * Success1
 */
// 注意它这里的coins[i]都是大于0的
//class Solution {
//    public int change(int amount, int[] coins) {
//        // 初始化为0
//        int[] nums = new int[amount + 1];
//        // 注意这个初始化条件是很重要的
//        // 否则无法遍历递进，虽然它初始化的思路不太常规成体系
//        nums[0] = 1;
//        for (int i = 0; i < coins.length; i++) {
//            for (int j = coins[i]; j < amount + 1; j++) {
//                // 注意因为存在重复遍历，所以其实最大的要在当前这种方案和不选当前的上一种方案对比
//                // 注意应该是在总数为j-coins[i]的方案基础上
//                nums[j] += nums[j - coins[i]];
//            }
//        }
//        return nums[amount];
//
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{1, 2, 5};
//        int count = 5;
//        int res = solution.change(count, nums);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
