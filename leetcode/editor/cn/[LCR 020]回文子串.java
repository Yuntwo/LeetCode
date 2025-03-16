//给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//
//
//
// 示例 1：
//
//
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
//
//
// 示例 2：
//
//
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 由小写英文字母组成
//
//
//
//
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/palindromic-substrings/
//
// Related Topics 字符串 动态规划 👍 108 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success4
 * Manacher 算法，不常规
 */
//class Solution {
//    public int countSubstrings(String s) {
//        int n = s.length();
//        StringBuffer t = new StringBuffer("$#");
//        for (int i = 0; i < n; ++i) {
//            t.append(s.charAt(i));
//            t.append('#');
//        }
//        n = t.length();
//        t.append('!');
//
//        int[] f = new int[n];
//        int iMax = 0, rMax = 0, ans = 0;
//        for (int i = 1; i < n; ++i) {
//            // 初始化 f[i]
//            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
//            // 中心拓展
//            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
//                ++f[i];
//            }
//            // 动态维护 iMax 和 rMax
//            if (i + f[i] - 1 > rMax) {
//                iMax = i;
//                rMax = i + f[i] - 1;
//            }
//            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
//            ans += f[i] / 2;
//        }
//
//        return ans;
//    }
//}

/**
 * Success3
 * 动态规划，迭代要采用中心拓展的形式，效率有所提高
 * 但是一层循环还是免不了的
 */
//class Solution {
//
//    public int countSubstrings(String s) {
//        boolean[][] isPalindromic = new boolean[s.length()][s.length()];
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            // 注意要分奇数串和偶数串的情况
//            for (int j = 0; j < 2; j++) {
//                int start = i;
//                int end = i + j;
//                // 最后一种偶数对超出索引了
//                if (end > s.length() - 1) {
//                    break;
//                }
//                isPalindromic[start][end] = s.charAt(start) == s.charAt(end);
//                // 从中心向外扩展
//                while (start > 0 && end < s.length() - 1) {
//                    // 如果里面的不是回文串，向外扩展一定不相等，没有必要循环下去了
//                    if (!isPalindromic[start][end]) {
//                        break;
//                    }
//                    res++;
//                    isPalindromic[start - 1][end + 1] = (s.charAt(start - 1) == s.charAt(end + 1) && isPalindromic[start][end]);
//                    start--;
//                    end++;
//                }
//                // 跳出循环时的边界情况还没有判断
//                if (isPalindromic[start][end]) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }
//
//}

/**
 * Success2
 * 尝试使用动态规划，但是应该为假的动态规划
 * 动态规划应该直接迭代+记忆化数组就能做，关键是这个迭代一定是很有规律的保证它引用的上一步结果一定在上一次迭代中处理了
 * 所以你设计的这个迭代顺序很重要
 */
//class Solution {
//    public int countSubstrings(String s) {
//        isPalindromic = new boolean[s.length()][s.length()];
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i; j < s.length(); j++) {
//                if (isPalindromic(s, i, j)) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }
//
//    private boolean[][] isPalindromic;
//    private boolean isPalindromic(String s, int start, int end) {
//        // 对角线上的本身都是true
//        if (start == end) {
//            isPalindromic[start][end] = true;
//            return isPalindromic[start][end];
//        }
//        if ((start + 1) == end) {
//            isPalindromic[start][end] = (s.charAt(start) == s.charAt(end));
//            return isPalindromic[start][end];
//        }
//        isPalindromic[start][end] = (s.charAt(start) == s.charAt(end) && isPalindromic(s, start + 1, end - 1));
//        return isPalindromic[start][end];
//    }
//
//}

/**
 * Success1
 * 暴力搜索，效率很低
 */
//class Solution {
//    public int countSubstrings(String s) {
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i; j < s.length(); j++) {
//                if (isPalindromic(s, i, j)) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }
//
//    private boolean isPalindromic(String s, int start, int end) {
//        while (start < end) {
//            if (s.charAt(start) != s.charAt(end)) {
//                return false;
//            }
//            start++;
//            end--;
//        }
//        return true;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
