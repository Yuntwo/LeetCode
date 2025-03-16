//完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2ⁿ⁻¹ 个节点）的，并且所有的节点都尽可能地集中在左侧。
//
// 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
//
//
// CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
// CBTInserter.insert(int v) 向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的
//新节点的父节点的值；
// CBTInserter.get_root() 将返回树的根节点。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
//输出：[null,1,[1,2]]
//
//
// 示例 2：
//
//
//输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,
//5,6]],[7],[8],[]]
//输出：[null,3,4,[1,2,3,4,5,6,7,8]]
//
//
//
//
// 提示：
//
//
// 最初给定的树是完全二叉树，且包含 1 到 1000 个节点。
// 每个测试用例最多调用 CBTInserter.insert 操作 10000 次。
// 给定节点或插入节点的每个值都在 0 到 5000 之间。
//
//
//
//
//
// 注意：本题与主站 919 题相同： https://leetcode-cn.com/problems/complete-binary-tree-
//inserter/
//
// Related Topics 树 广度优先搜索 设计 二叉树 👍 50 👎 0

import java.util.LinkedList;
import java.util.Queue;

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
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

/**
 * Solution1
 * Queue
 * Inefficient in time and space
 */
//class CBTInserter {
//
//    private TreeNode root;
//
//    private TreeNode toInsertNode;
//
//    private TreeNode searchInsertNode() {
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode temp = queue.poll();
//            if (temp.left == null || temp.right == null) {
//                toInsertNode = temp;
//                break;
//            }
//            queue.add(temp.left);
//            queue.add(temp.right);
//        }
//        return toInsertNode;
//    }
//
//    public CBTInserter(TreeNode root) {
//        this.root = root;
//        toInsertNode = searchInsertNode();
//    }
//
//    private Queue<TreeNode> queue = new LinkedList<>();
//
//    /**
//     * 插入的节点其实就是第一个非满左右子节点父节点
//     */
//    public int insert(int v) {
//        int res = toInsertNode.val;
//        if (toInsertNode.left == null) {
//            toInsertNode.left = new TreeNode(v);
//        } else {
//            // This Node can no longer be inserted
//            toInsertNode.right = new TreeNode(v);
//            toInsertNode = searchInsertNode();
//        }
//        return res;
//    }
//
//    public TreeNode get_root() {
//        return this.root;
//    }
//}

/**
 * Solution2
 * 改进的队列，不用每一次insert都去查找下一个要插入的节点位置，直接用队列信息即可
 * Much faster, but not less space
 */
class CBTInserter {

    private TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
        TreeNode toInsertNode = null;
        // Find the first insert node
        queue.add(root);
        while (toInsertNode == null) {
            // Here we don't poll first
            TreeNode temp = queue.peek();
            if (temp.left == null || temp.right == null) {
                toInsertNode = temp;
                // Or we will miss a node
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                // we must break here or it will be polled
                break;
            }
            queue.add(temp.left);
            queue.add(temp.right);
            queue.poll();
        }
        // All sequece node has been added to queue
    }

    private Queue<TreeNode> queue = new LinkedList<>();

    /**
     * 队头就是要插入的节点
     */
    public int insert(int v) {
        // At first we don't poll the head
        TreeNode toInsertNode = queue.peek();
        TreeNode newNode = new TreeNode(v);
        if (toInsertNode.left == null) {
            toInsertNode.left = newNode;
        } else {
            toInsertNode.right = newNode;
            // We should change the head only under this condition
            queue.poll();
        }
        // Add new node to insert queue
        queue.add(newNode);
        return toInsertNode.val;
    }

    public TreeNode get_root() {
        return this.root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        CBTInserter cbtInserter = new CBTInserter(root);
        int res = cbtInserter.insert(3);
        res = cbtInserter.insert(4);
        System.out.println(root);
    }
}
/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
//leetcode submit region end(Prohibit modification and deletion)
