import java.util.LinkedList;

//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
//
// 假设二叉树中至少有一个节点。
//
//
//
// 示例 1:
//
//
//
//
//输入: root = [2,1,3]
//输出: 1
//
//
// 示例 2:
//
//
//
//
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [1,10⁴]
//
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
//
//
//
// 注意：本题与主站 513 题相同： https://leetcode-cn.com/problems/find-bottom-left-tree-
//value/
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 39 👎 0
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
 * Not very time efficient
 * 还得层序遍历
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> height = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        int res = 0;
        queue.add(root);
        int maxHeight = 0;
        height.add(1);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            int hi = height.poll();
            // If it's the first node in a new higher layer
            if (hi == (maxHeight + 1)) {
                maxHeight++;
                res = temp.val;
            }
            if (temp.left != null) {
                queue.add(temp.left);
                height.add(hi + 1);
            }
            if (temp.right != null) {
                queue.add(temp.right);
                height.add(hi + 1);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
