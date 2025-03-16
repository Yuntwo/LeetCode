//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。
//
//
//
// 示例:
//
// 现有矩阵 matrix 如下：
//
//
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
//
//
// 给定 target = 5，返回 true。
//
// 给定 target = 20，返回 false。
//
//
//
// 限制：
//
// 0 <= n <= 1000
//
// 0 <= m <= 1000
//
//
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
// Related Topics 数组 二分查找 分治 矩阵
// 👍 546 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**注意这里理解题意，不是你认为的这种情况，它每一行的大小分布不均匀的*/
//class Solution {
//    public boolean findNumberIn2DArray(int[][] matrix, int target) {
//        int rows = matrix.length;
//        // 找到锁定元素所在的行，二分查找什么查找都可以
//        // 二分查找
////        int low = 0;
////        int high = rows - 1;
////        int mid = (low + high) / 2;
////        while (!(matrix[mid][0] <= target && matrix[mid + 1][0] > target)) {
////            if (matrix[mid][0] < target) {
////                low = mid+1;
////            } else {
////                high = mid-1;
////            }
////            mid = (low + high) / 2;
////        }
//        // 顺序查找
//        int mid = rows - 1;
//        while (matrix[mid][0] > target) {
//            mid--;
//        }
//        // 找到一行中所在的列
//        int cols = matrix[mid].length;
//        if (matrix[mid][cols - 1] < target) {
//            return false;
//        } else {
//            for (int i = 0; i < cols; i++) {
//                if (matrix[mid][i] == target) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}
// 如果不是矩阵也不好整
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 注意判断一下空的情况
        if (matrix.length == 0) {
            return false;
        }
        // 注意选取右上角和左下角初始值和加减规律和边界规律
        int rows = 0;
        int cols = matrix[0].length-1;
        while (rows <= matrix.length-1 && cols >= 0) {
            if (matrix[rows][cols] == target) {
                return true;
            } else if (matrix[rows][cols] > target) {
                cols--;
            } else {
                rows++;
            }
        }
        return false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
