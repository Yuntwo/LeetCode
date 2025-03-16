//假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//
//
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
//
//
// 示例 2:
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
// 限制：
//
// 0 <= 数组长度 <= 10^5
//
//
//
// 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-
//stock/
// Related Topics 数组 动态规划 👍 228 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Review1
 */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int res = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if ((prices[i] - minPrice) > res) {
                res = prices[i] - minPrice;
            }
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return res;
    }
}

/**
 * Success2
 * 非动态规划解法
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        int res = 0;
//        for (int i = 1; i < prices.length; i++) {
//            // 注意这里的prices[i-1]其实是0到i-1中的最小价格，只不过为了节省空间放到prices数组里了
//            if (prices[i] - prices[i - 1] > res) {
//                res = prices[i] - prices[i - 1];
//            }
//            // 注意默认是继承前面最小的，只有小于才不用继承前面的值
//            if (prices[i] > prices[i - 1]) {
//                prices[i] = prices[i - 1];
//            }
//        }
//        return res;
//    }
//}

/**
 * Success1
 * 这样的动态规划明显很慢
 * 就是说像上面这样，在算profit的时候可能不需要那么多叠加信息
 */
//class Solution {
//    public int maxProfit(int[] prices) {
//        int[] profit = new int[prices.length + 1];
//        for (int i = 2; i < prices.length + 1; i++) {
//            for (int j = 1; j <= i; j++) {
//                if (prices[i - 1] > prices[j - 1]) {
//                    profit[i] = Math.max(profit[i], prices[i - 1] - prices[j - 1]);
//                } else {
//                    // 注意这里也要取最大，因为当前的值可能有其它比profit[j-1]大的
//                    profit[i] = Math.max(profit[i], profit[j - 1]);
//                }
//            }
//        }
//        return profit[prices.length];
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
