import java.util.*;
//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
//
//
// 示例 2：
//
//
//
//
//输入：root = [1,2,3], targetSum = 5
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1,2], targetSum = 0
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点总数在范围 [0, 5000] 内
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000
//
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 347 👎 0


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

// 注意审题是根结点到叶结点的路径
class Solution {

    private List<List<Integer>> res = new ArrayList<List<Integer>>();

    private List<Integer> nowPath = new ArrayList<Integer>();

    private int nowPathNum = 0;

    private int target;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        this.target = target;
        regPathSum(root);
        return res;
    }

    private void regPathSum(TreeNode nowNode) {

        // 注意根据题意结点的值有可能是负数

        // 注意此时叶结点的值还没有加入到nowPathNum中，要先加
        // 注意不管是叶结点还是非叶结点，都先将它加入路径之后再进行相应的判断
        nowPathNum += nowNode.val;
        nowPath.add(nowNode.val);

        // 到叶结点的返回情况
        if (nowNode.left == null && nowNode.right == null) {
            // 如果等于则添加这样一条路径
            // 注意由于ArrayList的add实际添加的是指向该对象的地址，所以你这里要进行深拷贝
//                res.add(nowPath);
            if (nowPathNum == target) {
                List<Integer> ans = new ArrayList<Integer>();
                for (int i = 0; i < nowPath.size(); i++) {
                    ans.add(nowPath.get(i));
                }
                res.add(ans);
            }

            // 但是注意不管此叶结点的这条路径是不是想要的，在叶结点返回时都要把叶结点从路径中删去
            // 注意java动态数组ArrayList没有规定-1索引
            int temp = nowPath.remove(nowPath.size() - 1).intValue();
            nowPathNum -= temp;
            return;
        }

        // 到非叶结点的递归处理
        // 由于结点的值和target可能是负数，所以条件不一定是小于，只要不等于就继续递归
        // 但其实如果刚好是等于也要递归，因为这不是到非叶结点的路径
        // 当然主要是因为结点值不是正整数，不具备路径总和单调递增的隐性条件
        // 所以是非叶结点都要没有条件的一直递归下去
        // 当然在这里可以进行题目变化
//        if (nowPathNum < target) {
            // 注意非叶结点有三种分支情况，你要控制非空访问
        if (nowNode.left != null) {
            regPathSum(nowNode.left);
        }
        if (nowNode.right != null) {
            regPathSum(nowNode.right);
        }

        // 非叶结点不递归返回的情况
        // 注意在递归访问完nowNode的左右节点不在继续执行其它语句返回回溯时，要从nowPath中删除nowNode的痕迹
        int temp = nowPath.remove(nowPath.size() - 1).intValue();
        nowPathNum -= temp;
        return;
    }

    public static void main(String[] args) {
        TestSolution solution = new TestSolution();
        // 测试用例1
        TreeNode root = new TreeNode(5);
        TreeNode temp = root;
        temp.left = new TreeNode(4);
        temp.right = new TreeNode(8);
        temp = root.left;
        temp.left = new TreeNode(11);
        temp = temp.left;
        temp.left = new TreeNode(7);
        temp.right = new TreeNode(2);
        temp = root.right;
        temp.left = new TreeNode(13);
        temp.right = new TreeNode(4);
        temp = temp.right;
        temp.left = new TreeNode(5);
        temp.right = new TreeNode(1);
        solution.pathSum(root, 22);
        // 测试用例2
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        solution.pathSum(root, 0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
