//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
//
// 例如，121 是回文，而 123 不是。
//
//
//
//
// 示例 1：
//
//
//输入：x = 121
//输出：true
//
//
// 示例 2：
//
//
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3：
//
//
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
//
//
//
//
// 提示：
//
//
// -2³¹ <= x <= 2³¹ - 1
//
//
//
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？
//
// Related Topics 数学 👍 2296 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 用字符串处理，效率低
//class Solution {
//    public boolean isPalindrome(int x) {
//        String s = String.valueOf(x);
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
//
// 反转一半的数
//class Solution {
//    public boolean isPalindrome(int x) {
//        // 注意特殊情况的直接排除返回有利于统一后面的判断逻辑
//        // 注意末尾为0的非0数的排除有利于后面的代码逻辑！！！
//        if (x < 0 || (x % 10 == 0 && x != 0)) {
//            return false;
//        }
//        int reverse = 0;
//        // 注意是当x<=reverse的时候就到了一半
//        while (x > reverse) {
//            int temp = x % 10;
//            reverse = reverse * 10 + temp;
//            x = x / 10;
//        }
//        // x的位数为偶数和奇数的情况
//        // 注意后者包含了一位数字返回true的情况
//        return (x == reverse || x == reverse / 10);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        boolean res = solution.isPalindrome(1);
//        System.out.println(res);
//    }
//}
// 反转全部的数
// 如果是回文数一定不会溢出
class Solution {
    public boolean isPalindrome(int x) {
        // 注意这里特殊情况的单独处理对于代码逻辑也很重要，否则可能溢出
        // 注意这里后面的括号必不可少
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reverse = 0;
        int xTemp = x;
        while (xTemp != 0) {
            int temp = xTemp % 10;
            reverse = reverse * 10 + temp;
            xTemp = xTemp / 10;
        }
        return x == reverse;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.isPalindrome(1);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
