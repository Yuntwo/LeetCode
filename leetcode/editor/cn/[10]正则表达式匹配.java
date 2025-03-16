//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
// 示例 1：
//
//
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3：
//
//
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 20
// 1 <= p.length <= 20
// s 只包含从 a-z 的小写字母。
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
//
// Related Topics 递归 字符串 动态规划 👍 3918 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Success1
 */
class Solution {
    public boolean isMatch(String s, String p) {
        // dp[i][j]表示p的第1到i字符串能否匹配s的第1到j字符串(实际索引i和j都要-1)
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        // 初始化，都是p去匹配s为0的情况
        // 1.对于s来说去匹配p只能是这种情况
        dp[0][0] = true;
        // 2.处理p去匹配s的冗余0处字符情况，s去匹配p为0的情况除了上面那个都是false，否则会漏掉这个初始条件
        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j][0] = dp[j - 2][0];
            }
        }
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (p.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    if (i >= 2) {
                        // 匹配前面0个字符
                        dp[i][j] |= dp[i - 2][j];
                        // 匹配前面1、2个或多个字符
                        // 注意这里直接用dp[i][j - 1]就够了，因为dp[i][j - 1]本身就包含了匹配1、2个或多个字符的意思，dp[i][j]只要在满足条件的前提下照抄它就行了
                        if (s.charAt(j - 1) == p.charAt(i - 2) || p.charAt(i - 2) == '.') {
                            dp[i][j] |= dp[i][j - 1];
                        }
                    }
                } else { // 如果是普通的字符只有一种比较途径
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        return dp[p.length()][s.length()];

    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        Solution solution = new Solution();
        System.out.println(solution.isMatch(s, p));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
