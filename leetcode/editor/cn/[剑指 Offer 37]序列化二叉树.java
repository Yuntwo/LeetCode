//请实现两个函数，分别用来序列化和反序列化二叉树。
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。
//
//
//
// 示例：
//
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
//
//
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 358 👎 0


import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
import java.util.*;
import java.util.StringTokenizer;
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Review1
 * 投机没有处理String两边的[]
 */
class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        String res = "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                res = res + "null,";
            } else {
                res = res + String.valueOf(temp.val) + ",";
                // 注意不管是否为null都要加进队列处理，而不能直接在当时判断，因为这样追加null的位置不对
                queue.add(temp.left);
                queue.add(temp.right);
            }
        }
        return res;
    }

    /**
     * Decode的关键其实在于我知道每一层的节点都是顺序添加的，关键是加在哪个头上！！！
     * 这个要用一个队列先进先出来记录
     */
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        String[] nodes = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode res = new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(res);
        int i = 1;
        while (!queue.isEmpty()) {
            // null结点不会被加进queue中
            // 所以每一趟，不管要访问的nodes的值是什么，i必+2
            TreeNode temp = queue.poll();
            if (!nodes[i].equals("null")) {
                temp.left = new TreeNode(Integer.valueOf(nodes[i]));
                queue.add(temp.left);
            }
            i++;
            if (!nodes[i].equals("null")) {
                temp.right = new TreeNode(Integer.valueOf(nodes[i]));
                queue.add(temp.right);
            }
            i++;
        }
        return res;
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
 * Success2
 * 应该不是自己做出来的，我的思路还是有问题
 */
//class Codec {
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            // 按照题目调用函数判断结果的方式这个为空的情况"[ ]"等等都可
//            return "[]";
//        }
//        // 其实这里也可以不要中间这个List，直接用StringBuffer连接
//        List<String> list = new ArrayList<>();
//        // PriorityQueue队列似乎不支持add自定义class
////        Queue<TreeNode> queue = new PriorityQueue<>();
//        // 注意可以这样在初始化的时候添加初始元素
//        Queue<TreeNode> queue = new LinkedList<>(){{add(root);}};
//        while (!queue.isEmpty()) {
//            // 注意接口Queue的poll都是弹出索引为0的元素，代表队头和栈顶
//            TreeNode treeNode = queue.poll();
//            if (treeNode == null) {
//                list.add("null");
//            } else {
//                list.add(String.valueOf(treeNode.val));
//                queue.add(treeNode.left);
//                queue.add(treeNode.right);
//            }
//        }
//        // StringBuffer比起StringBuilder是线程安全的，虽然单线程环境下没有这个问题
//        StringBuffer stringBuffer = new StringBuffer("[");
//        stringBuffer.append(list.get(0));
//        for (int i = 1; i < list.size(); i++) {
//            stringBuffer.append(",");
//            stringBuffer.append(list.get(i));
//        }
//        stringBuffer.append("]");
//        // 编码的时候后面多一些null没有关系，不影响结果题目调用函数方式结果的判定
//        // 但是非常有助于逻辑自洽
//        return stringBuffer.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        // 不用大括号有时候确实能让代码量看起来更少更舒服
//        if (data.equals("[]")) {
//            return null;
//        }
//        // 也可以用split方法存成String[]的方式，但是这种似乎效率稍微高一点
//        StringTokenizer stringTokenizer = new StringTokenizer(data, ",|[|]");
//        TreeNode root = new TreeNode(Integer.valueOf(stringTokenizer.nextToken()));
//        Queue<TreeNode> queue = new LinkedList<>() {{add(root);}};
//        while (!queue.isEmpty()) {
//            TreeNode treeNode = queue.poll();
//            // 由于为null的节点没有加入queue中进行处理，所以不需要判null了
//            // 或者说是因为为null的节点没有相关的资源元素放在data里需要处理，所以也就没必要加入queue中进行处理了
////            if (treeNode == null) {
////                continue;
////            }
//            // 按理说使用stringTokenizer.nextToken()要先判断是否有下一个
//            // 但是这里由于二叉树的特殊结构以及queue判空规避了这种情况
//            String next = stringTokenizer.nextToken();
//            // 注意由于左右子节点会默认null，所以不需要额外赋null
////            if (next.equals("null")) {
////                treeNode.left = null;
////            } else {
////                treeNode.left = new TreeNode(Integer.valueOf(next));
////            }
//            if (!next.equals("null")) {
//                treeNode.left = new TreeNode(Integer.valueOf(next));
//                queue.add(treeNode.left);
//            }
//            next = stringTokenizer.nextToken();
////            if (next.equals("null")) {
////                treeNode.right = null;
////            }else {
////                treeNode.right = new TreeNode(Integer.valueOf(next));
////            }
//            if (!next.equals("null")) {
//                treeNode.right = new TreeNode(Integer.valueOf(next));
//                queue.add(treeNode.right);
//            }
//            // 注意null的情况也是要占queue的空间的
//            // 这里是把null的情况也加入了queue
//            // 不要忘记加入队列
//            // 可以将它们放在非null分支中，这样为null的节点就不会被加入queue中进行处理
////            queue.add(treeNode.left);
//            // 不要忘记加入队列
////            queue.add(treeNode.right);
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
/**
 * Success1
 * 题解的方法也没有特别快
 */
//public class Codec {
//    public String serialize(TreeNode root) {
//        if(root == null) return "[]";
//        StringBuilder res = new StringBuilder("[");
//        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
//        while(!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if(node != null) {
//                res.append(node.val + ",");
//                queue.add(node.left);
//                queue.add(node.right);
//            }
//            else res.append("null,");
//        }
//        res.deleteCharAt(res.length() - 1);
//        res.append("]");
//        return res.toString();
//    }
//
//    public TreeNode deserialize(String data) {
//        if(data.equals("[]")) return null;
//        String[] vals = data.substring(1, data.length() - 1).split(",");
//        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
//        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
//        int i = 1;
//        while(!queue.isEmpty()) {
//            // 这个相当于是父节点
//            TreeNode node = queue.poll();
//            // 它这个i指向的是poll之后的下一个元素，也即node 的子节点
//            // 对于节点为null的情况，它没有将其加入queue中
//            // 因为对于本身为null的节点，没有做任何操作，没有消耗改变任何资源，所以加不加都无所谓了
//            // 所以在引用node.left/right的时候也就不需要啊判null
//            if(!vals[i].equals("null")) {
//                node.left = new TreeNode(Integer.parseInt(vals[i]));
//                queue.add(node.left);
//            }
//            i++;
//            if(!vals[i].equals("null")) {
//                node.right = new TreeNode(Integer.parseInt(vals[i]));
//                queue.add(node.right);
//            }
//            i++;
//        }
//        return root;
//    }
//}



// Q:不太懂为结点为null的话怎么处理
//public class Codec {
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            return "";
//        }
//        Queue<TreeNode> queue = new PriorityQueue<>();
//        StringBuilder stringBuilder = new StringBuilder();
//        while (!queue.isEmpty()) {
//            TreeNode temp = queue.poll();
//            if (temp.left != null) {
//                queue.add(temp.left);
//            }
//            if (temp.right != null) {
//                queue.add(temp.right);
//            }
//            stringBuilder.append(((char) temp.val));
//        }
//        return stringBuilder.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        return null;
//    }
//}

/**
 * This is end
 */
//leetcode submit region end(Prohibit modification and deletion)

