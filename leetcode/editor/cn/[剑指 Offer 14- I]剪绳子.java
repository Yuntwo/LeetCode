//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。
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
// 提示：
//
//
// 2 <= n <= 58
//
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
// Related Topics 数学 动态规划 👍 361 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 这样递归能出结果，但是Leetcode平台压栈时间会溢出
//class Solution {
//    public int cuttingRope(int n) {
//        int max = 0;
//        int temp = 0;
//        // n长的绳子最多分成n段
//        for (int i = 2; i <= n; i++) {
//            temp = cut(n, i);
//            if (temp > max) {
//                max = temp;
//            }
//        }
//        return max;
//    }
//
//    private int cut(int n, int m) {
//        if (m == 2) {
//            // 注意这里是n，不要搞错了
//            return n*n/4; // 均值不等式，这里就算不是整分取整刚好结果一致
//        }
//        // 感觉要是这样递归的话，递归的层次太多太乱了，要保留的额外存储空间太多了，而且外层还有递归
//        // 只求最大值的话不需要数组保存所有结果，直接覆盖
//        int max = 0;
//        int temp = 0;
//        // m-1段至少要m-1长的绳子
//        for (int i = 1; i <= n - m + 1; i++) {
//            temp = i * cut(n - i, m - 1);
//            if (temp > max) {
//                max = temp;
//            }
//        }
//        return max;
//    }
//}
// 循环法，与上述递归法的思路还是存在本质的区别
// 它这里的思路似乎就是分两段，在这两段上做文章
class Solution {
    public int cuttingRope(int n) {
        if (n <= 3) {
            switch (n) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 1;
                case 3:
                    return 2;
            }

        }
        int[] f = new int[n+1]; // 注意是n+1个存储空间，因为多存了个长度为0的
        // f[i]存储长度为i的结果
        // 注意这里与n=0、1、2前面的情况不一样，当n>2时，前面的值可以等于自身不剪短，因为循环本身就剪成了两段
        // 事实上这里很关键，因为有些剪成两段的乘积没有不剪短大
        f[0] = 0;
        f[1] = 1;
        f[2] = 2;
        f[3] = 3;
        for (int i = 4; i <= n; i++) {
            int max = i;
            int temp = 0;
            for (int j = 1; j <= i / 2; j++) {
                temp = f[j] * f[i - j];
                if (temp > max) {
                    max = temp;
                }
            }
            f[i] = max;
        }
        return f[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
