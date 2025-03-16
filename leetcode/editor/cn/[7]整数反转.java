//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -2³¹ <= x <= 2³¹ - 1
//
//
// Related Topics 数学 👍 3986 👎 0


import java.util.ArrayDeque;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 数学逻辑，采用乘10再加尾的方式，注意控制边界条件
 */
class Solution {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int temp = x % 10;
            x = x / 10;
            // 其实只有这两个边界条件需要控制
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }
            res = res * 10 + temp;
        }
        return res;
    }
}

/**
 * Success1
 * 还是用String来表示数字，投机取巧调用Integer.valueOf(String s)
 */
//class Solution {
//    public int reverse(int x) {
//        if (x == 0) {
//            return 0;
//        }
//        boolean lessThan0 = x < 0 ? true : false;
//        Deque<Integer> deque = new ArrayDeque<>();
//        while (x != 0) {
//            deque.add(Math.abs(x % 10));
//            x /= 10;
//        }
//        StringBuffer stringBuffer = new StringBuffer();
//        if (lessThan0) {
//            stringBuffer.append('-');
//        }
//        while (!deque.isEmpty()) {
//            stringBuffer.append(deque.pollFirst());
//        }
//        int res = 0;
//        try {
//            res = Integer.valueOf(stringBuffer.toString());
//        } catch (NumberFormatException numberFormatException) {
//            return res;
//        }
//        return res;
//    }
//}

/**
 * Failure1
 * 注意因为它是以十进制数为反转单位的，所以不能直接把对应的二进制串进行反转，也不能四位一组进行反转，逻辑不一样
 * 这是进制的本质区别
 */
//class Solution {
//    public int reverse(int x) {
//        Deque<Integer> deque = new ArrayDeque<>();
//        while (x != 0) {
//            deque.add(x & 1);
//            x >>= 1;
//        }
//        int toAdd = 1;
//        int res = 0;
//        int signal = deque.pollFirst();
//        if (signal == 0) {
//            while (!deque.isEmpty()) {
//                res += toAdd * deque.pollLast();
//                toAdd <<= 1;
//            }
//        }
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
