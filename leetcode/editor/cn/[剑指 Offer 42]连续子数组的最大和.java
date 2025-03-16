//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
// 要求时间复杂度为O(n)。
//
//
//
// 示例1:
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
//
// 提示：
//
//
// 1 <= arr.length <= 10^5
// -100 <= arr[i] <= 100
//
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
//
//
//
// Related Topics 数组 分治 动态规划 👍 613 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 用二维数组存路径值，这样会超出内存
//class Solution {
//    public int maxSubArray(int[] nums) {
//        // 注意在申明数组变量时不需要指定数组大小
//        int[][] matrix=new int[nums.length][nums.length];
//        // 注意由于存在负数，所以max的初值不能赋为0，而是要选取存在的值
//        int max = nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j < nums.length; j++) {
//                if (i == j) {
//                    matrix[i][j] = nums[i];
//                } else {
//                    matrix[i][j] = matrix[i][j - 1] + nums[j];
//                }
//                if (matrix[i][j] > max) {
//                    max = matrix[i][j];
//                }
//            }
//        }
//        return max;
//    }
//}
// 复用一维数组存路径
// 不知道为什么这样会超出时间限制
//class Solution {
//    public int maxSubArray(int[] nums) {
//        // 注意在申明数组变量时不需要指定数组大小
//        int[] matrix = new int[nums.length];
//        // 注意由于存在负数，所以max的初值不能赋为0，而是要选取存在的值
//        int max = nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j < nums.length; j++) {
//                if (i == j) {
//                    matrix[i] = nums[i];
//                } else {
//                    matrix[j] = matrix[j - 1] + nums[j];
//                }
//                if (matrix[j] > max) {
//                    max = matrix[j];
//                }
//            }
//        }
//        return max;
//    }
//}
// 滚动数组的思想，由于f(i)只与f(i-1)有关，所以连一维数组其实都可以不用，直接用一个变量就行
//class Solution {
//    public int maxSubArray(int[] nums) {
//        // 注意这里pre和max必须初始化
//        int pre = nums[0];
//        int max = nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j < nums.length; j++) {
//                if (i == j) {
//                    pre = nums[i];
//                } else {
//                    pre = pre + nums[j];
//                }
//                if (pre > max) {
//                    max = pre;
//                }
//            }
//        }
//        return max;
//    }
//}
// 注意这里动态规划/递归的思想就有点复杂高阶了
// 你甚至都不需要遍历两次，你在这个过程中会多次挑选最大的丢弃
class Solution {
    public int maxSubArray(int[] nums) {
        // 注意这里pre和max必须初始化
        // 注意这两个变量都有取最大的操作，但是问题的维度不一样，本质不一样
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
//            pre = Math.max(pre + nums[i], nums[i]);
            // 或者本质上是这样
            // 这样判断之后内存占用小很多
            if (pre > 0) {
                pre = pre + nums[i];
            }
            else {
                pre = nums[i];
            }

            if (pre > max) {
                max = pre;
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
