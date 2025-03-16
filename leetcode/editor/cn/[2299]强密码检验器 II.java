//如果一个密码满足以下所有条件，我们称它是一个 强 密码：
//
//
// 它有至少 8 个字符。
// 至少包含 一个小写英文 字母。
// 至少包含 一个大写英文 字母。
// 至少包含 一个数字 。
// 至少包含 一个特殊字符 。特殊字符为："!@#$%^&*()-+" 中的一个。
// 它 不 包含 2 个连续相同的字符（比方说 "aab" 不符合该条件，但是 "aba" 符合该条件）。
//
//
// 给你一个字符串 password ，如果它是一个 强 密码，返回 true，否则返回 false 。
//
//
//
// 示例 1：
//
// 输入：password = "IloveLe3tcode!"
//输出：true
//解释：密码满足所有的要求，所以我们返回 true 。
//
//
// 示例 2：
//
// 输入：password = "Me+You--IsMyDream"
//输出：false
//解释：密码不包含数字，且包含 2 个连续相同的字符。所以我们返回 false 。
//
//
// 示例 3：
//
// 输入：password = "1aB!"
//输出：false
//解释：密码不符合长度要求。所以我们返回 false 。
//
//
//
// 提示：
//
//
// 1 <= password.length <= 100
// password 包含字母，数字和 "!@#$%^&*()-+" 这些特殊字符。
//
//
// Related Topics 字符串 👍 18 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasLowLetter = false;
        boolean hasUpLetter = false;
        boolean hasNum = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            if (i > 0) {
                if (password.charAt(i) == password.charAt(i - 1)) {
                    return false;
                }
            }
            int temp = password.charAt(i);
            if (temp >= 97 && temp <= 122) {
                hasLowLetter = true;
            } else if (temp >= 65 && temp <= 90) {
                hasUpLetter = true;
            } else if (temp >= 48 && temp <= 57) {
                hasNum = true;
            } else {
                hasSpecial = true;
            }
        }
        return hasLowLetter && hasUpLetter && hasNum && hasSpecial;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "IloveLe3tcode!";
        boolean res = solution.strongPasswordCheckerII(s);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
