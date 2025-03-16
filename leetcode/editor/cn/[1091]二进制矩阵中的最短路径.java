//给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
//
// 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求
//：
//
//
// 路径途经的所有单元格的值都是 0 。
// 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
//
//
// 畅通路径的长度 是该路径途经的单元格总数。
//
//
//
// 示例 1：
//
//
//输入：grid = [[0,1],[1,0]]
//输出：2
//
//
// 示例 2：
//
//
//输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
//输出：4
//
//
// 示例 3：
//
//
//输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
//输出：-1
//
//
//
//
// 提示：
//
//
// n == grid.length
// n == grid[i].length
// 1 <= n <= 100
// grid[i][j] 为 0 或 1
//
//
// Related Topics 广度优先搜索 数组 矩阵 👍 355 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.Queue;

/**
 * Success1
 * BFS，注意BFS保证所有节点只访问一次
 */
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        // 矩阵的行数和列数
        int n = grid.length;

        // 检查起点和终点是否可达
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        // 8个可能的方向：上，下，左，右，以及四个对角方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

        // 队列用于BFS，存储每个节点的位置及其从起点开始的路径长度
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1}); // 初始位置和路径长度

        // BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1], pathLength = current[2];

            // 检查是否到达终点
            // 注意由于queue中的节点都是一层一层放的，所以在队列前面的只要到达了终点就一定是最短的，后面到达的节点只会比它更长
            if (row == n - 1 && col == n - 1) {
                return pathLength;
            }

            // 探索所有可能的方向
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // 检查新位置是否在矩阵内且可达
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0) {
                    queue.add(new int[]{newRow, newCol, pathLength + 1});
                    // 标记为已访问，这里直接利用grid就行
                    grid[newRow][newCol] = 1;
                }
            }
        }

        // 如果队列为空，说明没有找到路径
        return -1;
    }
}


/**
 * Failure1
 * DFS，正确，但如果是最优问题，DFS来遍历所有情况而不中途找到就返回的话很容易超时
 */
//class Solution {
//
//    private int res = 0;
//
//    private int finalRes = Integer.MAX_VALUE;
//
//    /**
//     * 因为不一定找得到，所以需要额外的状态标记
//     */
//    private boolean canFind = false;
//
//    private boolean[][] isVisited;
//
//    public int shortestPathBinaryMatrix(int[][] grid) {
//        isVisited = new boolean[grid.length][grid[0].length];
//        shortestReg(0, 0, grid);
//        if (canFind) {
//            return finalRes;
//        }
//        return -1;
//    }
//
//    private void shortestReg(int y, int x, int[][] grid) {
//        // 超出边界不处理
//        if (y >= grid.length || x >= grid[0].length || y < 0 || x < 0) {
//            return;
//        }
//        // 中间或者(n, n)行不通
//        if (grid[y][x] == 1 || isVisited[y][x]) {
//            return;
//        }
//        // 底层到达(n, n)的情况，且一定是等于0且没有被访问过的情况
//        if (y == (grid.length - 1) && x == (grid[0].length - 1)) {
//            canFind = true;
//            finalRes = Math.min(finalRes, res + 1);
//            return;
//        }
//        res += 1;
//        isVisited[y][x] = true;
//        // 这里是当前子问题传入到下级时希望是怎样的一个状态
//        // 8个方向去找，但是不加格外判断状态的话容易栈溢出
//        shortestReg(y + 1, x + 1, grid);
//        shortestReg(y + 1, x, grid);
//        shortestReg(y, x + 1, grid);
//        shortestReg(y - 1, x - 1, grid);
//        shortestReg(y - 1, x, grid);
//        shortestReg(y - 1, x + 1, grid);
//        shortestReg(y + 1, x - 1, grid);
//        shortestReg(y, x - 1, grid);
//        // 这里都是当前子问题返回到上级时希望是怎样的一个状态
//        res -= 1;
//        isVisited[y][x] = false;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[][] grid = new int[][]{{0, 0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 0}, {1, 0, 0, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 0, 1}, {0, 0, 1, 0, 0, 0, 0}};
//        int res = solution.shortestPathBinaryMatrix(grid);
//        System.out.println(res);
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
