//给你一个整数 n ，请你找出并返回第 n 个 丑数 。
//
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
//
//
//
// 示例 1：
//
//
//输入：n = 10
//输出：12
//解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//解释：1 通常被视为丑数。
//
//
//
//
// 提示：
//
//
// 1 <= n <= 1690
//
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 992 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        int[] num = new int[n];
        int a = 0, b = 0, c = 0;
        num[0] = 1;
        for (int i = 1; i < n; i++) {
            num[i] = Math.min(2 * num[a], Math.min(3 * num[b], 5 * num[c]));
            // 注意他们的
            if (num[i]==2*num[a]) a++;
            if (num[i]==3*num[b]) b++;
            if (num[i]==5*num[c]) c++;
        }
        return num[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
