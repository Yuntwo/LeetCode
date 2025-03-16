//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
//
//
//
// 示例 1：
//
// 输入：m = 2, n = 3, k = 1
//输出：3
//
//
// 示例 2：
//
// 输入：m = 3, n = 1, k = 0
//输出：1
//
//
// 提示：
//
//
// 1 <= n,m <= 100
// 0 <= k <= 20
//
// Related Topics 深度优先搜索 广度优先搜索 动态规划 👍 432 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 如果在声明时初始化赋值，不知道这样不在构造函数中初始化标不标准
    // 需要额外用到的比较全局的变量
    private int grimSum;

    private boolean isVisited[][];

    // 主要是不想在多个函数之间传递参数
    private int m;
    private int n;
    private int k;

    // 天哪，把这个函数当做构造函数来初始化一些值了，虽然它一定会被执行，但还是不规范
    // 构造函数的独特之处在于创建类时这个构造函数一定会被执行，那些初始化语句一定会被执行，放在其它函数中初始化就不一定了
    public int movingCount(int m, int n, int k) {
        this.grimSum = 0;
        this.isVisited = new boolean[m][n];  //  应该是默认值为false
        this.m = m;
        this.n = n;
        this.k = k;
        // 注意不管m, n, k是多少，search这个函数始终是从(0, 0)这个位置开始递归
        this.search(0, 0);
        return this.grimSum;
    }

    private void search(int row, int col) {
        // 对于跳出递归的条件不是很明白
        // 应该不是这样的: 因为是从(0,0)开始递归的，所以上下左右到回溯重新退到到(0,0)
        // 这样呢: 各种移动到了最右下那一格 yes
        // 应该来说这种回溯法的递归过程要通过返回值来留存一些值是容易混乱的，只能是说在这种回溯的过程中改变一些全局变量
        // 这道题其实类似于树的遍历
        if (row == m-1 && col == n-1 && !this.isVisited[row][col]) {
            if (checkDigitSum(m - 1, n - 1)) {
                this.grimSum++;
            }
            this.isVisited[row][col] = true;
            return;
        }
        // TODO 关于m、n边界的条件限制使用不是特别清楚
        // 首先访问当前点
        if (row < m && col < n && !this.isVisited[row][col]) {
            this.grimSum++;
            this.isVisited[row][col] = true;
        }
        // 判断是否能向上移动
        if (row - 1 >= 0 && !this.isVisited[row - 1][col] && checkDigitSum(row - 1, col)) {
            this.grimSum++;
            this.isVisited[row - 1][col] = true;
            search(row - 1, col);
        }
        // 判断是否能向下移动
        if (row + 1 < this.m && !this.isVisited[row + 1][col] && checkDigitSum(row + 1, col)) {
            this.grimSum++;
            this.isVisited[row + 1][col] = true;
            search(row + 1, col);
        }
        // 判断能否向左移动
        if (col - 1 >= 0 && !this.isVisited[row][col-1] && checkDigitSum(row, col-1)) {
            this.grimSum++;
            this.isVisited[row][col-1] = true;
            search(row, col-1);
        }
        // 判断能否向右移动
        // 注意因为是从(0,0)开始递归的，所以对边界值的限定能保证不会超出边界
        if (col + 1 < this.n && !this.isVisited[row][col+1] && checkDigitSum(row, col+1)) {
            this.grimSum++;
            this.isVisited[row][col+1] = true;
            search(row, col+1);
        }
    }

    private boolean checkDigitSum(int m, int n) {
        if (getDigitSum(m, n) <= this.k) {
            return true;
        } else {
            return false;
        }
    }

    private int getDigitSum(int m, int n) {
        return m / 10 + m % 10 + n / 10 + n % 10;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
