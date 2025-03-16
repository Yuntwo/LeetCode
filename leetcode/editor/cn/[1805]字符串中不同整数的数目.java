//给你一个字符串 word ，该字符串由数字和小写英文字母组成。
//
// 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123 34 8 34" 。注意，剩下的这些整数为（相邻彼此至少有
//一个空格隔开）："123"、"34"、"8" 和 "34" 。
//
// 返回对 word 完成替换后形成的 不同 整数的数目。
//
// 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
//
//
//
// 示例 1：
//
//
//输入：word = "a123bc34d8ef34"
//输出：3
//解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
//
//
// 示例 2：
//
//
//输入：word = "leet1234code234"
//输出：2
//
//
// 示例 3：
//
//
//输入：word = "a1b01c001"
//输出：1
//解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
//
//
//
//
// 提示：
//
//
// 1 <= word.length <= 1000
// word 由数字和小写英文字母组成
//
//
// Related Topics 哈希表 字符串 👍 42 👎 0

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)

/**Success1*/
//class Solution {
//    public int numDifferentIntegers(String word) {
//        HashSet<String> set = new HashSet<>();
//        int n = word.length();
//        int p1 = 0;
//        int p2;
//        while (true) {
//            while (p1 < n && !Character.isDigit(word.charAt(p1))) {
//                p1++;
//            }
//            if (p1 == n) {
//                break;
//            }
//            // p1找到了第一个数字
//            // p2找这组数字的末尾
//            p2 = p1;
//            while (p2 < n && Character.isDigit(word.charAt(p2))) {
//                p2++;
//            }
//            // 去除前导0，这样肯定是重复遍历了这组数字
//            while (p2 - p1 > 1 && word.charAt(p1) == '0') {
//                p1++;
//            }
//            set.add(word.substring(p1, p2));
//            p1 = p2;
//        }
//        return set.size();

/**
 * Success2
 * 这种有头尾且去除前后导的类型用双指针最合适
 * 可能存成特别大的数字，HashSet还是用String类型更好
 * 这倒没有快很多
 */
class Solution {
    public int numDifferentIntegers(String word) {
        HashSet<String> hashSet = new HashSet<>();
        // 由于后面加入hashSet的分支一定是遇到字母，所以为了保证以数字结尾的那部分能加入hashSet，额外在末尾添加一个字母
        word = new StringBuilder(word).append('x').toString();
        int start = 0;
        int end = -1;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                // 一组数字开头的分支
                if (start == end + 1) {
                    // 非前导0，注意这里取非要用括号
                    // 但是这样容易将只含0的情况剔除掉
                    // 你要去除前导0又要保证不是只有0，只能是这组数字全局看重复局部遍历了，但是不影响时间复杂度
//                    if (!(word.charAt(i) == '0')) {
//                        start = i;
//                        end = start;
//                    }
                    start = i;
                    end = start;
                // 一组数字后续的分支
                } else {
                    end++;
                }
            } else {
                if (end >= start) {
                    // 去除前导0
                    // 注意不能是end >= start，否则就剔除了只含0的情况
                    while (end > start && word.charAt(start) == '0') {
                        start++;
                    }
                    // 注意subString方法是左闭右开的
                    hashSet.add(word.substring(start, end + 1));
                    start = end + 1;
                }
            }
        }
        return hashSet.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 注意这个数字太大了用Integer或者其他整数类型总有存不下来的
        // 注意整数的表示范围是字符串转整数场景中特别要注意的问题
//        int res = solution.numDifferentIntegers("167278959591294");
//        int res = solution.numDifferentIntegers("leet1234code234");
//        int res = solution.numDifferentIntegers("a123bc34d8ef34");
//        int res = solution.numDifferentIntegers("0a0");
        int res = solution.numDifferentIntegers("ubhb4xkon7yxlr0007c");
        System.out.println(res);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

/**Failed1*/
//class Solution {
//    public int numDifferentIntegers(String word) {
//        // 这是为了保证最后一个数字能够加入hashSet中而额外添加的一个字母
//        word = new StringBuilder(word).append('x').toString();
//
//        HashSet<Integer> hashSet = new HashSet<>();
//        boolean isNum = false;
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < word.length(); i++) {
//            if (Character.isDigit(word.charAt(i))) {
//                isNum = true;
//                stringBuilder.append(word.charAt(i));
//            } else {
//                isNum = false;
//                String temp = stringBuilder.toString();
//                // 注意非""的String才能转为int
//                if (!temp.equals("")) {
//                    hashSet.add(Integer.valueOf(temp));
//                    stringBuilder = new StringBuilder();
//                }
//            }
//        }
//        return hashSet.size();
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        // 注意这个数字太大了用Integer或者其他整数类型总有存不下来的
//        // 注意整数的表示范围是字符串转整数场景中特别要注意的问题
//        int res = solution.numDifferentIntegers("167278959591294");
////        int res = solution.numDifferentIntegers("leet1234code234");
////        int res = solution.numDifferentIntegers("a123bc34d8ef34");
//        System.out.println(res);
//    }
//}
