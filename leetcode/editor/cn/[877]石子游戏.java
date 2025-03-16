//Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。
//
// 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
//
// Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此
//时手中 石子最多 的玩家 获胜 。
//
// 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。
//
//
//
// 示例 1：
//
//
//输入：piles = [5,3,4,5]
//输出：true
//解释：
//Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
//假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
//如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
//如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
//这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
//
//
// 示例 2：
//
//
//输入：piles = [3,7,2,3]
//输出：true
//
//
//
//
// 提示：
//
//
// 2 <= piles.length <= 500
// piles.length 是 偶数
// 1 <= piles[i] <= 500
// sum(piles[i]) 是 奇数
//
//
// Related Topics 数组 数学 动态规划 博弈 👍 483 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

    private int[] piles;

    public boolean stoneGame(int[] piles) {
        // sum[i][j]表示i到j的石头总数
        int[][] sum = new int[piles.length + 1][piles.length + 1];
        // dp[i][j]表示考虑i到j的石头堆发挥最佳水平所能得到的最大石头数
        int[][] dp = new int[piles.length + 1][piles.length + 1];
        for (int i = 1; i <= piles.length; i++) {
            sum[i][i] = piles[i];
            dp[i][i] = piles[i];
        }
        for (int i = piles.length / 2; i <= piles.length; i++) {

        }
    }

    /**
     * @param i
     * @param j
     * @param sum   表示i到j的石头总数
     * @return 考虑i到j的石头堆发挥最佳水平能获得的最大石头数
     */
    private int stone(int i, int j, int sum) {
        // 只剩一堆直接拿完
        if (i == j) {
            return sum;
        }
        // 返回拿i处和拿j处的最大值
        return Math.max(sum - stone(i + 1, j, sum - piles[i]), sum - stone(i, j - 1, sum - piles[j]));
    }

}

/**
 * Failure1
 * 用递归思路是正确的，但是超时，那就只能动态规划记录中间状态以空间换时间
 * 递归从思路上还是简单些，省去了设计dp和遍历逻辑的麻烦。主要是动态规划它是两头走的，不好设计
 */
//class Solution {
//
//    private int[] piles;
//
//    public boolean stoneGame(int[] piles) {
//        this.piles = piles;
//        int sum = 0;
//        for (int pile : piles) {
//            sum += pile;
//        }
//        int maxNum = stone(0, piles.length - 1, sum);
//        // 看它发挥最佳水平能拿的最大的数是否大于另一个被拿走的
//        return maxNum > (sum - maxNum);
//    }
//
//    /**
//     * @param i
//     * @param j
//     * @param sum   表示i到j的石头总数
//     * @return 考虑i到j的石头堆发挥最佳水平能获得的最大石头数
//     */
//    private int stone(int i, int j, int sum) {
//        // 只剩一堆直接拿完
//        if (i == j) {
//            return sum;
//        }
//        // 返回拿i处和拿j处的最大值
//        return Math.max(sum - stone(i + 1, j, sum - piles[i]), sum - stone(i, j - 1, sum - piles[j]));
//    }
//
//    public static void main(String[] args) {
//        int[] piles = new int[]{7, 7, 12, 16, 41, 48, 41, 48, 11, 9, 34, 2, 44, 30, 27, 12, 11, 39, 31, 8, 23, 11, 47, 25, 15, 23, 4, 17, 11, 50, 16, 50, 38, 34, 48, 27, 16, 24, 22, 48, 50, 10, 26, 27, 9, 43, 13, 42, 46, 24};
//        System.out.println(new Solution().stoneGame(piles));
//    }
//}

/**
 * Success1
 * 笑死，先手必赢
 */
//class Solution {
//    public boolean stoneGame(int[] piles) {
//        return true;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
