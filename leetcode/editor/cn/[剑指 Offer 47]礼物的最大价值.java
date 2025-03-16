//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
//
//
//
// 示例 1:
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
//
//
//
// 提示：
//
//
// 0 < grid.length <= 200
// 0 < grid[0].length <= 200
//
//
// Related Topics 数组 动态规划 矩阵 👍 336 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 不知道为啥会出现时间溢出的问题
//class Solution {
//
//    private int max = 0;
//
//    private int sum = 0;
//
//    public int maxValue(int[][] grid) {
//        maxReg(grid, 0, 0);
//        return max;
//    }
//
//    // 不是每一种递归相关的问题都需要用到递归返回值，这样能得到的信息也是有限的
//    // 很多只是需要递归这样一个中间过程，然后利用一些中间状态和信息
//    private void maxReg(int[][] grid, int i, int j) {
//        // 这个是一定要加的
//        sum += grid[i][j];
//        // 一定是递归到了右下角才能返回
//        // 或者叫做最底层的操作
//        if (i == grid.length-1 && j == grid[0].length-1) {
//            if (sum > max) {
//                // 全局判断这条完整路径的sum是否全局最大
//                max = sum;
//                // 注意这是return之前必须的操作
//                // 对于底层来说只是没了向下递归，故而只有向上返回
//                // 但是对于每一个中间层来说，有向下递归，向上返回及其操作也是必须的
//                sum -= grid[i][j];
//                return;
//            }
//        // 中间层的操作
//        // 撞到最下边只能向右走了
//        } else if (i == grid.length - 1 && j != grid[0].length - 1) {
//            maxReg(grid, i, j + 1);
//        // 撞到最右边只能向下走了
//        } else if (i != grid.length - 1 && j == grid[0].length - 1) {
//            maxReg(grid, i + 1, j);
//        // 都右下可以走
//        } else {
//            maxReg(grid, i + 1, j);
//            maxReg(grid, i, j+1);
//        }
//        // 注意对于每一个结点向下递归后，当它这一层都有个向上返回的动作！！！
//        sum -= grid[i][j];
//        return;
//    // 没有return的话，最后一个"}"就是默认return
//    }
//
//    public static void main(String[] args) {
//        int[][] grid = new int[][]{{1, 2, 5}, {3, 2, 1}};
//        Solution solution = new Solution();
//        int res = solution.maxValue(grid);
//        System.out.println(res);
//    }
//}
// 这样递归正确，但是数据多的话会超时
//class Solution {
//
//    private int sum = 0;
//    private int max = 0;
//
//    public int maxValue(int[][] grid) {
//        reg(grid, 0, 0);
//        return max;
//    }
//
//    private void reg(int[][] grid, int x, int y){
//        if(x == grid.length-1 && y == grid[0].length-1){
//            sum += grid[x][y];
//            if(sum > max){
//                max = sum;
//            }
//            sum -= grid[x][y];
//            return;
//        }else if(x == grid.length-1){
//            sum += grid[x][y];
//            reg(grid, x, y+1);
//        }else if(y == grid[0].length-1){
//            sum += grid[x][y];
//            reg(grid, x+1, y);
//        }else{
//            sum += grid[x][y];
//            reg(grid, x+1, y);
//            reg(grid, x, y+1);
//        }
//        sum -= grid[x][y];
//        return;
//    }
//}
// 感觉和哪一道题是相似的
// 用动态规划数组就不会超时了
// 动态规划可以理解为递归的逆过程，但是它优化了信息量，只保留了关键结果，展成了一维或者二维
// 动态规划就是自底向上的，所以本质上是符合回溯的
class Solution {

    public int maxValue(int[][] grid) {
        int[][] maxMatrix = new int[grid.length][grid[0].length];
        // 右下角的元素直接是它本身
        maxMatrix[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
        // 最下面那一行
        for (int i = grid[0].length - 2; i >= 0; i--) {
            maxMatrix[grid.length - 1][i] = grid[grid.length - 1][i] + maxMatrix[grid.length - 1][i + 1];
        }
        // 最右面那一列
        for (int i = grid.length - 2; i >= 0; i--) {
            maxMatrix[i][grid[0].length - 1] = grid[i][grid[0].length - 1] + maxMatrix[i + 1][grid[0].length - 1];
        }
        // 其它情况，先行后列进行循环，先列后行进行循环也可以
        for (int i = grid.length - 2; i >= 0; i--) {
            for (int j = grid[0].length - 2; j >= 0; j--) {
                maxMatrix[i][j] = Integer.max(maxMatrix[i + 1][j], maxMatrix[i][j + 1]) + grid[i][j];
            }
        }
        return maxMatrix[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
