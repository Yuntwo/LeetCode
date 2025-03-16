//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
// 问总共有多少条不同的路径？
//
//
//
// 示例 1：
//
//
//
//
//输入：m = 3, n = 7
//输出：28
//
// 示例 2：
//
//
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
//
//
// 示例 3：
//
//
//输入：m = 7, n = 3
//输出：28
//
//
// 示例 4：
//
//
//输入：m = 3, n = 3
//输出：6
//
//
//
// 提示：
//
//
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 10⁹
//
//
//
//
// 注意：本题与主站 62 题相同： https://leetcode-cn.com/problems/unique-paths/
// Related Topics 数学 动态规划 组合数学 👍 13 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 自顶向下递归记忆化搜索
 */
//class Solution {
//
//    private int[][] path;
//
//    public int uniquePaths(int m, int n) {
//        path = new int[m][n];
//        return unique(0, 0, m-1, n-1);
//    }
//
//    private int unique(int m, int n, int mEnd, int nEnd) {
//        // 注意走到底的情况都只有一条路了，这是底层情况
//        // m == mEnd - 1 && n == nEnd - 1 的情况也是可以推导的
//        if (n == nEnd || m == mEnd) {
//            return 1;
//        }
//        if (path[m][n + 1] != 0 && path[m + 1][n] != 0) {
//            path[m][n] = path[m][n + 1] + path[m + 1][n];
//        } else if (path[m][n + 1] != 0) {
//            path[m][n] = path[m][n + 1] + unique(m + 1, n, mEnd, nEnd);
//        } else if (path[m + 1][n] != 0) {
//            path[m][n] = unique(m, n + 1, mEnd, nEnd) + path[m + 1][n];
//        } else {
//            path[m][n] = unique(m, n + 1, mEnd, nEnd) + unique(m + 1, n, mEnd, nEnd);
//        }
//        return path[m][n];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int res = solution.uniquePaths(3, 7);
//        System.out.println(res);
//    }
//}
/**
 * 自底向上动态规划
 * 当然如果这个是二维数组的话，其实迭代是不太方便的，感觉找不到一个比较好的线性迭代路径
 */
class Solution {

    public int uniquePaths(int m, int n) {
        int[][] path = new int[m][n];
        int i = m - 1;
        int j = n - 1;

        while (i >= 0 && j >= 0) {

        }
    }

    public static void main(String[] args) {
        TestSolution solution = new TestSolution();
        int res = solution.uniquePaths(3, 7);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
