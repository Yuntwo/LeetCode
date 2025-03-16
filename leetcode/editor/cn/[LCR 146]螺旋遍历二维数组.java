//给定一个二维数组 array，请返回「螺旋遍历」该数组的结果。
//
// 螺旋遍历：从左上角开始，按照 向右、向下、向左、向上 的顺序 依次 提取元素，然后再进入内部一层重复相同的步骤，直到提取完所有元素。
//
//
//
// 示例 1：
//
//
//输入：array = [[1,2,3],[8,9,4],[7,6,5]]
//输出：[1,2,3,4,5,6,7,8,9]
//
//
// 示例 2：
//
//
//输入：array  = [[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]
//输出：[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
//
//
//
//
// 限制：
//
//
// 0 <= array.length <= 100
// 0 <= array[i].length <= 100
//
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
//
//
//
// Related Topics 数组 矩阵 模拟 👍 595 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 思路很简单，但是注意控制边界条件和索引对应
 */
class Solution {
    public int[] spiralArray(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        boolean[][] called = new boolean[array.length][array[0].length];
        int[] res = new int[array.length * array[0].length];
        int index = -1;
        // 注意对于数组索引来说，x是纵轴，y是横轴
        int x = 0;
        int y = -1;
        while (!called[array.length / 2][(array[0].length - 1) / 2]) {
            // 注意控制边界条件
            while (y < (array[0].length - 1) && !called[x][y + 1]) {
                res[++index] = array[x][++y];
                called[x][y] = true;
            }
            while (x < (array.length - 1) && !called[x + 1][y]) {
                res[++index] = array[++x][y];
                called[x][y] = true;
            }
            while (y > 0 && !called[x][y - 1]) {
                res[++index] = array[x][--y];
                called[x][y] = true;
            }
            while (x > 0 && !called[x - 1][y]) {
                res[++index] = array[--x][y];
                called[x][y] = true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] res = solution.spiralArray(array);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
