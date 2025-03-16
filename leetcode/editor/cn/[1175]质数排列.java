//请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
//
// 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
//
// 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
//
//
//
// 示例 1：
//
// 输入：n = 5
//输出：12
//解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
//
//
// 示例 2：
//
// 输入：n = 100
//输出：682289015
//
//
//
//
// 提示：
//
//
// 1 <= n <= 100
//
// Related Topics 数学 👍 41 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 注意可以这样全局定义一下
    private int MOD = (int) 1e9 + 7;

    public int numPrimeArrangements(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        // 从3开始找素数
        // 注意1不是素数
        int numPrime = findPrimeNumber(3, n) + 1;
        long res = (fac(numPrime) * fac(n - numPrime)) % MOD;
        // 注意返回结果要进行一下类型转换
        return (int) res;
    }

    public int findPrimeNumber (int m, int n) {
        int count = 0;
        for (int i = m; i <= n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 也可以不封装这个函数
    private long fac(int num) {
        // 注意一定要定义为long型，不能定义为int型，否则阶乘结果过大容易溢出
        long sum = 1;
        // 一般这样控制为小于方便些
        for (int i = 1; i <= num; i++) {
            sum *= i;
            sum %= MOD;
        }
        return sum;
    }
}

//class Solution {
//    public int numPrimeArrangements(int n) {
//        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
//        int cnt = 0;
//        for (int prime : primes) if (n >= prime) cnt++;
//        long ans = 1;
//        final int MOD = (int) 1e9 + 7;
//        for (int i = 1; i <= cnt; i++) ans = ans * i % MOD;
//        for (int i = 1; i <= n - cnt; i++) ans = ans * i % MOD;
//        return (int) ans % MOD;
//    }
//
//    public static void main(String[] args) {
//        test myTest = new test();
//        int result = myTest.numPrimeArrangements(100);
//        System.out.println(result);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
