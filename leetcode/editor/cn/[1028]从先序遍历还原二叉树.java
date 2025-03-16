import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

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

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 用栈来模拟递归似乎于我而言更好理解
 */
class Solution {

    public TreeNode recoverFromPreorder(String traversal) {
        if (traversal == null | traversal == "") {
            return null;
        }
        int i = 0;
        while (i < traversal.length() && traversal.charAt(i) != '-') {
            i++;
        }
        TreeNode root = new TreeNode(Integer.valueOf(traversal.substring(0, i)));
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int start;
        int level;
        while (i < traversal.length()) {
            level = 0;
            // 注意为charAt()控制访问边界
            while (i < traversal.length() && traversal.charAt(i) == '-') {
                level++;
                i++;
            }
            start = i;
            while (i < traversal.length() && traversal.charAt(i) != '-') {
                i++;
            }
            TreeNode node = new TreeNode(Integer.valueOf(traversal.substring(start, i)));
            while (stack.size() > level) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                // 注意这里是==
                // 注意由于题目说了如果节点只有一个子节点，那么保证该子节点为左子节点；所以这里才能这么赋值
                // 如果没有这个信息的话就算是信息缺失了
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.push(node);
        }
        return root;
    }

}

/**
 * Success1
 * 就是说二叉树这种非线性结构随便怎么个顺序遍历一次一定能线性化表示
 * 但是你光知道逻辑上的遍历顺序还不足以表示这个非线性结构，信息有丢失，所有还需要存储额外的结构信息比如结点深度
 * 只要信息是完备的，怎么从线性结构恢复成非线性结构就有很多方式了，常见的就是模拟递归与回溯
 * 递归和回溯是密不可分的是一体的，只是说递归的时候强调行为，回溯的时候强调保存一些信息
 * 所以它题目里给的先序遍历序列是有层级表示的
 */
//class Solution {
//    public TreeNode recoverFromPreorder(String traversal) {
//        // 注意null和空字符串""是有区别的
//        if (traversal == null || traversal.length() == 0) {
//            return null;
//        }
//
//        bound = traversal.length() - 1;
//        this.traversal = traversal;
//        // 注意题目说了每个节点的值介于 1 和 10 ^ 9 之间。那么你就不能只取一个值
//        int start = p;
//        while (p <= bound && traversal.charAt(p) != '-') {
//            p++;
//        }
//        TreeNode root = new TreeNode(Integer.parseInt(traversal.substring(start, p)));
//
//        regBuild(root, 0);
//        return root;
//    }
//
//    private void regBuild(TreeNode node, int level) {
//        if (p > bound) {
//            return;
//        }
//        int childLevel = 0;
//        while(p <= bound && traversal.charAt(p) == '-') {
//            childLevel++;
//            p++;
//        }
//        if(childLevel == (level+1) && p <= bound) {
//            int start = p;
//            while (p <= bound && traversal.charAt(p) != '-') {
//                p++;
//            }
//            node.left = new TreeNode(Integer.parseInt(traversal.substring(start, p)));
//            regBuild(node.left, childLevel);
//        }else{
//            // 注意如果不满足条件，当前结点的构建终止，但是p要回溯回去，因为根本没用上，还有被重复利用，这很重要
//            // 注意涉及到回溯一般都有一个全局指针，而这个全局指针的值一定要注意重置
//            p -= childLevel;
//            return;
//        }
//        childLevel = 0;
//        // 注意这个bound随时都要
//        while(p <= bound && traversal.charAt(p) == '-') {
//            childLevel++;
//            p++;
//        }
//        if(childLevel == (level+1) && p <= bound) {
//            int start = p;
//            while (p <= bound && traversal.charAt(p) != '-') {
//                p++;
//            }
//            node.right = new TreeNode(Integer.parseInt(traversal.substring(start, p)));
//            regBuild(node.right, childLevel);
//        }else{
//            p -= childLevel;
//        }
//    }
//
//    private int p = 0;
//
//    private int bound = 0;
//
//    private String traversal;
//}

//leetcode submit region end(Prohibit modification and deletion)
