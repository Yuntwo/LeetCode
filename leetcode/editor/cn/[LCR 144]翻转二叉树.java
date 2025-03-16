//给定一棵二叉树的根节点 root，请左右翻转这棵二叉树，并返回其根节点。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [5,7,9,8,3,2,4]
//输出：[5,9,7,4,2,3,8]
//
//
//
//
// 提示：
//
//
// 树中节点数目范围在 [0, 100] 内
// -100 <= Node.val <= 100
//
//
//
//
// 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/
//
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 383 👎 0


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

import java.util.LinkedList;

/**
 * Success2
 * 广度遍历，循环解法。队列是个好东西，并且天然地保留了原二叉树的层级关系
 */
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            queue.add(node.left);
            queue.add(node.right);
        }
        return root;
    }
}

/**
 * Success1
 * 由于深度遍历一遍二叉树也需要递归，所以循环解法也还是递归解法
 * 注意题目中的镜像是每一个节点关于自身中心对称翻转，而不是都关于根节点中心对称翻转
 */
//class Solution {
//    public TreeNode mirrorTree(TreeNode root) {
//        if (root == null) {
//            return null;
//        }
//        // 实际的节点操作
//        TreeNode temp = root.left;
//        root.left = root.right;
//        root.right = temp;
//        mirrorTree(root.left);
//        mirrorTree(root.right);
//        return root;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
