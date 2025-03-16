//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
//
//
//
// 示例1：
//
//
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
//解释:
//          1
//         / \
//        3   2
//       / \   \
//      5   3   9
//
//
// 示例2：
//
//
//输入: root = [1,2,3]
//输出: [1,3]
//解释:
//          1
//         / \
//        2   3
//
//
// 示例3：
//
//
//输入: root = [1]
//输出: [1]
//
//
// 示例4：
//
//
//输入: root = [1,null,2]
//输出: [1,2]
//解释:
//           1
//            \
//             2
//
//
// 示例5：
//
//
//输入: root = []
//输出: []
//
//
//
//
// 提示：
//
//
// 二叉树的节点个数的范围是 [0,10⁴]
//
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
//
//
//
// 注意：本题与主站 515 题相同： https://leetcode-cn.com/problems/find-largest-value-in-
//each-tree-row/
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 43 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Success2.b
 * 可以不用height栈，因为直接一层一层遍历height一定是递增的
 * 至于怎么知道一层的节点个数，可以巧妙的运用栈的size，把握好时机即可
 * 这样效率高很多
 */
class Solution {

    /**
     * Success2
     * 用ArrayDeque，似乎和LinkedList时间和空间效率是一样的，那就没意思了
     */
    public List<Integer> largestValues(TreeNode root) {

        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            int maxVal = Integer.MIN_VALUE;
            while (len > 0) {
                len--;
                TreeNode t = queue.poll();
                maxVal = Math.max(maxVal, t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(maxVal);
        }
        return res;
    }


    /**
     * Success1
     * 用LinkedList
     */
//    public List<Integer> largestValues(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        if (root == null) {
//            return res;
//        }
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            // 注意这一信息很关键！！！一层一层地算最大
//            int layerLen = queue.size();
//            // 注意node的取值范围是-2³¹ <= Node.val <= 2³¹ - 1，所以这里你不能将max初始化为0
//            int max = Integer.MIN_VALUE;
//            while (layerLen > 0) {
//                TreeNode temp = queue.poll();
//                // 这样写更简洁，感觉实际上也是比较的if写法
//                max = Math.max(max, temp.val);
//                layerLen--;
//                if (temp.left != null) {
//                    queue.add(temp.left);
//                }
//                if (temp.right != null) {
//                    queue.add(temp.right);
//                }
//            }
//            res.add(max);
//        }
//        return res;
//    }

}

/**
 * Success2.a
 * 广度遍历
 * 广度遍历用栈是必须的，不太明白为什么广度遍历的时间效率比深度遍历低这么多
 */
//class Solution {
//
//    LinkedList<TreeNode> node = new LinkedList<>();
//
//    LinkedList<Integer> height = new LinkedList<>();
//
//    List<Integer> res = new ArrayList<>();
//
//    public List<Integer> largestValues(TreeNode root) {
//        if (root == null) {
//            // 注意不能return null，这和return一个空的List是不同的概念
//            return res;
//        } else {
//            node.add(root);
//            height.add(0);
//        }
//        while (!node.isEmpty()) {
//            TreeNode temp = node.poll();
//            int high = height.poll();
//            if (high > res.size() - 1) {
//                res.add(temp.val);
//            } else {
//                res.set(high, Math.max(res.get(high), temp.val));
//            }
//            if (temp.left != null) {
//                node.add(temp.left);
//                height.add(high + 1);
//            }
//            if (temp.right != null) {
//                node.add(temp.right);
//                height.add(high + 1);
//            }
//        }
//        return res;
//    }
//
//}

/**
 * Success1.b
 * 直接参数传递，优化序号，你可以用任何你想要的序号，也可以不用索引适应加1减1，只需要调整比较符号即可
 * 递归传入参数只是在引用上麻烦了点，但有时候能够简化逻辑
 */
//class Solution {
//
//    public List<Integer> largestValues(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        reg(root, 0, res);
//        return res;
//    }
//
//    private void reg(TreeNode root, int curHeight, List<Integer> res) {
//        if (root == null) {
//            return;
//        }
//        if (curHeight == res.size()) {
//            res.add(root.val);
//        } else {
//            res.set(curHeight, Math.max(res.get(curHeight), root.val));
//        }
//        reg(root.left, curHeight + 1, res);
//        reg(root.right, curHeight + 1, res);
//    }
//}

/**
 * Success1.a
 * 深度遍历
 * 不管哪种遍历方式，逻辑上来说，只要以任何一种遍历方式遍历一遍tree就能得到答案
 * 这个题很基础啦，除了基础的树的遍历之外，没有其他任何叠加的复杂逻辑
 */
//class Solution {
//
//    private List<Integer> res = new ArrayList<>();
//
//    private int nextHeight = 1;
//
//    public List<Integer> largestValues(TreeNode root) {
//        reg(root);
//        return res;
//    }
//
//    private void reg(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (res.size() < nextHeight) {
//            res.add(root.val);
//        } else {
//            // 这里减1是索引需要
//            res.set(nextHeight - 1, Math.max(res.get(nextHeight - 1), root.val));
//        }
//        nextHeight++;
//        reg(root.left);
//        reg(root.right);
//        nextHeight--;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
