//给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
//
//
// 示例 1:
//
//
//
//
//输入: [1,2,3,null,5,null,4]
//输出: [1,3,4]
//
//
// 示例 2:
//
//
//输入: [1,null,3]
//输出: [1,3]
//
//
// 示例 3:
//
//
//输入: []
//输出: []
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,100]
//
// -100 <= Node.val <= 100
//
//
//
//
//
// 注意：本题与主站 199 题相同：https://leetcode-cn.com/problems/binary-tree-right-side-
//view/
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 42 👎 0


import java.util.LinkedList;
import java.util.Map;

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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.AbstractMap;

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
 * Failure1
 * 注意这题的困难情景在于左子树高于右子树的情况
 */
//class Solution {
//    public List<Integer> rightSideView(TreeNode root) {
//        while (root != null) {
//            res.add(root.val);
//            root = root.right;
//        }
//        return res;
//    }
//
//    private ArrayList<Integer> res = new ArrayList<Integer>();
//}

/**
 * Success1
 * 效率不是特别高，可能数据结构有点冗余
 * 感觉其实是要层序遍历的
 */
class Solution {
//    public List<Integer> rightSideView(TreeNode root) {
//        LinkedList<Integer> res = new LinkedList<>();
//        if (root == null) {
//            return res;
//        }
//        LinkedList<Map.Entry<Integer, TreeNode>> queue = new LinkedList<>();
//        queue.add(new AbstractMap.SimpleEntry<>(1, root));
//        while (!queue.isEmpty()) {
//            Map.Entry<Integer, TreeNode> temp = queue.poll();
//            if (temp.getValue().left != null) {
//                queue.add(new AbstractMap.SimpleEntry<>(temp.getKey() + 1, temp.getValue().left));
//            }
//            if (temp.getValue().right != null) {
//                queue.add(new AbstractMap.SimpleEntry<>(temp.getKey() + 1, temp.getValue().right));
//            }
//            // 注意分两种情况，层序遍历最后一个节点一定是右侧视图
//            if (queue.isEmpty() || queue.peek().getKey() == (temp.getKey() + 1)) {
//                res.add(temp.getValue().val);
//            }
//        }
//        return res;
//    }


    /**
     * 换一种数据结构，效率还是没有提升，应该就是算法逻辑而不是数据结构的问题了
     */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> treeNodeQueue = new LinkedList<>();
        LinkedList<Integer> heightQueue = new LinkedList<>();
        treeNodeQueue.add(root);
        heightQueue.add(1);
        while (!treeNodeQueue.isEmpty()) {
            TreeNode temp = treeNodeQueue.poll();
            int height = heightQueue.poll();
            if (temp.left != null) {
                treeNodeQueue.add(temp.left);
                heightQueue.add(height + 1);
            }
            if (temp.right != null) {
                treeNodeQueue.add(temp.right);
                heightQueue.add(height + 1);
            }
            // 注意分两种情况，层序遍历最后一个节点一定是右侧视图
            if (treeNodeQueue.isEmpty() || heightQueue.peek() == (height + 1)) {
                res.add(temp.val);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
