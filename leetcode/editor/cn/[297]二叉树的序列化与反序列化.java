//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。
//
//
//
// 示例 1：
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
// 树中结点数在范围 [0, 10⁴] 内
// -1000 <= Node.val <= 1000
//
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 1026 👎 0

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Success1
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode == null) {
                stringBuilder.append("null,");
            } else {
                stringBuilder.append(String.valueOf(treeNode.val));
                stringBuilder.append(",");
                queue.add(treeNode.left);
                queue.add(treeNode.right);
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(data.substring(1, data.length() - 1), ",");
        TreeNode root = new TreeNode(Integer.valueOf(stringTokenizer.nextToken()));
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            String temp = stringTokenizer.nextToken();
            if (!temp.equals("null")) {
                treeNode.left = new TreeNode(Integer.valueOf(temp));
                queue.add(treeNode.left);
            }
            temp = stringTokenizer.nextToken();
            if (!temp.equals("null")) {
                treeNode.right = new TreeNode(Integer.valueOf(temp));
                queue.add(treeNode.right);
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
        String temp = codec.serialize(root);
        TreeNode res = codec.deserialize(temp);
        System.out.println(temp);
    }
}

/**
 * Failure1
 * serialize不能这么写
 */
//class Codec {
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            return "";
//        }
//        StringBuilder stringBuilder = new StringBuilder("[");
//        Queue<TreeNode> queue = new LinkedList<>() {{
//            add(root);
//        }};
//        while (!queue.isEmpty()) {
//            TreeNode treeNode = queue.poll();
//            stringBuilder.append(String.valueOf(treeNode.val));
//            stringBuilder.append(",");
//            // 注意不能这么写，不能只将非null的节点加入队列中
//            // 必须将null的节点也加入队列中，这和deserialise不一样
//            // deserialise就是层序顺序取出来，层序顺序处理，相当于有null的那些地方因为有默认值跳过了所以不需要再加入队列
//            // 否则当前节点的左右节点为null的话，会优先于其父节点的子节点插入到String中
//            if (treeNode.left != null) {
//                queue.add(treeNode.left);
//            }else {
//                stringBuilder.append("null");
//                stringBuilder.append(",");
//            }
//            if (treeNode.right != null) {
//                queue.add(treeNode.right);
//            }else {
//                stringBuilder.append("null");
//                stringBuilder.append(",");
//            }
//        }
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        stringBuilder.append("]");
//        return stringBuilder.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.equals("")) {
//            return null;
//        }
//        StringTokenizer stringTokenizer = new StringTokenizer(data.substring(1, data.length() - 1), ",");
//        TreeNode root = new TreeNode(Integer.valueOf(stringTokenizer.nextToken()));
//        Queue<TreeNode> queue = new LinkedList<>() {{
//            add(root);
//        }};
//        while (!queue.isEmpty()) {
//            TreeNode treeNode = queue.poll();
//            String temp = stringTokenizer.nextToken();
//            if (!temp.equals("null")) {
//                treeNode.left = new TreeNode(Integer.valueOf(temp));
//                queue.add(treeNode.left);
//            }
//            temp = stringTokenizer.nextToken();
//            if (!temp.equals("null")) {
//                treeNode.right = new TreeNode(Integer.valueOf(temp));
//                queue.add(treeNode.right);
//            }
//        }
//        return root;
//    }
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
//        Codec codec = new Codec();
//        String temp = codec.serialize(root);
//        TreeNode res = codec.deserialize(temp);
//        System.out.println(temp);
//    }
//}


//leetcode submit region end(Prohibit modification and deletion)
