//给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
//
// 如果 s 中存在多个符合条件的子字符串，返回任意一个。
//
//
//
// 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3：
//
//
//输入：s = "a", t = "aa"
//输出：""
//解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 10⁵
// s 和 t 由英文字母组成
//
//
//
//
// 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
//
//
//
//
// 注意：本题与主站 76 题相似（本题答案不唯一）：https://leetcode-cn.com/problems/minimum-window-
//substring/
//
// Related Topics 哈希表 字符串 滑动窗口 👍 101 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;

/**
 * Success2
 * 用HashMap逻辑很清楚，虽然效率低了
 * 注意这题的特殊之处在于t子串是可能含有重复字符的，所以单纯统计不同的字符个数diff，这不能排除多余重复字符抵消的情况
 */
//class Solution {
//    public String minWindow(String s, String t) {
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < t.length(); i++) {
//            hashMap.put(t.charAt(i), hashMap.getOrDefault(t.charAt(i), 0) - 1);
//        }
//        int start = 0;
//        int end = 0;
//        int startRes = 0;
//        int endRes = s.length();
//        while (end < s.length()) {
//            if (hashMap.containsKey(s.charAt(end))) {
//                hashMap.put(s.charAt(end), hashMap.get(s.charAt(end)) + 1);
//            }
//            while (start <= end && (!hashMap.containsKey(s.charAt(start)) || hashMap.get(s.charAt(start)) > 0)) {
//                // 这是第二种情况，为了避免空访问，所有采用这种形式
//                if (hashMap.containsKey(s.charAt(start))) {
//                    hashMap.put(s.charAt(start), hashMap.get(s.charAt(start)) - 1);
//                }
//                start++;
//            }
//            boolean success = true;
//            for (int count : hashMap.values()) {
//                if (count < 0) {
//                    success = false;
//                    break;
//                }
//            }
//            if (success) {
//                if ((end - start) < (endRes - startRes)) {
//                    endRes = end;
//                    startRes = start;
//                }
//                hashMap.put(s.charAt(start), hashMap.get(s.charAt(start)) - 1);
//                start++;
//            }
//            end++;
//        }
//        return endRes == s.length() ? "" : s.substring(startRes, endRes + 1);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = "ADOBECODEBANC";
//        String t = "ABC";
//        String res = solution.minWindow(s, t);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 不知道这种滑动窗口算不算O(n)，但是模板应该就是这样的，两个while循环是必须的吧
 * 用数组，有点绕，不容易调对
 * 注意理解题意，也就是s的子串中可以包含t中不存在的字符，但是必须包含全部t中的字符
 * 那么答案一定是在s的子串中尽量含有少的t中不存在的字符的冗余
 */
class Solution {
    public String minWindow(String s, String t) {
        // 注意大写字母和小写字母的编码中间插了其它符号，大写字母在前面
        int[] count = new int[58];
        // 将t之外的字符个数赋予特殊的值方便区别处理
        for (int i = 0; i < count.length; i++) {
            count[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'A'] = 0;
        }
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'A']--;
        }
        int startRes = 0;
        // 故意设置得特殊一点方便区别没有找到的情况
        int endRes = s.length();
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            // 这个字符目标值
            if (count[s.charAt(end) - 'A'] != Integer.MAX_VALUE) {
                count[s.charAt(end) - 'A']++;
            }
            // 这都是冗余的字符，不要它们以减少长度
            // 分两种情况，一种是t中不存在的，一种是t中存在但是数量多了
            // 注意这样在滑动窗口内的第一个位置一定是t字符串中的字符，否则一定会把它移了以减少不存在于t中的冗余
            // 注意这个字符的个数不一定是<=0的，因为它可能是处在中间一直累加从而处理不到，但是后续移除冗余后才到了start才能处理它的冗余
            // 所以不能单纯地认为它的个数保持在0而不对其实际个数做任何处理
            // 注意控制start的边界
            while (start <= end && count[s.charAt(start) - 'A'] > 0) {
                if (count[s.charAt(start) - 'A'] != Integer.MAX_VALUE) {
                    count[s.charAt(start) - 'A']--;
                }
                start++;
            }
            boolean success = true;
            for (int i = 0; i < count.length; i++) {
                if (count[i] < 0) {
                    success = false;
                    break;
                }
            }
            // 找到一个子串
            if (success) {
                // 更新结果
                if ((end - start + 1) < (endRes - startRes + 1)) {
                    startRes = start;
                    endRes = end;
                }
                // 强行剔除start使滑动窗口不满足条件从而向后继续寻找窗口
                count[s.charAt(start) - 'A']--;
                start++;
            }
            // 不要忘了，而且把它放在赋值之后
            end++;
        }
        // 注意subString是左闭右开
        return endRes == s.length() ? "" : s.substring(startRes, endRes + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String s = "ADOBECODEBANC";
//        String t = "ABC";
        String s = "cgklivwehljxrdzpfdqsapogwvjtvbzahjnsejwnuhmomlfsrvmrnczjzjevkdvroiluthhpqtffhlzyglrvorgnalk";
        String t = "mqfff";
        String res = solution.minWindow(s, t);
        System.out.println(res);
    }
}

/**
 * End
 */
//leetcode submit region end(Prohibit modification and deletion)
