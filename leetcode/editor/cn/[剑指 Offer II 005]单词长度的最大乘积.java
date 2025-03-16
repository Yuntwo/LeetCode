//给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语
//的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
//
//
//
// 示例 1:
//
//
//输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
//输出: 16
//解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
//
// 示例 2:
//
//
//输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
//输出: 4
//解释: 这两个单词为 "ab", "cd"。
//
// 示例 3:
//
//
//输入: words = ["a","aa","aaa","aaaa"]
//输出: 0
//解释: 不存在这样的两个单词。
//
//
//
//
// 提示：
//
//
// 2 <= words.length <= 1000
// 1 <= words[i].length <= 1000
// words[i] 仅包含小写字母
//
//
//
//
//
// 注意：本题与主站 318 题相同：https://leetcode-cn.com/problems/maximum-product-of-word-
//lengths/
//
// Related Topics 位运算 数组 字符串 👍 142 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;

/**
 * Success2
 * 当然本质上还是双重循环，大的框架没有变，变的只是比较两个字母不同的方法/减小循环长度而已
 * 空间换时间，预先运算一遍并记录所有String的bitMask，避免重复计算
 * 时间复杂度：O((m + n)* n)
 * 空间复杂度：O(n)
 * 这样已经够快了
 */
class Solution {

    /**
     * Success2
     * 用HashMap代替数组，减小数组长度，从而也减小双重循环的长度
     * 本质上也是空间换时间
     * 但是这样似乎并没有更快
     */
    public int maxProduct(String[] words) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            int bitMask = 0;
            for (char c : word.toCharArray()) {
                bitMask |= 1 << (c - 'a');
            }
            // 注意不同的单词可能具有相同的bitMask，这个性质很重要！！！
            // 如ab和aabb
            hashMap.put(bitMask, Math.max(hashMap.getOrDefault(bitMask, 0), word.length()));
        }

        int res = 0;
        // 不变的双重循环，但是由于是HashMap不能用索引了，所以只有采用这种Set遍历的方式
        // 重复在所难免了
        for (int x : hashMap.keySet()) {
            for (int y : hashMap.keySet()) {
                if ((x & y) == 0) {
                    res = Math.max(res, hashMap.get(x) * hashMap.get(y));
                }
            }
        }
        return res;
    }

    /**
     * Success1
     * 直接把每一个word的bitMask用数组存起来，当然这样这个数组长度取决于words数组长度
     * 而且数组长度越长的话双重循环越久
     */
//    public int maxProduct(String[] words) {
//        int[] bitMask = new int[words.length];
//        for (int i = 0; i < words.length; i++) {
//            for (char c : words[i].toCharArray()) {
//                bitMask[i] |= 1 << (c - 'a');
//            }
//        }
//        int res = 0;
//        for (int i = 0; i < words.length; i++) {
//            for (int j = i + 1; j < words.length; j++) {
//                if ((bitMask[i] & bitMask[j]) == 0) {
//                    res = Math.max(res, words[i].length() * words[j].length());
//                }
//            }
//        }
//        return res;
//    }

}

/**
 * Success1
 * 暴力解法，双重循环，效率低下
 */
//class Solution {
//    public int maxProduct(String[] words) {
//        int res = 0;
//        for (int i = 0; i < words.length; i++) {
//            for (int j = i + 1; j < words.length; j++) {
//                if (!isCovered(words[i], words[j])) {
//                    res = Math.max(res, words[i].length() * words[j].length());
//                }
//            }
//        }
//        return res;
//    }
//
//    /**
//     * Success3
//     * 思路同2，直接用位掩码，不用位数组了
//     * 时间复杂度O(m)，空间复杂度O(1)
//     */
//    private boolean isCovered(String s1, String s2) {
//        // 注意它是int型，不是单bit
//        int bitMask1 = 0;
//        int bitMask2 = 0;
//        for (char c : s1.toCharArray()) {
//            // 注意这个表达的意思是将1左移
//            bitMask1 |= 1 << (c - 'a');
//        }
//        // 注意Java是大小写敏感的
//        for (char c : s2.toCharArray()) {
//            bitMask2 |= 1 << (c - 'a');
//        }
//        return (bitMask1 & bitMask2) != 0;
//    }
//
//    /**
//     * Success2
//     * 用计数数组来比较，效率会更高，某种程度上说明bit数据结构对于本题效率更高
//     * 时间复杂度O(m)，空间复杂度O(1)
//     */
////    private boolean isCovered(String s1, String s2) {
////        int[] array = new int[26];
////        for (char c : s1.toCharArray()) {
////            array[c - 'a'] = 1;
////        }
////        for (char c : s2.toCharArray()) {
////            if (array[c - 'a'] == 1) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//    /**
//     * Success1
//     * 直接用String数据类型比较
//     * 时间复杂度O(m)，空间复杂度O(1)
//     */
////    private boolean isCovered(String s1, String s2) {
////        for (char c : s1.toCharArray()) {
////            // 注意这种比较的表达方法
////            // 只要有一个相同就返回true
////            if (s2.indexOf(c) != -1) {
////                return true;
////            }
////        }
////        // 所有都不相同才能返回false
////        return false;
////    }
//}
//leetcode submit region end(Prohibit modification and deletion)
