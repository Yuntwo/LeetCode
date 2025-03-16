//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
//
// 换句话说，s1 的排列之一是 s2 的 子串 。
//
//
//
// 示例 1：
//
//
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 10⁴
// s1 和 s2 仅包含小写字母
//
//
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 967 👎 0


import java.util.ArrayList;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 空间和时间效率不是很高
 */
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // 注意这种情况
        if (s2.length() < s1.length()) {
            return false;
        }
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            window.put(s1.charAt(i), window.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int start = 0;
        int end = s1.length() - 1;
        for (int i = start; i <= end; i++) {
            if (window.containsKey(s2.charAt(i))) {
                window.put(s2.charAt(i), window.get(s2.charAt(i)) - 1);
            }
        }
        if (match(window)) {
            return true;
        }
        while (end < s2.length() - 1) {
            if (window.containsKey(s2.charAt(start))) {
                window.put(s2.charAt(start), window.get(s2.charAt(start)) + 1);
            }
            // 注意滑动窗口的start只去除，不需要重新加，因为之前已经加过了
            start++;
            end++;
            if (window.containsKey(s2.charAt(end))) {
                window.put(s2.charAt(end), window.get(s2.charAt(end)) - 1);
            }
            if (match(window)) {
                return true;
            }
        }
        return false;
    }

    private boolean match(HashMap<Character, Integer> window) {
        for (Integer value : window.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "ab";
        String s2 = "eidbaooo";
//        String s2 = "eidboaoo";
        boolean res = solution.checkInclusion(s1, s2);
        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
