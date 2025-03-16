//给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
//
//
//
// 注意：
//
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 2³¹−1]。本题中，如果除法结果溢出，则返回 231 − 1
//
//
//
//
// 示例 1：
//
//
//输入：a = 15, b = 2
//输出：7
//解释：15/2 = truncate(7.5) = 7
//
//
// 示例 2：
//
//
//输入：a = 7, b = -3
//输出：-2
//解释：7/-3 = truncate(-2.33333..) = -2
//
// 示例 3：
//
//
//输入：a = 0, b = 1
//输出：0
//
// 示例 4：
//
//
//输入：a = 1, b = 1
//输出：1
//
//
//
// 提示:
//
//
// -2³¹ <= a, b <= 2³¹ - 1
// b != 0
//
//
//
//
// 注意：本题与主站 29 题相同：https://leetcode-cn.com/problems/divide-two-integers/
//
//
//
// Related Topics 数学 👍 286 👎 0



//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

    public int divide(int a, int b) {
        boolean isReversed = false;
        if (a > 0) {
            a = -a;
            isReversed = !isReversed;
        }
        if (b > 0) {
            b = -b;
            isReversed = !isReversed;
        }
        if (b == -1) {
            if (a == Integer.MIN_VALUE) {
                return isReversed ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            return isReversed ? a : -a;
        }
        // 这种情况单独拎出来，会超时，有可能是哪里不符合以下整体逻辑
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        }
        // 二分法找商，注意都转换为了负数，商就一定是正数
        int start = 0;
        int end = Integer.MAX_VALUE;
        // 注意它是向0取整，这里负数情况的就应该还是往小了找
        // 不能用除号，但是移位操作符可以除以2
        // 注意这里必须这样写
        while (start <= end) {
            // 注意加减法的优先级高于移位操作符
            int mid = ((end - start) >> 2) + start;
            // 注意不管被除数是正数还是负数，结果都是向0取整，那么对于正数结果来说都是向0偏
            // 定义函数是正数乘子mid是否大了，而不是其它含义，所以这里正常变就可以
            if (isGreaterMultiplier(b, mid, a)) {
                end = mid - 1;
            } else {
                // 注意这里必须这么写，否则由于除法总是会向下取整则可能上不去退不出循环
                start = mid + 1;
            }
        }
        start--;
        return isReversed ? -start : start;
    }

    /**
     * 注意这里定义函数是GreaterMultiplier，也即这个正数的乘子是否需要减小
     * 而不是通常意义上的这个负数被乘数乘正数乘子的结果大于比较数(更靠近0)
     * 注意这里负数更大是更靠近0，整数更大是更远离0，因为大都是往右边方向走的极限
     * @param multiplicand
     * @param multiplier
     * @param tobeCompared
     * @return
     */
    private boolean isGreaterMultiplier(int multiplicand, int multiplier, int tobeCompared) {
        int res = 0;
        while (multiplier != 0) {
            if ((multiplier & 1) == 1) {
                // 注意这里multiplier还没乘完
                // 注意考虑到可能溢出的情况还是先比较
                if (res < (tobeCompared - multiplicand)) {
                    // 注意这里是反着来的对于负数
                    return true;
                }
                res += multiplicand;
            }
            multiplicand <<= 1;
            multiplier >>= 1;
        }
        return false;
    }
}

/**
 * Review1
 * 加法思路，效率一般
 */
//class Solution {
//
//    public int divide(int a, int b) {
//        // 先把所有数转为负数
//        boolean isReversed = false;
//        if (a > 0) {
//            a = -a;
//            isReversed = !isReversed;
//        }
//        if (b > 0) {
//            b = -b;
//            isReversed = !isReversed;
//        }
//        // 考虑b为-1的特殊情况，直接返回不浪费时间，也有溢出的情况需要特殊考虑
//        if (b == -1) {
//            // 注意这种情况下结果会溢出，返回特殊值
//            if (a == Integer.MIN_VALUE) {
//                // -2147483648/1=-2147483648，-2147483648/-1=2147483647
//                return isReversed ? Integer.MIN_VALUE : Integer.MAX_VALUE;
//            }
//            // 注意这里a本来就是reversed
//            return isReversed ? a : -a;
//        }
//        int res = 0;
//        while (a < 0) {
//            a -= b;
//            res++;
//        }
//        if (a > 0) {
//            res--;
//        }
//        return isReversed ? -res : res;
//    }
//
//}

