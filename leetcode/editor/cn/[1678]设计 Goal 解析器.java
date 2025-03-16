//请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解
//析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字
//符串。
//
// 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
//
//
//
// 示例 1：
//
// 输入：command = "G()(al)"
//输出："Goal"
//解释：Goal 解析器解释命令的步骤如下所示：
//G -> G
//() -> o
//(al) -> al
//最后连接得到的结果是 "Goal"
//
//
// 示例 2：
//
// 输入：command = "G()()()()(al)"
//输出："Gooooal"
//
//
// 示例 3：
//
// 输入：command = "(al)G(al)()()G"
//输出："alGalooG"
//
//
//
//
// 提示：
//
//
// 1 <= command.length <= 100
// command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成
//
//
// Related Topics 字符串 👍 50 👎 0

// ArrayList似乎不支持直接获取最后一个元素，你得单独维护一个索引
import java.util.ArrayList;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
// 主要是有一些字符串拼接的操作
//class Solution {
//    public String interpret(String command) {
//        LinkedList<Character> stack = new LinkedList<>();
//        int i = 0;
//        String res = "";
//        while (i < command.length()) {
//            if (command.charAt(i) == 'G') {
//                // 运算符重载可以直接进行字符串拼接
//                res += "G";
//                i++;
//            } else if (command.charAt(i) == '(' || command.charAt(i) == 'a' || command.charAt(i) == 'l') {
//                stack.push(command.charAt(i));
//                i++;
//            } else if (command.charAt(i) == ')') {
//                if (stack.peek().equals('(')) {
//                    res += "o";
//                    i++;
//                    stack.poll();
//                } else {
//                    res += "al";
//                    i++;
//                    stack.poll();
//                    stack.poll();
//                    stack.poll();
//                }
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String res = solution.interpret("G()(al)");
//        System.out.println(res);
//    }
//}
// 可以不借助额外的空间直接进行规则模式匹配
class Solution {
    public String interpret(String command) {
        // 注意可以用这个操作类很方便
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < command.length()) {
            if (command.charAt(i) == 'G') {
                res.append('G');
                i++;
            } else {
            // 注意由于它是规则匹配的，所以这样一定不会越界，一定会顺序出现
            // 很多判断都可以省了
//            } else if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    res.append('o');
                    i += 2;
                } else {
//                } else if (command.charAt(i + 1) == 'a') {
                    res.append("al");
                    i += 4;
                }
            }
        }
        // 注意最后转换为String
        return res.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String res = solution.interpret("G()(al)");
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
