//现需要将一根长为正整数 bamboo_len 的竹子砍为若干段，每段长度均为 正整数。请返回每段竹子长度的 最大乘积 是多少。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
//
//
// 示例 1：
//
//
//输入：bamboo_len = 12
//输出：81
//
//
//
//
// 提示：
//
//
// 2 <= bamboo_len <= 1000
//
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
//
//
//
// Related Topics 数学 动态规划 👍 267 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
import java.math.BigInteger;

/**
 * Success1
 * 动态规划，效率很低，需要用BigInteger来保证不会溢出
 * 注意它这里说的取余意思应该是最终结果取余，所以对中间结果取余再计算的话会错误，加法乘的方法来中间取余避免溢出自然也不行
 */
class Solution {
    public int cuttingBamboo(int bamboo_len) {
        BigInteger[] max = new BigInteger[bamboo_len + 1];
        BigInteger mod = BigInteger.valueOf((long) 1e9 + 7);
        max[1] = BigInteger.valueOf((long) 1);
        max[bamboo_len] = BigInteger.ZERO;
        for (int i = 2; i <= bamboo_len; i++) {
            // 完整不砍的情况，只是对于中间结果使用，bamboo_len这里必须至少砍一刀
            if (i != bamboo_len) {
                max[i] = BigInteger.valueOf((long) i);
            }
            for (int j = 1; j <= (i / 2); j++) {
                max[i] = max[i].max(max[j].multiply(max[i - j]));
            }
        }
        return max[bamboo_len].mod(mod).intValue();
    }

    public int multi(int multiplicand, int multipler, int mod) {
        BigInteger sum = BigInteger.valueOf((long) 0);
        BigInteger mul = BigInteger.valueOf((long) multiplicand);
        BigInteger modd = BigInteger.valueOf((long) mod);
        while (multipler != 0) {
            if ((multipler & 1) == 1) {
                sum = sum.add(mul);
                sum = sum.mod(modd);
            }
            // 直接移位加倍
//            multiplicand += multiplicand;
//            mul <<= 1;
            mul = mul.add(mul);
            mul = mul.mod(modd);
//            mul %= mod;
            // 注意这里要等于
            multipler >>= 1;
        }
        return sum.intValue();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.multi(2, 4, (int) 1e9 + 7));
        System.out.println(solution.cuttingBamboo(30));
    }

}

//leetcode submit region end(Prohibit modification and deletion)
