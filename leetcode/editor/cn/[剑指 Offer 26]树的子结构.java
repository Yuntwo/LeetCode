//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。
//
// 例如:
//给定的树 A:
//
// 3
// / \
// 4 5
// / \
// 1 2
//给定的树 B：
//
// 4
// /
// 1
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
//
// 示例 1：
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
//
//
// 示例 2：
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true
//
// 限制：
//
// 0 <= 节点个数 <= 10000
// Related Topics 树 深度优先搜索 二叉树 👍 482 👎 0


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
 * Success2
 * 题解递归做法
 * 同时用两种递归函数也很方便
 */
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 前半部分控制树为空的情况，就是false不用后续操作了
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    // 这个函数就是精确比较了
    boolean recur(TreeNode A, TreeNode B) {
        // B为空的情况算作包含
        if(B == null) return true;
        // 这里提前判断值的情况可以避免多余的递归
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}

/**
 * Success1
 * 题解非递归做法
 * 确实是很慢很占内存
 */
//class Solution {
//
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//        if(A==null||B==null){
//            return false;
//        }
//
//        //开始先序遍历
//        Stack<TreeNode> stack=new Stack<>();//这是记录A的节点
//        stack.push(A);
//        ArrayList<Boolean> ans=new ArrayList<>();
//        do {
//            TreeNode treeNode=stack.pop();
//            if(treeNode.val==B.val){
//                ans.add(innerVerse(treeNode,B));
//            }
//            if(treeNode.right!=null){
//                stack.push(treeNode.right);
//            }
//            if(treeNode.left!=null){
//                stack.push(treeNode.left);
//            }
//        }while(!stack.isEmpty());
//        for (int i = 0; i < ans.size(); i++) {
//            if(ans.get(i)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean innerVerse(TreeNode A,TreeNode B){
//        Stack<TreeNode> stack1=new Stack<>();//这是记录A的节点
//        Stack<TreeNode> stack2=new Stack<>();//这是记录B的节点
//        stack1.push(A);
//        stack2.push(B);
//        do {
//            TreeNode a=stack1.pop();
//            TreeNode b=stack2.pop();
//            if(a.val!=b.val){
//                return false;
//            }
//            if(b.right!=null){
//                if(a.right==null){
//                    return false;
//                }else{
//                    stack2.push(b.right);
//                    stack1.push(a.right);
//                }
//            }
//            if(b.left!=null){
//                if(a.left==null){
//                    return false;
//                }else{
//                    stack2.push(b.left);
//                    stack1.push(a.left);
//                }
//            }
//        }while(!stack2.isEmpty());
//        return true;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
