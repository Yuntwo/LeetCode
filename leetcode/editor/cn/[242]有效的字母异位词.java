//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "rat", t = "car"
//输出: false
//
// 说明:
//你可以假设字符串只包含小写字母。
//
// 进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表
// 👍 352 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 用count数组
 */
// 这样用两个count数组略显麻烦了
//class Solution {
//  public boolean isAnagram(String s, String t) {
//    if (s.length() != t.length()) {
//      return false;
//    }
//    char[] s1 = s.toCharArray();
//    char[] t1 = t.toCharArray();
//    int[] tagS = new int[26];
//    int[] tagT = new int[26];
//    for (int i = 0; i < s1.length; i++) {
//      tagS[s1[i] - 97]++;
//      tagT[t1[i] - 97]++;
//    }
//    for (int i = 0; i < 26; i++) {
//      if (tagS[i] != tagT[i]) {
//        return false;
//      }
//    }
//    return true;
//  }
//}
// 用一个count数组就可以
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        // 这就是一个自己定义的hash表
        int[] alpha = new int[26];
        for(int i = 0; i< s.length(); i++) {
            // charAt(i) 就是在第i个位置的字符
            // 每一次遍历一定会有Hash table中相应字母的数据变化情况
            // 这样遍历一次就够了
            alpha[s.charAt(i) - 'a'] ++;
            alpha[t.charAt(i) - 'a'] --;
        }
        for(int i=0;i<26;i++){
            if(alpha[i] != 0)
                return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
