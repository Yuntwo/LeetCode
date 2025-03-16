//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
//
//
//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
//
//
// 示例 1：
//
//
//输入：n = 2
//输出：1
//
//
// 示例 2：
//
//
//输入：n = 5
//输出：5
//
//
//
//
// 提示：
//
//
// 0 <= n <= 100
//
// Related Topics 记忆化搜索 数学 动态规划 👍 293 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 这样不行，44就时间溢出了
//class Solution {
//    public int fib(int n) {
//        if (n <= 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        return fib(n - 2) + fib(n - 1);
//    }
//}
// 从下往上的循环解法
// 很快，但不知道为什么占的空间算多的
class Solution {
    public int fib(int n) {
        // 注意数组声明是不用说明数组大小的，只有申请分配空间才需要
        int[] result = {0, 1};
        if (n < 2) {
            return result[n];
        }
        int fibN = 0;
        // 不需要数组存储，只需要两个元素空间覆盖存储即可
        int fibNMinus1 = result[1];
        int fibNMinus2 = result[0];
        for (int i = 0; i < n - 1; i++) {
            // 注意根据题目要求，应该是在这里就要取余，而不只是最后的结果取余
            fibN = (fibNMinus1 + fibNMinus2) % 1000000007;
            fibNMinus2 = fibNMinus1;
            fibNMinus1 = fibN;
        }
        return fibN;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
