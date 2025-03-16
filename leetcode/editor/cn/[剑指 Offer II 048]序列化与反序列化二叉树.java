import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

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
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 70 👎 0
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
 * Success2
 * 用层序遍历逻辑是最简洁、细节最不冗杂的
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 注意因为ArrayQue不能push null，所以只能用LinkedList
        Deque<TreeNode> deque = new LinkedList<>();
        StringBuffer stringBuffer = new StringBuffer();
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            if (node == null) {
                stringBuffer.append("null").append(',');
            } else {
                stringBuffer.append(node.val).append(',');
                deque.push(node.left);
                deque.push(node.right);
            }
        }
        return stringBuffer.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(',');
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = s[0].equals("null") ? null : new TreeNode(Integer.valueOf(s[0]));
        int i = 1;
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            if (node != null) {
                node.left = s[i].equals("null") ? null : new TreeNode(Integer.valueOf(s[i++]));
                node.right = s[i].equals("null") ? null : new TreeNode(Integer.valueOf(s[i++]));
                deque.push(node.left);
                deque.push(node.right);
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
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        Codec codec = new Codec();
        String temp = codec.serialize(root);
        TreeNode res = codec.deserialize(temp);
        System.out.println(temp);
    }

}

/**
 * Failure1
 * 要是序列化的格式中有null就麻烦一点了
 *
 * 二叉树序列化格式的官方文档访问不了了
 *
 * 注意这里的序列化是层序遍历的结果
 * 注意二叉树2*i+1这种规律是在没有存储null的情况下成立的
 * 注意序列化中包含"null"当做完全二叉树的
 * 但其实这就麻烦了，究竟要多少null记录呢
 * 注意序列化后的格式是数组样子的字符串
 *
 * 感觉是要把null的null也加进去才能构造成完全二叉树，但这样什么时候是个头呢
 */
//class Codec {
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            return "[]";
//        }
//        String res = "[";
//        arr.add(root);
//        // 注意null的也要加进去
//        while (!arr.isEmpty()) {
//            TreeNode temp = arr.poll();
//            if (temp != null) {
//                // 注意res.concat()和String相加返回的都是新字符串
//                res = res + String.valueOf(temp.val) + ",";
//                arr.add(temp.left);
//                arr.add(temp.right);
//            } else {
//                res = res + "null,";
//            }
//        }
//        // remove last "," and add last "]"
//        res = res.substring(0, res.length() - 1);
//        res = res + "]";
//        return res;
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.equals("[]")) {
//            return null;
//        }
//        // remove "[" at beginning and "]" at end
//        data = data.substring(1, data.length() - 1);
//        String[] nodes = data.split(",");
//        TreeNode res = new TreeNode(Integer.valueOf(nodes[0]));
//        arr.add(res);
//        for (int i = 0; i < nodes.length; i++) {
//            TreeNode temp = arr.poll();
//            if (temp == null) {
//                continue;
//            }
//            if ((2 * i + 1) < nodes.length && !nodes[2 * i + 1].equals("null")) {
//                temp.left = new TreeNode(Integer.valueOf(nodes[2 * i + 1]));
//            }
//            arr.add(temp.left);
//            if ((2 * i + 2) < nodes.length && !nodes[2 * i + 2].equals("null")) {
//                temp.right = new TreeNode(Integer.valueOf(nodes[2 * i + 2]));
//            }
//            arr.add(temp.right);
//        }
//        return res;
//    }
//
//    LinkedList<TreeNode> arr = new LinkedList<>();
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
//        root.right.left.left = new TreeNode(6);
//        root.right.left.right = new TreeNode(7);
//        Codec codec = new Codec();
//        String temp = codec.serialize(root);
//        TreeNode res = codec.deserialize(temp);
//        System.out.println(temp);
//    }
//
//}

/**
 * Failure2
 * 由于没有对外暴露序列化的格式以及检查官方文档的格式，所以题解中有很多抖机灵的做法，这题显得不是很有意思
 * 得到剑指offer原书是怎么说的
 * 注意2*i的那种规律是完全二叉树才有的性质
 */
//class Codec {
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            // 这是我们自己定义的roo为空时候返回的值，你可以定义为任意
//            // 因为这里没有对外暴露序列化的结果，官方文档也失效了
//            return "[]";
//        }
//        String res = "[";
//        arr.add(root);
//        // 注意null的也要加进去
//        while (!arr.isEmpty()) {
//            TreeNode temp = arr.poll();
//            if (temp != null) {
//                // 注意res.concat()和String相加返回的都是新字符串
//                res = res + String.valueOf(temp.val) + ",";
//                arr.add(temp.left);
//                arr.add(temp.right);
//            }
//        }
//        // remove last "," and add "]"
//        // 注意这个函数是左闭右开
//        res = res.substring(0, res.length() - 1);
//        res = res + "]";
//        return res;
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.equals("[]")) {
//            return null;
//        }
//        // remove "[" at beginning and "]" at end
//        data = data.substring(1, data.length() - 1);
//        String[] nodes = data.split(",");
//        TreeNode res = new TreeNode(Integer.valueOf(nodes[0]));
//        // 这个解决方案加的都是一些非null的节点
//        arr.add(res);
//        for (int i = 0; i < nodes.length; i++) {
//            TreeNode temp = arr.poll();
//            if ((2 * i + 1) < nodes.length) {
//                temp.left = new TreeNode(Integer.valueOf(nodes[2 * i + 1]));
//                arr.add(temp.left);
//            }
//            if ((2 * i + 2) < nodes.length) {
//                temp.right = new TreeNode(Integer.valueOf(nodes[2 * i + 2]));
//                arr.add(temp.right);
//            }
//        }
//        return res;
//    }
//
//    LinkedList<TreeNode> arr = new LinkedList<>();
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
//        root.right.left.left = new TreeNode(6);
//        root.right.left.right = new TreeNode(7);
//        Codec codec = new Codec();
//        String temp = codec.serialize(root);
//        TreeNode res = codec.deserialize(temp);
//        System.out.println(temp);
//    }
//
//}

/**
 * Success1
 * 感觉这种反序列化的思路还是不自然啊，我是想不到的
 * 关键在于：
 * 1.叶结点的null也是要放进占坑的，否则没有办法成功
 * 2.非完全二叉树怎么知道某个节点的子结点位置，不常规，需要你区别加入队列的节点和下两个未加入队列的节点就是队头节点的子节点
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            // The node in queue may be null
            if (temp == null) {
                // Attention this method returns stringBuilder itself not a new one
                stringBuilder.append("null,");
            } else {
                stringBuilder.append(String.valueOf(temp.val) + ",");
                // We always add a null node's children to queue no matter whether they are null or not
                queue.add(temp.left);
                queue.add(temp.right);
            }
        }
        // Attention this method includes left and excludes right
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        // exclusive "[" at beginning and "]" at end
        data = data.substring(1, data.length() - 1);
        String[] nodes = data.split(",");
        TreeNode res = new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(res);
        int i = 0;
        // Attention we should use queue.isEmpty to control rather than i
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                continue;
            }
            temp.left = nodes[++i].equals("null") ? null : new TreeNode(Integer.valueOf(nodes[i]));
            temp.right = nodes[++i].equals("null") ? null : new TreeNode(Integer.valueOf(nodes[i]));
            // Attention we should add temp's children to queue
            queue.add(temp.left);
            queue.add(temp.right);
        }
        return res;
    }

    LinkedList<TreeNode> queue = new LinkedList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        Codec codec = new Codec();
        String temp = codec.serialize(root);
        TreeNode res = codec.deserialize(temp);
        System.out.println(temp);
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
