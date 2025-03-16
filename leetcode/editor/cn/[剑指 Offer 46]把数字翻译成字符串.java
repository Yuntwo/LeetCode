//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//
//
//
// 示例 1:
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
//
//
//
// 提示：
//
//
// 0 <= num < 2³¹
//
//
// Related Topics 字符串 动态规划 👍 494 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 用字符串处理，占用内存略多
//class Solution {
//    public int translateNum(int num) {
//        String nums = String.valueOf(num);
//        int[] res = new int[nums.length()];
//        res[0] = 1;
//        for (int i = 1; i < nums.length(); i++) {
//            res[i] = res[i - 1];
//            StringBuilder temp = new StringBuilder();
//            temp.append(nums.charAt(i - 1));
//            temp.append(nums.charAt(i));
//            String temptemp = temp.toString();
//            // 注意这里一定是要大于"09"，否则不能复用，注意不能写成"9"
//            if (temptemp.compareTo("26") < 0 && temptemp.compareTo("09") > 0) {
//                if (i == 1) {
//                    res[i] += 1;
//                } else {
//                    // ！！！注意这个递归关系，既不是+2，也不是*2
//                    res[i] += res[i - 2];
//                }
//            }
//        }
//        return res[nums.length() - 1];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.translateNum(25);
//        System.out.println(res);
//    }
//}
// 不用字符串，引入一个函数获取某位上的数字
// 为啥感觉这样做用的内存还更多了呢？
class Solution {
    public int translateNum(int num) {
        int length = 1;
        // 和那个反转数字一样，可能反转之后的数字溢出了
        // 所以要用long而不能用int
        long numVerse = num % 10;
        num /= 10;
        while (num != 0) {
            numVerse = numVerse * 10 + num % 10;
            num /= 10;
            length++;
        }
        int[] res = new int[length];
        res[0] = 1;
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1];
            long temp = (numVerse % 10) * 10;
            numVerse /= 10;
            temp += numVerse % 10;
//            numVerse /= 10;
            // 注意这里一定是要大于9，小于26(包含25)
            if (temp < 26 && temp > 9) {
                if (i == 1) {
                    res[i] += 1;
                } else {
                    // ！！！注意这个递归关系，既不是+2，也不是*2
                    res[i] += res[i - 2];
                }
            }
        }
        return res[length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.translateNum(1250156147);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
