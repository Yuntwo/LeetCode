//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
//
// 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
//
//
//
// 示例 1：
//
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
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
//
//
//
// 注意：本题与主站 567 题相同： https://leetcode-cn.com/problems/permutation-in-string/
//
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 104 👎 0



//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 只是用数组，整体逻辑没有变，没有更快
 * 滑动窗口大部分时候都挺麻烦的，要初始化窗口然后判断条件，有局限性，很多时候不能更快节省判断
 */
class Solution {

    public boolean checkInclusion(String s1, String s2) {
        // 注意处理这种情况
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            // 注意这里对s1采用--逻辑，希望s2能补全它
            count[s1.charAt(i) - 'a']--;
            count[s2.charAt(i) - 'a']++;
        }
        // 注意这里检查第一个窗口是否匹配
        if (isAll0(count)) {
            return true;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            count[s2.charAt(i - s1.length()) - 'a']--;
            count[s2.charAt(i) - 'a']++;
            if (isAll0(count)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAll0(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion("a", "ab"));
    }

}

/**
 * Success1
 * 效率低
 */
//class Solution {
//    public boolean checkInclusion(String s1, String s2) {
//        if (s2.length() < s1.length()) {
//            return false;
//        }
//        int start = 0;
//        int end = s1.length() - 1;
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < s1.length(); i++) {
//            hashMap.put(s1.charAt(i), hashMap.getOrDefault(s1.charAt(i), 0) + 1);
//        }
//        for (int i = start; i <= end; i++) {
//            if (hashMap.containsKey(s2.charAt(i))) {
//                hashMap.put(s2.charAt(i), hashMap.get(s2.charAt(i)) - 1);
//            }
//        }
//        // 注意这里要判断一下
//        if (match(hashMap)) {
//            return true;
//        }
//        while (end < s2.length() - 1) {
//            if (hashMap.containsKey(s2.charAt(start))) {
//                hashMap.put(s2.charAt(start), hashMap.get(s2.charAt(start)) + 1);
//            }
//            start++;
//            end++;
//            if (hashMap.containsKey(s2.charAt(end))) {
//                hashMap.put(s2.charAt(end), hashMap.get(s2.charAt(end)) - 1);
//            }
//            if (match(hashMap)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean match(HashMap<Character, Integer> hashMap) {
//        for (int value : hashMap.values()) {
//            if (value != 0) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
