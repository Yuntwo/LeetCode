//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1:
//
//
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//
// 示例 2：
//
//
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
//
// 示例 4：
//
//
//输入：prices = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 10⁵
// 0 <= prices[i] <= 10⁵
//
//
// Related Topics 数组 动态规划 👍 1354 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 四个状态动态规划
 */
class Solution {



    /**
     * Success3
     * 压缩数组，思路和条件应该是和下面这种一样
     * 允许当天重复买卖，所以比较的是前一天的某个状态和当前更新的某个状态的max
     * 这里如果是按照不允许当天重复买卖的思路，就还要多几个状态保留之前的，压缩数组写法就没有那么简洁
     */
//    public int maxProfit(int[] prices) {
//        int buy1 = -prices[0];
//        int sell1 = 0;
//        int buy2 = -prices[0];
//        int sell2 = 0;
//        for (int i = 1; i < prices.length; i++) {
//            buy1 = Math.max(buy1, -prices[i]);
//            sell1 = Math.max(sell1, buy1 + prices[i]);
//            buy2 = Math.max(buy2, sell1 - prices[i]);
//            sell2 = Math.max(sell2, buy2 + prices[i]);
//        }
//        return sell2;
//    }

    /**
     * Success2
     * 未压缩数组
     * 显式当天可以重复买卖，引用的状态天数与下面那种有区别，也是对的；这应该能说明某种问题
     * 这是因为不管是否允许当天重复买卖，因为当天重复买卖的收益是0，所以在取max的时候根本不会取这个操作
     * 实际上还是当做当天不会重复买卖来处理的
     */
//    public int maxProfit(int[] prices) {
//        // 这都表示不同操作在i处的最大利润
//        int[] buy1 = new int[prices.length];
//        int[] sell1 = new int[prices.length];
//        int[] buy2 = new int[prices.length];
//        int[] sell2 = new int[prices.length];
//        buy1[0] = -prices[0];
//        sell1[0] = 0;
//        buy2[0] = -prices[0];
//        sell2[0] = 0;
//        for (int i = 1; i < prices.length; i++) {
//            buy1[i] = Math.max(buy1[i - 1], -prices[i]);
//            sell1[i] = Math.max(sell1[i - 1], buy1[i] + prices[i]);
//            buy2[i] = Math.max(buy2[i - 1], sell1[i] - prices[i]);
//            sell2[i] = Math.max(sell2[i - 1], buy2[i] + prices[i]);
//        }
//        return sell2[prices.length - 1];
//    }

    /**
     * Success1.a
     * 按照不允许当天多次买卖的思路直接从1初始化以合理化初始化buy2和sell2的值，循环也直接从2开始
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        // 这都表示不同操作在i处的最大利润
        int[] buy1 = new int[prices.length];
        int[] sell1 = new int[prices.length];
        int[] buy2 = new int[prices.length];
        int[] sell2 = new int[prices.length];
        buy1[1] = Math.max(-prices[0], -prices[1]);
        sell1[1] = buy1[1] + prices[1];
        buy2[1] = sell1[1] - prices[1];
        sell2[1] = buy2[1] + prices[1];
        for (int i = 2; i < prices.length; i++) {
            buy1[i] = Math.max(buy1[i - 1], -prices[i]);
            sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
            buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
            sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);
        }
//        return Math.max(sell2[prices.length - 1], sell1[prices.length - 1]);
        // Q:不太懂这里按理说限制了不能在当天多次买入卖出，sell1怎么能转移到sell2导致直接返回sell2的答案也是对的呢
        // 或许案例全部设置的都是sell2的情况答案？
        return sell2[prices.length - 1];
    }

    /**
     * Success1
     * 二维数组
     * 这里我们都是和前一天做比较，默认的内涵是当天只买卖一次，不允许当天重复买卖
     */
//    public int maxProfit(int[] prices) {
//        // 这都表示不同操作在i处的最大利润
//        int[] buy1 = new int[prices.length];
//        int[] sell1 = new int[prices.length];
//        int[] buy2 = new int[prices.length];
//        int[] sell2 = new int[prices.length];
//        buy1[0] = -prices[0];
//        sell1[0] = 0;
//        // 不是很理解这里buy2的初始值，如果默认当天只买卖一次的话但是也只有这样勉强赋值啊，不然初值都赋不了
//        buy2[0] = -prices[0];
//        sell2[0] = 0;
//        for (int i = 1; i < prices.length; i++) {
//            buy1[i] = Math.max(buy1[i - 1], -prices[i]);
//            sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
//            buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
//            sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);
//        }
//        return sell2[prices.length - 1];
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
        int res = solution.maxProfit(prices);
        System.out.println(res);
    }
}

/**
 * Failure2
 * 代码应该是没有问题的，但是时间复杂度太高超时
 * 其实就是二维动态规划数组
 * 动态规划似乎不只是记忆化，还有状态转移
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        // 注意这里数组的值应该是递增的
//        int[][] profit = new int[3][prices.length + 1];
//        for (int k = 1; k <= 2; k++) {
//            for (int i = 2; i < prices.length + 1; i++) {
//                for (int j = 1; j <= i; j++) {
//                    if (prices[i - 1] > prices[j - 1]) {
//                        profit[k][i] = Math.max(profit[k][i], profit[k - 1][j - 1] + prices[i - 1] - prices[j - 1]);
//                    } else {
//                        // 注意这里因为相当于这一次没有买卖股票，所以最大值应该与这一层次的作比较！！！
//                        profit[k][i] = Math.max(profit[k][i], profit[k][j - 1]);
//                    }
//                }
//            }
//        }
//        return profit[2][prices.length];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
//        int res = solution.maxProfit(prices);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 这样还是超时
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        // 这样存这个minPrices会超memory
////        int[][] minPrices = new int[prices.length + 1][prices.length + 1];
////        for (int i = 1; i < prices.length + 1; i++) {
////            for (int j = i; j < prices.length + 1; j++) {
////                if (i == j) {
////                    minPrices[i][j] = prices[i - 1];
////                } else if (prices[j - 1] < minPrices[i][j - 1]) {
////                    minPrices[i][j] = prices[j - 1];
////                } else {
////                    minPrices[i][j] = minPrices[i][j - 1];
////                }
////            }
////        }
//        int[][] profit = new int[3][prices.length + 1];
//        for (int k = 1; k < 3; k++) {
//            for (int i = 2; i < prices.length + 1; i++) {
//                for (int j = 1; j <= i; j++) {
//                    // j到i的最小price
//                    int minPrice = prices[j - 1];
//                    for (int l = j + 1; l <= i; l++) {
//                        if (prices[l - 1] < minPrice) {
//                            minPrice = prices[l - 1];
//                        }
//                    }
//                    if (prices[i - 1] > minPrice) {
//                        profit[k][i] = Math.max(profit[k][i], profit[k - 1][j - 1] + prices[i - 1] - minPrice);
//                    } else {
//                        profit[k][i] = Math.max(profit[k][i], profit[k][j - 1]);
//                    }
//                }
//            }
//        }
//        return profit[2][prices.length];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
//        int res = solution.maxProfit(prices);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
