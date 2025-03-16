//请设计一个程序来支持用户在文本编辑器中的模糊搜索功能。用户输入内容中可能使用到如下两种通配符：
//
//
// '.' 匹配任意单个字符。
// '*' 匹配零个或多个前面的那一个元素。
//
//
//
//
// 请返回用户输入内容 input 所有字符是否可以匹配原文字符串 article。
//
//
//
// 示例 1:
//
//
//输入: article = "aa", input = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入: article = "aa", input = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3:
//
//
//输入: article = "ab", input = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
//
//
// 提示：
//
//
// 1 <= article.length <= 20
// 1 <= input.length <= 20
// article 只包含从 a-z 的小写字母。
// input 只包含从 a-z 的小写字母，以及字符 . 和 * 。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
//
//
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
//
//
//
//
// Related Topics 递归 字符串 动态规划 👍 570 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1-Review2
 */
class Solution {
    public boolean articleMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < s.length() + 1; j++) {
            for (int i = 1; i < p.length() + 1; i++) {
                if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '.')) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 2][j];
                    if (j > 0 && (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1))) {
                        dp[i][j] |= dp[i][j - 1];
                    }
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String s = "aab";
//        String p = "c*a*b";
//        String s = "aaa";
//        String p = ".*";
//        String s = "a";
//        String p = "ab*";
//        String s = "ab";
//        String p = ".*..";
//        String s = "";
//        String p = ".*";
//        String s = "aasdfasdfasdfasdfas";
//        String p = "aasdf.*asdf.*asdf.*asdf.*s";
        String s = "mississippi";
        String p = "mis*is*p*.";
        System.out.println(solution.articleMatch(s, p));
    }
}

/**
 * Success1-Review1
 */
//class Solution {
//    public boolean articleMatch(String s, String p) {
//        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
//        dp[0][0] = true;
//        for (int j = 0; j <= s.length(); j++) {
//            for (int i = 1; i <= p.length(); i++) {
//                if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '.')) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                }
//                // 注意题目限定也只有i>1的才会出现'*'，所以这里这个边界控制可要可不要
//                if (i > 1 && p.charAt(i - 1) == '*') {
//                    // 注意这个条件是不论本身p.charAt(i-1-1)是否与s.charAt(j-1)匹配，都可以不用来看匹配情况
//                    dp[i][j] = dp[i - 2][j];
//                    // 这是用上了来看匹配情况
//                    if (j > 0 && (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1))) {
//                        // 当然我们每次对于s中i位置的匹配都是基于i-1位置的，但是用多少个p的j去匹配可变。由于我们是
//                        // 尽可能地匹配成功，所以用dp[i][j-1]涵盖的情况是比dp[i-2][j-1]多的，
//                        // 而且由于上面第一个赋值，所以[i][j-1]实际上已经包括了dp[i-2][j-1]的可能性，dp[i-2][j-1]是不完备的
//                        dp[i][j] |= dp[i][j - 1];
//                    }
//                }
//            }
//        }
//        return dp[p.length()][s.length()];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        String s = "aab";
////        String p = "c*a*b";
////        String s = "aaa";
////        String p = ".*";
////        String s = "a";
////        String p = "ab*";
////        String s = "ab";
////        String p = ".*..";
////        String s = "";
////        String p = ".*";
////        String s = "aasdfasdfasdfasdfas";
////        String p = "aasdf.*asdf.*asdf.*asdf.*s";
//        String s = "mississippi";
//        String p = "mis*is*p*.";
//        System.out.println(solution.articleMatch(s, p));
//    }
//}

/**
 * Success1
 */
//class Solution {
//    public boolean articleMatch(String s, String p) {
//        // s为空字符串的时候也要留0处的位置
//        boolean[][] canMatch = new boolean[p.length() + 1][s.length() + 1];
//        canMatch[0][0] = true;
//        // 注意这里是每一个p的char去匹配s的每一个字符，p遍历内循环
//        // 注意这里对于s的第0个字符也要去匹配，为后面做准备
//        for (int j = 0; j <= s.length(); j++) {
//            for (int i = 1; i <= p.length(); i++) {
//                // 注意这里的字符串的索引比实际含义-1，注意这里控制j为0的情况以防止越界访问
//                if (j > 0 && (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1))) {
//                    canMatch[i][j] = canMatch[i - 1][j - 1];
//                }
//                if (p.charAt(i - 1) == '*' && i > 1) {
//                    // 上层没用的情况
//                    canMatch[i][j] = canMatch[i - 2][j];
//                    // 上层第一次用、用过一次、第多次用当前这个字符的情况
//                    if (j > 0 && (p.charAt(i - 2) == s.charAt(j - 1) || p.charAt(i - 2) == '.')) {
////                        canMatch[i][j] |= (canMatch[i - 2][j - 1] || canMatch[i - 1][j - 1] || canMatch[i][j - 1]);
//                        // 其实这一个就能涵盖所有情况了，就是说之前看前一个字符能否匹配就可以
//                        canMatch[i][j] |= canMatch[i][j - 1];
//                    }
//                }
//            }
//        }
//        return canMatch[p.length()][s.length()];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        String s = "aab";
////        String p = "c*a*b";
////        String s = "aaa";
////        String p = ".*";
////        String s = "a";
////        String p = "ab*";
////        String s = "ab";
////        String p = ".*..";
////        String s = "";
////        String p = ".*";
////        String s = "aasdfasdfasdfasdfas";
////        String p = "aasdf.*asdf.*asdf.*asdf.*s";
//        String s = "mississippi";
//        String p = "mis*is*p*.";
//        System.out.println(solution.articleMatch(s, p));
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
