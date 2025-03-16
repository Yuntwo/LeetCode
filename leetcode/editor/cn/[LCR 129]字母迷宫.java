//字母迷宫游戏初始界面记作 m x n 二维字符串数组 grid，请判断玩家是否能在 grid 中找到目标单词 target。 注意：寻找单词时 必须 按照字
//母顺序，通过水平或垂直方向相邻的单元格内的字母构成，同时，同一个单元格内的字母 不允许被重复使用 。
//
//
//
//
//
//
//
// 示例 1：
//
//
//输入：grid = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], target =
//"ABCCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：grid = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], target =
//"SEE"
//输出：true
//
//
// 示例 3：
//
//
//输入：grid = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], target =
//"ABCB"
//输出：false
//
//
//
//
// 提示：
//
//
// m == grid.length
// n = grid[i].length
// 1 <= m, n <= 6
// 1 <= target.length <= 15
// grid 和 target 仅由大小写英文字母组成
//
//
//
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
//
//
//
//
//
// Related Topics 数组 回溯 矩阵 👍 839 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * DFS
 */
class Solution {

    private boolean[][] isVisited;

    public boolean wordPuzzle(char[][] grid, String target) {
        isVisited = new boolean[grid.length][grid[0].length];
        // 因为开头位置不定，所以需要这样遍历一下
        // 同时找到了就返回，不要多做功
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (regExist(grid, target, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 这个函数的意义是能否在(y, x)开始包围的右下角矩阵中找到以word.charAt(index)为左上角开头的、到word.charAt(word.length - 1)结束(不管什么位置)的路径
     * 注意这个题是找到就可以，不像找最短那种必须便利所有情况，为了避免时间太长，找到了就应该提前返回
     * 即使是回溯，也是可以有返回值，带着返回值提前结束遍历的
     */
    private boolean regExist(char[][] board, String word, int index, int y, int x) {
        // 找到的情况，它应该是在后面两种不再寻找的情况之前判断
        if (index == word.length()) {
            return true;
        }
        // 超出范围，不再寻找
        if (y >= board.length || x >= board[0].length || y < 0 || x < 0) {
            return false;
        }
        // 不满足条件，不再寻找
        if (board[y][x] != word.charAt(index) || isVisited[y][x]) {
            return false;
        }
        isVisited[y][x] = true;
        // 继续向下找
        if (regExist(board, word, index + 1, y - 1, x) ||
            regExist(board, word, index + 1, y + 1, x) ||
            regExist(board, word, index + 1, y, x - 1) ||
            regExist(board, word, index + 1, y, x + 1)) {
            return true;
        }
        isVisited[y][x] = false;
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
