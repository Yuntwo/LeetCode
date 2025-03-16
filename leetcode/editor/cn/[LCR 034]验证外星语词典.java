//某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
//
// 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回
//false。
//
//
//
// 示例 1：
//
//
//输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//输出：true
//解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
//
// 示例 2：
//
//
//输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
//输出：false
//解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
//
// 示例 3：
//
//
//输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
//输出：false
//解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅
//' 是空白字符，定义为比任何其他字符都小（更多信息）。
//
//
//
//
// 提示：
//
//
// 1 <= words.length <= 100
// 1 <= words[i].length <= 20
// order.length == 26
// 在 words[i] 和 order 中的所有字符都是英文小写字母。
//
//
//
//
//
// 注意：本题与主站 953 题相同： https://leetcode-cn.com/problems/verifying-an-alien-
//dictionary/
//
// Related Topics 数组 哈希表 字符串 👍 50 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 用数组很快
 * 简化写法
 */
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] hashMap = new int[26];
        for (int i = 0; i < order.length(); i++) {
            hashMap[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                if (hashMap[words[i].charAt(j) - 'a'] > hashMap[words[i + 1].charAt(j) - 'a']) {
                    return false;
                } else if (hashMap[words[i].charAt(j) - 'a'] < hashMap[words[i + 1].charAt(j) - 'a']) {
                    break;
                    // 到这里一定是j位置相等的
                } else if (j == Math.min(words[i].length(), words[i + 1].length()) - 1 && words[i].length() > words[i + 1].length()) {
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * Success1
 * 逐一单词比较
 */
//class Solution {
//    public boolean isAlienSorted(String[] words, String order) {
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < order.length(); i++) {
//            hashMap.put(order.charAt(i), i);
//        }
//        for (int i = 0; i < words.length - 1; i++) {
//            // 标记一下是否为break的情况
//            boolean temp = true;
//            for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
//                if (hashMap.get(words[i].charAt(j)) > hashMap.get(words[i + 1].charAt(j))) {
//                    return false;
//                } else if (hashMap.get(words[i].charAt(j)) < hashMap.get(words[i + 1].charAt(j))) {
//                    temp = false;
//                    break;
//                } else {
//                    continue;
//                }
//            }
//            if (temp && words[i].length() > words[i + 1].length()) {
//                return false;
//            }
//        }
//        return true;
//    }
//}

/**
 * Failure1
 * 不能按照第i个字母逐一比较，只能逐个word比较
 * 而且应该是找是否有false，如果没有最后返回true，而不能在局部返回true
 */
//class Solution {
//    public boolean isAlienSorted(String[] words, String order) {
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < order.length(); i++) {
//            hashMap.put(order.charAt(i), i);
//        }
//        for (int i = 0; i < words[words.length - 1].length(); i++) {
//            for (int j = 1; j < words.length; j++) {
//                if (words[j - 1].length() > i && words[j].length() > i) {
//                    if (hashMap.get(words[j - 1].charAt(i)) > hashMap.get(words[j].charAt(i))) {
//                        return false;
//                    } else if (hashMap.get(words[j - 1].charAt(i)) < hashMap.get(words[j].charAt(i))) {
//                        return true;
//                    }
//                } else if (words[j - 1].length() <= i && words[j].length() > i) {
//                    return false;
//                } else if (words[j - 1].length() > i && words[j].length() <= i) {
//                    return true;
//                } else {
//                    continue;
//                }
//
//            }
//        }
//        return true;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
