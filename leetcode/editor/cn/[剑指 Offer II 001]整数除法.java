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
// Related Topics 位运算 数学 👍 212 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 有些逻辑还是有点混乱
 */
class Solution {

    /**
     * Success1
     */
    public int divide(int a, int b) {
        if (a == Integer.MAX_VALUE && b == 1) {
            return a;
        }
        // 把正数都转为负数，统一符号且不会有负数转为正数的溢出问题
        boolean rev = false;
        if (a > 0) {
            rev = !rev;
            a = -a;
        }
        if (b > 0) {
            rev = !rev;
            b = -b;
        }
        // 溢出的特殊情况
        if (a == Integer.MIN_VALUE && b == -1) {
            // 注意要区分原始情况
            if (rev) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        // 这种情况下面的逻辑处理不了
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        }
        int left = 0;
        int right = Integer.MAX_VALUE;
        // 注意对于二分查找的标准算法来说是要到<=而不只是<，因为并不天然的认为最后剩余的left==right的那个位置一定能找到，有可能找不到
        // 当然对于这个问题来说不是必要的，这样设置只是为了迎合本题的逻辑
        while (left <= right) {
            // 注意这种写法避免溢出
            int mid = left + ((right - left) >> 1);
            // 注意因为x是截断的商，所以|b*x|<=a，注意a、b都是负数，x是正数
            if (greater(a, b, mid)) {
                right = mid - 1;
            // 比较麻烦的点：
            // 1、在于二分查找除以2是下取整，怎么保证能跳出循环
            // 2、小于等于的这个分支中由于除法本身的下取整，所以这个分支中等于或者小于的情况都有可能包含结果值
            } else {
                left = mid + 1;
            }
        }
        return rev ? -(left - 1) : left - 1;

    }

    /**
     * Failure1
     */
//    public int divide(int a, int b) {
//        // 把正数都转为负数，统一符号且不会有负数转为正数的溢出问题
//        boolean rev = false;
//        if (a > 0) {
//            rev = !rev;
//            a = -a;
//        }
//        if (b > 0) {
//            rev = !rev;
//            b = -b;
//        }
//        // 溢出的特殊情况
//        if (a == Integer.MIN_VALUE && b == -1) {
//            return Integer.MAX_VALUE;
//        }
//        int left = 0;
//        int right = Integer.MAX_VALUE;
//        int ans = 0;
//        while (left <= right) {
//            // 注意这种写法避免溢出
//            int mid = left + ((right - left) >> 1);
//            // 注意因为x是截断的商，所以|b*x|<=a，注意a、b都是负数，x是正数
//            if (quickMul(b, mid) >= a) {
//                ans = mid;
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return rev ? -ans : ans;
//
//    }

    /**
     * Success1
     */
    private boolean greater(int a, int b, int x) {
        int sum = 0;
        while (x != 0) {
            if ((x & 1) == 1) {
                // 注意这种写法避免溢出
                // 这样已经能确定x是更大的就直接跳出循环不要再算完了避免值太大溢出
                if (sum < a - b) {
                    return true;
                }
                sum += b;
            }
            b <<= 1;
            x >>= 1;
        }
        return false;
    }

    /**
     * Failure2
     * 乘法正确，但是对于结合二分查找的乘法结果可能太大会溢出，所以不能单独把乘的结果拿出来而要一起比较
     */
//    private int quickMul(int b, int x) {
//        int sum = 0;
//        while (x != 0) {
//            if ((x & 1) == 1) {
//                sum += b;
//            }
//            b <<= 1;
//            x >>= 1;
//        }
//        return sum;
//    }

    /**
     * Failure1
     * @param b 除数，负数
     * @param x 商，正数
     * @return 负数
     * 因为算术右移除以2对于正数是下取整，对于负数是上取整，所以-1算术右移到不了0而会死循环
     * 所以还是得用正数x的二进制表示
     */
//    private int quickMul(int b, int x) {
//        int sum = 0;
//        while (b != 0) {
//            if ((b & 1) == 1) {
//                sum -= x;
//            }
//            b >>= 1;
//            x <<= 1;
//        }
//        return sum;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int a = 15;
//        int b = 2;
//        int a = 7;
//        int b = -3;
//        int a = 1;
//        int b = 1;
//        int a = Integer.MIN_VALUE;
//        int b = 1;
        int a = Integer.MIN_VALUE;
        int b = Integer.MIN_VALUE;
        int res = solution.divide(a, b);
        System.out.println(res);
    }
}

/**
 * Success1
 * 官方题解快速乘+二分查找
 * 注意题目说了b!=0
 */
