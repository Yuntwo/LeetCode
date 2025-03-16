//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
//
// 示例 1:
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3:
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
// 示例 4:
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//
//
// 示例 5:
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false
//
//
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
//
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
//
//
// Related Topics 递归 字符串 动态规划 👍 488 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * BEGIN
 */


/**
 * Review1
 * 这个题的难点在于状态转移不太好理解
 */
class Solution {

    public boolean isMatch(String s, String p) {
        // dp[i][j]表示s的第1-i子字符串和p的第1-j子字符串是否匹配
        // 这里多用空间方便处理空字符串和的情况
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 空字符串能够匹配
        dp[0][0] = true;
        // p串为空s串非空的索引0列都是默认的false
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (i > 0 && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.')) {
                    dp[i][j] = dp[i - 1][j - 1];
                    // 注意这个分支逻辑比较复杂，要分情况讨论
                } else if (p.charAt(j - 1) == '*') {
                    // 这是第i和第j-1可以匹配，*可以当做0个字符处理的情况，只需要看i和j-1前的所有字符串是否匹配
                    if (i > 0 && j > 1 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                        // 这个逻辑不太好理解，因为j-1和j可以重复多次，所以直接i-1与j匹配
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];

                    }
                    // 这是把*以及前面的1个字符当做0个字符处理的情况，注意这里要控制访问越界
                    if (j > 1) {
                        // 注意这里是|=，是对前面一种情况的补充
                        dp[i][j] |= dp[i][j - 2];
                    }
                } else {
                    dp[i][j] = false;
                }

            }
        }
        return dp[s.length()][p.length()];
    }

}

/**
 * Success1
 * 这样是二维dp，应该还可以压缩为一维dp
 * 但是由于这个二维dp在实际迭代过程中既用了当前状态的更新值又用了前一状态的值，而且实际引用的值其实比较错综，所以不太方便压缩
 */
//class Solution {
//    public boolean isMatch(String s, String p) {
//        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
//        // 注意对于动态规划来说初始值很重要，否则后面逻辑再精妙也无法进行
//        /* 1-空字符串和空模式串必定匹配 */
//        dp[0][0] = true;
//        /* 2-非空字符串和空模式串必定不匹配 */
//        for (int i = 1; i <= s.length(); i++) {
//            dp[i][0] = false;
//        }
//        /* 3/4-空字符串和非空模式串以及非空字符串和非空模式串都要正常计算 */
//        // 这里实际上的含义是字符串长度，注意字符串在索引的差别
//        // 对于dp数组不需要进行索引的减1操作，但是对于字符串需要进行索引的减1操作
//        // 但是注意对于i的索引下越界控制
//        for (int i = 0; i <= s.length(); i++) {
//            for (int j = 1; j <= p.length(); j++) {
//                // 为'*'划为一种讨论情况
//                if (p.charAt(j - 1) == '*') {
//                    // 看'*'的两种情况，必须有这两种情况中的一种才能看
//                    // 注意这里前面的下越界控制
//                    if (i > 0 && j > 1 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
//                        dp[i][j] = dp[i - 1][j];
//                    }
//                    // 不看'*'的情况，不需要任何条件
//                    // 两种情况是或的关系，有一个为true就能成，直接这样或等于操作很方便
//                    // 注意这里也需要下越界控制
//                    if (j > 1) {
//                        dp[i][j] |= dp[i][j - 2];
//                    }
//                // 不为'*'合并划为一种讨论情况
//                } else {
//                    // 注意这里前面的下越界控制
//                    if (i > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
//                        dp[i][j] = dp[i - 1][j - 1];
//                        // 注意这里这个分支不影响'.'的结果，一定是纯字母不等的情况才一定会到这里不匹配，虽然这样写到了一起
//                    } else {
//                        dp[i][j] = false;
//                    }
//                }
//            }
//        }
//        return dp[s.length()][p.length()];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = "aa";
//        String p = "a";
//        boolean res = solution.isMatch(s, p);
//        System.out.println(res);
//
//    }
//}

/**
 * Failure1
 * 自顶向下递归解法
 * 注意用递归解法的越界控制有所不同，因为要用越界来控制递归返回，但是感觉这并不是一种常规模板化思路
 * 不行，感觉这样逻辑不是很通畅
 */
//class Solution {
//    public boolean isMatch(String s, String p) {
//        this.s = s;
//        this.p = p;
//        // 一、空s和空p可以匹配
//        if (s.equals("") && p.equals("")) {
//            return true;
//        // 二、非空s和空p必不匹配
//        } else if (!s.equals("") && p.equals("")) {
//            return false;
//        // 三、其它情况都需要进行递归判断
//        } else {
//            return regIsMatch(s.length() - 1, p.length() - 1);
//        }
//        // 不需要默认返回，因为unreachable
//    }
//
//    private String s;
//    private String p;
//
//
//
//    // 注意这个递归函数的内涵判断s到i的位置和p到j的位置是否匹配
//    // 递归函数注意越界控制
//    private boolean regIsMatch(int i, int j) {
//        /* 这是不需要再递归底层返回的情况 */
//        // 当然在其他递归层次中也有底层返回的情况
//        if (i == 0 && j == 0) {
//            // 只有这两种情况才能返回true
//            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        // 注意这种递归返回情况要单独讨论，它不可能再往下走了
//        if (i == 0 && j == 1) {
//            if (p.charAt(j) == '*' && (s.charAt(0) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
//                return true;
//            }
//        }
//        if (i < 0 || j < 0) {
//            return false;
//        }
//        /* 下面都是需要递归返回的情况，要递归下去的话就要有越界控制，否则可以直接到最底层返回的情况了 */
//        // *的两种情况
//        if (p.charAt(j) == '*') {
//            // 注意其他不满足这两种可能正确情况的都默认为false
//            boolean res = false;
//            // 不看*，注意是跳过了p的节点，但是s的节点没有进度，这种情况有越界限制
//            if (j >= 2) {
//                res |= regIsMatch(i, j - 2);
//            }
//            // 看*，注意p的节点没有进度(因为还可以复用)，但是s的节点有进度，这种情况同样有越界限制
//                // 注意这两种越界的判断层次不一样，要分开写
//            if (j >= 1 && (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
//                res |= regIsMatch(i - 1, j);
//            }
//            return res;
//        // 非*的两种情况
//        } else {
//            // 为'.'的情况
//            if (p.charAt(j) == '.') {
//                return regIsMatch(i - 1, j - 1);
//                // 为正常字母的情况
//            } else {
//                if (s.charAt(i) != p.charAt(j)) {
//                    return false;
//                    // 注意这里要递归下去寻找可能的正确情况也需要越界控制
//                } else if (i >= 1 && j >= 1) {
//                    return regIsMatch(i - 1, j - 1);
//                }
//            }
//        }
//        /* 默认返回false */
//        return false;
//    }
//
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        String s = "aa";
////        String p = "a*";
//        String s = "ab";
//        String p = ".*";
////        String s = "a";
////        String p = ".*..a*";
//        boolean res = solution.isMatch(s, p);
//        System.out.println(res);
//
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
