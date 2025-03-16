//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反
//序列化为原始的树结构。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
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
//
//
// 提示：
//
//
// 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，也可以采用其他的方法解决这
//个问题。
// 树中结点数在范围 [0, 10⁴] 内
// -1000 <= Node.val <= 1000
//
//
//
//
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 86 👎 0

import java.util.ArrayDeque;
import java.util.Deque;


class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

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
 * Success1
 * 前序遍历序列实现序列化和反序列化
 * 注意因为和1028的限制条件不同，所以细节上不完全一样，需要额外保留L、R的信息以及用'*'作为层级符号
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer stringBuffer = new StringBuffer();
        preOrder(root, stringBuffer, "L", 0);
        return stringBuffer.toString();
    }

    private void preOrder(TreeNode root, StringBuffer stringBuffer, String dir, int level) {
        for (int i = 0; i < level; i++) {
            // 注意因为可能有负数，所以不用'-'作为层级符号
            stringBuffer.append('*');
        }
        stringBuffer.append(dir);
        if (root == null) {
            stringBuffer.append("NULL");
            return;
        }
        stringBuffer.append(root.val);
        preOrder(root.left, stringBuffer, "L", level + 1);
        preOrder(root.right, stringBuffer, "R", level + 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("LNULL")) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        int i = 0;
        int start = 0;
        int level;
        while (i < data.length() && data.charAt(i) != '*') {
            i++;
        }
        TreeNode root = new TreeNode(Integer.valueOf(data.substring(start + 1, i)));
        stack.push(root);
        while (i < data.length()) {
            level = 0;
            // 注意这里不能直接i++，只有满足条件了才能i++
            while (i < data.length() && data.charAt(i) == '*') {
                level++;
                i++;
            }
            start = i;
            while (i < data.length() && data.charAt(i) != '*') {
                i++;
            }
            TreeNode node = (((i - start) > 4) && data.substring(start + 1, i).equals("NULL")) ? null : new TreeNode(Integer.valueOf(data.substring(start + 1, i)));
            // 注意这里是stack.size() > level
            while (level < stack.size()) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                // 注意这里是==
                if (data.charAt(start) == 'L') {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            // 注意这里如果node是null就不push了
            if (node != null) {
                stack.push(node);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Codec codec = new Codec();
        String res = codec.serialize(root);
        TreeNode node = codec.deserialize(res);
        System.out.println(node);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
