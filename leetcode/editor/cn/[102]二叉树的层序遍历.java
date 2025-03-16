//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
//
//
// 示例 2：
//
//
//输入：root = [1]
//输出：[[1]]
//
//
// 示例 3：
//
//
//输入：root = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 2000] 内
// -1000 <= Node.val <= 1000
//
//
// Related Topics 树 广度优先搜索 二叉树 👍 1206 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

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
 * Review4
 * dfs拿捏
 */
class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level > res.size() - 1) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
        } else {
            res.get(level).add(root.val);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}

/**
 * Review3
 * 队列思路
 * 根本不需要额外的数据结构维护当前层级，你就直接一层一层加就可以了，管你是第几层，很间接优雅的思路
 */
//class Solution {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        if (root == null) {
//            return new ArrayList<>();
//        }
//        List<List<Integer>> res = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            List<Integer> list = new ArrayList<>();
//            for (int i = queue.size(); i > 0; i--) {
//                TreeNode node = queue.poll();
//                list.add(node.val);
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//            res.add(list);
//        }
//        return res;
//    }
//}
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
 * Review2
 * 递归最简解法
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    /**
//     * Sucess2
//     * 更简洁的写法，把TreeNode为null的情况放到dfs中考虑，不管null都加进去，这种递归返回的写法都是最简洁的
//     */
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        dfs(root, 1);
//        return res;
//    }
//
//    private void dfs(TreeNode node, int height) {
//        // null直接返回，也不会再多加[]
//        if (node == null) {
//            return;
//        }
//        // 这是新的一层的第一个节点
//        if (height > res.size()) {
//            res.add(new ArrayList<>());
//        }
//        res.get(height - 1).add(node.val);
//        // 不管null都加进去，在递归中判断null返回，这种递归写法都是最简洁的
//        dfs(node.left, height + 1);
//        dfs(node.right, height + 1);
//    }
//
///**
// * Success1
// */
////    public List<List<Integer>> levelOrder(TreeNode root) {
////        if (root == null) {
////            return res;
////        }
////        dfs(root, 1);
////        return res;
////    }
////
////    private void dfs(TreeNode node, int height) {
////        // 这是新的一层的第一个节点
////        if (height > res.size()) {
////            res.add(new ArrayList<>());
////        }
////        res.get(height - 1).add(node.val);
////        if (node.left != null) {
////            dfs(node.left, height + 1);
////        }
////        if (node.right != null) {
////            dfs(node.right, height + 1);
////        }
////    }
//
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        // 测试用例1
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        // 测试用例2
////        TreeNode root = new TreeNode(1);
////        TreeNode temp = root;
////        for (int i = 2; i <= 5; i++) {
////            temp.left = new TreeNode(i);
////            temp = temp.left;
////        }
//
//        List<List<Integer>> res = solution.levelOrder(root);
//        System.out.println(root);
//    }
//}

/**
 * Review1
 * 两个队列实现
 */
