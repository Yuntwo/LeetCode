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
 * 排序二叉树本身的数字大小就包含了结构信息，所以更容易处理
 */
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        regBuild(root, preorder, 1, preorder.length - 1);
        return root;
    }

    private void regBuild(TreeNode root, int[] preorder, int start, int end) {
        int p = start;
        while (p <= end && preorder[p] < root.val) {
            p++;
        }
        if (p > start) {
            root.left = new TreeNode(preorder[start]);
            regBuild(root.left, preorder, start + 1, p - 1);
        }
        if (p <= end) {
            root.right = new TreeNode(preorder[p]);
            regBuild(root.right, preorder, p + 1, end);
        }
    }
}
