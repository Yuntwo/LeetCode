//给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
//
// 注意：此题 matrix 输入格式为一维 01 字符串数组。
//
//
//
// 示例 1：
//
//
//
//
//输入：matrix = ["10100","10111","11111","10010"]
//输出：6
//解释：最大矩形如上图所示。
//
//
// 示例 2：
//
//
//输入：matrix = []
//输出：0
//
//
// 示例 3：
//
//
//输入：matrix = ["0"]
//输出：0
//
//
// 示例 4：
//
//
//输入：matrix = ["1"]
//输出：1
//
//
// 示例 5：
//
//
//输入：matrix = ["00"]
//输出：0
//
//
//
//
// 提示：
//
//
// rows == matrix.length
// cols == matrix[0].length
// 0 <= row, cols <= 200
// matrix[i][j] 为 '0' 或 '1'
//
//
//
//
// 注意：本题与主站 85 题相同（输入参数格式不同）： https://leetcode-cn.com/problems/maximal-
//rectangle/
//
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 84 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.Stack;

/**
 * BEGIN
 */

/**
 * Success2
 * 转化为直方图扫描，思路也很清晰，效率并没有比直接扫描好很多
 */
class Solution {
    public int maximalRectangle(String[] matrix) {
        // 注意数组为空的情况要单独判断，因为后面的逻辑没有包括这种情况
        if (matrix.length == 0) {
            return 0;
        }
        int res = 0;
        // 注意这里索引matrix[0]要求matrix不为空，就算前面不判空这里索引的时候也是必须要判空的
        int[] nums = new int[matrix[0].length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if (matrix[i].charAt(j) == '1') {
                    nums[j] += 1;
                } else {
                    nums[j] = 0;
                }
            }
            res = Math.max(res, maximalHist(nums));
        }
        return res;
    }

    private int maximalHist(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        // 注意用这个-1来代替判空栈可以使问题更简单
        // 而且-1这个数就是按照数组索引递减来的，够用了
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 注意这里是小于，小于才算排除外的边界，就算是等于也是可以算在这个直方图面积中的
            // 注意这里比较都是nums
            while (stack.peek() != -1 && nums[i] < nums[stack.peek()]) {
                // 注意这里要用pop
                res = Math.max(res, nums[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            res = Math.max(res, nums[stack.pop()] * (nums.length - stack.peek() - 1));
        }
        return res;
    }

    /**
     * Failure1
     * 注意这里不要直接stack判空，有可能出现peek空访问的情况
     */
//    private int maximalHist(int[] nums) {
//        LinkedList<Integer> stack = new LinkedList<>();
//        int res = 0;
//        for (int i = 0; i < nums.length; i++) {
//            while (!stack.isEmpty() && nums[i] < stack.peek()) {
//                res = Math.max(res, nums[stack.poll()] * (i - stack.peek() - 1));
//            }
//            stack.add(i);
//        }
//        while (!stack.isEmpty()) {
//            res = Math.max(res, nums[stack.poll()] * (nums.length - stack.peek() - 1));
//        }
//        return res;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] matrix = new String[]{"10100", "10111", "11111", "10010"};
        int res = solution.maximalRectangle(matrix);
        System.out.println(res);
    }
}

/**
 * Success1
 * 暴力扫描法，直观易懂，效率还不错
 */
