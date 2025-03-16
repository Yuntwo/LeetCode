//递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
//
// 示例1:
//
//
// 输入：A = 1, B = 10
// 输出：10
//
//
// 示例2:
//
//
// 输入：A = 3, B = 4
// 输出：12
//
//
// 提示:
//
//
// 保证乘法范围不会溢出
//
//
// Related Topics 位运算 递归 数学 👍 94 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 也不需要递归
 */
class Solution {
    public int multiply(int A, int B) {
        int res = 0;
        while (B != 0) {
            if ((B & 1) == 1) {
                res += A;
            }
            A <<= 1;
            B = B >> 1;
        }
        return res;
    }

}

/**
 * Success1
 * 快速乘和递归乘，效率很高了
 * 这里A表示乘数，将要被拆分成2进制的数；B表示被乘数
 */
//class Solution {
//    public int multiply(int A, int B) {
//        if (A == 0) {
//            return 0;
//        }
//        // 注意这里递归返回的是什么和
//        return (A & 1) == 0 ? multiply(A >> 1, B << 1) : B + multiply(A >> 1, B << 1);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
