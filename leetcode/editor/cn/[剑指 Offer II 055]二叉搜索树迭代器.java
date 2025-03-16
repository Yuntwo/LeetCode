import java.util.LinkedList;

//实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
//
//
//
//
// BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出
//。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
// boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
// int next()将指针向右移动，然后返回指针处的数字。
//
//
//
//
// 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
//
// 可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
//
//
//
// 示例：
//
//
//
//
//输入
//inputs = ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next",
// "hasNext", "next", "hasNext"]
//inputs = [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
//
//输出
//[null, 3, 7, true, 9, true, 15, true, 20, false]
//
//解释
//BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
//bSTIterator.next();    // 返回 3
//bSTIterator.next();    // 返回 7
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 9
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 15
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 20
//bSTIterator.hasNext(); // 返回 False
//
//
//
//
// 提示：
//
//
// 树中节点的数目在范围 [1, 10⁵] 内
// 0 <= Node.val <= 10⁶
// 最多调用 10⁵ 次 hasNext 和 next 操作
//
//
//
//
// 进阶：
//
//
// 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高
//度。
//
//
//
//
//
// 注意：本题与主站 173 题相同： https://leetcode-cn.com/problems/binary-search-tree-
//iterator/
//
// Related Topics 栈 树 设计 二叉搜索树 二叉树 迭代器 👍 43 👎 0
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
 * Success2
 * 和Success1思路差不多，但是不修改原Tree，空间复杂度也是O(h)，迭代的思路，不是很好理解
 */
class BSTIterator {

    /**
     * 迭代的思路，Stack中的元素都是没有输出过的元素
     */
    private LinkedList<TreeNode> stack = new LinkedList<>();

    private TreeNode cur;

    public BSTIterator(TreeNode root) {
        this.cur = root;
    }

    public int next() {
        // 其实这里的while就是一个递归转迭代的过程，跳出循环的地方就是递归返回的地方，注意这里直接用的是cur==null而不是cur.left==null
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        // 中序输出
        TreeNode temp = stack.pop();
        // 这里又开始递归访问temp.right，注意这里的递归是在加深递归深度，前面的while循环是在减少递归深度
        cur = temp.right;
        return temp.val;
    }


    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}

/**
 * Review1
 * 还是Success1的做法，空间复杂度是O(h)，时间复杂度是O(n)
 * 唯一的缺点是修改了原Tree
 */
//class BSTIterator {
//
//    private LinkedList<TreeNode> stack = new LinkedList<>();
//
//    private TreeNode root;
//
//    public BSTIterator(TreeNode root) {
//        this.root = root;
//        stack.push(root);
//    }
//
//    public int next() {
//        while (stack.peek().left != null) {
//            TreeNode temp = stack.peek().left;
//            stack.peek().left = null;
//            stack.push(temp);
//        }
//        TreeNode temp = stack.poll();
//        if (temp.right != null) {
//            stack.push(temp.right);
//        }
//        return temp.val;
//    }
//
//    public boolean hasNext() {
//        return !stack.isEmpty();
//    }
//}

/**
 * Success1
 * 效率很高了，虽然没有100%
 * 其实就是手动堆栈过程
 * 注意堆栈是一个动态的反复的push和pop的过程，pop就是在返回结果，需要控制push和pop的时机
 */
//class BSTIterator {
//
//    public BSTIterator(TreeNode root) {
//        stack.push(root);
//    }
//
//    public int next() {
//        // Attention stack top implemented by LinkedList is at 0 index
//        TreeNode node = stack.peek();
//        while (node.left != null) {
//            stack.push(node.left);
//            // When we add a node to stack, we should break its relationship with left
//            // for advantage of returning
//            node.left = null;
//            node = stack.peek();
//        }
//        // Iter until we can get the ideal node
//        stack.pop();
//        // But we should also iter the right if it's not null
//        if (node.right != null) {
//            stack.push(node.right);
//        }
//        // No need to break relationship at right
//        return node.val;
//    }
//
//    public boolean hasNext() {
//        return !stack.isEmpty();
//    }
//
//    private LinkedList<TreeNode> stack = new LinkedList<>();
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(7, new TreeNode(3), new TreeNode(15, new TreeNode(9), new TreeNode(20)));
//        BSTIterator bstIterator = new BSTIterator(root);
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.hasNext());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//        System.out.println(bstIterator.next());
//    }
//}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)
