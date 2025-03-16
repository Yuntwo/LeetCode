//给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为
// k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k
//为 0 。
//
// 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
//
//
//
// 示例 1：
//
//
//输入：sequence = "ababc", word = "ab"
//输出：2
//解释："abab" 是 "ababc" 的子字符串。
//
//
// 示例 2：
//
//
//输入：sequence = "ababc", word = "ba"
//输出：1
//解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
//
//
// 示例 3：
//
//
//输入：sequence = "ababc", word = "ac"
//输出：0
//解释："ac" 不是 "ababc" 的子字符串。
//
//
//
//
// 提示：
//
//
// 1 <= sequence.length <= 100
// 1 <= word.length <= 100
// sequence 和 word 都只包含小写英文字母。
//
//
// Related Topics 字符串 字符串匹配 👍 67 👎 0




//leetcode submit region begin(Prohibit modification and deletion)
// 注意题目说的是[连续]k次重复
// 写得不对，复杂
//class Solution {
//    public int maxRepeating(String sequence, String word) {
//        // 注意初始化
//        int i = 0;
//        // 注意初始化
//        int res = 0;
//        while (i < sequence.length()) {
//            if (sequence.charAt(i) != word.charAt(0)) {
//                i++;
//            } else {
//                i++;
//                // 注意用相对偏移更方便索引word
//                int j = 1;
//                while (j < word.length()) {
//                    if (sequence.charAt(i) == word.charAt(j)) {
//                        i++;
//                        j++;
//                    } else {
//                        break;
//                    }
//                }
//                if (j == word.length()) {
//                    res++;
//                } else {
//                    // 注意如果不匹配要倒回去匹配，而不是接着后面匹配
//                    // 而且你有可能不只是倒回到原位置的下一位置，而且还有可能是前面的位置
//                    // 而且倒回到前面才不会有遗漏，而且res要置零
//                    i = i - j - 1;
//                    // 但是如果就是不匹配的这种情况就必须向后寻找，否则就一直陷入循环了
//                    res = 0;
//                }
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.maxRepeating("ababc", "ba");
//        System.out.println(res);
//    }
//}
import java.util.Arrays;
//class Solution {
//    public int maxRepeating(String sequence, String word) {
//        int n = sequence.length();
//        int m = word.length();
//        if (n < m) {
//            return 0;
//        }
//
//        int[] f = new int[n];
//        // 注意i一定是从m-1开始的
//        for (int i = m - 1; i < n; ++i) {
//            boolean valid = true;
//            // 注意j是word的相对偏移
//            for (int j = 0; j < m; ++j) {
//                if (sequence.charAt(i - m + 1 + j) != word.charAt(j)) {
//                    valid = false;
//                    break;
//                }
//            }
//            if (valid) {
//                f[i] = (i == m - 1 ? 0 : f[i - m]) + 1;
//            }
//        }
//        // 注意这个获取最大值的写法
//        return Arrays.stream(f).max().getAsInt();
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.maxRepeating("ababc", "ba");
//        System.out.println(res);
//    }
//}
// f数组还是一定要的，因为这个i的f[i]和f[i-m]下一个i的f[i]和f[i-m]之间没有依赖关系
class Solution {
    public int maxRepeating(String sequence, String word) {
        int n = sequence.length();
        int m = word.length();
        if (n < m) {
            return 0;
        }
        // 直接比较一下大小而不用util的方法更节省空间时间
        int res = 0;
        int[] f = new int[n];
        // 注意i一定是从m-1开始的
        for (int i = m - 1; i < n; ++i) {
            boolean valid = true;
            // 注意j是word的相对偏移
            for (int j = 0; j < m; ++j) {
                if (sequence.charAt(i - m + 1 + j) != word.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                f[i] = (i == m - 1 ? 0 : f[i - m]) + 1;
                if (f[i] > res) {
                    res = f[i];
                }
            }
        }
        // 注意这个获取最大值的写法
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.maxRepeating("ababc", "ba");
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
