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
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 93 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;

/**
 * Success2
 * 双指针变长滑动窗口
 * 注意滑动窗口的核心在于滑动窗口向右增长的过程中一定都是在补足靠近条件，一旦向右超过违背条件了，就要左边抵消条件
 * 注意因为你除了最右边超过的那个地方，其余整个滑动窗口都是满足条件的，你向左滑动只会更不满足条件，而不会再次超出违背条件，这很关键
 */
class Solution {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] cnt = new int[26];
        // 注意这样统计之后cnt数组之和为-s1.length
        for (int i = 0; i < s1.length(); i++) {
            // 注意是--，表示还差这么多，当然也可以用++啦，理解起来稍微绕点
            cnt[s1.charAt(i) - 'a']--;
        }
        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            // x是将要加入滑动窗口的字母
            int x = s2.charAt(right) - 'a';
            cnt[x]++;
            // 我们一直滑动希望维护cnt的每个值都<=0，这样才是满足子串的要求
            // 当不满足时，就要调整这个滑动窗口的起始位置了使cnt[x]满足<=0
            // 当然有可能是减的其他位置的cnt，但是由于都是--，整个滑动窗口都是满足候选条件的
            while (cnt[x] > 0) {
                // 注意这里是--
                cnt[s2.charAt(left) - 'a']--;
                left++;
            }
            // 注意这个条件满足的同时cnt数组之后也是为0的，因为滑动窗口每增加一个cnt就+1，初始cnt之和为-n
            if (right - left + 1 == s1.length()) {
                return true;
            }
        }
        return false;
    }

}

/**
 * Success1
 * 这题还算比较简单的，固定滑动窗口就可以解决，比较暴力
 * 但是如果有个变题是变长滑动窗口，我可能还是搞不定了
 */
//class Solution {
//
//    /**
//     * Success3
//     * 用一个数组进行差值比较，减少两个数组重复比较次数
//     */
//    public boolean checkInclusion(String s1, String s2) {
//        if (s1.length() > s2.length()) {
//            return false;
//        }
//        int[] cnt = new int[26];
//        for (int i = 0; i < s1.length(); i++) {
//            cnt[s1.charAt(i) - 'a']++;
//            // s2含有该字母的话就抵消该计数
//            cnt[s2.charAt(i) - 'a']--;
//        }
//        // 这是还需要抵消的字母个数
//        int diff = 0;
//        for (int c : cnt) {
//            if (c != 0) {
//                diff++;
//            }
//        }
//        if (diff == 0) {
//            return true;
//        }
//        for (int i = s1.length(); i < s2.length(); i++) {
//            // 这是将要进入窗口的字母编码
//            int x = s2.charAt(i) - 'a';
//            // 这是将要移除窗口的字母编码
//            int y = s2.charAt(i - s1.length()) - 'a';
//            // 进入的和移除的相同就抵消了
//            if (x == y) {
//                continue;
//            }
//            // x还未进入窗口，如果这之前计数持平的话，x进入窗口会使diff增加
//            if (cnt[x] == 0) {
//                diff++;
//            }
//            // x进入窗口，抵消cnt
//            // 注意这个if一定是和上面的if互斥的，不太懂但是上面这个if又是必须的
//            cnt[x]--;
//            if (cnt[x] == 0) {
//                diff--;
//            }
//            if (cnt[y] == 0) {
//                diff++;
//            }
//            cnt[y]++;
//            if (cnt[y] == 0) {
//                diff--;
//            }
//            if (diff == 0) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Success2
//     * 减少了一些重复计算，效率有所提高
//     */
////    public boolean checkInclusion(String s1, String s2) {
////        if (s1.length() > s2.length()) {
////            return false;
////        }
//////        int[] target = new int[26];
//////        for (int i = 0; i < s1.length(); i++) {
//////            target[s1.charAt(i) - 'a']++;
//////        }
//////        int[] count = new int[26];
//////        for (int i = 0; i < s1.length(); i++) {
//////            count[s2.charAt(i) - 'a']++;
//////        }
////        // 上面可以合并写
////        int[] target = new int[26];
////        int[] count = new int[26];
////        for (int i = 0; i < s1.length(); i++) {
////            target[s1.charAt(i) - 'a']++;
////            count[s2.charAt(i) - 'a']++;
////        }
////        for (int i = 0; i < s2.length() - s1.length(); i++) {
////            if (Arrays.equals(target, count)) {
////                return true;
////            }
////            count[s2.charAt(i) - 'a']--;
////            count[s2.charAt(i + s1.length()) - 'a']++;
////        }
////        return Arrays.equals(target, count);
////    }
//
//    /**
//     * Success1
//     * 比较暴力，有重复计算
//     */
////    public boolean checkInclusion(String s1, String s2) {
////        int[] target = new int[26];
////        for (int i = 0; i < s1.length(); i++) {
////            target[s1.charAt(i) - 'a']++;
////        }
////        for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
////            int[] count = new int[26];
////            for (int j = i; j < i + s1.length(); j++) {
////                count[s2.charAt(j) - 'a']++;
////            }
////            if (Arrays.equals(target, count)) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s1 = "ab";
////        String s2 = "eidbaooo";
//        String s2 = "eidboaoo";
//        boolean res = solution.checkInclusion(s1, s2);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 逻辑太混乱了，不容易调对
 * 不记得那种简单的位数组怎么表示了
 */
//class Solution {
//    public boolean checkInclusion(String s1, String s2) {
//        int[] target = new int[26];
//        for (int i = 0; i < s1.length(); i++) {
//            target[s1.charAt(i) - 'a']++;
//        }
//        int[] count = new int[26];
//        int start = 0;
//        int end = 0;
//        while (end < s2.length() && !equal(count, target)) {
//            while (start < s2.length() && (target[s2.charAt(start) - 'a'] == 0 || count[s2.charAt(start) - 'a'] > target[s2.charAt(start) - 'a'])) {
//                if (target[s2.charAt(start) - 'a'] == 0) {
//                    start++;
//                    end++;
//                } else if (count[s2.charAt(start) - 'a'] > target[s2.charAt(start) - 'a']) {
//                    count[s2.charAt(start) - 'a']--;
//                    start++;
//                }
//            }
//            count[s2.charAt(end) - 'a']++;
//            end++;
//        }
//        return start <= end;
//    }
//
//    private boolean equal(int[] count, int[] target) {
//        for (int i = 0; i < count.length; i++) {
//            if (count[i] != target[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s1 = "ab";
////        String s2 = "eidbaooo";
//        String s2 = "eidboaoo";
//        boolean res = solution.checkInclusion(s1, s2);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
