//给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
//
// 混合字符串 由小写英文字母和数字组成。
//
//
//
// 示例 1：
//
//
//输入：s = "dfa12321afd"
//输出：2
//解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
//
//
// 示例 2：
//
//
//输入：s = "abc1111"
//输出：-1
//解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 500
// s 只包含小写英文字母和（或）数字。
//
//
// Related Topics 哈希表 字符串 👍 28 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;

//leetcode submit region begin(Prohibit modification and deletion)
// 注意这里的第二大意思是second largest，就是升序排列倒数第二个数
//class Solution {
//    public int secondHighest(String s) {
//        char[] chars = s.toCharArray();
//        HashSet<Integer> hashSet = new HashSet<>();
//        for (char c :
//            chars) {
//            if (Character.isDigit(c)) {
//                // 注意直接将char类型的数字转为int得到的是其对应的ASCL码
//                // '0'的ASCL码是48，'1'的ASCL码是49
//                // 如果是char - '0'的话就能直接得到对应的int
//                // 当然对应数字的ASCL码都是递增的
//                hashSet.add((int) c);
//            }
//        }
//        if (hashSet.isEmpty() || hashSet.size() == 1) {
//            return -1;
//        }
//        hashSet.remove(Collections.max(hashSet));
//        return Collections.max(hashSet) - 48;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String s = "abc1111";
//        int res = solution.secondHighest(s);
//        System.out.println(res);
//    }
//}
// 注意这种最大/最小的夹逼思路很巧妙，其实就是最大最小改进了一点
class Solution {
    public int secondHighest(String s) {
        int firstLargest = -1;
        int secondLargest = -1;
        for (int i = 0; i < s.length(); i++) {
            // 注意这个条件不能放在for循环条件里面，否则后面的i直接不执行了
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                // 注意这里的分支处理并不是闭合的，因为相等值的情况就不处理
                // 这样不需要额外的HashSet来判断
                if (num > firstLargest) {
                    secondLargest = firstLargest;
                    firstLargest = num;
                } else if (num > secondLargest && num < firstLargest) {
                    secondLargest = num;
                }
            }
        }
        return secondLargest;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "dfa12321afd";
        int res = solution.secondHighest(s);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
