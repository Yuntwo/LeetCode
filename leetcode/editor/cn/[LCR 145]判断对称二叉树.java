//请设计一个函数判断一棵二叉树是否 轴对称 。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [6,7,7,8,9,9,8]
//输出：true
//解释：从图中可看出树是轴对称的。
//
// 示例 2：
//
//
//
//
//输入：root = [1,2,2,null,3,null,3]
//输出：false
//解释：从图中可看出最后一层的节点不对称。
//
//
//
// 提示：
//
// 0 <= 节点个数 <= 1000
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
//
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 477 👎 0


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
 * Success2
 * 迭代法
 * 对于每一对节点，比较它们的值，并将它们的子节点以相反的顺序插入两个队列中。
 * 这样，当队列中的节点被依次取出比较时，每一对节点都应该是相等的，如果不是，则意味着树不对称。这个过程会持续进行，直到队列为空。
 * 如果所有比较都相等，则树是对称的。这种迭代方法的关键在于始终以相同的顺序比较左右子树的节点。
 * 实际用一个队列也够了
 */
class Solution {
    public boolean checkSymmetricTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}

/**
 * Review1
 * 用递归关键是定义好递归问题，参数及解及其含义
 */
//class Solution {
//
//    public boolean checkSymmetricTree(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        return isSymmetric(root.left, root.right);
//    }
//
//    private boolean isSymmetric(TreeNode left, TreeNode right) {
//        if (left == null && right == null) {
//            return true;
//        } else if (left != null && right != null) {
//            return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
//        } else {
//            return false;
//        }
//    }
//
//}

/**
 * Success1
 * 递归解法
 * 前序遍历序列等于后序遍历序列理解起来挺绕的，就理解是所有节点中心对称很直观
 * 注意对称二叉树是所有节点都关于中心对称；翻转二叉树是所有节点都关于自身中心对称翻转
 * 注意对称二叉树!=原二叉树与其翻转二叉树中心对称
 */
//class Solution {
//    public boolean checkSymmetricTree(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        return isSymmetric(root.left, root.right);
//    }
//
//    private boolean isSymmetric(TreeNode left, TreeNode right) {
//        if (left == null && right == null) {
//            return true;
//        }
//        // 这样写很优雅
//        if (left == null || right == null || left.val != right.val) {
//            return false;
//        }
//        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
//    }
//
//}


//leetcode submit region end(Prohibit modification and deletion)
