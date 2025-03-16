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
 * 不保存额外的层级信息
 * 层序遍历比较特殊，因为层序遍历的序列足够表示结构了
 * 就是说每一层的每一个节点(队列中的头节点)的子节点一定是所有还未处理的节点中的头两个(序列中)
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "error";
        }
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(root);
        StringBuffer stringBuffer = new StringBuffer();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                stringBuffer.append("null,");
            }else {
                stringBuffer.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return stringBuffer.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("error")) {
            return null;
        }
        String[] elements = data.split(",");
        LinkedList<TreeNode> queue = new LinkedList();
        TreeNode root = new TreeNode(Integer.valueOf(elements[0]));
        queue.add(root);
        int p = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (p < elements.length && !elements[p].equals("null")) {
                node.left = new TreeNode(Integer.valueOf(elements[p]));
                queue.add(node.left);
            }
            p++;
            if (p < elements.length && !elements[p].equals("null")) {
                node.right = new TreeNode(Integer.valueOf(elements[p]));
                queue.add(node.right);
            }
            p++;
        }
        return root;
    }
}

/**
 * Success1
 * 用了额外的层级队列来存储层级信息
 */
//public class Codec {
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) {
//            return "error";
//        }
//        // 注意这里层级和节点是不同的数据结构，要用两个队列
//        LinkedList<Integer> queueLevel = new LinkedList();
//        LinkedList<TreeNode> queueNode = new LinkedList();
//        StringBuffer res = new StringBuffer();
//        queueLevel.add(0);
//        queueNode.add(root);
//        while(!queueNode.isEmpty()) {
//            int curLevel = queueLevel.poll();
//            TreeNode node = queueNode.poll();
//            // 这样更方便
//            // res.append(curLevel).append(",");
//            res.append(String.valueOf(curLevel) + ",");
//            if (node == null) {
//                res.append("null,");
//            }else {
//                // 注意这里append内容的形式处理
//                res.append(String.valueOf(node.val) + ",");
//                queueLevel.add(curLevel + 1);
//                queueNode.add(node.left);
//                queueLevel.add(curLevel + 1);
//                queueNode.add(node.right);
//            }
//        }
//        return res.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.equals("error")) {
//            return null;
//        }
//        String[] elements = data.split(",");
//        TreeNode root = new TreeNode(Integer.valueOf(elements[1]));
//        LinkedList<Integer> queueLevel = new LinkedList();
//        LinkedList<TreeNode> queueNode = new LinkedList();
//        queueLevel.add(0);
//        queueNode.add(root);
//        int p = 2;
//        while (!queueNode.isEmpty()) {
//            int curLevel = queueLevel.poll();
//            TreeNode node = queueNode.poll();
//            // 注意这里判断是curLevel+1，表示下一层的层数
//            if (p < elements.length && Integer.valueOf(elements[p]) == (curLevel + 1)) {
//                p++;
//                if (p < elements.length && !elements[p].equals("null")) {
//                    node.left = new TreeNode(Integer.valueOf(elements[p]));
//                    queueLevel.add(curLevel + 1);
//                    queueNode.add(node.left);
//                }
//                p++;
//            }
//            if (p < elements.length && Integer.valueOf(elements[p]) == (curLevel + 1)) {
//                p++;
//                if (p < elements.length && !elements[p].equals("null")) {
//                    node.right = new TreeNode(Integer.valueOf(elements[p]));
//                    queueLevel.add(curLevel + 1);
//                    queueNode.add(node.right);
//                }
//                p++;
//            }
//        }
//        return root;
//    }
//
//}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
