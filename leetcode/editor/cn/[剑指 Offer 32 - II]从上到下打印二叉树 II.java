//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
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
//  [9,20],
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
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-
//traversal/
// Related Topics 树 广度优先搜索 二叉树 👍 231 👎 0


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
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            // 注意它不是null，只是里面的元素为空
            return new ArrayList<List<Integer>>();
        }
        int now = 1;
        int next = 0;
        // 注意抽象类不可以实例化，但是也可以当做泛型参数，因为本质上传入的是各种方法声明
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 注意由于不认为：类型相同、泛型是某种意义上的父子关系而使得声明的类和实际生成对象的类型是父子关系
        // 所以这种方式是错的
//        ArrayList<List<Integer>> res = new ArrayList<ArrayList<Integer>>();
        // 注意尽管List<>可以被声明为List<Integer>，这是因为List<>会被自动转成List<Integer>
        // 但本质上这是因为将List<>看做List<Integer>的父类，可以兼容它；但是将它们本身作为泛型参数时，由于泛型不认这种父子关系
        // 所以必须严格一致声明，或者干脆就<>不指明泛型类型
//        List<List<Integer>> res = new ArrayList<List<>>();
        // 注意这种声明方式也是ok的，实际对象没有指明泛型的话会默认是声明的泛型
//        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (queue.size() != 0) {
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < now; i++) {
                TreeNode temp = queue.poll();
                ans.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                    next++;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    next++;
                }
            }
            now = next;
            next = 0;
            res.add(ans);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
