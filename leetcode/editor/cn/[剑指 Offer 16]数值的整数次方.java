//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xⁿ）。不得使用库函数，同时不需要考虑大数问题。
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
// 示例 3：
//
//
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2⁻² = 1/2² = 1/4 = 0.25
//
//
//
// 提示：
//
//
// -100.0 < x < 100.0
// -2³¹ <= n <= 2³¹-1
// -10⁴ <= xⁿ <= 10⁴
//
//
//
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
// Related Topics 递归 数学 👍 253 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.lang.Math;
import java.math.BigInteger;

// 这里乘数是double型的让事情复杂许多
// 1.00000的乘数不好处理
// 这道题主要是各种特殊情况的处理比较麻烦，函数实现思路很简单
// 不太明白2.00000^(-2147483648)的结果是0.0怎么理解
//class Solution {
//    public double myPow(double x, int n) {
//        double power = 1;
//        // 注意这里必须带绝对值，否则负数也会满足这个条件
//        if (abs(x - 1.00000) < 0.000001) {
//            return 1.00000;
//        }
//        int absN = abs(n);
//        for (int i = 0; i < absN; i++) {
//            power *= x;
//        }
//        if (n < 0) {
//            return 1/power;
//        }
//        return power;
//    }
//
//    private int abs(int n) {
//        if (n < 0) {
//            // 注意这些操作在n很大时都会溢出从而没有取到相反数，Q:但是我觉得也没有很大啊
////            int temp = 0-n;
////            int temp = -n;
////            int temp = Math.negateExact(n);
//            return temp;
//        }
//        return n;
//    }
//
//    private double abs(double n) {
//        if (n < 0) {
//            double temp = 0-n;
//            return temp;
//        }
//        return n;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        double res = solution.myPow(2.00000, -2);
//        System.out.println(res);
//    }
//}
class Solution {
    public double myPow(double x, int n) {
        double res = 1;
        // double型判断如果为1的话，单独处理，否则会一直算时间溢出
        if (Math.abs(x - 1.00000) < 0.000001) {
            return 1;
        }
        // double型判断如果为-1的话，单独处理，否则会一直算时间溢出
        if (Math.abs(x + 1.00000) < 0.000001) {
            // 分奇数和偶数的情况
            long temp = Math.abs((long) n);
            if (temp % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                res *= x;
            }
        } else if (n == 0) {
            return 1;
        } else {
            // int和long都是基本数据类型，进行强制类型转换
            long temp = -(long) n;
            for (long i = 0; i < temp; i++) {
                res /= x;
                // 由于本身对精度限制了，所以小于这个精度就可以认为结果就是0不用算了，否则时间溢出
                if ((res - 0) < 0.000001) {
                    return 0.00000;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double res = solution.myPow(-1.00000, 2147483647);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
