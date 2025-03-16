//给你一个二叉树的根节点 root ， 检查它是否轴对称。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,2,3,4,4,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [1,2,2,null,3,null,3]
//输出：false
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [1, 1000] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 2616 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

public class TreeNode {

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
 * Success3
 * 迭代解法，用一个队列就够了，当然效率没有明显提高
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode temp1 = queue.poll();
            TreeNode temp2 = queue.poll();
            // 这样的层级处理比较好，不要用开放的else if
            if (temp1 == null && temp2 == null) {
                continue;
            }
            if (temp1 == null || temp2 == null) {
                return false;
            }
            if (temp1.val != temp2.val) {
                return false;
            }
            queue.add(temp1.left);
            queue.add(temp2.right);
            queue.add(temp1.right);
            queue.add(temp2.left);
        }
        return true;
    }
}


/**
 * Success2
 * 迭代解法
 */
//class Solution {
//    public boolean isSymmetric(TreeNode root) {
//        LinkedList<TreeNode> queue1 = new LinkedList<>();
//        LinkedList<TreeNode> queue2 = new LinkedList<>();
//        queue1.add(root.left);
//        queue2.add(root.right);
//        while (!queue1.isEmpty()) {
//            TreeNode temp1 = queue1.poll();
//            TreeNode temp2 = queue2.poll();
//            if (temp1 != null && temp2 != null) {
//                if (temp1.val != temp2.val) {
//                    return false;
//                }
//                queue1.add(temp1.left);
//                queue1.add(temp1.right);
//                queue2.add(temp2.right);
//                queue2.add(temp2.left);
//            } else if (temp1 == null && temp2 == null) {
//                continue;
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }
//}

/**
 * Success1
 * 递归解法，已经最优了
 * 但是注意这里的子问题是不是子节点也轴对称，都不是同一个轴
 */
//class Solution {
//    public boolean isSymmetric(TreeNode root) {
//        return isSymmetric(root.left, root.right);
//    }
//
//    private boolean isSymmetric(TreeNode left, TreeNode right) {
//        if (left == null && right == null) {
//            return true;
//        } else if (left == null || right == null) {
//            return false;
//        } else if (left.val != right.val || !isSymmetric(left.left, right.right) || !isSymmetric(left.right, right.left)) {
//            return false;
//        }
//        return true;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
