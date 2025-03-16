//实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xⁿ ）。
//
//
//
// 示例 1：
//
//
//输入：x = 2.00000, n = 10
//输出：1024.00000
//
//
// 示例 2：
//
//
//输入：x = 2.10000, n = 3
//输出：9.26100
//
//
// 示例 3：
//
//
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
//
//
//
//
// 提示：
//
//
// -100.0 < x < 100.0
// -231 <= n <= 231-1
// n 是一个整数
// 要么 x 不为零，要么 n > 0 。
// -104 <= xⁿ <= 104
//
//
// Related Topics 递归 数学 👍 1181 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 快速幂，和快速乘的思想差不多，只不过一个把结果加起来，一个把结果乘起来
 * 比如9*7和9^7，9都是重复核心，7(2^2+2^1+2^0)是重复次数，核心是用的重复次数的二进制表示
 * 9*(2^2+2^1+2^0)和9^(2^2+2^1+2^0)只是被乘/幂数加/乘和结果处理的差异
 * 注意分正指数和负指数，而且正负指数转换的话可能存在溢出
 * 效率很高，100%了
 */
class Solution {
    public double myPow(double x, int n) {
        // 1的任何次幂都是1，避免计算太久时间溢出的情况
        if (x == 1) {
            return 1;
        }
        // 这个负数太小以至于它的幂很小接近0，而且转换成正数会溢出，所以单独处理
        if (n == Integer.MIN_VALUE) {
            // 当然这种特殊情况特殊处理
            if (x == -1) {
                return 1.0;
            }
            return 0.0;
        }
        double res = 1;
        double mul = x;
        // 这样直接除处理正幂和负幂就够了
        boolean rev = n < 0;
        n = rev ? -n : n;
        while (n != 0) {
            if ((n & 1) != 0) {
                res *= mul;
            }
            mul *= mul;
            n >>= 1;
        }
        return rev ? 1 / res : res;
    }
}

/**
 * Failure1
 * 直接这样递归会栈溢出
 */
//class Solution {
//    public double myPow(double x, int n) {
//        // 注意分正幂和负幂
//        if (n > 0) {
//            return myPow(x, n - 1) * x;
//        } else if (n < 0) {
//            return myPow(x, n + 1) / x;
//        } else {
//            return 1;
//        }
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
