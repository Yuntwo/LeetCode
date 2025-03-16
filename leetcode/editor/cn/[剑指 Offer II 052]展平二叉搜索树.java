//给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
//
//
// 示例 2：
//
//
//
//
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
//
//
//
//
// 提示：
//
//
// 树中节点数的取值范围是 [1, 100]
// 0 <= Node.val <= 1000
//
//
//
//
//
// 注意：本题与主站 897 题相同： https://leetcode-cn.com/problems/increasing-order-search-
//tree/
//
// Related Topics 栈 树 深度优先搜索 二叉搜索树 二叉树 👍 57 👎 0

import java.util.LinkedList;

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
 * 时间效率和空间效率已经很高了
 * 虽然用了额外的队列，但是不知道是否有不用额外空间的方法
 * 代码应该还可以写得更简洁
 */
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        midSearch(root);
        TreeNode res = nodes.poll();
        res.left = null;
        TreeNode node = res;
        while (!nodes.isEmpty()) {
            TreeNode temp = nodes.poll();
            temp.left = null;
            node.right = temp;
            node = temp;
        }
        return res;
    }

    private LinkedList<TreeNode> nodes = new LinkedList<>();

    private void midSearch(TreeNode node) {
        if (node == null) {
            return;
        }
        midSearch(node.left);
        nodes.add(node);
        midSearch(node.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
