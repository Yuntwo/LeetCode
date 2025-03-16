//请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//
// 数值（按顺序）可以分成以下几个部分：
//
//
// 若干空格
// 一个 小数 或者 整数
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数
// 若干空格
//
//
// 小数（按顺序）可以分成以下几个部分：
//
//
// （可选）一个符号字符（'+' 或 '-'）
// 下述格式之一：
//
// 至少一位数字，后面跟着一个点 '.'
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
// 一个点 '.' ，后面跟着至少一位数字
//
//
//
// 整数（按顺序）可以分成以下几个部分：
//
//
// （可选）一个符号字符（'+' 或 '-'）
// 至少一位数字
//
//
// 部分数值列举如下：
//
//
// ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
//
//
// 部分非数值列举如下：
//
//
// ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
//
//
//
//
// 示例 1：
//
//
//输入：s = "0"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "e"
//输出：false
//
//
// 示例 3：
//
//
//输入：s = "."
//输出：false
//
// 示例 4：
//
//
//输入：s = "    .1  "
//输出：true
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 20
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
//
//
// Related Topics 字符串 👍 397 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 注意它这道题的意思应该是一个字符串对应判断一个数值
//class Solution {
//    public boolean isNumber(String s) {
//        int i = 0;
//        boolean res = false;
//        while (i < s.length()) {
//            // 有了一个'.'后就不允许有'.'了
//            // 注意char常量直接比较
//            if (s.charAt(i) == '.') {
//                i++;
//                while (i < s.length()) {
//                    if (s.charAt(i) == '.') {
//                        return false;
//                    }
//                    if (s.charAt(i) )
//                    i++;
//                }
//            }
//            // 有了一个'e/E'后就不允许有'e/E'了
//            // 有了一个'e/E'后一定要有个整数
//            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
//                i++;
//                while (i < s.length()) {
//                    if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
//                        return false;
//                    }
//                    i++;
//                }
//            }
//            i++;
//        }
//        return res;
//    }
//}
//class Solution {
//    public boolean isNumber(String s) {
//        // 只有一个'e/E/. '的情况
//        if (s.length() == 1 && (s.charAt(0) == 'e' || s.charAt(0) == 'E' || s.charAt(0) == '.' || s.charAt(0) == ' ')) {
//            return false;
//        }
//        // 注意默认eOrE的位置在界外
//        int eOrE = s.length();
//        // 找出eOrE的分界位置
//        for (int i = s.length() - 1; i >= 0; i--) {
//            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
//                eOrE = i;
//                break;
//            }
//        }
//        // 注意e卡在最后一个位置的情况返回false
//        if (eOrE == s.length() - 1) {
//            return false;
//        // 只有前半段的情况
//        } else if (eOrE == s.length()) {
//            // 判断eOrE的前半段是否为小数/整数
//            if (!isInteger(s, 0, eOrE - 1) && !isDecimal(s, 0, eOrE - 1)) {
//                return false;
//                // 如果满足条件且没有后半段了就不用判断了直接返回true
//            } else {
//                return true;
//            }
//            // 只有后半段的情况返回false，因为一定要后半段
//        } else if (eOrE == 0) {
//            return false;
//            // 前后半段都有的情况
//        } else {
//            if ((!isInteger(s, eOrE + 1, s.length() - 1)) && (!isInteger(s, 0, eOrE - 1) && !isDecimal(s, 0, eOrE - 1))) {
//                return false;
//                // 如果满足条件且没有前半段了就不用判断了直接返回true
//            }
//        }
//        // 错误排除法返回true
//        return true;
//    }
//
//    // 注意这里的start和end表示直接的索引位置
//    private boolean isInteger(String s, int start, int end) {
//        // 如果只有一个字符且是这些的话返回false
//        if (end == start) {
//            if (s.charAt(start) == 'e' || s.charAt(start) == 'E' || s.charAt(start) == '.') {
//                return false;
//            }
//        }
//        int i = start + 1;
//        while (i <= end) {
//            if (s.charAt(i) == 'e' || s.charAt(i) == 'E' || s.charAt(i) == '.') {
//                return false;
//            }
//            i++;
//        }
//        return true;
//    }
//
//    private boolean isDecimal(String s, int start, int end) {
//        // 如果只有一个字符且是这些的话返回false
//        if (end == start) {
//            if (s.charAt(start) == 'e' || s.charAt(start) == 'E' || s.charAt(start) == '.') {
//                return false;
//            }
//        }
//        // 开头是'+/-'但是后面只有一个'.'的情况
//        if ((s.charAt(start) == '+' || s.charAt(start) == '-') && s.charAt(start + 1) == '.' && start + 1 == end) {
//            return false;
//        }
//        while (i <= end) {
//            if (s.charAt(i) == 'e' || s.charAt(i) == 'E' || s.charAt(i) == '.') {
//                return false;
//            }
//            i++;
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        boolean res = solution.isNumber("3.");
//        System.out.println(res);
//    }
//}
/**
 * mid难度，但通过率真低
 * 题解：状态机解法
 */
class Solution {
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
        Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};
        transfer.put(State.STATE_INITIAL, initialMap);
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_POINT, pointMap);
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                state = transfer.get(state).get(type);
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else if (ch == ' ') {
            return CharType.CHAR_SPACE;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END
    }

    enum CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_SPACE,
        CHAR_ILLEGAL
    }
}
//leetcode submit region end(Prohibit modification and deletion)
