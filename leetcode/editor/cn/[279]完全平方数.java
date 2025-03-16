//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
//
//
//
//
// 示例 1：
//
//
//输入：n = 12
//输出：3
//解释：12 = 4 + 4 + 4
//
// 示例 2：
//
//
//输入：n = 13
//输出：2
//解释：13 = 4 + 9
//
//
//
// 提示：
//
//
// 1 <= n <= 10⁴
//
//
// Related Topics 广度优先搜索 数学 动态规划 👍 1549 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 重复使用就是从左往右覆盖式赋值
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        int max = (int) Math.sqrt(n);
        for (int i = 1; i <= max; i++) {
            int pow = i * i;
            for (int j = 1; j <= n; j++) {
                if (j - pow >= 0) {
                    dp[j] = Math.min(dp[j - pow] + 1, dp[j]);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.numSquares(12);
        System.out.println(res);
    }
}

/**
 * Success1
 * 注意它这里题目的意思是每个完全平方数可以重用很多次
 * 不需要can数组了
 */
//class Solution {
//    public int numSquares(int n) {
//        // 初始化都是0
//        int[] dp = new int[n + 1];
//        for (int i = 1; i <= n; i++) {
//            // 注意这里最坏的情况就是全部用很多次1来达到
//            // 这是显而易见的，不用费事计算直接初始化
//            dp[i] = i;
//            // 注意这里是从小到大遍历，不是01背包问题的那种思路
//            // 由于对于每一个目标，都是从最小的那种情况，所以一个完全平方数可以被加很多次
//            for (int j = 1; j * j <= i; j++) {
//                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
//            }
//        }
//        return dp[n];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.numSquares(12);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 */
//class Solution {
//    public int numSquares(int n) {
//        boolean[] can = new boolean[n + 1];
//        can[0] = true;
//        int[] num = new int[n + 1];
//        int res = 0;
//        for (int i = 1; n >= (i * i); i++) {
//            int temp = i * i;
//            int count = 1;
//            while (n >= (count * temp)) {
//                for (int j = n; j >= temp; j--) {
//                    if (can[j - temp]) {
//                        can[j] = can[j - temp];
//                        num[j] = num[j - temp] + count;
//                    }
//                }
//                count++;
//            }
//        }
//        return num[n];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.numSquares(12);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