/**
 * Success2
 * 还是二分法+加法乘的思路，厘清了逻辑，关键在于：
 * 1、逻辑定义要清楚、计算行为要清楚；有些逻辑定义不好处理就换逻辑定义(如这里的left)
 * 2、有些逻辑会破坏整体逻辑性陷入两难境地的话就单独拎出来，否则就是死结
 * 除法规律：
 * 1、偶/偶 与 奇/奇：无余数少一个结果就减一；有余数(偶/偶一定余偶数，奇/奇不一定)-少一个肯定不影响(反正有余)，多一个奇/奇可能多一个(奇/奇余偶数的情况才有可能加一个凑齐奇数)
 * 2、奇/偶 与 偶/奇：一定有余数(余奇数或者偶数)-少一个肯定不影响，多一个有可能多一个(奇/偶余偶数、偶/奇余奇数的情况才有可能加一个凑齐奇数/偶数)
 */
//class Solution {
//
//    public int divide(int a, int b) {
//        if (a == Integer.MIN_VALUE && b == -1) {
//            return Integer.MAX_VALUE;
//        }
//        boolean isNegative = false;
//        if (a > 0) {
//            a = -a;
//            isNegative = !isNegative;
//        }
//        if (b > 0) {
//            isNegative = !isNegative;
//            b = -b;
//        }
//        // 除数为abs(1)的情况就不计算了，节约资源
//        if (b == -1) {
//            return isNegative ? a : -a;
//        }
//        // 这种情况要统一到逻辑里面的话会破坏其他逻辑，只有单独剥离出来最方便
//        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
//            return 1;
//        }
//        // 仍然希望中间结果得到的除数是负负为正
//        int left = 0;
//        // 注意这里因为a为Integer.MIN_VALUE的情况仍有可能出现，所以要讨论一下，否则这种情况就溢出了
//        int right = a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
//        // 这里定义left是第一个更大的乘子
//        while (left <= right) {
//            // 注意加减法的优先级高于移位操作
//            int mid = (right - left >> 1) + left;
//            boolean check = isGreaterMultiplier(b, mid, a);
//            // 注意这里正负轴反起来的
////            boolean check = greater(a, b, mid);
//            // 这里减1的原因是因为不是第一个
//            if (check) {
//                right = mid - 1;
//            // 定义left是第一个更大的乘子，所以这里left要+1
//            } else {
//                left = mid + 1;
//            }
//        }
//        // 因为定义所以这里left统一减1
//        left--;
//        return isNegative ? -left : left;
//    }
//
//    private boolean isGreaterMultiplier(int multiplicand, int multiplier, int pivot) {
//        int res = 0;
//        while (multiplier != 0) {
//            // 注意这里直接用加法也容易溢出，当值特别大的时候
////            if ((multiplier & 1) == 1) {
////                res += multiplicand;
////            }
////            if (res > pivot) {
////                return false;
////            }
//            // 注意这里要先判断大小，不能在下面这个if里面，否则如果不为true的话这种情况就避开了，后面就有可能溢出
//            if (res < pivot - multiplicand) {
//                return true;
//            }
//            // 注意只有当multiplier为正数时才能这样运用这个公式
//            if ((multiplier & 1) == 1) {
//                res += multiplicand;
//            }
//            // 这里multiplicand也是容易溢出的，所以要控制判断，但是如果前面控制得好后面这里就不需要，不会到这么大的值来
////            if (multiplicand < (Integer.MIN_VALUE >> 1)) {
////                return true;
////            }
//            multiplicand <<= 1;
//            multiplier >>= 1;
//        }
//        return false;
//    }
//
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        System.out.println(-2137325331 << 1);
//        int res = 0;
////        System.out.println(res);
////        res = solution.divide(-2147483648, -2147483648);
////        res = solution.divide(15, 2);
////        res = solution.divide(1004958205, -2137325331);
//        System.out.println(solution.isGreaterMultiplier(-2137325331, 14, -1004958205));
//        res = solution.divide(-2147483648, 2);
////        res = solution.divide(2147483647, 2);
////        res = solution.divide(7, -3);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 搞不明白了，不能刚好
 */
