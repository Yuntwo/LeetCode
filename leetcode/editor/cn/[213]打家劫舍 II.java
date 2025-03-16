//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 3：
//
//
//输入：nums = [1,2,3]
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 1000
//
//
// Related Topics 数组 动态规划 👍 1317 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 感觉是两种情况的话逻辑上还是得用两套数组
 * 就是代码写得很复杂，时间效率很高
 */
class Solution {

    private int[] robStart;

    public int rob(int[] nums) {
        // 对全0的情况做处理
        // 主要是全0的情况的话初始值0就没有什么用，就有很多重复递归，除非把初始值换成-1的或许就不需要了
        // 但凡nums中有一个非0，robStart就会有很多非零，因为它是累加的嘛
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
        robStart = new int[nums.length];
        int res = 0;
        // 0的位置特殊考虑
        res = regRobStart0(nums, 0);
        // 重新清空其他位置，相当于两套数组逻辑
        for (int i = 1; i < nums.length; i++) {
            robStart[i] = 0;
        }
        // 其它位置统一考虑
        for (int i = 1; i < nums.length; i++) {
            // 注意从每一个i都要robReg一遍才能填满所有的值，只是robReg(nums, 0)不行
            if (regRob(nums, i) > res) {
                res = robStart[i];
            }
        }
        return res;
    }

    private int regRobStart0(int[] nums, int start) {
        // 就不会遍历到nums.length-1
        if (start == nums.length - 2) {
            robStart[start] = nums[start];
            return nums[start];
        }
        int max = 0;
        // 注意有环的情况实际上只是减少一个判断的可能性
        for (int i = start + 2; i < nums.length - 1; i++) {
            if (robStart[i] != 0) {
                // 注意这里应用已递归结果的存储信息是减少递归的关键
                max = Math.max(robStart[i], max);
            } else {
                max = Math.max(regRobStart0(nums, i), max);
            }
        }
        robStart[start] = nums[start] + max;
        return robStart[start];
    }

    private int regRob(int[] nums, int start) {
        if (start == nums.length - 1 || start == nums.length - 2) {
            robStart[start] = nums[start];
            return nums[start];
        }
        int max = 0;
        for (int i = start + 2; i < nums.length; i++) {
            if (robStart[i] != 0) {
                // 注意这里应用已递归结果的存储信息是减少递归的关键
                max = Math.max(robStart[i], max);
            } else {
                max = Math.max(regRob(nums, i), max);
            }
        }
        robStart[start] = nums[start] + max;
        return robStart[start];
    }

}
/**
 * Failure1
 * 思路到了关键点，但是还有细节没处理到
 */
//class Solution {
//
//    private int[] robStart;
//
//    public int rob(int[] nums) {
//        robStart = new int[nums.length];
//        int res = 0;
//        for (int i = 0; i < nums.length; i++) {
//            // 注意从每一个i都要robReg一遍才能填满所有的值，只是robReg(nums, 0)不行
//            if (regRob(nums, i) > res) {
//                res = robStart[i];
//            }
//        }
//        return res;
//    }
//
//    private int regRob(int[] nums, int start) {
//        if (start == nums.length - 1 || start == nums.length - 2) {
//            robStart[start] = nums[start];
//            return nums[start];
//        }
//        int max = 0;
//        if (start == 0) {
//            // 注意有环的情况实际上只是减少一个判断的可能性，但实际上不是这样的，对于它后续的rob的情况也是有限制
//            for (int i = start + 2; i < nums.length - 1; i++) {
//                if (robStart[i] != 0) {
//                    // 注意这里应用已递归结果的存储信息是减少递归的关键
//                    max = Math.max(robStart[i], max);
//                } else {
//                    max = Math.max(regRob(nums, i), max);
//                }
//            }
//        } else {
//            for (int i = start + 2; i < nums.length; i++) {
//                if (robStart[i] != 0) {
//                    // 注意这里应用已递归结果的存储信息是减少递归的关键
//                    max = Math.max(robStart[i], max);
//                } else {
//                    max = Math.max(regRob(nums, i), max);
//                }
//            }
//        }
//        robStart[start] = nums[start] + max;
//        return robStart[start];
//    }
//
//}
/**
 * This is end
 */
//leetcode submit region end(Prohibit modification and deletion)
