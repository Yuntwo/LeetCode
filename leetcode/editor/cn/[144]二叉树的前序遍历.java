//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
//
//
//
// 示例 1：
//
//
//输入：root = [1,null,2,3]
//输出：[1,2,3]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[1,2]
//
//
// 示例 5：
//
//
//输入：root = [1,null,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 100] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 深度优先搜索 二叉树 👍 748 👎 0

import java.util.*;

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
 * Solution1
 * 莫里斯遍历法，实际上还是回溯(那么本质还是递归而非迭代)和记录信息，不好理解
 * 感觉数这种深度结构不是一般的递归和迭代可以转换的那种类型
 * 实际上就是建立回溯链，判断回溯状态
 * 注意在前序遍历中，每一个节点的前继节点都是左子树的最右节点；每一个节点的后继结点都是最左第一个右拐的第一个节点
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        // 注意为什么要用当前节点的左子树的最右叶结点作为pre，因为它就是回溯到当前节点的上一节点
        TreeNode pre;
        // 最后结束在右子树的最右节点，不需要回溯到root就已经遍历完了
        while (cur != null) {
            // 注意如果cur.left为null了的话，就可以直接立即回溯，下一节点直接访问cur.right
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
                // 当cur.left不为null，此时访问到cur分为第一次深度递归和回溯两种情况，由pre.right指针具体记录回溯状态
            } else {
                pre = cur.left;
                // 前序遍历当前节点的pre节点一定是左子树的最右节点
                // 注意这两种情况都认为是pre，只不过是pre的右指针具体指向null还是cur，就是确定了是否回溯
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 像前面while有&&两种条件，后面就有两种讨论情况
                // 这种情况是第一次访问
                if (pre.right == null) {
                    // 注意要改变回溯状态，注意先引用cur再改变cur
                    // 实际上对于每一个叶结点，因为这里改变了它的右子节点指向它的祖先节点，所以cur.right只有在最右节点结束递归时才为null
                    pre.right = cur;
                    res.add(cur.val);
                    cur = cur.left;
                // 这种情况是回溯
                } else {
                    cur = cur.right;
                    // 回置指针
                    pre.right = null;
                }
            }
        }
        return res;
    }
}


/**
 * Success4
 * 用栈模拟递归，把null都放进栈里就不需要额外维护层级信息了
 * 但是入栈顺序有差异，不太好想
 */
//class Solution {
//
//    public List<Integer> preorderTraversal(TreeNode root) {
//        Deque<TreeNode> stack = new LinkedList<>();
//        List<Integer> res = new ArrayList<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            if (node != null) {
//                res.add(node.val);
//                // 注意这里迭代的压栈顺序和递归不一样
//                stack.push(node.right);
//                stack.push(node.left);
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(2);
//        Solution solution = new Solution();
//        System.out.println(solution.preorderTraversal(root));
//    }
//
//}

/**
 * Success3
 * 用栈模拟递归，因为实际上递归不仅保留了栈还保留了每个栈层的状态
 * 所以你要用栈模拟递归也需要用额外的状态栈来保留一些栈状态信息才可行
 */
//class Solution {
//
//    public List<Integer> preorderTraversal(TreeNode root) {
//        Deque<TreeNode> stack = new ArrayDeque<>();
//        Deque<Integer> stackStatus = new ArrayDeque<>();
//        List<Integer> res = new ArrayList<>();
//        if (root == null) {
//            return res;
//        }
//        stack.push(root);
//        res.add(root.val);
//        stackStatus.push(0);
//        while (!stack.isEmpty()) {
//            if (stackStatus.peek() == 0) {
//                stackStatus.pop();
//                stackStatus.push(1);
//                if (stack.peek().left != null) {
//                    stack.push(stack.peek().left);
//                    res.add(stack.peek().val);
//                    stackStatus.push(0);
//                }
//            } else if (stackStatus.peek() == 1) {
//                stackStatus.pop();
//                stackStatus.push(2);
//                if (stack.peek().right != null) {
//                    stack.push(stack.peek().right);
//                    res.add(stack.peek().val);
//                    stackStatus.push(0);
//                }
//            } else {
//                stack.pop();
//                stackStatus.pop();
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(2);
//        Solution solution = new Solution();
//        System.out.println(solution.preorderTraversal(root));
//    }
//
//}

/**
 * Success2
 * 递归简洁写法
 */
//class Solution {
//
//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        preOrder(root, list);
//        return list;
//    }
//
//    private void preOrder(TreeNode root, List<Integer> list) {
//        if (root == null) {
//            return;
//        }
//        list.add(root.val);
//        preOrder(root.left, list);
//        preOrder(root.right, list);
//    }
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
//        Solution solution = new Solution();
//        System.out.println(solution.preorderTraversal(root));
//    }
//
//}

/**
 * Success1
 * 递归解法
 */
//class Solution {
//    public List<Integer> preorderTraversal(TreeNode root) {
//        // 注意List只是一个接口，不能直接实例化，要用它的实现类
//        ArrayList<Integer> res = new ArrayList<Integer>();
//        // 注意要么在这里要么在preSearch中需要判断root是否为null
//        if (root == null) {
//            return res;
//        }
//        preSearch(root, res);
//        return res;
//    }
//
//    private void preSearch(TreeNode root, ArrayList<Integer> list) {
//        if (root.left == null && root.right == null) {
//            // 注意这里根结点是叶结点的情况要返回，但是也不要忘了访问
//            list.add(root.val);
//            return;
//        }
//        // 注意虽然有可能是左单支或者右单支的情况，但是本质上和前面的递归返回是不同的情况
//        list.add(root.val);
//        // 注意一定要判断子树是否为空，注意这里root表示的是子树的根结点
//        if (root.left != null) {
//            preSearch(root.left, list);
//        }
//        if (root.right != null) {
//            preSearch(root.right, list);
//        }
//    }
//
//}
//leetcode submit region end(Prohibit modification and deletion)
