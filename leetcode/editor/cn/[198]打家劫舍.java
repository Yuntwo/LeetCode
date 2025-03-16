//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 2：
//
//
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 400
//
// Related Topics 数组 动态规划 👍 2082 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Review1
 * 动态规划解法
 * 注意动态规划的本质模型是状态转移
 */
//class Solution {
//
//    public int rob(int[] nums) {
//        int[] isRob = new int[nums.length + 1];
//        int[] notRob = new int[nums.length + 1];
//        for (int i = 1; i <= nums.length; i++) {
//            isRob[i] = nums[i - 1] + notRob[i - 1];
//            notRob[i] = Math.max(isRob[i - 1], notRob[i - 1]);
//        }
//        return Math.max(isRob[nums.length], notRob[nums.length]);
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{1};
//        Solution solution = new Solution();
//        int res = solution.rob(nums);
//        System.out.println(res);
//    }
//}
/**
 * Success2
 * 不同的动态规划题目的难题其实在于转移状态的选取，怎么选可以可以使这个问题模型简单明了，怎么定义状态不会重复，通常状态的定义只需要偏向一种情况就可以了
 * 但是你另一种状态也是要考虑的
 * 自顶向下递归记忆化搜索
 * 注意理解题意：只要没有连续偷取，你可以多次偷取
 * 注意思路：你考虑问题不是直接从最优考虑的，不是从理论上分析哪种偷法最好
 * 动态规划的本质还是遍历搜索，不过是有记忆的遍历搜索
 * 递归的本质也还是遍历搜索了的
 */
class Solution {
    int[] robStart;

    public int rob(int[] nums) {
        // 对全0的情况做处理
        boolean zero = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                zero = false;
                break;
            }
        }
        if (zero) {
            return 0;
        }
        // 注意这里robStart[i]的含义应该是从i开始打劫的最大值，i一定会被打劫
        // 注意最后的结果是robStart数组中的最大值，这个数组其实就相当于一个递归记录数据/动态规划数组
        robStart = new int[nums.length];
        int max = robRg(nums, 0);
        for (int i = 1; i < nums.length; i++) {
            int temp = robRg(nums, i);
            if (temp > max) {
                max = temp;
            }
        }
        return max;

    }

    private int robRg(int[] nums, int start) {
        if (start == nums.length - 1 || start == nums.length - 2) {
            robStart[start] = nums[start];
            return robStart[start];
        }
        int max = 0;
        // 注意这里我们考虑从哪一个i开始打劫第一个能带来最大收益，这里的状态很好地与不打劫的隔离开了！！！不用考虑那么多了
        // 注意这道题的特殊之处在于它的递归也是有多个分支的
        for (int i = start + 2; i < nums.length; i++) {
            // 注意这一句是记忆化搜索简化的用处关键，否则还是重复递归计算
            if (robStart[i] != 0) {
                if (robStart[i] > max) {
                    max = robStart[i];
                }
            } else {
                if (robRg(nums, i) > max) {
                    max = robRg(nums, i);
                }
            }
        }
        // 注意这一句是记忆化搜索记录数据的关键，否则哪来的记忆数据给你用
        robStart[start] = nums[start] + max;
        return robStart[start];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        Solution solution = new Solution();
        int res = solution.rob(nums);
        System.out.println(res);
    }
}
/**
 * Success1
 * 自底向上动态规划
 */
// 注意顶和底的判断标准其实是看哪些是未知的，越顶越未知
//class Solution {
//
//    public int rob(int[] nums) {
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        int[] robStart = new int[nums.length];
//
//        robStart[nums.length - 1] = nums[nums.length - 1];
//        robStart[nums.length - 2] = nums[nums.length - 2];
//        for (int i = nums.length - 3; i >= 0; i--) {
//            robStart[i] = nums[i];
//            // 注意它还有很多种rob的方法，要在这些中找最大的
//            int max = 0;
//            for (int j = i + 2; j < nums.length; j++) {
//                if (robStart[j] > max) {
//                    max = robStart[j];
//                }
//            }
//            robStart[i] += max;
//        }
//
//        int max = robStart[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (robStart[i] > max) {
//                max = robStart[i];
//            }
//        }
//        return max;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{2, 7, 9, 3, 1};
//        Solution solution = new Solution();
//        int res = solution.rob(nums);
//        System.out.println(res);
//    }
//}
/**
 * This is end
 */
//leetcode submit region end(Prohibit modification and deletion)
