//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1：
//
//
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
//
// 示例 2：
//
//
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3
//。
//
//
//
// 提示：
//
//
// 0 <= k <= 100
// 0 <= prices.length <= 1000
// 0 <= prices[i] <= 1000
//
//
// Related Topics 数组 动态规划 👍 889 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 最优解法
 * 可以看出动态规划不是只有最优子结构的问题，还在于问题的每一步递进涉及多个状态，有状态转移这样的思维逻辑在
 * 题目没有说是否允许当天多次买卖，因为在题目“最多买卖k次”的条件下，这个限制不重要，不影响结果
 * 如果允许当天多次买卖，那么数组可以通过当天重复买卖达到严格买卖k次的效果；
 * 如果不允许当天多次买卖，那么数组的含义就是最多买卖k次，买当天的不能更新buy的话，就是不买，不强制当天重复买又卖来达到买卖次数严格等于k的要求
 * 归根结底就是当天重复买卖不会对结果造成影响
 */
class Solution {
    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k];
        int[] sell = new int[k];
        for (int i = 0; i < k; i++) {
            buy[i] = -prices[0];
        }
        // 既然考虑了prices[i]，就一定要对其进行操作(买入或卖出)，然后再考虑是否队最终结果做出贡献
        // 但是不用管是否做出贡献，我们只需要得出最终结果即可，但是在考虑问题的时候一定是规划，一定是到了当前要对其进行操作！！！
        // 注意这里外层循环时prices.length次
        for (int i = 1; i < prices.length; i++) {
            buy[0] = Math.max(-prices[i], buy[0]);
            sell[0] = Math.max(buy[0] + prices[i], sell[0]);
            for (int j = 1; j < k; j++) {
                buy[j] = Math.max(sell[j - 1] - prices[i], buy[j]);
                // 这里buy[j]可以是更新后的值，buy了j的属性是不会变的
                // 至于能否用更新后的值主要是考虑到更新后的值可能是在当天买入更新的，就看题意是否支持当天多次买入卖出
                // 但事实上这个情况不会影响最终结果，这样假设只是为了统一简化建模
                // 注意要sell了j次，一定是要buy了j次
                sell[j] = Math.max(buy[j] + prices[i], sell[j]);
            }
        }
        return sell[k - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
