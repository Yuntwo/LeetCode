//给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。 注意，空树 不会是以
//tree1 的某个节点为根的子树具有 相同的结构和节点值 。
//
//
//
// 示例 1：
//
//
//
//
//
//
//
//
//输入：tree1 = [1,7,5], tree2 = [6,1]
//输出：false
//解释：tree2 与 tree1 的一个子树没有相同的结构和节点值。
//
//
// 示例 2：
//
//
//
//
//输入：tree1 = [3,6,7,1,8], tree2 = [6,1]
//输出：true
//解释：tree2 与 tree1 的一个子树拥有相同的结构和节点值。即 6 - > 1。
//
//
//
// 提示：
//
// 0 <= 节点个数 <= 10000
//
// Related Topics 树 深度优先搜索 二叉树 👍 805 👎 0


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
 * Success1-plus
 * 可以同时调用两种递归函数，不同的逻辑，同样的递归路线
 */
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 注意这里相当于是(A != null && B != null && isSub(A, B))会短路后面的或操作，不能完全隔离(A != null && B != null)的情况
//        return A != null && B != null && isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        return (A != null && B != null) && (isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean isSub(TreeNode subA, TreeNode subB) {
        if (subB == null) {
            return true;
        }
        if (subA == null || subA.val != subB.val) {
            return false;
        }
        return isSub(subA.left, subB.left) && isSub(subA.right, subB.right);
    }
}

/**
 * Success1
 * 注意理解题意，区分B和B的子树
 * B的子树为null时B的子树是A的子树，这样方便逻辑判断；对于B整个子树来说，null不为A的子树，再次递归的时候也应该B这个根节点与A的子节点比较
 */
//class Solution {
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//        if (B == null) {
//            return false;
//        }
//        return isSub(A, B, B);
//    }
//
//    private boolean isSub(TreeNode subA, TreeNode subB, TreeNode B) {
//        if (subB == null) {
//            return true;
//        }
//        if (subA == null) {
//            return false;
//        }
//        if (subA.val == subB.val && isSub(subA.left, subB.left, B) && isSub(subA.right, subB.right, B)) {
//            return true;
//        }
//        return isSub(subA.left, B, B) || isSub(subA.right, B, B);
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
