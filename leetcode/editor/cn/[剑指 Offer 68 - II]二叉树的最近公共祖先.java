//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
//
//
//
//
//
// 示例 1:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//
//
// 示例 2:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
//
//
//
//
// 说明:
//
//
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。
//
//
// 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-
//a-binary-tree/
//
// Related Topics 树 深度优先搜索 二叉树 👍 528 👎 0
class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Success1
 * 时间效率很低
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 有其中一个节点本身就是祖先的话，直接返回root了
        if (root == p || root == q) {
            return root;
        }
        // p、q分别在root的两支的话最近公共祖先一定是root了，就可以返回了
        if ((has(root.left, p) && has(root.right, q)) || (has(root.left, q) && has(root.right, p))) {
            return root;
        // 都在root.left分支继续递归root.left
        } else if (has(root.left, p) && has(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        // 都在root.right分支继续递归root.right
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }


    private boolean has(TreeNode root, TreeNode node) {
        // 到null都还没有找到node那就找不到了
        if (root == null) {
            return false;
        }
        // 只要某个祖先含有node后代，那么node后代一定在路径中，那么在递归过程中一定会有这个
        if (root == node) {
            return true;
        }
        return has(root.left, node) || has(root.right, node);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        TreeNode temp = solution.lowestCommonAncestor(root, root.left, root.left.right.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
