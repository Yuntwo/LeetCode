//集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
//
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
//
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
//
// 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
//输出：2
//解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
//总的来说，有两种计划。
//
// 示例 2：
//
//
//输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
//输出：7
//解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
//有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
//
//
//
//
// 提示：
//
//
// 1 <= n <= 100
// 0 <= minProfit <= 100
// 1 <= group.length <= 100
// 1 <= group[i] <= 100
// profit.length == group.length
// 0 <= profit[i] <= 100
//
//
// Related Topics 数组 动态规划 👍 267 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review2
 */
class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int) 1e9 + 7;
        int maxProfit = 0;
        for (int i = 0; i < group.length; i++) {
            maxProfit += profit[i];
        }
        // dp[i][j]表示刚好i个人产生j的利润的方案数
        int[][] dp = new int[n + 1][maxProfit + 1];
        int res = 0;
        dp[0][0] = 1;
        // 考虑这个特殊情况
        if (minProfit == 0) {
            res += 1;
        }
        // 考虑i项目的收益带来的方案数更新，这算是dp[j][k]的内容，实际上是第3个维度但是不占数组了
        for (int i = 0; i < group.length; i++) {
            // 注意这里算人必须倒着来，否则会算重复，要么就多一个维度了
            for (int j = n; j >= 0; j--) {
                for (int k = 0; k <= maxProfit; k++) {
                    // 如果要用的项目数人大于等于才能干这个项目，否则i这个项目对于j个人考虑的情况没有任何助益
                    if (j >= group[i]) {
                        // 看干这个项目和使用j-当前项目的人的所有刚好利益k+当前项目的利益能够>=minProfit
                        if ((k + profit[i]) >= minProfit) {
//                            res++;
                            // 注意这里不能直接res++要看dp[j - group[i]][k]有没有方案数，有多少方案数
                            res += dp[j - group[i]][k];
                            res %= mod;
                            // 如果不够的话就更新数组的方案数
                        }
                        // 考虑到(k + profit[i]) == minProfit的情况两种都要处理，否则会少
                        if ((k + profit[i]) <= maxProfit) {
                            dp[j][k + profit[i]] += dp[j - group[i]][k];
                            dp[j][k + profit[i]] %= mod;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // answer为2
//        int n = 5;
//        int minProfit = 3;
//        int[] group = new int[]{2, 2};
//        int[] profit = new int[]{2, 3};
        // answer为7
//        int n = 10;
//        int minProfit = 5;
//        int[] group = new int[]{2, 3, 5};
//        int[] profit = new int[]{6, 7, 8};
        // answer为2
        int n = 5;
        int minProfit = 3;
        int[] group = new int[]{2, 2};
        int[] profit = new int[]{2, 3};
        int res = solution.profitableSchemes(n, minProfit, group, profit);
        System.out.println(res);
    }
}

/**
 * Failure2
 * 第二个维度设为minProfit + 1的话实际上后面很多情况就被少算了
 * 注意题目的意思是项目不能重复干
 * 因为项目不能重复干，所以决定了外围循环只能是项目
 */
//class Solution {
//    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//        // dp[i][j]表示刚好i个人产生j的利润的方案数
//        int[][] dp = new int[n + 1][minProfit + 1];
//        int res = 0;
//        dp[0][0] = 1;
//        // 考虑i项目的收益带来的方案数更新，这算是dp[j][k]的内容，实际上是第3个维度但是不占数组了
//        for (int i = 0; i < group.length; i++) {
//            for (int j = 1; j <= n; j++) {
//                for (int k = 0; k <= minProfit; k++) {
//                    // 如果要用的项目数人大于等于才能干这个项目，否则i这个项目对于j个人考虑的情况没有任何助益
//                    if (j >= group[i]) {
//                        // 看干这个项目和使用j-当前项目的人的所有刚好利益k+当前项目的利益能够>=minProfit
//                        if ((k + profit[i]) >= minProfit) {
////                            res++;
//                            // 注意这里不能直接res++要看dp[j - group[i]][k]有没有方案数，有多少方案数
//                            res += dp[j - group[i]][k];
//                            // 如果不够的话就更新数组的方案数
//                        }
//                        // 考虑到(k + profit[i]) == minProfit的情况两种都要处理，否则会少
//                        if ((k + profit[i]) <= minProfit) {
//                            dp[j][k + profit[i]] += dp[j - group[i]][k];
//                        }
//                    }
//                }
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        // answer为2
////        int n = 5;
////        int minProfit = 3;
////        int[] group = new int[]{2, 2};
////        int[] profit = new int[]{2, 3};
//        // answer为7
//        int n = 10;
//        int minProfit = 5;
//        int[] group = new int[]{2, 3, 5};
//        int[] profit = new int[]{6, 7, 8};
//        int res = solution.profitableSchemes(n, minProfit, group, profit);
//        System.out.println(res);
//    }
//}

/**
 * Review1
 */
//class Solution {
//    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//        int mod = (int) 1e9 + 7;
//        int maxProfit = 0;
//        for (int pro : profit) {
//            maxProfit += pro;
//        }
//        // 这里dp[i][j]代表最大利润为i、最大工人数为j的方案数
//        int[][] dp = new int[maxProfit + 1][n + 1];
//        for (int i = 0; i < n + 1; i++) {
//            dp[0][i] = 1;
//        }
//
//        for (int i = 0; i < group.length; i++) {
//            // 注意这里要从大到小
//            for (int j = maxProfit; j >= profit[i]; j--) {
//                for (int k = n; k >= group[i]; k--) {
//                    dp[j][k] = (dp[j][k] + dp[j - profit[i]][k - group[i]]) % mod;
//                }
//            }
//        }
//        // 注意这里最后要加和
//        int res = 0;
//        for (int i = minProfit; i <= maxProfit; i++) {
//            res = (res + dp[i][n]) % mod;
//        }
//        return res;
//
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int n = 5;
////        int minProfit = 3;
////        int[] group = new int[]{2, 2};
////        int[] profit = new int[]{2, 3};
//        int n = 10;
//        int minProfit = 5;
//        int[] group = new int[]{2, 3, 5};
//        int[] profit = new int[]{6, 7, 8};
//        int res = solution.profitableSchemes(n, minProfit, group, profit);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 注意限制的是最小利益而不是最大利益
 */
//class Solution {
//    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//        // dp[i][j]表示最多j个工人参与工作的情况下产生大于等于i利益的计划数
//        int[][] dp = new int[minProfit + 1][n + 1];
//        int min = Integer.MAX_VALUE;
//        for (int grou : group) {
//            min = Integer.min(min, grou);
//        }
//        for (int i = 0; i < min; i++) {
//            dp[0][i] = 1;
//        }
//        for (int i = 0; i < profit.length; i++) {
//            for (int j = 1; j <= minProfit; j++) {
//                for (int k = 1; k <= n; k++) {
//                    if ((j - profit[i]) >= 0 && (k - group[i]) >= 0) {
//                        dp[j][k] += dp[j - profit[i]][k - group[i]];
//                    }
//                }
//            }
//        }
//        return dp[minProfit][n];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int n = 5;
////        int minProfit = 3;
////        int[] group = new int[]{2, 2};
////        int[] profit = new int[]{2, 3};
//        int n = 10;
//        int minProfit = 5;
//        int[] group = new int[]{2, 3, 5};
//        int[] profit = new int[]{6, 7, 8};
//        int res = solution.profitableSchemes(n, minProfit, group, profit);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 不快啊
 */
//class Solution {
//    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
//        int maxProfit = 0;
//        int mod = (int) 1e9 + 7;
//        for (int pro : profit) {
//            maxProfit += pro;
//        }
//        int[][] res = new int[n + 1][maxProfit + 1];
//        // 注意这些情况取值都是1，当且仅当都不用人这一种方案
//        for (int i = 0; i <= n; i++) {
//            res[i][0] = 1;
//        }
//        for (int k = 0; k < profit.length; k++) {
//            int people = group[k];
//            int pro = profit[k];
//            for (int i = n; i >= 0; i--) {
//                for (int j = maxProfit; j >= 0; j--) {
//                    if (people <= i && pro <= j) {
//                        res[i][j] = (res[i][j] + res[i - people][j - pro]) % mod;
//                    }
//                }
//            }
//        }
//        int ret = 0;
//        for (int i = minProfit; i <= maxProfit; i++) {
//            ret = (ret + res[n][i]) % mod;
//        }
//        return ret;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int n = 5;
//        int minProfit = 3;
//        int[] group = new int[]{2, 2};
//        int[] profit = new int[]{2, 3};
//        int res = solution.profitableSchemes(n, minProfit, group, profit);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
