//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
//
//
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// s.length <= 40000
//
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-
//repeating-characters/
//
// Related Topics 哈希表 字符串 滑动窗口 👍 506 👎 0


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 注意它是从上一个不重复的位置重新开始算起
        int max = 0;
        int len = 0;
        int start = 0;
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                len++;
                set.add(s.charAt(i));
                // 注意这里也要用len去修改max
                // 注意其实只有在不重复一直增长时判断最大就够了
                if (len > max) {
                    max = len;
                }
            } else {
                // 找到与i重复的索引
                while (s.charAt(start) != s.charAt(i)) {
                    set.remove(s.charAt(start));
                    start++;
                }
                len = i - start;
                start++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.lengthOfLongestSubstring(" ");
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
