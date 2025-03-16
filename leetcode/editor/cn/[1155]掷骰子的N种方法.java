//这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
//
// 给定三个整数 n , k 和 target ，返回可能的方式(从总共 kⁿ 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
//
// 答案可能很大，你需要对 10⁹ + 7 取模 。
//
//
//
// 示例 1：
//
//
//输入：n = 1, k = 6, target = 3
//输出：1
//解释：你扔一个有6张脸的骰子。
//得到3的和只有一种方法。
//
//
// 示例 2：
//
//
//输入：n = 2, k = 6, target = 7
//输出：6
//解释：你扔两个骰子，每个骰子有6个面。
//得到7的和有6种方法1+6 2+5 3+4 4+3 5+2 6+1。
//
//
// 示例 3：
//
//
//输入：n = 30, k = 30, target = 500
//输出：222616187
//解释：返回的结果必须是对 10⁹ + 7 取模。
//
//
//
// 提示：
//
//
// 1 <= n, k <= 30
// 1 <= target <= 1000
//
//
// Related Topics 动态规划 👍 159 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;
// 注意题目描述，这和背包问题还不一样的
// 它是一共只能且必须选n个，这n个数字可能取值是1-k
// 但是能遍历到的数据是差不多的
// 应该可以用01背包问题的思路解决问题，只是每一次的可能取值变为了1-k，必须选一个
// 答案应该是组合数而不是排列数
// 先假设答案不会超过int型表示的最大数吧

/**
 * Faliure1
 * 还是排列和组合的问题
 */
//class Solution {
//    public int numRollsToTarget(int n, int k, int target) {
//        int[] coms = new int[target + 1];
//        /* 这是01背包问题必须初始化的两个点 */
//        // 注意和为0也是重要初始起点数，但是怎么也投不出和为0，所以它只能算作0，有其它非0点作为迭变点
//        coms[0] = 0;
//        // 用一个骰子可以达到的target的组合数为1
//        // 注意这里要取一个min，否则会越界
//        for (int i = 1; i <= Math.min(k, target); i++) {
//            coms[i] = 1;
//        }
//        for (int i = 1; i < n; i++) {
//            // 注意掷第i个骰子(从0计数)，每次最小投1，累积和至少为i+1
//            // 注意对于01背包问题的一维数组解法，仍然要采用由大到小遍历方式
//            for (int j = target; j >= i + 1; j--) {
//                // 注意由于每一次掷骰子有可能投出1-k，这是一种可能性，就是按顺序拿出第i次的石头也不是常规的固定值nums[i]
//                // 由于不确定性，所以多一个自由度也就是一个维度
//                // 注意这里要取一个min，不清楚会不会越界，但是肯定会减少迭代量
//                // 这里应该由大由小无所谓啦，因为它只修改了coms[j]，只是读的位置不一样
//                // 如果大于了的话肯定是越加越大的，没有必要
//                // 这样应该得出的是排列数，因为凑7的话3和4、4和3是算了两次的
//                // 这样就会算多
//                for (int l = Math.min(j, k); l >= 1; l--) {
//                    coms[j] = (coms[j] + coms[j - l]) % 1000000007;
//                }
//            }
//        }
//        return coms[target] % 1000000007;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int res = solution.numRollsToTarget(2, 6, 7);
////        int res = solution.numRollsToTarget(1, 6, 3);
//        int res = solution.numRollsToTarget(5, 4, 10);
//        System.out.println(res);
//    }
//}
/**
 * Success1
 * 题解做法
 */
//class Solution {
//    // 注意这种科学计数法的表达以及转int型(科学计数法表达式默认是浮点型)
//    int mod = (int)1e9+7;
//    public int numRollsToTarget(int n, int m, int t) {
//        int[] f = new int[t + 1];
//        f[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            for (int j = t; j >= 0; j--) {
//                f[j] = 0;
//                for (int k = 1; k <= m; k++) {
//                    if (j >= k) {
//                        f[j] = (f[j] + f[j-k]) % mod;
//                    }
//                }
//            }
//        }
//        return f[t];
//    }
//}

/**
 * Review1
 * 这应该是完全背包问题
 * 所谓完全背包问题，对于完全背包问题的每一个背包就不是选与不选的差别，而是一定会选，但是有不同的值对不同结果的影响
 * 01背包问题是特殊的完全背包问题，是每一个背包只有两个值，0或者xx值
 * 所以完全背包问题肯定是要比01背包问题多一个维度的，或者是说空间上不会多一个维度，但是数据上肯定是多一个循环的
 */
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int mod = (int) 1e9 + 7;
        int[][] res = new int[n + 1][target + 1];
        res[0][0] = 1;
        // 注意res[0][x!=0]的值都为0
        // 注意res[x!=0][x]的值都为0，因为每一个骰子一定会选只是值不同，所以只要选了和不可能为0，方案数一定是0
        // 如果设为1的话会多出很多结果来
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // 每一个骰子有k种取值
                for (int l = 1; l <= k; l++) {
                    if (l <= j) {
                        // 注意这里是要看上一轮的和，而且是加等于
                        // 注意这里可能会超出int的表示范围从而数据溢出
                        res[i][j] = (res[i][j] + res[i - 1][j - l]) % mod;
                    }
                }
            }
        }
        return res[n][target];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int res = solution.numRollsToTarget(2, 6, 7);
//        int res = solution.numRollsToTarget(1, 6, 3);
        int res = solution.numRollsToTarget(5, 4, 10);
//        int res = solution.numRollsToTarget(20, 30, 500);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
