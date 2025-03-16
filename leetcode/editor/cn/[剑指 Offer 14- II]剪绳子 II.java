//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1]
// 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘
//积是18。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
//
//
// 示例 1：
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1
//
// 示例 2:
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
//
//
//
// 提示：
//
//
// 2 <= n <= 1000
//
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
// Related Topics 数学 动态规划 👍 162 👎 0




//leetcode submit region begin(Prohibit modification and deletion)

// 注意在这个插件导入包也要从这上面的注释开始，否则识别不了
import java.math.BigInteger;
import java.lang.String;

/**
 * Success2-plus
 * 与1相比，这个n更大了，对时间和空间的要求更高
 * 动态规划循环法勉强可以，但是应该来说贪婪算法是最高效的
 * 主要是大数问题，所以要用BigInteger包装类，但是这样空间和时间效率都很低
 */
class Solution {
    public int cuttingRope(int n) {
        // 注意我这里的数组应该是包含了不拆的情况，但是题目的意思是至少要拆成两半
        // 所以针对这两种情况要用两个数组
        // 注意是n+1个存储空间，因为多存了个长度为0的，为了索引方便
        BigInteger[] ftemp = new BigInteger[n+1];
        BigInteger[] f = new BigInteger[n+1];
        // f[i]存储长度为i的结果
        // 注意这里与n=0、1、2前面的情况不一样，当n>2时，前面的值可以等于自身不剪短，因为循环本身就剪成了两段
        // 事实上这里很关键，因为有些剪成两段的乘积没有不剪短大
        // 题目说了n>1
        // 注意BigInteger实例化用的是字符串
        // 存储的是含整段剪的中间态
        ftemp[0] = new BigInteger("0");
        ftemp[1] = new BigInteger("1");
        // 注意由于从i=3及之前的每一小段一定是不剪比剪了大，i=4是临界状态不剪和剪了一样大，i=4之后一定是剪了比不剪大
        // 所以其实只要给f[0-3/4]赋好值之后，ftemp可以不要
//        ftemp[2] = new BigInteger("2");
//        ftemp[3] = new BigInteger("3");
        // 注意差别
        // 存的是最终的结果
        f[0] = new BigInteger("0");
        f[1] = new BigInteger("1");
//        f[2] = new BigInteger("1");
//        f[3] = new BigInteger("2");
        for (int i = 2; i <= n; i++) {
            // 先假设整段不剪
            // 注意int转String的方法
            ftemp[i] = new BigInteger(String.valueOf(i));
            // f必须剪
            f[i] = new BigInteger("0");
            // 在这个层面上考虑问题粒度就很简单了，要么就是剪成2半，要么整个就不剪
            for (int j = 1; j <= i / 2; j++) {
                BigInteger temp = ftemp[j].multiply(ftemp[i - j]);
                // 注意它是要求对最终的结果进行取余，所以中间就取余了的话结果其实是不对的
//                temp = temp.mod(new BigInteger("1000000007"));
                // 找剪了的最大
                // max返回f[i]和temp中最大的那个，temp.max(f[i])也是一样的
                f[i] = f[i].max(temp);
                // 找剪与不剪的最大
                ftemp[i] = ftemp[i].max(temp);
            }
        }
        return f[n].mod(new BigInteger("1000000007")).intValue();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.cuttingRope(100);
        System.out.println(result);
    }

}

/**
 * Success1-plus
 * 规律法简洁写法
 */
//class Solution {
//    public int cuttingRope(int n) {
//        if(n <= 3) return n - 1;
//        int b = n % 3, p = 1000000007;
//        long rem = 1, x = 3;
//        for(int a = n / 3 - 1; a > 0; a /= 2) {
//            if(a % 2 == 1) rem = (rem * x) % p;
//            x = (x * x) % p;
//        }
//        if(b == 0) return (int)(rem * 3 % p);
//        if(b == 1) return (int)(rem * 4 % p);
//        return (int)(rem * 6 % p);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int result = solution.cuttingRope(120);
//        System.out.println(result);
//    }
//}

/**
 * Success2
 * 动态规划思路
 * 主要就还是考虑到乘积太大，用了BigInteger这种数据类型，效率较低
 */
//class Solution {
//    public int cuttingRope(int n) {
//        if(n<2)
//            return 0;
//        if(n==2)
//            return 1;
//        if(n==3)
//            return 2;
//        // d[i]表示长度为i的绳子剪完后各段乘积的最大值, 最终目标是dp[n]
//        // dp[i]可以看成是长度为i-k的绳子的最大值和长度为k的绳子的最大值的乘积, 子问题最优, 所以dp[i]也是最优
//        // 状态转移方程: dp[i] = max(dp[i], dp[i-k]*dp[k])
//        // 下面的初始值不同于上面的特殊情况, 上面是必须剪一刀, 下面的三个初始值不用再减了
//        BigInteger[] dp = new BigInteger[n+1];
//        // 它这里的dp相当于我上面的
//        dp[1] = new BigInteger("1");//内循环中会用到这个值
//        dp[2] = new BigInteger("2");
//        dp[3] = new BigInteger("3");
//        for(int i=4; i<=n; i++){
//            // 初始化dp[i]
//            dp[i] = new BigInteger("0");
//            // 长度为i的绳子有i-1个剪切位置; 不论i是奇数还是偶数, 只考虑前i/2个剪切位置即可, 后面的剪切位置是重复的
//            for(int j=1; j<=i/2; j++){
//                // 因为j和i-j都小于i, 所以这是自底向上的计算方式
//                dp[i] = dp[i].max(dp[j].multiply(dp[i-j]));
//            }
//        }
//        return dp[n].mod(new BigInteger("1000000007")).intValue();
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int result = solution.cuttingRope(120);
//        System.out.println(result);
//    }
//}

/**
 * Success1
 * 总结规律得出贪心规律从而用贪心算法，效率最高
 * 规律就是尽可能多的分成3的长度段，当长度剩余4时，应该不分或者分成2*2，当长度剩余1/2时不分
 */
//class Solution {
//    public int cuttingRope(int n) {
//        if (n == 2) {
//            return 1;
//        }
//        if (n == 3) {
//            return 2;
//        }
//        if (n == 4) {
//            return 4;
//        }
//        // 注意这里还是要用long存取余之后再转为int才够，哪怕用了贪心算法
//        long res = 1;
//        while (n > 4) {
//            res *= 3;
//            // 不太明白这里为什么就可以先直接余一遍
//            // 但是不用显然是溢出了
//            res %= 1000000007;
//            n = n - 3;
//        }
//        // 注意这里刚好比较巧，如果是小于等于4的情况，那么那一部分一定是不剪更大，就直接乘
//        // 而且由于经历了n>4，那么其他部分一定是剪了的，那么它们可以直接保留不剪，真的是很巧又必然
//        res = res * n % 1000000007;
//        return (int)res;
//    }
//
//}

//leetcode submit region end(Prohibit modification and deletion)
