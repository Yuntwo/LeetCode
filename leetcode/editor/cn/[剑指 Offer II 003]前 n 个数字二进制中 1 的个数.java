//给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
//
//
//
// 示例 1:
//
//
//输入: n = 2
//输出: [0,1,1]
//解释:
//0 --> 0
//1 --> 1
//2 --> 10
//
//
// 示例 2:
//
//
//输入: n = 5
//输出: [0,1,1,2,1,2]
//解释:
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101
//
//
//
//
// 说明 :
//
//
// 0 <= n <= 10⁵
//
//
//
//
// 进阶:
//
//
// 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？
// 要求算法的空间复杂度为 O(n) 。
// 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。
//
//
//
//
//
// 注意：本题与主站 338 题相同：https://leetcode-cn.com/problems/counting-bits/
//
// Related Topics 位运算 动态规划 👍 119 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 时间复杂度为 O(n*sizeof(integer))
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            // 注意这里不能直接对i操作，否则会改变外部循环的边界条件
            int index = i;
            int count = 0;
            for (int j = 0; j < 32; j++) {
                if ((index & 1) == 1) {
                    count++;
                }
                index = index >> 1;
            }
            res[i] = count;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int[] res = solution.countBits(n);
        System.out.println(res);
    }
}

/**
 * Success2
 * 需要掌握一些奇偶的规律
 * 一般找规律也要学会从奇偶的性质着手
 */
//class Solution {
//    public int[] countBits(int n) {
//        int[] dp = new int[n + 1];
//        for (int i = 0; i <= n; i++) {
////            // 注意这里不能直接对i操作，否则会改变外部循环的边界条件
////            int index = i;
////            // 注意index & 1要用()，&的优先级并不比+高
////            dp[index] = dp[index >> 1] + (index & 1);
//            // 好像可以直接对i操作，或许因为实际上不是直接对i操作的，而是返回的一个新的基本数据类型，应该是这样的
//            // 而且这里没有改变i的值再对i进行引用，不像第一种方法实际上在内层循环中需要改变i的值
//            dp[i] = dp[i >> 1] + (i & 1);
//        }
//        return dp;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
