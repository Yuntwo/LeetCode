//给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 本题中，将空字符串定义为有效的 回文串 。
//
//
//
// 示例 1:
//
//
//输入: s = "A man, a plan, a canal: Panama"
//输出: true
//解释："amanaplanacanalpanama" 是回文串
//
// 示例 2:
//
//
//输入: s = "race a car"
//输出: false
//解释："raceacar" 不是回文串
//
//
//
// 提示：
//
//
// 1 <= s.length <= 2 * 10⁵
// 字符串 s 由 ASCII 字符组成
//
//
//
//
//
// 注意：本题与主站 125 题相同： https://leetcode-cn.com/problems/valid-palindrome/
//
// Related Topics 双指针 字符串 👍 54 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 用了一个反转字符串判断相等
 * 这样没有太大必要，还多占空间
 * 把大写全转为小写判断起来更方便，当然调用这个API也是要消耗资源的
 * 主要用的API是Character.toLowerCase和Character.isLetterOrDigit
 */
//class Solution {
//    public boolean isPalindrome(String s) {
//        StringBuffer sgood = new StringBuffer();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            if (Character.isLetterOrDigit(ch)) {
//                sgood.append(Character.toLowerCase(ch));
//            }
//        }
//        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
//        return sgood.toString().equals(sgood_rev.toString());
//    }
//}

/**
 * Success1
 * 双指针
 * 注意题意，只考虑字母和数字，但是忽略大小写
 * 思路不难，就是处理不同的情况，如果直接手写方法的会很麻烦
 */
class Solution {

    /**
     * Success3
     * 调用API，效率更低
     * 这个是一个一个判断的，写得不优雅
     */
//    public boolean isPalindrome(String s) {
//
//        StringBuffer sgood = new StringBuffer();
//        int length = s.length();
//        for (int i = 0; i < length; i++) {
//            char ch = s.charAt(i);
//            if (Character.isLetterOrDigit(ch)) {
//                sgood.append(Character.toLowerCase(ch));
//            }
//        }
//        int n = sgood.length();
//        int left = 0, right = n - 1;
//        while (left < right) {
//            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
//                return false;
//            }
//            ++left;
//            --right;
//        }
//        return true;
//    }

    /**
     * Success2
     * 总体思路和下面一样
     * 但是直接转小写判断和调用API，看上去简洁很多
     */
    public boolean isPalindrome(String s) {

        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    /**
     * Success1
     * 不调用API，效率还不错
     */
//    public boolean isPalindrome(String s) {
//        if (s.length() == 1) {
//            return true;
//        }
//        int start = 0;
//        int end = s.length() - 1;
//        while (start < end) {
//            // 注意不要把数字的情况漏了
//            while (start < end && !(s.charAt(start) >= 65 && s.charAt(start) <= 90) && !(s.charAt(start) >= 97 && s.charAt(start) <= 122) && !(s.charAt(start) >= 48 && s.charAt(start) <= 57)) {
//                start++;
//            }
//            while (start < end && !(s.charAt(end) >= 65 && s.charAt(end) <= 90) && !(s.charAt(end) >= 97 && s.charAt(end) <= 122) && !(s.charAt(end) >= 48 && s.charAt(end) <= 57)) {
//                end--;
//            }
//            // 这是数字的情况，如果不单独处理的话例如"0P"的差值也是32，但它们不是同字母大小写的情况
//            if ((s.charAt(start) >= 48 && s.charAt(start) <= 57) ^ (s.charAt(end) >= 48 && s.charAt(end) <= 57)) {
//                return false;
//            }
//            if (Math.abs(s.charAt(start) - s.charAt(end)) != 0 && Math.abs(s.charAt(start) - s.charAt(end)) != 32) {
//                return false;
//            }
//            // 注意while循环要手动改变条件，这个条件的改变和上面的条件改变内涵与作用不同
//            start++;
//            end--;
//        }
//        return true;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "A man, a plan, a canal: Panama";
        s = ".,";
        s = "0P";
        s = "ab2a";
        boolean res = solution.isPalindrome(s);
        System.out.println(res);
    }
}

/**
 * End
 */
//leetcode submit region end(Prohibit modification and deletion)
