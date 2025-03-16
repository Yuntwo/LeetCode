//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
// 返回：
//
// [3,9,20,15,7]
//
//
//
//
// 提示：
//
//
// 节点总数 <= 1000
//
// Related Topics 树 广度优先搜索 二叉树 👍 208 👎 0


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
    public int[] levelOrder(TreeNode root) {
        // 注意队列中的数据结构是TreeNode
//        LinkedList<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
//        LinkedList<Integer> res = new LinkedList<>();
        // 注意如果单纯像存动态数据，用ArrayList这种更为简单的数据结构时间和空间效率都会高很多
        ArrayList<Integer> res = new ArrayList<>();
        queue.add(root);
        while (queue.size() != 0) {
//            TreeNode now = queue.pollFirst();
            // poll就是first，是Queue和LinkedList都有的
            TreeNode now = queue.poll();
            if (now != null) {
                queue.add(now.left);
                queue.add(now.right);
                res.add(now.val);
            }
        }
        // 将Integer转成int
        // 注意没有更好的办法，只有这样转
        // 注意无初始化的数组生成
        int[] re = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            re[i] = res.get(i).intValue();
        }
        return re;
    }
}
//class Solution {
//    public int[] levelOrder(TreeNode root) {
//        if(root == null) return new int[0];
//        // 这种写法有意思
//        Queue<TreeNode> queue = new LinkedList<>(){{ add(root); }};
//        ArrayList<Integer> ans = new ArrayList<>();
//        while(!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            ans.add(node.val);
//            if(node.left != null) queue.add(node.left);
//            if(node.right != null) queue.add(node.right);
//        }
//        int[] res = new int[ans.size()];
//        for(int i = 0; i < ans.size(); i++)
//            res[i] = ans.get(i);
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