//class Solution {
//    public int maximalRectangle(String[] matrix) {
//
//        if (matrix.length == 0) {
//            return 0;
//        }
//
//        int res = 0;
//
//        boolean[][] nums = new boolean[matrix.length][matrix[0].length()];
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length(); j++) {
//                if (matrix[i].charAt(j) == '1') {
//                    nums[i][j] = true;
//                } else {
//                    nums[i][j] = false;
//                }
//            }
//        }
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length(); j++) {
//                if (!nums[i][j]) {
//                    continue;
//                }
//                int row;
//                int line = matrix[0].length();
//                for (row = i; row < matrix.length && nums[row][j]; row++) {
//                    for (int l = j + 1; l < line; l++) {
//                        if (!nums[row][l]) {
//                            line = l;
//                        }
//                    }
//                    // 注意需要再每一行扫描结束时都计算一次面积，而不是找到最小的长和宽再计算，这样会漏掉中间可能更大的情况
//                    // 注意这里计算面积row在循环内，所以是true满足条件的，所以要+1
//                    res = Math.max(res, (row - i + 1) * (line - j));
//                }
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String[] matrix = new String[]{"10100", "10111", "11111", "10010"};
//        int res = solution.maximalRectangle(matrix);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 动态规划，企图用右下角矩形+向右长条+向下长条构建递归关系，但是这样的规律是不完备、错误的
 */
//class Solution {
//    public int maximalRectangle(String[] matrix) {
//
//        int res = 0;
//
//        boolean[][] nums = new boolean[matrix.length][matrix[0].length()];
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length(); j++) {
//                if (matrix[i].charAt(j) == '1') {
//                    nums[i][j] = true;
//                } else {
//                    nums[i][j] = false;
//                }
//            }
//        }
//
//        int[][] level = new int[matrix.length][matrix[0].length()];
//        int[][] vertical = new int[matrix.length][matrix[0].length()];
//        int[][][] diagonal = new int[matrix.length][matrix[0].length()][2];
//
//        level[matrix.length - 1][matrix[0].length() - 1] = nums[matrix.length - 1][matrix[0].length() - 1] ? 1 : 0;
//        vertical[matrix.length - 1][matrix[0].length() - 1] = nums[matrix.length - 1][matrix[0].length() - 1] ? 1 : 0;
//        diagonal[matrix.length - 1][matrix[0].length() - 1][0] = nums[matrix.length - 1][matrix[0].length() - 1] ? 1 : 0;
//        diagonal[matrix.length - 1][matrix[0].length() - 1][1] = nums[matrix.length - 1][matrix[0].length() - 1] ? 1 : 0;
//        // 最右列，列不变，行改变
//        for (int i = matrix.length - 2; i >= 0; i--) {
//            level[i][matrix[0].length() - 1] = nums[i][matrix[0].length() - 1] ? 1 : 0;
//            vertical[i][matrix[0].length() - 1] = nums[i][matrix[0].length() - 1] ? vertical[i + 1][matrix[0].length() - 1] + 1 : 0;
//            diagonal[i][matrix[0].length() - 1][0] = level[i][matrix[0].length() - 1];
//            diagonal[i][matrix[0].length() - 1][1] = vertical[i][matrix[0].length() - 1];
//        }
//        // 最底行，行不变，列改变
//        for (int i = matrix[0].length() - 2; i >= 0; i--) {
//            vertical[matrix.length - 1][i] = nums[matrix.length - 1][i] ? 1 : 0;
//            level[matrix.length - 1][i] = nums[matrix.length - 1][i] ? level[matrix.length - 1][i + 1] + 1 : 0;
//            diagonal[matrix.length - 1][i][0] = level[matrix.length - 1][i];
//            diagonal[matrix.length - 1][i][1] = vertical[matrix.length - 1][i];
//        }
//
//        for (int i = matrix.length - 2; i >= 0; i--) {
//            for (int j = matrix[0].length() - 2; j >= 0; j--) {
//                level[i][j] = nums[i][j] ? level[i][j + 1] + 1 : 0;
//                vertical[i][j] = nums[i][j] ? vertical[i + 1][j] + 1 : 0;
//                diagonal[i][j][0] = nums[i][j] ? Math.min(level[i][j + 1], diagonal[i+1][j + 1][0]) + 1 : 0;
//                diagonal[i][j][1] = nums[i][j] ? Math.min(vertical[i + 1][j], diagonal[i + 1][j + 1][1]) + 1 : 0;
//                res = Math.max(res, Math.max(level[i][j], Math.max(vertical[i][j], diagonal[i][j][0] * diagonal[i][j][1])));
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String[] matrix = new String[]{"10100", "10111", "11111", "10010"};
//        int res = solution.maximalRectangle(matrix);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
