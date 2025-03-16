//输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
//
//
//
// 示例 1:
//
// 给定二叉树 [3,9,20,null,null,15,7]
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回 true 。 示例 2:
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4]
//
//
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
//
//
// 返回 false 。
//
//
//
// 限制：
//
//
// 0 <= 树的结点个数 <= 10000
//
//
// 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
//
//
//
// Related Topics 树 深度优先搜索 二叉树 👍 338 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Definition for a binary tree node.
 * 注意定义为public的class对应的文件名也得类名
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Success1
 * 效率已经很高了
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        // 不要漏了这种边界条件，感觉但凡是出这种题，一定会设置这个测试用例的
        if (root == null) {
            return true;
        }
        height(root);
        return isBal;
    }

    private boolean isBal = true;

    private int height(TreeNode node) {
        // 如果已经发现不平衡了，没必要继续递归计算了
        if (!isBal) {
            return -1;
        }
        // 叶节点的高度是1
        if (node.left == null && node.right == null) {
            return 1;
        }
        // 非叶结点至少有一个孩子，孩子的高度至少为1
        int maxChildHeight = 1;
        if (node.left != null && node.right == null) {
            int leftHeight = height(node.left);
            if (leftHeight > 1) {
                isBal = false;
            }
            maxChildHeight = Math.max(maxChildHeight, leftHeight);
        } else if (node.right != null && node.left == null) {
            int rightHeight = height(node.right);
            if (rightHeight > 1) {
                isBal = false;
            }
            maxChildHeight = Math.max(maxChildHeight, rightHeight);
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                isBal = false;
            }
            maxChildHeight = Math.max(leftHeight, rightHeight);
        }
        // 当前节点的高度等于孩子的最大高度+1
        return maxChildHeight + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
