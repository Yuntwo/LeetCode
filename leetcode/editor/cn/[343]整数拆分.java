//给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
//
// 返回 你可以获得的最大乘积 。
//
//
//
// 示例 1:
//
//
//输入: n = 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。
//
// 示例 2:
//
//
//输入: n = 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
//
//
//
// 提示:
//
//
// 2 <= n <= 58
//
// Related Topics 数学 动态规划 👍 794 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 自顶向下记忆化搜索
//class Solution {
//    public int integerBreak(int n) {
//        // 注意这里多占一个存储空间方便理解叙述
//        int[] num = new int[n + 1];
//        for (int i = 0; i < n + 1; i++) {
//            num[i] = -1;
//        }
//        return intBreak(n, num);
//    }
//
//    private int intBreak(int n, int[] num) {
//        if (n == 2) {
//            num[n] = 1;
//            return 1;
//        }
//        if (num[n] != -1) {
//            return num[n];
//        }
//        int[] temp = new int[n / 2 + 1];
//        int max = 0;
//        for (int i = 1; i <= n / 2; i++) {
//            int temp1 = i * intBreak(n - i, num);
//            // 注意还有不分这种情况
//            int temp2 = i * (n - i);
//            if (temp1 > temp2) {
//                temp[i] = temp1;
//            }else{
//                temp[i] = temp2;
//            }
//            if (temp[i] > max) {
//                max = temp[i];
//            }
//        }
//        num[n] = max;
//        return num[n];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.integerBreak(10);
//        System.out.println(res);
//    }
//}
// 自底向上动态规划
class Solution {
    public int integerBreak(int n) {
        // 注意这里多占一个存储空间方便理解叙述
        int[] num = new int[n + 1];
        num[2] = 1;
        // 两层循环就是容易把变量关系搞错
        for (int i = 3; i <= n; i++) {
            int max = 0;
            int[] temp = new int[i / 2 + 1];
            for (int j = 1; j <= i / 2; j++) {
                // 注意这里是个容易忽略的地方
                int temp1 = j * num[i - j];
                int temp2 = j * (i - j);
                if (temp1 > temp2) {
                    temp[j] = temp1;
                } else {
                    temp[j] = temp2;
                }
                if (temp[j] > max) {
                    max = temp[j];
                }
            }
            num[i] = max;
        }
        return num[n];
    }

    public static void main(String[] args) {
        TestSolution solution = new TestSolution();
        int res = solution.integerBreak(10);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
