//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
//
//
// 示例 2：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
//
//
//
//
// 提示:
//
//
// 二叉树的节点个数的范围是 [0,1000]
//
// -10⁹
// -1000 <= targetSum <= 1000
//
//
//
//
//
// 注意：本题与主站 437 题相同：https://leetcode-cn.com/problems/path-sum-iii/
//
// Related Topics 树 深度优先搜索 二叉树 👍 85 👎 0

import java.util.LinkedList;
import java.util.List;

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
 * Success1
 * 时间和空间效率都很低
 */
class Solution {

    /**
     * 还需要遍历一遍这个树，注意考察每个结点能否达到全局targetSum
     * 注意这里可能重复访问了在canReach函数递归时的结点，但不是冗余的，因为canReach函数在递归时考察的事能否达到局部targetSum
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 注意canReach中的判null和这里的判null作用不同，这里的判null不能省
        // 否则会出现null.left/right的问题，虽然canReach处理null没有问题
        // 注意queue.add(null)也是会占索引的，本质上是一个引用吧，总之不会实际没有改变
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            // 当然这样一个判null也够了
            if (temp == null) {
                continue;
            }
            res += canReach(temp, targetSum);
            queue.add(temp.left);
            queue.add(temp.right);
        }
        return res;
    }

    /**
     * 返回从某个结点达到某个值的路径数
     * 注意这里的targetNum必须存成long型，否则对于较大的数字计算容易溢出
     */
    private int canReach(TreeNode root, long targetSum) {
        // 注意如何定义为null的结点，认为它的贡献是0，只有当targetSum==0时才返回true
        // 通常把null也考虑进递归且赋予其合理的逻辑定义，然后将其作为递归的底层返回会使代码与逻辑更简洁！
        if (root == null) {
            // 注意null不能算targetNum为0的路径
            return 0;
        }
        int res = 0;
        // 注意由于结点值有正有负，所有下面这种情况不能终止查找
        if (root.val == targetSum) {
            res++;
        }
        res += canReach(root.left, targetSum - root.val);
        res += canReach(root.right, targetSum - root.val);
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        TreeNode root = new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1))), new TreeNode(-3, null, new TreeNode(11)));
//        int res = solution.pathSum(root, 8);
        // 注意这个算例的数字很大，溢出了
        TreeNode root = new TreeNode(715827882, new TreeNode(715827882, new TreeNode(715827882, new TreeNode(1, new TreeNode(715827882, new TreeNode(715827882, new TreeNode(715827882), null), null), null), null),null), null);
        int res = solution.pathSum(root, -3);
        System.out.println(res);
    }

}

/**
 * Fialure1
 * 注意由于从某个结点能否达到某个值不只1条路径，也不止2条路径，所以递归函数不能只返回boolean型，这信息和递归程度不够
 */
//class Solution {
//
//    /**
//     * 还需要遍历一遍这个树，注意考察每个结点能否达到全局targetSum
//     * 注意这里可能重复访问了在canReach函数递归时的结点，但不是冗余的，因为canReach函数在递归时考察的事能否达到局部targetSum
//     */
//    public int pathSum(TreeNode root, int targetSum) {
//        if (root == null) {
//            return 0;
//        }
//        int res = 0;
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        // 注意canReach中的判null和这里的判null作用不同，这里的判null不能省
//        // 否则会出现null.left/right的问题，虽然canReach处理null没有问题
//        // 注意queue.add(null)也是会占索引的，本质上是一个引用吧，总之不会实际没有改变
//        while (!queue.isEmpty()) {
//            TreeNode temp = queue.poll();
//            // 当然这样一个判null也够了
//            if (temp == null) {
//                continue;
//            }
//            if (canReach(temp, targetSum)) {
//                res++;
//            }
//            queue.add(temp.left);
//            queue.add(temp.right);
//        }
//        return res;
//    }
//
//    /**
//     * 这个函数表示从某个结点能否达到某个值
//     * 注意这里的错误点在于可能不止一条路径，只用boolean值的话就丢失了信息
//     */
//    private boolean canReach(TreeNode root, int targetSum) {
//        // 注意如何定义为null的结点，认为它的贡献是0，只有当targetSum==0时才返回true
//        // 通常把null也考虑进递归且赋予其合理的逻辑定义，然后将其作为递归的底层返回会使代码与逻辑更简洁！
//        if (root == null) {
//            return targetSum == 0;
//        }
//        if (root.val == targetSum) {
//            return true;
//        }
//        return canReach(root.left, targetSum - root.val) | canReach(root.right, targetSum - root.val);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        TreeNode root = new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1))), new TreeNode(-3, null, new TreeNode(11)));
//        int res = solution.pathSum(root, 8);
//        System.out.println(res);
//    }
//
//}
//leetcode submit region end(Prohibit modification and deletion)
