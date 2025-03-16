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
// Related Topics 动态规划 👍 169 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Reivew2
 * 使用一维dp，但还是要使用两个一维dp
 */
//class Solution {
//    public int numRollsToTarget(int n, int k, int target) {
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            int[] newDp = new int[target + 1];
//            for (int j = target; j >= 1; j--) {
//                for (int l = 1; l <= k; l++) {
//                    if (j >= l) {
//                        newDp[j] += dp[j - l];
//                        newDp[j] %= ((int) 1e9 + 7);
//                    }
//                }
//            }
//            dp = newDp;
//        }
//        return dp[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int res = solution.numRollsToTarget(2, 6, 7);
////        int res = solution.numRollsToTarget(5, 4, 10);
////        int res = solution.numRollsToTarget(30, 30, 500);
//        int res = solution.numRollsToTarget(1, 6, 3);
//        System.out.println(res);
//    }
//}

/**
 * Sccuess3
 * 用一维dp+一个变量
 */
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // 注意这里对于后续的要更改，否则会多算
            if (i > 1) {
                dp[0] = 0;
            }
            for (int j = target; j >= 1; j--) {
                int temp = 0;
                for (int l = 1; l <= k; l++) {
                    if (j >= l) {
                        // 注意这里dp[j]不能直接+=，因为此时的dp[j]是上一轮掷骰子为j的数量，下一轮掷骰子的数量是不能加上它的
//                        dp[j] += dp[j - l];
                        temp += dp[j - l];
                        temp %= ((int) 1e9 + 7);
                    }
                }
                dp[j] = temp;
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int res = solution.numRollsToTarget(2, 6, 7);
//        int res = solution.numRollsToTarget(5, 4, 10);
//        int res = solution.numRollsToTarget(30, 30, 500);
        int res = solution.numRollsToTarget(1, 6, 3);
        System.out.println(res);
    }
}

/**
 * Review1
 * 二维dp
 */
//class Solution {
//    public int numRollsToTarget(int n, int k, int target) {
//        int[][] dp = new int[n + 1][target + 1];
//        dp[0][0] = 1;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= target; j++) {
//                for (int l = 1; l <= k; l++) {
//                    if (j >= l) {
//                        dp[i][j] += dp[i - 1][j - l];
//                        dp[i][j] %= ((int) 1e9 + 7);
//                    }
//                }
//            }
//        }
//        return dp[n][target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int res = solution.numRollsToTarget(2, 6, 7);
////        int res = solution.numRollsToTarget(5, 4, 10);
////        int res = solution.numRollsToTarget(30, 30, 500);
//        int res = solution.numRollsToTarget(1, 6, 3);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 一维dp有些细节要多考虑一下
 */
//class Solution {
//    public int numRollsToTarget(int n, int k, int target) {
//        int[] dp = new int[target + 1];
//        for (int i = 1; i < Math.min(k, target) + 1; i++) {
//            dp[i] = 1;
//        }
//        for (int j = 2; j < n + 1; j++) {
//            for (int i = target; i >= 1; i--) {
//                int temp = 0;
//                for (int l = 1; l < k + 1; l++) {
//                    // 注意这里不能等于0，因为等于0就相当于没有骰，这于实际骰的次数定义不符合，会多算
//                    // 但是骰一次必须用到==0的状态，所以将骰一次的情况单独处理
//                    if (i - l > 0) {
//                        temp = (temp + dp[i - l]) % 1000000007;
//                    }
//                }
//                dp[i] = temp;
//            }
//        }
//        return dp[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int res = solution.numRollsToTarget(2, 6, 7);
////        int res = solution.numRollsToTarget(5, 4, 10);
//        int res = solution.numRollsToTarget(30, 30, 500);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 注意可以压缩成一维数组，但是这样写不太行，因为这样是叠加了骰1、2、3...次所有的组合数结果
 * 你当论的dp[i]理论上应该是初始值0然后加上上一轮的各种结果，所以这种写法是多了
 */
//class Solution {
//    public int numRollsToTarget(int n, int k, int target) {
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        for (int j = 1; j < n + 1; j++) {
//            for (int i = target; i >= 1; i--) {
//                for (int l = 1; l < k + 1; l++) {
//                    if (i - l >= 0) {
//                        dp[i] = (dp[i] + dp[i - l]) % 1000000007;
//                    }
//                }
//            }
//        }
//        return dp[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int res = solution.numRollsToTarget(2, 6, 7);
//        int res = solution.numRollsToTarget(5, 4, 10);
////        int res = solution.numRollsToTarget(30, 30, 500);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 二维dp
 * 题目理解：
 * 1、这里求的是排列数
 * 2、对于每一个target最多只能有n个数组成排列，就是说最多只能选n次骰子
 */
//class Solution {
//    public int numRollsToTarget(int n, int k, int target) {
//        int[][] dp = new int[n + 1][target + 1];
//        // 注意只有这一种初始状态
//        dp[0][0] = 1;
//        for (int i = 1; i < target + 1; i++) {
//            for (int j = 1; j < n + 1; j++) {
//                for (int l = 1; l < k + 1; l++) {
//                    if (i - l >= 0) {
//                        // 注意取余
//                        dp[j][i] = (dp[j][i] + dp[j - 1][i - l]) % 1000000007;
//                    }
//                }
//            }
//        }
//        return dp[n][target];
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