//class Solution {
//
//    public int divide(int a, int b) {
//        if (a == Integer.MIN_VALUE && b == -1) {
//            return Integer.MAX_VALUE;
//        }
//        boolean isNegative = false;
//        if (a > 0) {
//            a = -a;
//            isNegative = !isNegative;
//        }
//        if (b > 0) {
//            isNegative = !isNegative;
//            b = -b;
//        }
//        // 除数为abs(1)的情况就不计算了，节约资源
//        if (b == -1) {
//            return isNegative ? a : -a;
//        }
//        // 仍然希望中间结果得到的除数是负负为正
//        int left = 0;
//        // 注意这里因为a为Integer.MIN_VALUE的情况仍有可能出现，所以要讨论一下，否则这种情况就溢出了
//        int right = a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
//        while (left <= right) {
//            // 注意加减法的优先级高于移位操作
//            int mid = (right - left >> 1) + left;
//            boolean check = isGreaterMultiplier(b, mid, a);
//            // 注意这里正负轴反起来的
////            boolean check = greater(a, b, mid);
//            if (check) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        left--;
//        return isNegative ? -left : left;
//    }
//
//
//    private boolean isGreaterMultiplier(int multiplicand, int multiplier, int pivot) {
//        int res = 0;
//        while (multiplier != 0) {
//            // 注意这里直接用加法也容易溢出，当值特别大的时候
////            if ((multiplier & 1) == 1) {
////                res += multiplicand;
////            }
////            if (res > pivot) {
////                return false;
////            }
//            // 注意只有当multiplier为正数时才能这样运用这个公式
//            if ((multiplier & 1) == 1) {
//                if (res < pivot - multiplicand) {
//                    return true;
//                }
//                res += multiplicand;
//            }
//            // 这里multiplicand也是容易溢出的，所以要控制判断
//            if (multiplicand < (Integer.MIN_VALUE >> 1)) {
//                return true;
//            }
//            multiplicand <<= 1;
//            multiplier >>= 1;
//        }
//        return false;
//    }
//
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        System.out.println(-2137325331 << 1);
//        int res = 0;
////        System.out.println(res);
//        res = solution.divide(-2147483648, -2147483648);
////        res = solution.divide(15, 2);
////        res = solution.divide(1004958205, -2137325331);
////        System.out.println(solution.isGreaterMultiplier(-2137325331, 14, -1004958205));
////        res = solution.divide(-2147483648, 2);
////        res = solution.divide(2147483647, 2);
////        res = solution.divide(7, -3);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 其它思路基本正确
 * 但是把负数转成正数进行处理还是容易溢出，把正数转成负数好做些
 */
