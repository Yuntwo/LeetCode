//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
//
// 示例 1:
//
//
//输入：s = "abaccdeff"
//输出：'b'
//
//
// 示例 2:
//
//
//输入：s = ""
//输出：' '
//
//
//
//
// 限制：
//
// 0 <= s 的长度 <= 50000
//
// Related Topics 队列 哈希表 字符串 计数 👍 270 👎 0


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
// 从信息的角度来说，结果需要全局信息，所以至少需要遍历一遍
// 这样效率是有点低
//class Solution {
//    public char firstUniqChar(String s) {
//        // 注意char也是基本数据类型，它的包装类是Character
//        // 注意基本类型及其包装类默认相互装换，不需要额外操作
//        // 注意由于HashMap根据char自动排序了，所以不能保证第一个，要用LinkedMap
////        HashMap<Character, Integer> map = new HashMap<>();
//        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
//        // 注意String其实就是一个类而不是数组，所以获取它的长度不能用数组的length，而是用方法length()
//        for (int i = 0; i < s.length(); i++) {
//            if (map.containsKey(s.charAt(i))) {
//                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
//            } else {
//                map.put(s.charAt(i), 1);
//            }
//        }
//        // 其实第二遍按s遍历就可以了，还不用要求map是原序放置的，也不用迭代器这么麻烦
//        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            // 注意这个Entry接口
//            Map.Entry<Character, Integer> entry = iterator.next();
//            if (entry.getValue() == 1) {
//                return entry.getKey();
//            }
//            System.out.println("key:" + entry.getKey() + ",vaule:" + entry.getValue());
//        }
//        // 注意在Java中char用''，String用" "；
//        return ' ';
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        char result = solution.firstUniqChar("leetcode");
//        System.out.println(result);
//    }
//}
// 遍历两遍似乎是必须的
// 注意对于字符串处理来说，用c-'a'建立它的索引及hash映射非常方便
class Solution {
    public char firstUniqChar(String s) {
        int[] freq = new int[26];
        // 注意String也有这种写法
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char result = solution.firstUniqChar("leetcode");
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