//class Solution {
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        if (root == null) {
//            // 注意这里虽然root也是null，但是不能返回root，类型不对
//            // 也不能返回null
//            return new ArrayList<>();
//        }
//        List<List<Integer>> res = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        Queue<Integer> height = new LinkedList<>();
//        queue.add(root);
//        height.add(0);
//        int heightNow = -1;
//        while (!queue.isEmpty()) {
//            TreeNode temp = queue.poll();
//            int heightCur = height.poll();
//            // 注意==
//            if (heightCur == (heightNow + 1)) {
//                res.add(new ArrayList<>());
//                heightNow++;
//            }
//            res.get(heightCur).add(temp.val);
//            // 注意要先加左边再加右边
//            if (temp.left != null) {
//                queue.add(temp.left);
//                height.add(heightCur + 1);
//            }
//            if (temp.right != null) {
//                queue.add(temp.right);
//                height.add(heightCur + 1);
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        // 测试用例1
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        // 测试用例2
////        TreeNode root = new TreeNode(1);
////        TreeNode temp = root;
////        for (int i = 2; i <= 5; i++) {
////            temp.left = new TreeNode(i);
////            temp = temp.left;
////        }
//
//        List<List<Integer>> res = solution.levelOrder(root);
//        System.out.println(root);
//    }
//}

/**
 * Success4
 * 利用链表类来记录dfs过程中需要添加的list的位置，时间效率没有比用队列那种方法高
 * 本质上和Reference1的递归思路是一样的，只是数据结构用复杂了，时间效率也就下去了，直接用index就够了
 */
//class Solution {
//    class LinkNode {
//        public List<Integer> cur;
//
//        public LinkNode next;
//
//        public LinkNode(List<Integer> list) {
//            cur = list;
//        }
//    }
//
//    private List<List<Integer>> res = new LinkedList<>();
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//
//        if (root == null) {
//            return res;
//        }
//        LinkNode linkNode = new LinkNode(new ArrayList<>());
//        res.add(linkNode.cur);
//        levelSearch(root, linkNode);
//        // 注意最后一层一定多了个[]，要删去
//        res.remove(res.size() - 1);
//        return res;
//    }
//
//    private void levelSearch(TreeNode root, LinkNode linkNode) {
//        if (root == null) {
//            return;
//        }
//        linkNode.cur.add(root.val);
//        if (linkNode.next == null) {
//            linkNode.next = new LinkNode(new ArrayList<>());
//            res.add(linkNode.next.cur);
//        }
//        levelSearch(root.left, linkNode.next);
//        levelSearch(root.right, linkNode.next);
//    }
//}

/**
 * Review2
 */
//class Solution {
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> res = new LinkedList<>();
//        reg(root, res, 0);
//        return res;
//    }
//
//    private void reg(TreeNode root, List<List<Integer>> list, int level) {
//        if (root == null) {
//            return;
//        }
//        if (level > list.size() - 1) {
//            list.add(new LinkedList<Integer>());
//        }
//        list.get(level).add(root.val);
//        reg(root.left, list, level + 1);
//        reg(root.right, list, level + 1);
//    }
//
//}

/**
 * Review1
 * 递归思路自己写一遍
 * 注意其实对于树结构的递归只能是深度遍历，但是你既然都访问到了，那么肯定是能达成广度遍历的
 * 关键在于你要用一些额外的变量来记录你想要的中间值
 */
//class Solution {
//
//    // 用来存放最终结果
//    // 注意这里的new ArrayList只有一个外壳，里面的List<Integer>还没有生成
//    private List<List<Integer>> res = new ArrayList<List<Integer>>();
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        if (root == null) {
//            return new ArrayList<List<Integer>>();
//        }
//        // 将第一个深度定义为0
//        dfs(root, 0);
//        return res;
//    }
//
//    private void dfs(TreeNode now, int depth) {
//        if ((res.size() - 1) < depth) {
//            // 注意有索引的add也只能在已有的那些元素中插入
//            res.add(new ArrayList<Integer>());
//        }
//        if (now.left == null && now.right == null) {
//            // 这里似乎可以直接int当做Integer
//            res.get(depth).add(now.val);
//            return;
//        }
//        if (now.left != null && now.right != null) {
//            res.get(depth).add(now.val);
//            dfs(now.left, depth + 1);
//            dfs(now.right, depth + 1);
//        } else if (now.left != null) {
//            res.get(depth).add(now.val);
//            dfs(now.left, depth + 1);
//        } else {
//            res.get(depth).add(now.val);
//            dfs(now.right, depth + 1);
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        // 测试用例1
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        // 测试用例2
////        TreeNode root = new TreeNode(1);
////        TreeNode temp = root;
////        for (int i = 2; i <= 5; i++) {
////            temp.left = new TreeNode(i);
////            temp = temp.left;
////        }
//
//        List<List<Integer>> res = solution.levelOrder(root);
//        System.out.println(root);
//    }
//}

/**
 * Best
 * Reference1
 * 递归解法，真的很快，100%，不太好理解
 */
//class Solution {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        if(root==null) {
//            return new ArrayList<List<Integer>>();
//        }
//        //用来存放最终结果
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        dfs(1,root,res);
//        return res;
//    }
//
//    void dfs(int index,TreeNode root, List<List<Integer>> res) {
//        //假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
//        if(res.size()<index) {
//            res.add(new ArrayList<Integer>());
//        }
//        //将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
//        //res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
//        res.get(index-1).add(root.val);
//        //递归的处理左子树，右子树，同时将层数index+1
//        if(root.left!=null) {
//            dfs(index+1, root.left, res);
//        }
//        if(root.right!=null) {
//            dfs(index+1, root.right, res);
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution test = new Solution();
//        // 测试用例1
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        // 测试用例2
////        TreeNode root = new TreeNode(1);
////        TreeNode temp = root;
////        for (int i = 2; i <= 5; i++) {
////            temp.left = new TreeNode(i);
////            temp = temp.left;
////        }
//
//        List<List<Integer>> res = test.levelOrder(root);
//        System.out.println(root);
//    }
//}

/**
 * Success3
 * 用两个队列，这样就省去了计数的麻烦
 * 但是似乎并没有更快
 */
//class Solution {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        if (root == null) {
//            return ret;
//        }
//        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
//        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
//        queue1.offer(root);
//        List<Integer> level;
//        // 一定是一个队列为空一个队列不为空交替来的，当两个队列都为空的时候跳出循环
//        while (!queue1.isEmpty() || !queue2.isEmpty()) {
//            level = new ArrayList<Integer>();
//            while (!queue1.isEmpty()) {
//                TreeNode cur = queue1.poll();
//                level.add(cur.val);
//                if (cur.left != null) {
//                    queue2.offer(cur.left);
//                }
//                if (cur.right != null) {
//                    queue2.offer(cur.right);
//                }
//            }
//            if (level.size() != 0) {
//                ret.add(level);
//            }
//            level = new ArrayList<Integer>();
//            while (!queue2.isEmpty()) {
//                TreeNode cur = queue2.poll();
//                level.add(cur.val);
//                if (cur.left != null) {
//                    queue1.offer(cur.left);
//                }
//                if (cur.right != null) {
//                    queue1.offer(cur.right);
//                }
//            }
//            if (level.size() != 0) {
//                ret.add(level);
//            }
//        }
//        return ret;
//    }
//}

/**
 * Success2
 * 本质和下面的循环思路一样，时间复杂度都是一样的，只是可能用的Queue数据结构空间复杂度小点
 */
//class Solution {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        if (root == null) {
//            return ret;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            List<Integer> level = new ArrayList<Integer>();
//            int currentLevelSize = queue.size();
//            for (int i = 1; i <= currentLevelSize; ++i) {
//                TreeNode node = queue.poll();
//                level.add(node.val);
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//            ret.add(level);
//        }
//
//        return ret;
//    }
//}

/**
 * Success1
 * 这样通过记录上一层的结点数从而控制出队数的时间效率还行
 */
//class Solution {
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        // 不太懂这里的类嵌套逻辑
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        // 注意这样写的话还是会有编译错误
////        List<List<Integer>> res = new ArrayList<ArrayList<Integer>>();
//        // 注意这样写的话会有编译错误
////        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
//        if (root == null) {
//            return res;
//        }
//        // 注意这里限制了声明为List，LinkedList有但是List没有的方法就不能用
//        // 所以不能直接用getFirst和addLast实现队列，只能单独实现
//        List<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        // 注意List的特殊性，头结点的索引始终为0
//        int head = 0;
//        // 注意List的长度用size()而不是length()，当然这里的rear可以直接加减操作，不一定要用size()-1
//        int rear = 0;
//        int level = 0;
//        // 记录当前循环层有多少个结点，这个数是由上一步迭代确定的
//        int num = 1;
//        int tempNum = 0;
//        // 如果把null加进去，那么就不能用queue.isEmpty()判断队列里真正的元素是否访问完毕
//        while (!queue.isEmpty()) {
//            List<Integer> oneRes = new ArrayList<Integer>();
////            for (int i = 0; i < pow(2, level); i++) {
//            // 这里就不全部遍历加null了
//            tempNum = 0;
//            for (int i = 0; i < num; i++) {
//                // 注意外层循环判断isEmpty()是整体是否还有元素可以遍历
//                // 而内层还要判一次isEmpty()是在遍历这一层时，因为for循环的次数是固定的，而如果这一层
//                // 有null的元素，就会导致访问越界。但是注意queue是整体的元素，而不是这一层的元素，所以也不能用下面这个语句判断
////                if (queue.isEmpty()) {
////                    break;
////                }
//                // 注意getFirst方法不会删除List中的元素
//                TreeNode cur = queue.remove(head);
//                rear--;
////                TreeNode cur = queue.getFirst();
//                if (cur != null) {
//                    oneRes.add(cur.val);
//                    // 注意这里一定要判null，否则queue.add之后size还会增长，rear也会
//                    if (cur.left != null) {
//                        queue.add(cur.left);
//                        rear++;
//                        tempNum++;
//                    }
//                    if (cur.right != null) {
//                        queue.add(cur.right);
//                        rear++;
//                        tempNum++;
//                    }
//                }
//            }
//            num = tempNum;
//            res.add(oneRes);
//            level++;
//        }
//        return res;
//    }
//
//    private int pow(int base, int e) {
//        if (e == 0) {
//            return 1;
//        }
//        int res = 1;
//        for (int i = 0; i < e; i++) {
//            res *= base;
//        }
//        return res;
//    }
//}

/**
 * Failure1
 * 循环实现
 * 这种实现方法执行效率不高，Time Limit Exceeded
 */
//class Solution {
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        // 不太懂这里的类嵌套逻辑
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//        // 注意这样写的话还是会有编译错误
////        List<List<Integer>> res = new ArrayList<ArrayList<Integer>>();
//        // 注意这样写的话会有编译错误
////        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
//        if (root == null) {
//            return res;
//        }
//        // 注意这里限制了声明为List，LinkedList有但是List没有的方法就不能用
//        // 所以不能直接用getFirst和addLast实现队列，只能单独实现
//        List<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        // 注意List的特殊性，头结点的索引始终为0
//        int head = 0;
//        // 注意List的长度用size()而不是length()，当然这里的rear可以直接加减操作，不一定要用size()-1
//        int rear = 0;
//        int level = 0;
//        boolean isOver = false;
//        // 如果把null加进去，那么就不能用queue.isEmpty()判断队列里真正的元素是否访问完毕
//        while (isOver == false) {
//            isOver = true;
//            List<Integer> oneRes = new ArrayList<Integer>();
//            for (int i = 0; i < pow(2, level); i++) {
//                // 注意外层循环判断isEmpty()是整体是否还有元素可以遍历
//                // 而内层还要判一次isEmpty()是在遍历这一层时，因为for循环的次数是固定的，而如果这一层
//                // 有null的元素，就会导致访问越界。但是注意queue是整体的元素，而不是这一层的元素，所以也不能用下面这个语句判断
////                if (queue.isEmpty()) {
////                    break;
////                }
//                // 注意getFirst方法不会删除List中的元素
//                TreeNode cur = queue.remove(head);
//                rear--;
////                TreeNode cur = queue.getFirst();
//                if (cur != null) {
//                    oneRes.add(cur.val);
//                    // 注意这里一定要判null，否则queue.add之后size还会增长，rear也会
//                    // 但是这样会导致缺项遍历不方便的问题
////                    if (cur.left != null) {
////                        queue.add(cur.left);
////                        rear++;
////                    }
////                    if (cur.right != null) {
////                        queue.add(cur.right);
////                        rear++;
////                    }
//                    queue.add(cur.left);
//                    rear++;
//                    queue.add(cur.right);
//                    rear++;
//                    // 只要这一层的孩子中有一个不为null，那么继续遍历
//                    if (cur.left != null || cur.right != null) {
//                        isOver = false;
//                    }
//                    // 注意这里结点为null也要添加为null的左右结点占个坑
//                    // 但这样会导致的问题就是无法判断什么时候真正的树访问完了，无法跳出循环
//                    // 那么必须单独设一个变量控制
//                } else {
//                    queue.add(null);
//                    rear++;
//                    queue.add(null);
//                    rear++;
//                }
//            }
//            res.add(oneRes);
//            level++;
//        }
//        return res;
//    }
//
//    private int pow(int base, int e) {
//        if (e == 0) {
//            return 1;
//        }
//        int res = 1;
//        for (int i = 0; i < e; i++) {
//            res *= base;
//        }
//        return res;
//    }
//}

/**
 * This is end
 */
//leetcode submit region end(Prohibit modification and deletion)
