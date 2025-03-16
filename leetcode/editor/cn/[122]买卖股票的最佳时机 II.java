//给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
//
// 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
//
// 返回 你能获得的 最大 利润 。
//
//
//
// 示例 1：
//
//
//输入：prices = [7,1,5,3,6,4]
//输出：7
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
//     总利润为 4 + 3 = 7 。
//
// 示例 2：
//
//
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     总利润为 4 。
//
// 示例 3：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 3 * 10⁴
// 0 <= prices[i] <= 10⁴
//
//
// Related Topics 贪心 数组 动态规划 👍 2030 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Review3
 * 贪心算法，由于当天可以买进卖出，所以直接贪心不影响，最后都是合并的结果
 */
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}

/**
 * Review2
 * 可以优化空间，只用两个变量存储
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        int[] dp0 = new int[prices.length + 1];
//        int[] dp1 = new int[prices.length + 1];
//        // 这两种初始化都可以，就是注意不是0
////        dp1[0] = Integer.MIN_VALUE;
//        dp1[0] = -prices[0];
//        for (int i = 0; i < prices.length; i++) {
//            int price = prices[i];
//            dp0[i + 1] = Math.max(dp1[i] + price, dp0[i]);
//            dp1[i + 1] = Math.max(dp0[i] - price, dp1[i]);
//        }
//        return dp0[prices.length];
//    }
//}

/**
 * Success3
 * 贪心算法，效率最高
 * 但是只能针对这道题的特殊情况：最多持有一支股票&可以多次买卖
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        int res = 0;
//        for (int i = 1; i < prices.length; i++) {
//            if (prices[i] > prices[i - 1]) {
//                res += prices[i] - prices[i - 1];
//            }
//        }
//        return res;
//    }
//}

/**
 * Review1
 * 动态规划的核心是状态转移以及确定这个问题用几个状态来描述最好
 * 注意这个还可以把数组压缩成单个变量
 * 状态定义为是否持有股票时的现金数
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        int[] buy = new int[prices.length];
//        int[] sell = new int[prices.length];
//        buy[0] = -prices[0];
//        sell[0] = 0;
//        for (int i = 1; i < prices.length; i++) {
//            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
//            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
//        }
//        return sell[prices.length - 1];
//    }
//}

/**
 * Success2
 * 动态规划的本质实际上是状态转移记录吧
 * 它不算是背包问题，如果按照背包问题的思路去构造动态规划数组能解出来没有错，但是效率会很低
 * 动态规划的难点在于对状态建模，这里的状态建得就很巧妙
 */
//class Solution {
//
//    /**
//     * Success3
//     * 压缩动态规划数组，时间效率没有影响
//     */
//    public int maxProfit(int[] prices) {
//        // 对于这个动态规划问题没必要留一个多余的空间，没有用，反倒不是很方便
//        int[] profit = new int[2];
//        // 注意这个初始值很重要，否则会被带偏
//        profit[1] = -prices[0];
//        int tempProfit0, tempProfit1;
//        for (int i = 1; i < prices.length; i++) {
//            tempProfit0 = Math.max(profit[0], profit[1] + prices[i]);
//            tempProfit1 = Math.max(profit[0] - prices[i], profit[1]);
//            profit[0] = tempProfit0;
//            profit[1] = tempProfit1;
//        }
//        return profit[0];
//    }
//
//    /**
//     * Success2
//     * 细节优化版，Q:不知道怎么反而时间效率变慢了
//     */
////    public int maxProfit(int[] prices) {
////        // 对于这个动态规划问题没必要留一个多余的空间，没有用，反倒不是很方便
////        int[][] profit = new int[prices.length][2];
////        // 注意这个初始值很重要，否则会被带偏
////        profit[0][1] = -prices[0];
////        for (int i = 1; i < prices.length; i++) {
////            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i]);
////            profit[i][1] = Math.max(profit[i - 1][0] - prices[i], profit[i - 1][1]);
////        }
////        return profit[prices.length - 1][0];
////    }
//    /**
//     * Success1
//     * 自己写的，还可以优化细节
//     */
////    public int maxProfit(int[] prices) {
////        int[][] profit = new int[prices.length + 1][2];
////        // 注意这个初始值很重要，否则会被带偏
////        profit[0][1] = Integer.MIN_VALUE;
////        for (int i = 1; i < prices.length + 1; i++) {
////            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i - 1]);
////            profit[i][1] = Math.max(profit[i - 1][0] - prices[i - 1], profit[i - 1][1]);
////        }
////        return profit[prices.length][0];
////    }
//}

/**
 * Success1
 * 不知道为什么效率很低，或许哪里可以简化一下
 * 注意这题与121的区别是可以多次买卖，但是也只能买了卖了之后再买
 * 注意这个题是任意多次买卖
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        // profit[i]表示截止到包含i已经完成买卖获得的最大收益
//        // 一般动态规划都要多留一个空间给初始值，这个初始值也很重要，索引一般也从1开始
//        int[] profit = new int[prices.length + 1];
//        for (int i = 1; i <= prices.length; i++) {
//            int max = 0;
//            // 注意这里也要包含i，虽然当天对i买入卖出没有profit，但是可以到第二个分支不会漏掉与上一次完全交易的比较
//            for (int j = 1; j <= i; j++) {
//                // 注意这里price的索引还是都要减1
//                if (prices[i-1] > prices[j-1]) {
//                    // 注意这里叠加profit[j-1]其实就是叠加了在这之前的多次交易，值是实时变化的，用的共同空间
//                    max = Math.max(max, profit[j - 1] + prices[i - 1] - prices[j - 1]);
//                } else {
//                    // 注意这里也是要和max做比较的
//                    max = Math.max(max, profit[j - 1]);
//                }
//            }
//            profit[i] = max;
//        }
//        return profit[prices.length];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
//        int[] prices = new int[]{1, 2, 3, 4, 5};
//        int res = solution.maxProfit(prices);
//        System.out.println(res);
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
