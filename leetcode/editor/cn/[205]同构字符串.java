//给定两个字符串 s 和 t ，判断它们是否是同构的。
//
// 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
//
// 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
//
//
//
// 示例 1:
//
//
//输入：s = "egg", t = "add"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "foo", t = "bar"
//输出：false
//
// 示例 3：
//
//
//输入：s = "paper", t = "title"
//输出：true
//
//
//
// 提示：
//
//
//
//
//
// 1 <= s.length <= 5 * 10⁴
// t.length == s.length
// s 和 t 由任意有效的 ASCII 字符组成
//
//
// Related Topics 哈希表 字符串 👍 537 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
// 注意题目这里的映射应该是单向映射，就是说a->b不一定要求b->a
// 其实感觉更多的不是强调s和t的映射关系，而是s和t它们本身字符的同与不同关系
// 这种规律掌握得不精炼，就是一个一个试例子然后排除就没有意思了
//class Solution {
//    public boolean isIsomorphic(String s, String t) {
//        HashMap<Character, Character> map = new HashMap<Character, Character>();
//        for (int i = 0; i < s.length(); i++) {
//            if (!map.containsKey(s.charAt(i))) {
//                if (map.containsKey(t.charAt(i)) && (map.get(t.charAt(i)).equals(t.charAt(i)))) {
//                    return false;
//                } else if (s.charAt(i) == t.charAt(i) && map.containsValue(t.charAt(i))) {
//                    return false;
//                } else {
//                    map.put(s.charAt(i), t.charAt(i));
//                }
//            } else {
//                if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        boolean res = solution.isIsomorphic("badc", "baba");
////        boolean res = solution.isIsomorphic("paper", "title");
//        boolean res = solution.isIsomorphic("egcd", "adfd");
//        System.out.println(res);
//    }
//}
// 其实字符串的题做起来都挺无聊的
// 这样的概括是精炼全面的
//class Solution {
//    public boolean isIsomorphic(String s, String t) {
//        HashMap<Character, Character> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            // 只有这种情况才会加入新的map
//            if (!(map.containsKey(s.charAt(i)) || map.containsValue(t.charAt(i)))) {
//                map.put(s.charAt(i), t.charAt(i));
//            // 除了当前既含key又含value并且是对应的情况，才是正确的，其它三种情况都是错误的，分别是
//            } else if (!(map.containsKey(s.charAt(i)) && map.containsValue(t.charAt(i)) && map.get(s.charAt(i)).equals(t.charAt(i)))) {
//            // 1、既含key又含value，但它们是错位不对应的
//            // 2、只含key，意味着当前的value没有在map中，不对应
//            // 3、只含value，意味着当前的key没有在map中，不对应
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        boolean res = solution.isIsomorphic("badc", "baba");
////        boolean res = solution.isIsomorphic("paper", "title");
////        boolean res = solution.isIsomorphic("egcd", "adfd");
//        System.out.println(res);
//    }
//}
// 官方题解用两种Hash表把s->t和t->s的映射关系都存起来，时间和空间效率都更好
// Q:不太明白为啥用了两张Hash表空间效率还更好
//class Solution {
//    public boolean isIsomorphic(String s, String t) {
//        Map<Character, Character> s2t = new HashMap<Character, Character>();
//        Map<Character, Character> t2s = new HashMap<Character, Character>();
//        int len = s.length();
//        for (int i = 0; i < len; ++i) {
//            char x = s.charAt(i), y = t.charAt(i);
//            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
//                return false;
//            }
//            s2t.put(x, y);
//            t2s.put(y, x);
//        }
//        return true;
//    }
//}
// 大佬解法，抓住了异构的本质，是s和t的重复位置相同
// 关键在于只索引到第一次出现的位置即可唯一特殊代表这个字符
class Solution {
    public boolean isIsomorphic(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            // 注意这里indexOf应该传的是对应char的ASCLE码，但是不影响它这个函数的内涵功能
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
