import java.util.ArrayList;

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
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,null,5,1], targetSum = 22
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
//
//
//
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 457 👎 0

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
 * Review2
 * 一般来说值传递可以直接代入参数里不需要设全局变量否则回溯容易忘记修改原值
 */
class Solution {

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> cur = new ArrayList<>();

    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        dfs(root, target, 0);
        return res;
    }

    public void dfs(TreeNode root, int target, int sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        cur.add(root.val);
        if (root.left == null && root.right == null && sum == target) {
            res.add(new ArrayList<>(cur));
        }
        dfs(root.left, target, sum);
        dfs(root.right, target, sum);
        cur.remove(cur.size() - 1);
    }

}

/**
 * Review1
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    private List<Integer> cur = new ArrayList<>();
//
//    private int sum = 0;
//
//    public List<List<Integer>> pathTarget(TreeNode root, int target) {
//        dfs(root, target);
//        return res;
//    }
//
//    public void dfs(TreeNode root, int target) {
//        if (root == null) return;
//        sum += root.val;
//        cur.add(root.val);
//        if (root.left == null && root.right == null) {
//            if (sum == target) {
//                res.add(new ArrayList<>(cur));
//            }
//        }
//        dfs(root.left, target);
//        dfs(root.right, target);
//        // 注意这里有两个操作
//        sum -= root.val;
//        cur.remove(cur.size() - 1);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        TreeNode root = new TreeNode(5, new TreeNode(4), new TreeNode(8));
//        root.left.left = new TreeNode(11, new TreeNode(7), new TreeNode(2));
//        root.right.left = new TreeNode(13);
//        root.right.right = new TreeNode(4, new TreeNode(5), new TreeNode(1));
//        System.out.println(solution.pathTarget(root, 8));
//    }
//}

/**
 * Success1
 * 注意这里题目说的一定是遍历到叶结点才算是完整的路径
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    private ArrayList<Integer> curList = new ArrayList<>();
//
//    public List<List<Integer>> pathTarget(TreeNode root, int target) {
//        reg(root, target);
//        return res;
//    }
//
//    private void reg(TreeNode root, int target) {
//        if (root == null) {
//            return;
//        }
//        curList.add(root.val);
//        if (root.left == null && root.right == null && root.val == target) {
//            res.add(clone(curList));
//        }
//        reg(root.left, target - root.val);
//        reg(root.right, target - root.val);
//        curList.remove(curList.size() - 1);
//    }
//
//    private ArrayList<Integer> clone(ArrayList<Integer> list) {
//        ArrayList<Integer> res = new ArrayList<>();
//        for (int k : list) {
//            res.add(k);
//        }
//        return res;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
