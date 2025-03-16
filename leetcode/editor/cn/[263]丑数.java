//丑数 就是只包含质因数 2、3 和 5 的正整数。
//
// 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：n = 6
//输出：true
//解释：6 = 2 × 3
//
// 示例 2：
//
//
//输入：n = 1
//输出：true
//解释：1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。
//
// 示例 3：
//
//
//输入：n = 14
//输出：false
//解释：14 不是丑数，因为它包含了另外一个质因数 7 。
//
//
//
//
// 提示：
//
//
// -2³¹ <= n <= 2³¹ - 1
//
//
// Related Topics 数学 👍 354 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 数学公式表达法+暴力解法
class Solution {
    public boolean isUgly(int n) {
        if (n == 1) {
            return true;
        }
        int[] factors = new int[]{2, 3, 5};
        // 注意0会导致无限除法，排除这种情况
        if (n == 0) {
            return false;
        }
        // 注意这种写法
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        // 注意对于除法来讲最后整除的话是结果1不是0
        if (n == 1) {
            return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
