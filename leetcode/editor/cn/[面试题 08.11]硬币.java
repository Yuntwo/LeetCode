//硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
//
// 示例1:
//
//
// 输入: n = 5
// 输出：2
// 解释: 有两种方式可以凑成总金额:
//5=5
//5=1+1+1+1+1
//
//
// 示例2:
//
//
// 输入: n = 10
// 输出：4
// 解释: 有四种方式可以凑成总金额:
//10=10
//10=5+5
//10=5+1+1+1+1+1
//10=1+1+1+1+1+1+1+1+1+1
//
//
// 说明：
//
// 注意:
//
// 你可以假设：
//
//
// 0 <= n (总金额) <= 1000000
//
//
// Related Topics 数组 数学 动态规划 👍 290 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 很简单，就是背包问题，每个硬币可以重复用很多次
 */
class Solution {
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] coins = new int[]{25, 10, 5, 1};
        for (int i = 0; i < 4; i++) {
            for (int j = coins[i]; j < n + 1; j++) {
                dp[j] += dp[j - coins[i]];
                dp[j] %= 1000000007;
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
