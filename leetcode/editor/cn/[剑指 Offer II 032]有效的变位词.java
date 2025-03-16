//给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
//
// 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
//
//
//
// 示例 1:
//
//
//输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
//
//输入: s = "rat", t = "car"
//输出: false
//
// 示例 3:
//
//
//输入: s = "a", t = "a"
//输出: false
//
//
//
// 提示:
//
//
// 1 <= s.length, t.length <= 5 * 10⁴
// s and t 仅包含小写字母
//
//
//
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
//
//
//
//
// 注意：本题与主站 242 题相似（字母异位词定义不同）：https://leetcode-cn.com/problems/valid-anagram/
//
// Related Topics 哈希表 字符串 排序 👍 41 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 比较直白
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        int[] char1 = new int[26];
        int[] char2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char1[s.charAt(i) - 'a']++;
            char2[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (char1[i] != char2[i]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
