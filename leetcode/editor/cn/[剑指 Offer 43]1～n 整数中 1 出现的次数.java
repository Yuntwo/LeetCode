//输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
//
//
//
// 示例 1：
//
//
//输入：n = 12
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 13
//输出：6
//
//
//
// 限制：
//
//
// 1 <= n < 2^31
//
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
//
// Related Topics 递归 数学 动态规划 👍 384 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 这种算法还是耗时太久，还是得找规律用递推公式
//class Solution {
//    public int countDigitOne(int n) {
//        int sum = 0;
//        for (int i = 1; i <= n; i++) {
//            sum += countOne(i);
//        }
//        return sum;
//    }
//
//    //    private int countOne(int i) {
////        int count = 0;
////        char[] nums = String.valueOf(i).toCharArray();
////        for (char num :
////            nums) {
////            if (num == '1') {
////                count++;
////            }
////        }
////        return count;
////    }
//    private int countOne(int i) {
//        int count = 0;
//        while (i != 0) {
//            if (i % 10 == 1) {
//                count++;
//            }
//            i /= 10;
//        }
//        return count;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.countDigitOne(103);
////        int res = solution.countDigitOne(824883294);
//        System.out.println(res);
//    }
//}
// 这其实是从数字进位构造的角度来解的
// 应该来说出现0-9任一种数字的思路都可以这样来解
// 也不一定，因为它是这样1个1个加构造的数字
class Solution {
    public int countDigitOne(int n) {
        int digit = 1;
        int res = 0;
        int high = n / 10;
        int cur = n % 10;
        int low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    //    private int countOne(int i) {
//        int count = 0;
//        char[] nums = String.valueOf(i).toCharArray();
//        for (char num :
//            nums) {
//            if (num == '1') {
//                count++;
//            }
//        }
//        return count;
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
