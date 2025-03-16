//求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
//
//
//
// 示例 1：
//
// 输入: n = 3
//输出: 6
//
//
// 示例 2：
//
// 输入: n = 9
//输出: 45
//
//
//
//
// 限制：
//
//
// 1 <= n <= 10000
//
//
// Related Topics 位运算 递归 脑筋急转弯 👍 607 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 还是不太明白递归和动态规划的本质区别
 * 当然这种情况动态数组记忆化太庞大，而且子问题就是层层返回没有复杂到不同层次返回，确实动态规划没必要了
 * 而且限制了不能用for的话实际上就是不能用迭代了，就不能自底向上了
 */
class Solution {
    public int sumNums(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumNums(n - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
