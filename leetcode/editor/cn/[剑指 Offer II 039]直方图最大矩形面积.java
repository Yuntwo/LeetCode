//给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
// 示例 1:
//
//
//
//
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
//
//
// 示例 2：
//
//
//
//
//输入： heights = [2,4]
//输出： 4
//
//
//
// 提示：
//
//
// 1 <= heights.length <=10⁵
// 0 <= heights[i] <= 10⁴
//
//
//
//
//
// 注意：本题与主站 84 题相同： https://leetcode-cn.com/problems/largest-rectangle-in-
//histogram/
//
// Related Topics 栈 数组 单调栈 👍 101 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Failure1
 * 直接覆盖，思路正确，代码正确，但是超时
 */
//class Solution {
//    public int largestRectangleArea(int[] heights) {
//        int res = 0;
//        for (int i = 0; i < heights.length; i++) {
//            int start = i;
//            int end = i;
//            // 注意这里的循环控制条件
//            while (start >= 0 && heights[start] >= heights[i]) {
//                start--;
//            }
//            while (end <= heights.length - 1 && heights[end] >= heights[i]) {
//                end++;
//            }
//            int temp = (end - start - 1) * heights[i];
//            res = temp > res ? temp : res;
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
//        int res = solution.largestRectangleArea(heights);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 思路和上面是一样的，但是用了单调栈来进行记录以减小时间复杂度，这个单调栈的设计有点巧妙，不容易想到
 * 递增栈，但是递增的是节点的start位置，在加栈的时候小于的节点就是end位置
 * 其实索引在添加的时候都是顺序添加的，只不过是pop的时候让索引有了更大差值，这个差值是解题的关键，也是这个单调栈设计的关键
 */
class Solution {

    /**
     * Success1
     */
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0;
        // 初始化方便宽的计算
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                // 注意这种peek又pop的写法
                res = Math.max(res, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        // 注意最后这里还有全部pop出来一次，最后的堆栈情况一定是全部正确的索引位置了
        while (stack.peek() != -1) {
            // 这里就是heights.length了，相当于又多加了一个
            res = Math.max(res, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return res;
     }

    /**
     * Failure1
     * 这么单纯地设计单调栈不行
     */
//    public int largestRectangleArea(int[] heights) {
//        LinkedList<Integer> stack = new LinkedList<>();
//        int res = 0;
//        int num = 0;
//        for (int i = 0; i < heights.length; i++) {
//            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
//                int temp = stack.pop();
//                num++;
//                int tempRes = num * heights[temp];
//                if (tempRes > res) {
//                    res = tempRes;
//                }
//            }
//            stack.push(i);
//        }
//        while (!stack.isEmpty()) {
//            int temp = stack.pop();
//            num++;
//            int tempRes = num * heights[temp];
//            if (tempRes > res) {
//                res = tempRes;
//            }
//        }
//        return res;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
