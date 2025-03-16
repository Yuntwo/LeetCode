//输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
//
// 示例 1:
//
// 输入: n = 1
//输出: [1,2,3,4,5,6,7,8,9]
//
//
//
//
// 说明：
//
//
// 用返回一个整数列表来代替打印
// n 为正整数
//
// Related Topics 数组 数学 👍 186 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Success1
 * 直接做
 */
class Solution {
    public int[] printNumbers(int n) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            max += 9 * power(10, i);
        }
        int[] result = new int[max];
        for (int i = 0; i < max; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    private int power(int base , int exponent) {
        int power = 1;
        for (int i = 0; i < exponent; i++)
            power *= base;
        return power;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
