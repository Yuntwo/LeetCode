//给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
//
// 节点 node 的子树为 node 本身，以及所有 node 的后代。
//
//
//
// 示例 1:
//
//
//输入: [1,null,0,0,1]
//输出: [1,null,0,null,1]
//解释:
//只有红色节点满足条件“所有不包含 1 的子树”。
//右图为返回的答案。
//
//
//
//
// 示例 2:
//
//
//输入: [1,0,1,0,0,0,1]
//输出: [1,null,1,null,1]
//解释:
//
//
//
//
// 示例 3:
//
//
//输入: [1,1,0,1,1,0,1,0]
//输出: [1,1,0,1,1,null,1]
//解释:
//
//
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [1,200]
// 二叉树节点的值只会是 0 或 1
//
//
//
//
//
// 注意：本题与主站 814 题相同：https://leetcode-cn.com/problems/binary-tree-pruning/
//
// Related Topics 树 深度优先搜索 二叉树 👍 67 👎 0
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
 * Success1
 * 递归解法，时间效率已经很高了
 * 空间效率是动态变化的，参考性不是很大，还是得看时间复杂度
 * 注意理解题意，所有结点的值为0的子树意思是这个要被剪去的子树的所有节点的值都是0
 */
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        prune(root);
        // 注意对递归后的root还要判断一次剪枝，这是剃光了的情况
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    private void prune(TreeNode node) {
        // 没有子树的节点，递归返回
        if (node.left == null && node.right == null) {
            return;
        }
        // 注意不能用else if，否则只会递归一个分支
        if (node.left != null) {
            // 从叶结点直接剪枝返回，否则继续递归
            if (node.left.val == 0 && node.left.left == null && node.left.right == null) {
                node.left = null;
            } else {
                prune(node.left);
                // 注意递归回来之后还要判断一次剪枝
                if (node.left.val == 0 && node.left.left == null && node.left.right == null) {
                    node.left = null;
                }
            }
        }
        // 注意不能用else if，否则只会递归一个分支
        if (node.right != null) {
            // 从叶结点直接剪枝返回，否则继续递归
            if (node.right.val == 0 && node.right.left == null && node.right.right == null) {
                node.right = null;
            } else {
                prune(node.right);
                // 注意递归回来之后还要判断一次剪枝
                if (node.right.val == 0 && node.right.left == null && node.right.right == null) {
                    node.right = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1, new TreeNode(0, new TreeNode(0), new TreeNode(0)), new TreeNode(1, new TreeNode(0), new TreeNode(1)));
        TreeNode res = solution.pruneTree(root);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
