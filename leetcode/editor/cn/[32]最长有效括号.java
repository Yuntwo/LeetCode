//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
//
//
//
//
// 示例 1：
//
//
//
//
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
//
// 示例 2：
//
//
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
//
// 示例 3：
//
//
//输入：s = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 3 * 10⁴
// s[i] 为 '(' 或 ')'
//
//
// Related Topics 栈 字符串 动态规划 👍 2497 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)

//class Solution {
//
//    public static int longestValidParentheses(String s) {
//        int maxAns = 0;
//        Stack<Integer> stack = new Stack<>();
//        stack.push(-1);
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(') {
//                stack.push(i);
//            } else {
//                stack.pop();
//                if (stack.empty()) {
//                    stack.push(i);
//                } else {
//                    maxAns = Math.max(maxAns, i - stack.peek());
//                }
//            }
//        }
//        return maxAns;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.longestValidParentheses("()(()"));
//        System.out.println(solution.longestValidParentheses("(())("));
//        System.out.println(solution.longestValidParentheses("()()("));
//    }
//}


/**
 * Success1
 * 栈的内涵设计太巧妙了，定义一举多得，逻辑自然就简单了
 * 栈中存储索引，索引的差值直接代表有效子字符串的长度，就是说记录的有效信息更多
 * 注意还是要了解栈的整体构成，这里虽然push了')'，但控制条件保证它只会出现在栈底；条件保证某些事情只会在某些地方发生
 */
class Solution {
    public int longestValidParentheses(String s) {
        // 存储索引
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        int res = 0;
        // 默认这里的栈底元素是')'
        stack.push(-1);
        while (index < s.length()) {
            if (s.charAt(index) == '(') {
                stack.push(index++);
            } else {
                // 注意这里只有可能pop栈底的')'和普通位置的'('
                stack.pop();
                // 注意这里只有在空栈时候才会push')'
                // 也就是说')'只有可能出现在栈底的位置，其它位置都是'('
                // 注意这里的栈底')'都没了说明上一波已经匹配完了，而且上一波在还有栈底')'时就已经计算过了，而且还要补栈底')'
                if (stack.isEmpty()) {
                    stack.push(index++);
                // 从栈顶元素的角度讲，它都是不匹配的索引，不论是还没得到匹配的'('还是栈底')' 表示新分割如"())()"
                } else {
                    res = Math.max(res, index++ - stack.peek());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("())()"));
        System.out.println(solution.longestValidParentheses("()(()"));
        System.out.println(solution.longestValidParentheses("(())("));
        System.out.println(solution.longestValidParentheses("()()("));
        System.out.println(solution.longestValidParentheses(")()())"));
    }
}

/**
 * Failure2
 * 有些情况逻辑不对，这种思路还是不够精炼
 */
//class Solution {
//    public int longestValidParentheses(String s) {
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            int index = i;
//            int temp = 0;
//            // deque里面只有可能留下'('
//            Deque<Character> deque = new ArrayDeque();
//            while (index < s.length()) {
//                if (s.charAt(index) == ')') {
//                    if (deque.isEmpty()) {
//                        break;
//                    } else {
//                        // 其实只有这样匹配的才会+2
//                        deque.pop();
//                        temp++;
//                    }
//                } else {
//                    deque.push(s.charAt(index));
//                    temp++;
//                }
//                index++;
//            }
//            res = Math.max(res, temp - deque.size());
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.longestValidParentheses("()(()"));
//        System.out.println(solution.longestValidParentheses("(())("));
//        System.out.println(solution.longestValidParentheses("()()("));
//    }
//}

/**
 * Failure1
 * 题意理解错了，括号的类型没有理清楚
 */
//class Solution {
//    public int longestValidParentheses(String s) {
//        int res = 0;
//        int index = 0;
//        while (index < s.length()) {
//            if (s.charAt(index) == '(') {
//                Deque<Character> deque = new ArrayDeque();
//                deque.push(s.charAt(index++));
//                while (index < s.length()) {
//                    if ((deque.peek() == '(' && s.charAt(index) == ')')
//                        || (deque.peek() == ')' && s.charAt(index) == '(')) {
//                        deque.push(s.charAt(index++));
//                    } else {
//                        break;
//                    }
//                }
//                int temp = deque.peek() == ')' ? deque.size() : (deque.size() - 1);
//                res = Math.max(res, temp);
//            } else {
//                index++;
//            }
//        }
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
