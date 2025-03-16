//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
//
//
// 参考以下这颗二叉搜索树：
//
//      5
//    / \
//   2   6
//  / \
// 1   3
//
// 示例 1：
//
// 输入: [1,6,3,2,5]
//输出: false
//
// 示例 2：
//
// 输入: [1,3,2,6,5]
//输出: true
//
//
//
// 提示：
//
//
// 数组长度 <= 1000
//
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 👍 531 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 注意审题，说的是二叉搜索树，它的特征是左小右大
class Solution {

    // 注意这样声明应该只是在初始化时赋值一次
    private boolean res = true;

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return res;
        }
        verify(0, postorder.length-1,postorder );
        return res;
    }

    // 这里end就是直接的root的索引位置
    private void verify(int leftStart, int end, int[] postorder) {
        int rootNum = postorder[end];
        int rightStart;
        // 判断是否为二叉搜索树的关键就是能够完全划分为这样两部分，所谓后序遍历不过是结构特点基础而已，不是逻辑判断的关键
        // 注意循环控制变量可以在循环外声明，但是必须要for循环内进行一次赋值，它才知道从哪儿开始
        for (rightStart = leftStart; rightStart <= end - 1 && postorder[rightStart] < rootNum; rightStart++) {
            continue;
        }
        // 注意有可能找不到比root大的分支，此时rightStart = end
        // Q:不太清楚其它临界返回情况是这个return涵盖了吗？？？
        if (rightStart != end) {
            for (int i = rightStart; i <= end - 1; i++) {
                if (postorder[i] <= rootNum) {
                    res = false;
                    return;
                }
            }
            verify(rightStart, end - 1, postorder);
        }
        // 注意有可能找不到比root小的分支，此时rightStart = leftStart
        if (rightStart != leftStart) {
            verify(leftStart, rightStart - 1, postorder);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
