//请完成一个函数，输入一个二叉树，该函数输出它的镜像。
//
// 例如输入：
//
// 4
// / \
// 2 7
// / \ / \
//1 3 6 9
//镜像输出：
//
// 4
// / \
// 7 2
// / \ / \
//9 6 3 1
//
//
//
// 示例 1：
//
// 输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
//
//
//
//
// 限制：
//
// 0 <= 节点个数 <= 1000
//
// 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 219 👎 0


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
 * 递归解法，写得稍冗余
 */
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        // 注意root为null的情况，不能访用它的left和right，这和mirr的递归判断null逻辑不同
        if (root == null) {
            return root;
        }
        mirr(root);
        return root;
    }

    private void mirr(TreeNode root) {
        // 注意这是底层返回情况
        if (root.left == null && root.right == null) {
            return;
        }
        // 这里交换是不是null都不影响
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 注意这是根据分支结构的不同递归情况
        if (root.left != null & root.right != null) {
            mirr(root.left);
            mirr(root.right);
        } else if (root.left == null) {
            mirr(root.right);
        } else {
            mirr(root.left);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
