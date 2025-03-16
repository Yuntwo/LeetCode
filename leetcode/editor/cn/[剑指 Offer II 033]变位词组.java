//给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
//
// 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
//
//
//
// 示例 1:
//
//
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2:
//
//
//输入: strs = [""]
//输出: [[""]]
//
//
// 示例 3:
//
//
//输入: strs = ["a"]
//输出: [["a"]]
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 10⁴
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
//
//
//
//
// 注意：本题与主站 49 题相同： https://leetcode-cn.com/problems/group-anagrams/
//
// Related Topics 数组 哈希表 字符串 排序 👍 56 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * Success1
     * 将count数组转为String做key
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        for (String s : strs) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            // 注意这个方法
            String bit = Arrays.toString(count);
            // 注意这里的containsKey方法能够识别不同String对象的相同值
            if (!hashMap.containsKey(bit)) {
                hashMap.put(bit, new ArrayList<>());
            }
            hashMap.get(bit).add(s);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> value : hashMap.values()) {
            res.add(value);
        }
        return res;
    }

    /**
     * Failure1
     * 注意只能对于出现或者不出现两种情况的值用bit键，如果一个值可能出现多次，用bit键会进位，表示就不唯一了
     */
//    public List<List<String>> groupAnagrams(String[] strs) {
//        HashMap<Integer, List<String>> hashMap = new HashMap<Integer, List<String>>();
//        for (String s : strs) {
//            int bit = 0;
//            for (int i = 0; i < s.length(); i++) {
//                bit += 1 << (s.charAt(i) - 'a');
//            }
//            if (!hashMap.containsKey(bit)) {
//                hashMap.put(bit, new ArrayList<>());
//            }
//            hashMap.get(bit).add(s);
//        }
//        List<List<String>> res = new ArrayList<>();
//        for (List<String> value : hashMap.values()) {
//            res.add(value);
//        }
//        return res;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
