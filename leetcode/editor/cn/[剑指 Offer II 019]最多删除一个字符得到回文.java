//给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
//
//
//
// 示例 1:
//
//
//输入: s = "aba"
//输出: true
//
//
// 示例 2:
//
//
//输入: s = "abca"
//输出: true
//解释: 可以删除 "c" 字符 或者 "b" 字符
//
//
// 示例 3:
//
//
//输入: s = "abc"
//输出: false
//
//
//
// 提示:
//
//
// 1 <= s.length <= 10⁵
// s 由小写英文字母组成
//
//
//
//
//
// 注意：本题与主站 680 题相同： https://leetcode-cn.com/problems/valid-palindrome-ii/
//
// Related Topics 贪心 双指针 字符串 👍 75 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * Success1
     * 完善逻辑，但是写得有点麻烦
     */
    public boolean validPalindrome(String s) {
        boolean isChanged = false;
        int start = 0;
        int end = s.length() - 1;
        int start1 = 0;
        int start2 = 0;
        int end1 = 0;
        int end2 = 0;
        boolean can1 = false;
        boolean can2 = false;
        // 注意只能到小于不能等于
        while (start < end) {
            if (s.charAt(start) != s.charAt(end) && isChanged) {
                return false;
            } else if (s.charAt(start) != s.charAt(end) && !isChanged) {
                // 注意当然不能这么光看眼前这一个字符，有可能两种条件都满足，但是后续删哪一个的结果是不同的
                if (s.charAt(start + 1) == s.charAt(end)) {
                    start1 = start + 1;
                    end1 = end;
                    can1 = true;
                    // 别忘了修改条件
                    isChanged = true;
                }
                if (s.charAt(start) == s.charAt(end - 1)) {
                    start2 = start;
                    end2 = end - 1;
                    can2 = true;
                    isChanged = true;
                }
                if (!can1 && !can2) {
                    return false;
                }
                break;
            }
            start++;
            end--;
        }
        if (start >= end) {
            return true;
        }
        if (can1) {
            while (start1 < end1) {
                if (s.charAt(start1) != s.charAt(end1)) {
                    break;
                }
                start1++;
                end1--;
            }
            if (start1 >= end1) {
                return true;
            }
        }
        if (can2) {
            while (start2 < end2) {
                if (s.charAt(start2) != s.charAt(end2)) {
                    break;
                }
                start2++;
                end2--;
            }
            if (start2 >= end2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Failure1
     * 不能只看眼前，要两种情况都考虑到
     * @param args
     */
//    public boolean validPalindrome(String s) {
//        boolean isChanged = false;
//        int start = 0;
//        int end = s.length() - 1;
//        // 注意只能到小于不能等于
//        while (start < end) {
//            if (s.charAt(start) != s.charAt(end) && isChanged) {
//                return false;
//            } else if (s.charAt(start) != s.charAt(end) && !isChanged) {
//                // 注意当然不能这么光看眼前这一个字符，有可能两种条件都满足，但是后续删哪一个的结果是不同的
//                if (s.charAt(start + 1) == s.charAt(end)) {
//                    start++;
//                } else if (s.charAt(start) == s.charAt(end - 1)) {
//                    end--;
//                } else {
//                    return false;
//                }
//                isChanged = true;
//            }
//            start++;
//            end--;
//        }
//        return true;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String s = "eeccccbebaeeabebccceea";
//        String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        String s = "cbbcc";
        boolean res = solution.validPalindrome(s);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
