//给定一个二叉树的根节点 root ，返回它的 中序 遍历。
//
//
//
// 示例 1：
//
//
//输入：root = [1,null,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[2,1]
//
//
// 示例 5：
//
//
//输入：root = [1,null,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 100] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1299 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

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
 * 莫里斯遍历法
 * 其实对于任何深度遍历的任何一种遍历法，遍历和回溯路径都是一样的，只是记录节点的顺序不同，所以大体上代码结构都是一样的，只是细节差异
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    private void midSearch(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        midSearch(root.left, list);
        list.add(root.val);
        midSearch(root.right, list);
    }
}
/**
 * Success1
 * 递归法，基本上就是最标准的写法了
 */
//class Solution {
//    public List<Integer> inorderTraversal(TreeNode root) {
//        ArrayList<Integer> res = new ArrayList<Integer>();
//        if (root == null) {
//            return res;
//        }
//        midSearch(root, res);
//        return res;
//    }
//
//    private void midSearch(TreeNode root, ArrayList<Integer> list) {
//        if (root == null) {
//            return;
//        }
//        midSearch(root.left, list);
//        list.add(root.val);
//        midSearch(root.right, list);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