//class Solution {
//    public int divide(int a, int b) {
//        // 处理边界条件，溢出或者值太大刚好不用算了
//        if (a == -2147483648) {
//            if (b == 1) {
//                return a;
//            } else if (b == -1) {
//                // 题目说了除法结果溢出返回最大值
//                return Integer.MAX_VALUE;
//            }
//        }
//        // 值太大刚好不用算了
//        if (b == -2147483648) {
//            if (a == -2147483648) {
//                return 1;
//            } else {
//                return 0;
//            }
//        }
//
//        // 将除数和被除数都处理为负数，正数转负数刚好不存在溢出的可能，反之则有
//        boolean rev = false;
//        if (a > 0) {
//            a = -a;
//            rev = !rev;
//        }
//        if (b > 0) {
//            b = -b;
//            rev = !rev;
//        }
//
//        // 二分查找过程
//        int left = 1;
//        int right = Integer.MAX_VALUE;
//        int ans = 0;
//        // 注意不能使用/，而用>>1右移一位表示除以2，注意是小于等于
//        while (left <= right) {
//            // 注意调换表达式，直接(left+right)>>1，left+right可能溢出
//            int mid = left + ((right - left) >> 1);
//            boolean check = quickAdd(b, mid, a);
//            // 如果满足商的条件
//            if (check) {
//                // 保存这个结果，但是还要继续寻找
//                ans = mid;
//                // 注意判断溢出
//                if (mid == Integer.MAX_VALUE) {
//                    break;
//                }
//                // 满足条件的话继续往大了找
//                left = mid + 1;
//            // 不满足条件的话往小了找
//            } else {
//                right = mid - 1;
//            }
//        }
//        return rev ? -ans : ans;
//    }
//
//    /**
//     * x是被除数，y是除数，z是商，判断z这个商是否满足z*y>=x，也即满足x/y的商的基本条件(当然还有一个必要条件是z必须是最大的商)
//     * 注意由于不能直接用*，所以要用快速乘的实现方式
//     * 快速乘的思想是y乘以z的二进制表示中每一位的权重，然后把所有结果相加，核心在于把z拆成2进制表示
//     * 比如1*(2^0)*y相当于1*((2^0)*y)，0*(2^3)*y相当于0*((2^3)*y)，所以实际上add的那个数是y倍增的
//     */
//    private boolean quickAdd(int y, int z, int x) {
//        int result = 0;
//        //这里以y为加的基数，以z为加的次数
//        int add = y;
//        // 注意这里z在减的时候不是一个一个减的，而是直接对半减
//        while (z != 0) {
//            // 当前处理的位不为1就要加在结果中
//            if ((z & 1) != 0) {
//                // 不满足条件就直接跳出循环了
//                if (result < x - add) {
//                    return false;
//                }
//                result += add;
//            }
//            if (z != 1) {
//                // 这里是防止add倍增溢出，甚至都不用判断到add < Integer.MAX_VALUE，只要这个将要倍增的数大于x就不满足条件了
//                if (add < x - add) {
//                    return false;
//                }
//                // 自身加自身就是倍增
//                add += add;
//            }
//            // 每次循环z必移一位，就是一位一位地处理
//            z >>= 1;
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int a = -2147483648;
////        int b = 2;
//        int a = 7;
//        int b = -3;
////        int a = -2147483648;
////        int b = -1;
////        int a = -2147483648;
////        int b = 1;
//        int res = solution.divide(a, b);
//        System.out.println(res);
//    }
//}

/**
 * Failure 2
 * 注意要是用加减法的解法还是不方便用正负数转换的形式
 * 这样只是规避了正负转换中的溢出错误
 * Q:不知道Time Limit Exceeded是个什么情况，结果可能都是正确的，但确实是不允许这样算
 */
//class Solution {
//    public int divide(int a, int b) {
//        // 这两种情况结果都是正数
//        if (a == 0) {
//            return 0;
//        }
//        // 注意这是计算结果的溢出错误处理要求
//        if (a == -2147483648 && b == -1) {
//            return 2147483647;
//        }
//        int res = 0;
//        if (a > 0 && b > 0) {
//            while (a >= b) {
//                a -= b;
//                res++;
//            }
//        } else if (a < 0 && b < 0) {
//            while (a <= b) {
//                a -= b;
//                res++;
//            }
//        // 注意以下这两种结果都是负数，res要用--
//        } else if (a > 0 && b < 0) {
//            while (a > 0) {
//                a += b;
//                res--;
//            }
//            // 补多减了的
//            if (a < 0) {
//                res++;
//            }
//        } else {
//            while (a < 0) {
//                a += b;
//                res--;
//            }
//            // 补多减了的
//            if (a > 0) {
//                res++;
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int a = -2147483648;
////        int b = 2;
//        int a = 7;
//        int b = -3;
////        int a = -2147483648;
////        int b = -1;
////        int a = -2147483648;
////        int b = 1;
//        int res = solution.divide(a, b);
//        System.out.println(res);
//    }
//}

/**
 * Failure 1
 * 注意可能存在结果溢出的情况，要怎么检测，怎么返回
 */
//class Solution {
//    public int divide(int a, int b) {
//        if (a == 0) {
//            return 0;
//        }
//        if (a == -2147483648 && b == -1) {
//            return 2147483647;
//        }
//        if (a == -2147483648 && b == 1) {
//            return -2147483648;
//        }
//        boolean isAnsPos = false;
//        if ((a > 0 && b > 0) || (a < 0 && b < 0)) {
//            isAnsPos = true;
//        }
//        // 注意这里正负数转换在临界位置会出现溢出
//        a = Math.abs(a);
//        b = Math.abs(b);
//        int res = 0;
//        while (a >= b) {
//            a -= b;
//            res++;
//        }
//        if (isAnsPos) {
//            return res;
//        } else {
//            return 0 - res;
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int a = -2147483648;
//        int b = 2;
//        int res = solution.divide(a, b);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
