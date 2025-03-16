//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
//
// 示例 2：
//
//
//输入：n = 1, k = 1
//输出：[[1]]
//
//
//
// 提示：
//
//
// 1 <= n <= 20
// 1 <= k <= n
//
//
// Related Topics 回溯 👍 1622 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Review4
 */
class Solution {

    private List<Integer> cur = new ArrayList<>();

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1);
        return res;
    }

    private void dfs(int n, int k, int index) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i <= n; i++) {
            cur.add(i);
            dfs(n, k, i+1);
            cur.remove(cur.size() - 1);
        }
    }

}

/**
 * Review3
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    private List<Integer> cur = new ArrayList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        dfs(n, k, 1);
//        return res;
//    }
//
//    private void dfs(int n, int k, int index) {
//        if (cur.size() == k) {
//            res.add(new ArrayList(cur));
//            // 注意这里的return
//            return;
//        }
//        for (int i = index; i <= n - k + cur.size() + 1; i++) {
//            cur.add(i);
//            dfs(n, k, i+1);
//            cur.remove(cur.size() - 1);
//        }
//    }
//
//}

/**
 * Review2
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        recursion(n, k, 1, new ArrayList<>());
//        return res;
//    }
//
//    private void recursion(int n, int k, int index, List<Integer> cur) {
//        if (cur.size() == k) {
//            res.add(new ArrayList<>(cur));
//            return;
//        }
//        for (int i = index; i <= n - k + cur.size() + 1; i++) {
//            cur.add(i);
//            recursion(n, k, i + 1, cur);
//            cur.remove(cur.size() - 1);
//        }
//    }
//
//}

/**
 * Review1
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        dfs(n, k, 1, new ArrayList<>());
//        return res;
//    }
//
//    /**
//     * 注意这里index表示从index开始及以后寻找Cn^k，这样可能会有重复
//     */
//    private void dfs(int n, int k, int index, List<Integer> cur) {
//        if(cur.size() == k) {
//            res.add(new ArrayList<>(cur));
//            return;
//        }
//        for (int i = index; i <= n - k + cur.size() + 1; i++) {
//            cur.add(i);
//            dfs(n, k, i + 1, cur);
//            cur.remove(cur.size() - 1);
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.combine(4, 2));
//    }
//
//}

/**
 * Success1
 * 经典回溯，时间复杂度比较高
 */
//class Solution {
//
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        dfs(n, k, new ArrayList<>(), 1);
//        return res;
//    }
//
//    /**
//     * 表示下一个索引位置为index时寻找Cn^k
//     *
//     * @param n 注意这里n、k直接用参数传递含义上比较好理解，也比this.方式更清晰
//     * @param k
//     * @param list 已经经过的索引构成的列表
//     * @param index 下一个索引位置(还未访问到)
//     */
//    private void dfs(int n, int k, List<Integer> list, int index) {
//        if (list.size() == k) {
//            // 注意这里要创建一个新的对象，否则原有对象总是被覆盖
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        // 注意这里不是单一递归，而是多层次递归
//        // 剪枝操作
//        for (int i = index; i <= n - (k - list.size()) + 1; i++) {
//            list.add(i);
//            dfs(n, k, list, i + 1);
//            list.remove(list.size() - 1);
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.combine(4, 2));
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
