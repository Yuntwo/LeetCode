//给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
//
//
//
// 每条从根节点到叶节点的路径都代表一个数字：
//
//
//
//
// 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
//
//
// 计算从根节点到叶节点生成的 所有数字之和 。
//
// 叶节点 是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：25
//解释：
//从根到叶子节点路径 1->2 代表数字 12
//从根到叶子节点路径 1->3 代表数字 13
//因此，数字总和 = 12 + 13 = 25
//
// 示例 2：
//
//
//输入：root = [4,9,0,5,1]
//输出：1026
//解释：
//从根到叶子节点路径 4->9->5 代表数字 495
//从根到叶子节点路径 4->9->1 代表数字 491
//从根到叶子节点路径 4->0 代表数字 40
//因此，数字总和 = 495 + 491 + 40 = 1026
//
//
//
//
// 提示：
//
//
// 树中节点的数目在范围 [1, 1000] 内
// 0 <= Node.val <= 9
// 树的深度不超过 10
//
//
//
//
//
// 注意：本题与主站 129 题相同： https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
//
//
// Related Topics 树 深度优先搜索 二叉树 👍 52 👎 0

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
 * Success1
 * 只能是在遍历的过程中加结果，而不能是通过递归返回结果这样的一种关系
 */
class Solution {

    private int res = 0;

    private int curNum = 0;

    public int sumNumbers(TreeNode root) {
        reg(root);
        return res;
    }

    private void reg(TreeNode root) {
        // 注意这是单独算的，null节点对结果没有贡献，直接返回，不参加后续计算
        if (root == null) {
            return;
        }
        int bit = countBit(root.val);
        curNum = curNum * pow(10, bit) + root.val;
        // 这是叶结点，额外需要做的事就是加入结果
        // 注意判断叶节点不能保证不传入null
        if (root.left == null && root.right == null) {
            res += curNum;
        // 不是叶结点的话就要继续递归
        } else {
            reg(root.left);
            reg(root.right);
        }
        // 这是在当天结点要返回时必须做的，否则这条路径就重了
        curNum /= pow(10, bit);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(4, new TreeNode(9, new TreeNode(5), new TreeNode(1)), new TreeNode(0));
        int res = solution.sumNumbers(root);
        System.out.println(res);
    }

    private int countBit(int num) {
        // 注意0也要占一个bit
        int res = 1;
        while ((num / 10) != 0) {
            num /= 10;
            res++;
        }
        return res;
    }

    private int pow(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
        }
        return res;
    }

}

/**
 * Failure1
 * 注意题目结构，这种递归结果关系不成立
 */
//class Solution {
//
//    public int sumNumbers(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int res = 0;
//        int res1 = sumNumbers(root.left);
//        res = root.val * pow(10, countBit(res1)) + res1 + res;
//        int res2 = sumNumbers(root.right);
//        res = root.val * pow(10, countBit(res2)) + res2 + res;
//        return res;
//    }
//
//    private int countBit(int num) {
//        int res = 0;
//        while (num!= 0) {
//            num /= 10;
//            res++;
//        }
//        return res;
//    }
//
//    private int pow(int a, int b) {
//        int res = 1;
//        for (int i = 0; i < b; i++) {
//            res *= a;
//        }
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
