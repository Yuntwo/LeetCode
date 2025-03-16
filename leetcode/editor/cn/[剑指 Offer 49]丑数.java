//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
//
//
// 示例:
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//
// 说明:
//
//
// 1 是丑数。
// n 不超过1690。
//
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 391 👎 0



import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
//leetcode submit region begin(Prohibit modification and deletion)
// 用BigInteger防止数据溢出，达成目标，但是时间和空间效率都很低
//import java.math.BigInteger;
//class Solution {
//    public int nthUglyNumber(int n) {
//        HashSet<BigInteger> set = new HashSet<BigInteger>();
//        BigInteger[] num = new BigInteger[n];
//        num[0] = new BigInteger("1");
//        // 如果用int/Integer型的话从n=1365数据就溢出了
//        // 注意num也要用BigInteger型
////        int[] num = new int[n];
////        num[0] = 1;
//        for (int i = 1; i < n; i++) {
////            set.add(new BigInteger(String.valueOf(2 * num[i - 1])));
////            set.add(new BigInteger(String.valueOf(3 * num[i - 1])));
////            set.add(new BigInteger(String.valueOf(5 * num[i - 1])));
//            set.add(num[i - 1].multiply(new BigInteger("2")));
//            set.add(num[i - 1].multiply(new BigInteger("3")));
//            set.add(num[i - 1].multiply(new BigInteger("5")));
//            BigInteger temp = Collections.min(set);
//            num[i] = temp;
////            num[i] = temp.intValue();
//            set.remove(temp);
//        }
////        return num[n - 1];
//        return num[n - 1].intValue();
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.nthUglyNumber(1365);
//        System.out.println(res);
//    }
//}
// 用精简之后的递推规律
class Solution {
    public int nthUglyNumber(int n) {
        int[] num = new int[n];
        num[0] = 1;
        int a = 0;
        int b = 0;
        int c = 0;
        // 注意可以这样简化写
//        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            num[i] = Math.min(2 * num[a], Math.min(3 * num[b], 5 * num[c]));
            if (num[i] == 2 * num[a]) {
                a++;
            }
            if (num[i] == 3 * num[b]) {
                b++;
            }
            if (num[i] == 5 * num[c]) {
                c++;
            }
        }
        return num[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
