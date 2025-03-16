//编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
//
//
//
// 提示：
//
//
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的
//还是无符号的，其内部的二进制表示形式都是相同的。
// 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
//
//
//
//
// 示例 1：
//
//
//输入：n = 11 (控制台输入 00000000000000000000000000001011)
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
//
//
// 示例 2：
//
//
//输入：n = 128 (控制台输入 00000000000000000000000010000000)
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
//
//
// 示例 3：
//
//
//输入：n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
//
//
//
// 提示：
//
//
// 输入必须是长度为 32 的 二进制串 。
//
//
//
//
// 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
// Related Topics 位运算 👍 215 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Failure1
 */
//public class Solution {
//    // you need to treat n as an unsigned value
//    public int hammingWeight(int n) {
//        int index = 1;
//        while (n > power(2, index) - 1) {
//            index++;
//        }
//        if (n == power(2, index) - 1) {
//            return index;
//        }
//        int result = 0;
//        for (int i = 0; i < index; i++) {
//            result += n / power(2, index - i - 1);
//            n = n % power(2, index - i - 1);
//        }
//        return result;
//    }
//
//    // 直接这样做的话数字大了会溢出
//    private int power(int base , int exponent) {
//        int power = 1;
//        for (int i = 0; i < exponent; i++)
//            power *= base;
//        return power;
//    }
//}

/**
 * Success3
 * 直接进行位运算
 * 纯粹是语法操作的问题
 */
//public class Solution {
//    // you need to treat n as an unsigned value
//    public int hammingWeight(int n) {
//        int cnt = 0;
//        for (int i = 0; i < 32; i++) {
//            // 注意1<<i的意思是对1左移i位，这样就可以直接检测n的第i位是否为1了
//            if ((n & 1 << i) != 0) {
//                cnt++;
//            }
//        }
//        return cnt;
//    }
//}

/**
 * Success2
 * 位运算中的一个技巧：对于任何二进制数 n，当 n 与 n - 1 进行按位与（&）运算时，n 的最低位的1会被翻转为0
 * 例如，假设 n = 1101000（二进制）：
 * 第一次 n &= (n - 1)：1101000 & 1100111 = 1100000，cnt = 1
 * 第二次 n &= (n - 1)：1100000 & 1011111 = 1000000，cnt = 2
 * 第三次 n &= (n - 1)：1000000 & 0111111 = 0000000，cnt = 3
 */
//public class Solution {
//    // you need to treat n as an unsigned value
//    public int hammingWeight(int n) {
//        int cnt = 0;
//        while (n != 0) {
//            // 这样操作会将n的最低一位的1变为0
//            // 能够翻转多少次就说明有多少1
//            n &= (n - 1);
//            cnt++;
//        }
//        return cnt;
//    }
//}

/**
 * Success1
 * 可以直接调用函数
 */
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // 这是Integer类的静态方法
        return Integer.bitCount(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
