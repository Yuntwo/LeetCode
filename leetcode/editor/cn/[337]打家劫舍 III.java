import java.util.HashMap;

//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为
// root 。
//
// 除了
// root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的
//房子在同一天晚上被打劫 ，房屋将自动报警。
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
//
//
//
// 示例 1:
//
//
//
//
//输入: root = [3,2,3,null,3,null,1]
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
//
// 示例 2:
//
//
//
//
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
//
//
//
//
// 提示：
//
//
//
//
//
// 树的节点数在 [1, 10⁴] 范围内
// 0 <= Node.val <= 10⁴
//
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1610 👎 0

/**
 * Definition for a binary tree node.
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

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;

/**
 * Success2
 * 与上一版本的思路类似，明确地记录偷还是不偷，然后使用压缩版本的动态数组记录
 * 每一步递归抉择都是在明确当前偷与不偷之间比较
 * 参数返回式递归节省额外空间
 * 树形结构只能是自顶向下递归
 */
class Solution {

    /**
     * int[0]表示明确不偷当前节点所能获得的最大值，int[1]表示明确偷当前节点所能获得的最大值
     */
    public int rob(TreeNode root) {
        int[] cur = robReg(root);
        return Math.max(cur[0], cur[1]);
    }

    /**
     * 注意递归参数传递也是很有好处的，有时候并不麻烦，没有必要全局记录数据的时候是很有用的
     */
    private int[] robReg(TreeNode root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        int[] left = robReg(root.left);
        int[] right = robReg(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

}

/**
 * Success1
 * 注意这个首先思路和上面这个是不一样的，问题结构不同
 * 由于子问题结构不同，导致它的递归结构不同，上面那个递归是一层调用，层层返回，所以可以直接用返回值来传递记忆结果，而不需要全局化记忆信息
 * 但是这个子问题结构用了两层递归结构，函数返回只能返回一个结果，所以不能用返回值来传递记忆结果，必须用全局化记忆信息；
 * 当然如果你知道需要哪些信息，你组合返回这些信息结果也能抛开全局化记忆，就是很麻烦，代码很冗余；
 * 但似乎也不行，只要是多次递归重复递归，你就只能用全局记忆结果而不能直接层层递归返回来去除这个额外开销
 * 在递归的过程中记忆化，其实就是记忆化递归，以空间换时间，可以解决递归超时的问题
 */
//class Solution {
//
//    private HashMap<TreeNode, Integer> hashMap = new HashMap<>();
//
//    /**
//     * 题解写法，直接在底层返回中判断是否在hashMap中，就不需要再主逻辑中多次冗余判断了，十分简洁
//     */
//    public int rob(TreeNode root) {
//        if (root == null) return 0;
//        if (hashMap.containsKey(root)) return hashMap.get(root);
//        int money = root.val;
//
//        // 这确实是没有办法放在底层返回中判断
//        if (root.left != null) {
//            money += (rob(root.left.left) + rob(root.left.right));
//        }
//        if (root.right != null) {
//            money += (rob(root.right.left) + rob(root.right.right));
//        }
//        int result = Math.max(money, rob(root.left) + rob(root.right));
//        hashMap.put(root, result);
//        return result;
//    }
//
//    /**
//     * Success1
//     * 写得有点冗余
//     */
////    public int rob(TreeNode root) {
////        // null的节点就不用也不方便加入hashMap中
////        if (root == null) return 0;
////
////        int rob1 = root.val;
////        int rob2 = 0;
////        if (root.left != null) {
////            if (hashMap.containsKey(root.left.left)) {
////                rob1 += hashMap.get(root.left.left);
////            } else {
////                rob1 += rob(root.left.left);
////            }
////            if (hashMap.containsKey(root.left.right)) {
////                rob1 += hashMap.get(root.left.right);
////            } else {
////                rob1 += rob(root.left.right);
////            }
////            if (hashMap.containsKey(root.left)) {
////                rob2 += hashMap.get(root.left);
////            } else {
////                rob2 += rob(root.left);
////            }
////        }
////
////        if (root.right != null) {
////            if (hashMap.containsKey(root.right.left)) {
////                rob1 += hashMap.get(root.right.left);
////            } else {
////                rob1 += rob(root.right.left);
////            }
////            if (hashMap.containsKey(root.right.right)) {
////                rob1 += hashMap.get(root.right.right);
////            } else {
////                rob1 += rob(root.right.right);
////            }
////            if (hashMap.containsKey(root.right)) {
////                rob2 += hashMap.get(root.right);
////            } else {
////                rob2 += rob(root.right);
////            }
////        }
////        int res = Math.max(rob1, rob2);
////        hashMap.put(root, res);
////        return res;
////    }
//
//}

/**
 * Failure2
 * 基于树形结构，递归肯定是最方便的
 * 比起前面一种，就是没有分root节点究竟rob还是不rob，反正从root开始考虑rob，也就是合并了这两种情况的递归
 * 代码写起来更简洁，理解起来比较跳跃，执行起来应该还是一样的，所以也是超时
 * 递归，但是不用动态数组
 */
//class Solution {
//
//    /**
//     * 注意这里rob表示从root开始考虑偷能偷到的最大值，但是root不一定偷但是一定可以被偷
//     */
////    public int rob(TreeNode root) {
////        if (root == null) {
////            return 0;
////        }
////        int rob1 = root.val;
////        int rob2 = 0;
////        // 注意这里没有保证root.left和root.right非null，可能出现NullPointerException如果不判断的话
////        if (root.left != null) {
////            rob1 = rob1 + rob(root.left.left) + rob(root.left.right);
////            rob2 += rob(root.left);
////        }
////        if (root.right != null) {
////            rob1 = rob1 + rob(root.right.left) + rob(root.right.right);
////            rob2 += rob(root.right);
////        }
////        return Math.max(rob1, rob2);
////    }
//
//    /**
//     * 题解写法，但是也超时
//     */
//    public int rob(TreeNode root) {
//        if (root == null) return 0;
//
//        int money = root.val;
//        if (root.left != null) {
//            money += (rob(root.left.left) + rob(root.left.right));
//        }
//
//        if (root.right != null) {
//            money += (rob(root.right.left) + rob(root.right.right));
//        }
//
//        return Math.max(money, rob(root.left) + rob(root.right));
//    }
//
//}

/**
 * Failure1
 * 思路应该是正确的，但是这样递归会超时
 * 树形结构用动态数组感觉就不是很方便了
 * 注意动态规划的本质是记忆化搜索
 */
//class Solution {
//    public int rob(TreeNode root) {
//        return Math.max(isRob(root), notRob(root));
//    }
//
//    private int isRob(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        return notRob(node.left) + notRob(node.right) + node.val;
//    }
//
//    private int notRob(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        int v1 = notRob(node.left) + notRob(node.right);
//        int v2 = notRob(node.left) + isRob(node.right);
//        int v3 = isRob(node.left) + isRob(node.right);
//        int v4 = isRob(node.left) + notRob(node.right);
//        return Math.max(Math.max(v1, v2), Math.max(v3, v4));
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
