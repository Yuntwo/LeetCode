//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
//
//
// 示例 1：
//
// 输入：s = "We are happy."
//输出："We%20are%20happy."
//
//
//
// 限制：
//
// 0 <= s 的长度 <= 10000
// Related Topics 字符串 👍 200 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 这种分割方法无法解决连续空格的情况 no
//class Solution {
//    public String replaceSpace(String s) {
//        String res = new String();
//        String[] result = s.split(" ");
//        for (int i = 0; i < result.length - 1; i++) {
//            res = res + result[i] + "%20";
//        }
//        res += result[result.length - 1];
//        return res;
//    }
//}
// 这种字符串拼接的方式思路上很直观，但是从底层来说应该是比较复杂的，
// 所以空间和时间效率都很低 yes
//class Solution {
//    public String replaceSpace(String s) {
//        char[] result = s.toCharArray();
//        String res = new String();
//        for (int i = 0; i < result.length; i++) {
//            if (result[i] != ' ') {
//                res += result[i];
//            } else {
//                res += "%20";
//            }
//        }
//        return res;
//    }
//}
// 用StringBuilder去拼接挺快的比上面那种String+的方式，虽然空间效率还是很低 yes
class Solution {
    public String replaceSpace(String s){
        //使用StringBuilder去拼接
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();++i){
            char c=s.charAt(i);
            if(" ".equals(String.valueOf(c))){
                sb.append("%20");
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
// 书上的思路，时间效率高，空间效率低 yes
//class Solution {
//    public String replaceSpace(String s) {
//        char[] origin = s.toCharArray();
//        int numSpace = 0;
//        for (int i = 0; i < origin.length; i++) {
//            if (origin[i] == ' ') {
//                numSpace++;
//            }
//        }
//        // 注意这里只需要再多2而不是3
//        char[] res = new char[origin.length + 2 * numSpace];
//        int i = origin.length - 1;
//        int j = res.length - 1;
//        while (i != j) {
//            if (origin[i] != ' ') {
//                res[j] = origin[i];
//                i--;
//                j--;
//            } else {
//                i--;
//                res[j--] = '0';
//                res[j--] = '2';
//                res[j--] = '%';
//            }
//        }
//        // 注意最后这里没有空格的字符串了也要复制过来
//        while (i >= 0) {
//            res[j--] = origin[i--];
//        }
//        // 注意这个方法
//        return String.valueOf(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
