import java.util.*;
//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
//
//
//
// 例如:
//给定二叉树: [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其层次遍历结果：
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
//
//
//
//
// 提示：
//
//
// 节点总数 <= 1000
//
// Related Topics 树 广度优先搜索 二叉树 👍 233 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        int now = 1;
        int next = 0;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 双端队列，相当于两个栈，数据结构不同而已，但是空间效率应该是一样的
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null) {
            return res;
        } else {
            queue.add(root);
        }
        while (queue.size() != 0) {
            // 之字形左到右遍历一行，之前右进遍历右出
            List<Integer> ans1 = new ArrayList<Integer>();
            for (int i = 0; i < now; i++) {
                // 右出
                TreeNode temp = queue.pollLast();
                ans1.add(temp.val);
                if (temp.left != null) {
                    // 给下一行做准备，左进，注意先访问左节点!!!
                    queue.addFirst(temp.left);
                    next++;
                }
                if (temp.right != null) {
                    // 给下一行做准备，左进，注意再访问右节点!!!
                    queue.addFirst(temp.right);
                    next++;
                }
            }
            if (ans1.size() != 0) {
                res.add(ans1);
            } else {
                return res;
            }
            now = next;
            next = 0;
            // 之字形右到左遍历一行，之前左进遍历左出
            List<Integer> ans2 = new ArrayList<Integer>();
            for (int i = 0; i < now; i++) {
                // 左出
                TreeNode temp = queue.pollFirst();
                ans2.add(temp.val);
                if (temp.right != null) {
                    // 给下一行做准备，右进，注意先访问右节点!!!
                    queue.addLast(temp.right);
                    next++;
                }
                if (temp.left != null) {
                    // 给下一行做准备，右进，注意再访问左节点!!!
                    queue.addLast(temp.left);
                    next++;
                }
            }
            if (ans2.size() != 0) {
                res.add(ans2);
            } else {
                return res;
            }
            now = next;
            next = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        TestSolution solution = new TestSolution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        solution.levelOrder(root);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
