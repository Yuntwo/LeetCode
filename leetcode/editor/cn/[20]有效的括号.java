//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
// 每个右括号都有一个对应的相同类型的左括号。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁴
// s 仅由括号 '()[]{}' 组成
//
//
// Related Topics 栈 字符串 👍 3565 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        // 注意涉及到泛型的如果不指明类型，那么默认会是Object，这样在处理数据时会比较麻烦，所以最好指明类型
        LinkedList<Character> stack = new LinkedList<Character>();
        char[] brackets = s.toCharArray();
        if (brackets[0] == ')' || brackets[0] == ']' || brackets[0] == '}') {
            return false;
        }
        // 注意声明变量类型是必须的
        for (char bracket : brackets) {
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                // 注意LinkedList的push是放在线性表前面的，用add才是模拟的栈放在线性表后面
//                stack.push(bracket);
                stack.add(bracket);
            // 注意这种情况，当入右括号的时候，前面可能栈空了，这种实质上和第一种情况一样，直接错误返回
            // 而且一定要判断返回，否则后面的情况会在栈空的情况下peekLast()会报错
            } else if (stack.size() == 0) {
                return false;
            } else if (bracket == ')') {
                // 注意如果数据类型(或其包装类)不同的话是一定不相等的，会抛出异常所以一定要转换
//                if (stack.peekLast().charValue() != "(") {
                // 但是包装类和它的基本数据类型可以直接判断相等
                if (stack.peekLast() != '(') {
                    // 当然你也可以严格一点转换成基本数据类型
//                if (stack.peekLast().charValue() != '(') {
                    return false;
                } else {
                    stack.pollLast();
                }
            } else if (bracket == ']') {
                if (stack.peekLast() != '[') {
                    return false;
                } else {
                    stack.pollLast();
                }
            } else if (bracket == '}') {
                // 注意peek和poll的区别，peek就是看一眼，不会弹出对象
                if (stack.peekLast() != '{') {
                    return false;
                } else {
                    stack.pollLast();
                }
            }
        }
        // 注意如果匹配的话最后栈一定是空的
        // 而那种只有左括号留在栈里没有相应的右括号将它们弹出去的一定是无效的
        if (stack.size() != 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "([)]";
        Solution solution = new Solution();
        boolean res = solution.isValid(s);
        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
