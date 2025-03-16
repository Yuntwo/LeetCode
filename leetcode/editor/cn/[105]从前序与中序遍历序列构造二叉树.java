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

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 不需要在preorder里面找inRoot的位置，直接根据inorder里部分的个数来就可以了
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return regBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode regBuildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (preStart == preEnd) {
            return root;
        }
        int inRoot;
        for (inRoot = inStart; inRoot <= inEnd && inorder[inRoot] != root.val; inRoot++);
        root.left = regBuildTree(preorder, inorder, preStart + 1, preStart + inRoot - inStart, inStart, inRoot - 1);
        root.right = regBuildTree(preorder, inorder, preStart + inRoot - inStart + 1, preEnd, inRoot + 1, inEnd);
        return root;
    }
}

/**
 * Success1
 */
//class Solution {
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return regBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
//    }
//
//    private TreeNode regBuildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
//        if (preStart > preEnd || inStart > inEnd) {
//            return null;
//        }
//        TreeNode root = new TreeNode(preorder[preStart]);
//        if (preStart == preEnd) {
//            return root;
//        }
//        int rootIndex;
//        HashSet<Integer> hashSet = new HashSet();
//        for (rootIndex = inStart; rootIndex <= inEnd && inorder[rootIndex] != root.val; rootIndex++) {
//            hashSet.add(inorder[rootIndex]);
//        };
//        int rightStart;
//        for (rightStart = preStart + 1; rightStart <= preEnd && hashSet.contains(preorder[rightStart]); rightStart++);
//        root.left = regBuildTree(preorder, inorder, preStart + 1, rightStart - 1, inStart, rootIndex - 1);
//        root.right = regBuildTree(preorder, inorder, rightStart, preEnd, rootIndex + 1, inEnd);
//        return root;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
