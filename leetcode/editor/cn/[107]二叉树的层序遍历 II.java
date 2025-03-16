//给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[15,7],[9,20],[3]]
//
//
// 示例 2：
//
//
//输入：root = [1]
//输出：[[1]]
//
//
// 示例 3：
//
//
//输入：root = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 2000] 内
// -1000 <= Node.val <= 1000
//
//
// Related Topics 树 广度优先搜索 二叉树 👍 668 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * Review1
 * 普通的层序遍历最后结果倒一下，这样很直观且简单，不用想那么复杂
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        dfs(root, 0);
//        List<List<Integer>> ans = new ArrayList<>();
//        for (int i = res.size() - 1; i >= 0; i--) {
//            ans.add(res.get(i));
//        }
//        return ans;
//    }
//
//    private void dfs(TreeNode root, int level) {
//        if (root == null) {
//            return;
//        }
//        if (level > res.size() - 1) {
//            List<Integer> list = new ArrayList<>();
//            list.add(root.val);
//            res.add(list);
//        } else {
//            res.get(level).add(root.val);
//        }
//        dfs(root.left, level + 1);
//        dfs(root.right, level + 1);
//    }
//
//}

/**
 * Failure2
 * 还是不太明白怎么简化这个数据类型
 * 还得是掌握dfs的思路睡宽床，只需要在dfs的时候传递一个有关层级的变量即可
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        dfs(root, 1);
//        return res;
//    }
//
//    private void dfs(TreeNode node, int height) {
//        if (node == null) {
//            return;
//        }
//        dfs(node.left, height + 1);
//        dfs(node.right, height + 1);
//        if (height > res.size()) {
//            res.add(new ArrayList<>());
//        }
//        res.get(res.size() - 1).add(node.val);
//    }
//}
/**
 * Success1
 * 递归解法的话和102的递归解法差不多，只是节点访问顺序的先后不同而已
 * 在类型使用上有点取巧了
 */
//class Solution {
//
//    private LinkedList<List<Integer>> res = new LinkedList<>();
//
//    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        dfs(root, 1);
//        return res;
//    }
//
//    private void dfs(TreeNode node, int height) {
//        if (node == null) {
//            return;
//        }
//        if (height > res.size()) {
//            // 注意在加list的时候也要倒过来
//            // 注意但是这里的声明限制了List类型，不能用push方法，当然你最后转一下也是可以的，多少有点取巧了，可能不被允许
//            res.push(new ArrayList<>());
//        }
//        dfs(node.left, height + 1);
//        dfs(node.right, height + 1);
//        // 注意这里的-1是匹配索引，注意这里的索引也要倒过来
//        res.get(-height + res.size()).add(node.val);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