//class Solution {
//
//    public int divide(int a, int b) {
//        if (b == 1) {
//            return a;
//        }
//        if (b == -1) {
//            if (a == Integer.MIN_VALUE) {
//                return Integer.MAX_VALUE;
//            } else {
//                return -a;
//            }
//        }
//
//        boolean isNegative = false;
//        if (a < 0) {
//            isNegative = !isNegative;
//            a = -a;
//        }
//        if (b < 0) {
//            isNegative = !isNegative;
//            b = -b;
//        }
//        int left = 0;
//        int right = a;
//        while (left < right) {
//            // 注意加减法的优先级高于移位操作
//            int mid = (right - left >> 1) + left;
//            boolean check = isLessOrEqual(b, mid, a);
//            if (check) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        left--;
//        return isNegative ? -left : left;
//    }
//
//    /**
//     * Failure1
//     * 使用的quickAdd函数可能溢出，不能这样定义
//     * @param multiplicand
//     * @param multiplier
//     * @param pivot
//     * @return
//     */
////    public int divide(int a, int b) {
////        if (b == 1) {
////            return a;
////        }
////        if (b == -1) {
////            if (a == Integer.MIN_VALUE) {
////                return Integer.MAX_VALUE;
////            } else {
////                return -a;
////            }
////        }
////
////        boolean isNegative = false;
////        if (a < 0) {
////            isNegative = !isNegative;
////            a = -a;
////        }
////        if (b < 0) {
////            isNegative = !isNegative;
////            b = -b;
////        }
////        int left = 0;
////        int right = a;
////        while (left < right) {
////            // 注意加减法的优先级高于移位操作
////            int mid = (right - left >> 1) + left;
////            int temp = quickAdd(b, mid);
////            if (temp > a) {
////                right = mid - 1;
////            } else if (temp < a) {
////                left = mid;
////            } else {
////                return mid;
////            }
////        }
////        left--;
////        return isNegative ? -left : left;
////    }
//
//    private boolean isLessOrEqual(int multiplicand, int multiplier, int pivot) {
//        int res = 0;
//        while (multiplier != 0) {
//            // 注意这里直接用加法也容易溢出，当值特别大的时候
////            if ((multiplier & 1) == 1) {
////                res += multiplicand;
////            }
////            if (res > pivot) {
////                return false;
////            }
//            if ((multiplier & 1) == 1) {
//                if (res > pivot - multiplicand) {
//                    return false;
//                }
//                res += multiplicand;
//            }
//            multiplicand <<= 1;
//            multiplier >>= 1;
//        }
//        return true;
//    }
//
//    /**
//     * 注意这个quickAdd函数结果可能会溢出，所以不需要直接得出最终结果，只需要在中途判断大小返回即可
//     * @param multiplicand
//     * @param multiplier
//     * @return
//     */
//    private int quickAdd(int multiplicand, int multiplier) {
//        int res = 0;
//        while (multiplier != 0) {
//            if ((multiplier & 1) == 1) {
//                res += multiplicand;
//            }
//            multiplicand <<= 1;
//            multiplier >>= 1;
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.quickAdd(2, 7);
////        System.out.println(res);
////        res = solution.divide(-2147483648, -2147483648);
////        res = solution.divide(15, 2);
//        res = solution.divide(-2147483648, 2);
////        res = solution.divide(2147483647, 2);
////        res = solution.divide(7, -3);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 加法思路，要讨论的情况稍微麻烦点，代码复杂点
 */
//class Solution {
//    public int divide(int a, int b) {
//        if (b == -1) {
//            if (a == Integer.MIN_VALUE) {
//                return Integer.MAX_VALUE;
//            } else {
//                return -a;
//            }
//        }
//        if (a == -2147483648 && b == -2147483648) {
//            return 1;
//        }
//        if (b == 1) {
//            return a;
//        }
//        if (a == 0) {
//            return 0;
//        }
//        if (a > 0 && b > 0) {
//            int res = -1;
//            while (a >= 0) {
//                a -= b;
//                res++;
//            }
//            return res;
//        }
//        if (a > 0 && b < 0) {
//            int res = 1;
//            while (a >= 0) {
//                a += b;
//                res--;
//            }
//            return res;
//        }
//        if (a < 0 && b > 0) {
//            int res = 1;
//            while (a <= 0) {
//                a += b;
//                res--;
//            }
//            return res;
//        }
//        if (a < 0 && b < 0) {
//            int res = -1;
//            while (a <= 0) {
//                a -= b;
//                res++;
//            }
//            return res;
//        }
//        return 0;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.divide(-2147483648, -2147483648);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
