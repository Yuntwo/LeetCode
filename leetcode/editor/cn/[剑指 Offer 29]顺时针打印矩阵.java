//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
//
//
//
// 示例 1：
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
//
//
// 示例 2：
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//
//
// 限制：
//
//
// 0 <= matrix.length <= 100
// 0 <= matrix[i].length <= 100
//
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
// Related Topics 数组 矩阵 模拟 👍 385 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    public int[] spiralOrder(int[][] matrix) {
//        // Q:为了方便多个函数访问，我可以定义为成员变量吗？
//        // 但其实也不方便初始化
//        int[] res = new int[matrix.length*matrix[0][0]];
//        int pos = 0;
//        int gate = 0;
//        if (matrix.length % 2 == 0) {
//            gate = matrix.length / 2;
//        } else {
//            gate = matrix.length / 2 + 1;
//        }
//        for (int i = 0; i < gate; i++) {
//            // 向右移动
//            for (int j = i; j <= matrix[0].length-1 - i; j++) {
//                res[pos] = matrix[i][j];
//                pos++;
//            }
//            // 有条件地向下移动
//            if (i + 1 <= matrix.length / 2) {
//
//            }
//            pos = down(i + 1, matrix[0].length - i, matrix.length - i, matrix[0].length - i, res, pos);
//            // 向左移动
//            pos = left(matrix.length - i, matrix[0].length - i - 1, matrix.length - i, i, res, pos);
//            // 向上移动
//            pos = up(matrix.length - i - 1, i, i + 1, i, res, pos);
//        }
//        return res;
//    }
//}
// 注意它输入的是矩阵，不一定是方阵
class Solution {

    public int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return new int[0];
        }

        int[] res = new int[matrix.length * matrix[0].length];
        int pos = 0;

        // 用来标记访问过的数字不太方便赋
        // 但是用访问过来阻挡边界很方便啊
        int visited = -100000;
        // 从对角线遍历起走
        // 注意对角线索引要控制长和宽两种边界
        for (int i = 0; i < matrix.length && i < matrix[0].length && matrix[i][i] != visited; i++) {
            int a = i;
            int b = i;
            res[pos] = matrix[a][b];
            matrix[a][b] = visited;
            // 向左走
            while (b < matrix[0].length - 1 && matrix[a][b + 1] != visited) {
                b++;
                pos++;
                res[pos] = matrix[a][b];
                matrix[a][b] = visited;
            }
            // 向下走
            while (a < matrix.length - 1 && matrix[a + 1][b] != visited) {
                a++;
                pos++;
                res[pos] = matrix[a][b];
                matrix[a][b] = visited;
            }
            // 向右走
            while (b > 0 && matrix[a][b - 1] != visited) {
                b--;
                pos++;
                res[pos] = matrix[a][b];
                matrix[a][b] = visited;
            }
            // 向上走
            while (a > 0 && matrix[a - 1][b] != visited) {
                a--;
                pos++;
                res[pos] = matrix[a][b];
                matrix[a][b] = visited;
            }
            // 以上四种路径都走不通了说明这一圈已经结束
            // 但是pos还要++，因为一下轮会开个新头需要新的位置
            pos++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3}, {2}};
        Solution solution = new Solution();
        int[] res = solution.spiralOrder(matrix);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
