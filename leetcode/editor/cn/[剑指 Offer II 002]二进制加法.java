//给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
//
// 输入为 非空 字符串且只包含数字 1 和 0。
//
//
//
// 示例 1:
//
//
//输入: a = "11", b = "10"
//输出: "101"
//
// 示例 2:
//
//
//输入: a = "1010", b = "1011"
//输出: "10101"
//
//
//
// 提示：
//
//
// 每个字符串仅由字符 '0' 或 '1' 组成。
// 1 <= a.length, b.length <= 10^4
// 字符串如果不是 "0" ，就都不含前导零。
//
//
//
//
//
// 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/
//
// Related Topics 位运算 数学 字符串 模拟 👍 54 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 效率不是很高，模拟的方法
 */
//class Solution {
//    public String addBinary(String a, String b) {
//        int i = a.length() - 1;
//        int j = b.length() - 1;
//        String res = "";
//        int carry = 0;
//        int now = 0;
//        while (i >= 0 || j >= 0) {
//            if (i >= 0 && j >= 0) {
//                now = (a.charAt(i) - '0') + (b.charAt(j) - '0') + carry;
//                i--;
//                j--;
//            } else if (i >= 0) {
//                now = (a.charAt(i) - '0') + carry;
//                i--;
//            } else {
//                now = (b.charAt(j) - '0') + carry;
//                j--;
//            }
//            if (now >= 2) {
//                carry = 1;
//                // 注意这里是要加0
//                res = String.valueOf(now - 2) + res;
//            } else {
//                carry = 0;
//                res = String.valueOf(now) + res;
//            }
//            now = 0;
//        }
//        // 注意最后还有1进位的话要加上，0就不用了
//        if (carry == 1) {
//            res = String.valueOf(1) + res;
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String a = "1111";
//        String b = "1111";
//        String res = solution.addBinary(a, b);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 也是模拟的方法，代码更简洁，效率更高
 */
class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            // 注意这里分开加就行
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            carry = sum / 2;
            sb.append(sum % 2);
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
