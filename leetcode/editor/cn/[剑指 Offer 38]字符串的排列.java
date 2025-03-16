//输入一个字符串，打印出该字符串中字符的所有排列。
//
//
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
//
//
// 示例:
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
//
//
//
//
// 限制：
//
// 1 <= s 的长度 <= 8
//
// Related Topics 字符串 回溯 👍 615 👎 0


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 因为不确定答案有多少个结果，所以先用动态链表
    List<String> res = new LinkedList<>();
    // 这样来声明一个私有变量c用起来很方便
    // 由于它是String类型的返回值，所以用char数组来串联答案很方便
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        // 还是利用索引来定位
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    // 其实关键就是怎么来建DFS的模型
    void dfs(int x) {
        // dfs到最后一个字符就是函数底层返回的时候
        if (x == c.length - 1) {
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            // 重复，因此剪枝，不做任何操作
            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    void swap(int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
