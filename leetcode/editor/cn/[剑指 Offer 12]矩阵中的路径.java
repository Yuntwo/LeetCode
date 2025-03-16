//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
//
//
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
//"ABCCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
// Related Topics 数组 回溯 矩阵 👍 500 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 递归法
// 如果是用循环法的话，这里因为在循环过程中需要回溯，所以需要之前的很多状态，不太方便，或者除非你再次前向循环找一遍
//class Solution {
//    private class Node {
//        // 注意定义为私有的变量
//        private int row;
//        private int col;
//        // 标准获得方法
//        public int getCol() {
//            return col;
//        }
//
//        public int getRow() {
//            return row;
//        }
//
//        // 构造函数写法
//        public Node(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//    }
//
//    public boolean exist(char[][] board, String word) {
//        // 判断是否被访问过
//        boolean[][] isVisited = new boolean[board.length][board[0].length];
//
//        // 先找到第一个字符的所有位置
//        // 存储第一个字符的所有位置
//        LinkedList<Node> firstChar = new LinkedList<Node>();
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < boad[0].length; j++) {
//                if (board[i][j] == word.charAt(0)) {
//                    firstChar.add(new Node(i, j));
//                }
//            }
//        }
//
//        // 一次从第一个字符开始判断
//        while (!firstChar.isEmpty()) {
//            Node node = firstChar.getFirst();
//            int row = node.getRow();
//            int col = node.getCol();
//
//        }
//
//        // 上述情况都没有找到return false
//        return false;
//
//    }
//
//    private void step(int n) {
//
//    }
//}
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        // 这是从board中每一个不同的开头开始dfs寻找word
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // k表示匹配word的第k个char
    // 递归嘛，就要定义通用的函数接口
    private boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        // 越界还没有找到匹配的返回情况
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0) {
            return false;
        }
        // 当前char不匹配的返回情况
        if (board[i][j] != word[k]) {
            return false;
        }

        /*
        * 注意由于前一种情况排除了board[i][j]!=word[k]，所以后面的判断情况一定是在匹配的提前下
        */

        // 可以直接修改原数组来进行标记，而不需要额外的空间
        // 而且由于匹配的话board[i][j]=word[k]，这样回溯的时候也不需要额外暂存这个值了，只能说很巧妙
        board[i][j] = '\0';

        // 成功匹配到word的最后一个字符返回情况
        if (k == word.length - 1) {
            return true;
        }

        // 上下左右四个角进行递归寻找word的下一个char
        boolean res = dfs(board, word, i + 1, j, k + 1) ||
                      dfs(board, word, i - 1, j, k + 1) ||
                      dfs(board, word, i, j + 1, k + 1) ||
                      dfs(board, word, i, j - 1, k + 1);
        // 其实不是很懂，但是能到下面这一步，说明已经递归返回了，那么不管找没找到，都重置board[i][j]的值
        // 如果没有找到，方便另辟路径，如果找到了，重置对于返回值没有任何影响？？这里其实不是很直观能懂
        board[i][j] = word[k];
        return res;
    }
}


//leetcode submit region end(Prohibit modification and deletion)
