//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
//
// 1
// / \
// 2 2
// / \ / \
//3 4 4 3
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
//
// 1
// / \
// 2 2
// \ \
// 3 3
//
//
//
// 示例 1：
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
//
//
// 示例 2：
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false
//
//
//
// 限制：
//
// 0 <= 节点个数 <= 1000
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 294 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/**
 * Success2
 * 这种前序遍历序列和对称前序遍历序列的思路十分值得借鉴
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            // 注意这种情况下默认返回true
            return true;
        }
        // 需要两个指针分别进行前序遍历序列和对称前序遍历序列，它们都不改变这棵树本身就比较方便了
        // 其实前序遍历与对称前序遍历并没有改变递归的逻辑，本质上还是前序遍历的思路，所谓对称只是传入的递归参数发生了变化
        // 注意对称前序遍历不是后序遍历
        return isSym(root, root);
    }

    private boolean isSym(TreeNode p1, TreeNode p2) {
        // 底层递归返回
        if (p1.left == null && p1.right == null) {
            // 只有这种情况能返回true
            if (p2.left == null && p2.right == null && p1.val == p2.val) {
                return true;
            } else {
                return false;
            }
        }
        // 正常递归的几种情况
        // 注意前提是前序结点要相等也即要有外层if判断
        if (p1.val == p2.val) {
            if (p1.left == null) {
                // 注意都是在p1的情况前提下讨论p2的情况
                if (p2.right != null) {
                    return false;
                } else {
                    // 然后再比较p1、p2
                    return isSym(p1.right, p2.left);
                }
            } else if (p1.right == null) {
                // 注意都是在p1的情况前提下讨论p2的情况
                if (p2.left != null) {
                    return false;
                } else {
                    // 然后再比较p1、p2
                    return isSym(p1.left, p2.right);
                }
            } else {
                // 注意都是在p1的情况前提下讨论p2的情况
                if (p2.left == null || p2.right == null) {
                    return false;
                } else {
                    // 然后再比较p1、p2
                    return isSym(p1.left, p2.right) && isSym(p1.right, p2.left);
                }
            }
        }
        // 默认没有走到这一步return false
        return false;
    }

    public static void main(String[] args) {
        test myTest = new test();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        boolean result = myTest.isSymmetric(root);
        System.out.println(result);
    }
}

/**
 * Success1
 * 这样单独存储一个对称二叉树再查找对比的方法空间和时间复杂度都挺高
 */
//class Solution {
//
//    public boolean isSymmetric(TreeNode root) {
//        if (root == null) {
//            // 这种情况下是true
//            return true;
//        }
//        // 注意这样一条语句是浅拷贝，没有生成新的对象，改变的还是root
////        TreeNode mirrRoot = root;
//        TreeNode mirrRoot = copy(root);
//        mirrReg(mirrRoot);
//        return isSame(root, mirrRoot);
//    }
//
//    private void mirrReg(TreeNode mirrRoot) {
//        if (mirrRoot.left == null && mirrRoot.right == null) {
//            return;
//        }
//        if (mirrRoot.left == null) {
//            mirrRoot.left = mirrRoot.right;
//            mirrRoot.right = null;
//            mirrReg(mirrRoot.left);
//        } else if (mirrRoot.right == null) {
//            mirrRoot.right = mirrRoot.left;
//            mirrRoot.left = null;
//            mirrReg(mirrRoot.right);
//        } else {
//            TreeNode temp = mirrRoot.left;
//            mirrRoot.left = mirrRoot.right;
//            mirrRoot.right = temp;
//            mirrReg(mirrRoot.right);
//            mirrReg(mirrRoot.left);
//        }
//    }
//
//    private boolean isSame(TreeNode root, TreeNode mirrRoot) {
//        // 递归返回的底层情况
//        if (root.right == null && root.left == null) {
//            //底层情况也分几种，但只有这种情况能返回true
//            if (mirrRoot.right == null && mirrRoot.left == null && root.val == mirrRoot.val) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        // 递归情况只有三种:只有左分支、只有右分支、左右分支都有，左右分支都没有是递归返回的情况
//        if (root.val == mirrRoot.val) {
//            if (root.left == null) {
//                if (mirrRoot.left != null) {
//                    return false;
//                } else {
//                    return isSame(root.right, mirrRoot.right);
//                }
//            } else if (root.right == null) {
//                if (mirrRoot.right != null) {
//                    return false;
//                } else {
//                    return isSame(root.left, mirrRoot.left);
//                }
//            } else {  // 左右分支都不等于null的情况
//                if (mirrRoot.left == null || mirrRoot.right == null) {
//                    return false;
//                } else {
//                    return isSame(root.right, mirrRoot.right) && isSame(root.left, mirrRoot.left);
//                }
//            }
//            // 注意这里不能判断不能同时root和mirrRoot一起判断，这样的话会漏掉一些情况
////            if (root.left == null && mirrRoot.left == null) {
////                return isSame(root.right, mirrRoot.right);
////            } else if (root.right == null && mirrRoot.right == null) {
////                return isSame(root.left, mirrRoot.left);
////            } else {
////                return isSame(root.right, mirrRoot.right) && isSame(root.left, mirrRoot.left);
////            }
//        }
//        // 默认没有走到最后这一步比较return false
//        return false;
//    }
//
//    // 深拷贝一个Tree的话在递归中相当于需要进行两步操作，这样有点麻烦
//    // 也就是说从底层来讲无论是递归实现还是循环实现，都需要维护两个栈
//    private TreeNode copy(TreeNode root) {
//        // 这个函数定义的前提是root非null，判空在函数外部已经实现
//        TreeNode mirrRoot = new TreeNode(root.val);
//        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
//        LinkedList<TreeNode> mirrStack = new LinkedList<TreeNode>();
//        TreeNode cur = root;
//        TreeNode mirrCur = mirrRoot;
//        while (cur != null || !stack.isEmpty()) {
//            if (cur != null) {
//                stack.push(cur);
//                mirrStack.push(mirrCur);
//                // 注意要判断cur.left是否为null，才决定了是否能访问实例化，与外层判断null的逻辑不同
//                if (cur.left != null) {
//                    mirrCur.left = new TreeNode(cur.left.val);
//                }
//                // 这里即使cur.left是null也没有关系
//                cur = cur.left;
//                mirrCur = mirrCur.left;
//            } else {
//                cur = stack.pop();
//                mirrCur = mirrStack.pop();
//                // 注意要判断cur.right是否为null，才决定了是否能访问实例化，与外层判断null的逻辑不同
//                if (cur.right != null) {
//                    mirrCur.right = new TreeNode(cur.right.val);
//                }
//                // 这里即使cur.right是null也没有关系
//                cur = cur.right;
//                mirrCur = mirrCur.right;
//            }
//        }
//        return mirrRoot;
//    }
//}


//leetcode submit region end(Prohibit modification and deletion)
