//给你一个整数数组 nums 和一个整数 target 。
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//
//
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
//
// 示例 2：
//
//
//输入：nums = [1], target = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 1000
//
//
// Related Topics 数组 动态规划 回溯 👍 1452 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review3
 * 至少要用两个dp，一维dp还是不行
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 会越界没办法只能直接返回
        if (target > sum || target < -sum) {
            return 0;
        }
        int[] dp = new int[2 * sum + 1];
        dp[nums[0] + sum] += 1;
        dp[-nums[0] + sum] += 1;
        for (int j = 1; j < nums.length; j++) {
            int[] temp = new int[2 * sum + 1];
            for (int i = sum; i >= -sum; i--) {
                if (i - nums[j] >= -sum) {
                    temp[i + sum] += dp[i - nums[j] + sum];
                }
                // 注意这里会用到后面的值，所以从后向前一维dp不行
                if (i + nums[j] <= sum) {
                    temp[i + sum] += dp[i + nums[j] + sum];
                }
            }
            dp = temp;
        }
        return dp[target + sum];
    }
}

/**
 * Review2
 * 背包问题思路动态规划，效率不高但好懂
 */
//class Solution {
//    public int findTargetSumWays(int[] nums, int target) {
//        int sum = 0;
//        for (int num : nums) {
//            sum += num;
//        }
//        if (target > sum || target < -sum) {
//            return 0;
//        }
//        int[][] dp = new int[2 * sum + 1][nums.length];
//        // 初始化
//        dp[nums[0] + sum][0] += 1;
//        dp[-nums[0] + sum][0] += 1;
//        // 背包逐个考虑后面的数字
//        for (int i = 1; i < nums.length; i++) {
//            // 考虑每个数字对和-sum到sum的影响
//            for (int j = -sum; j <= sum; j++) {
//                // 考虑加上这个数字得到目标值j
//                if (j - nums[i] >= -sum) {
//                    dp[j + sum][i] += dp[j - nums[i] + sum][i - 1];
//                }
//                // 考虑减去这个数字
//                if (j + nums[i] <= sum) {
//                    dp[j + sum][i] += dp[j + nums[i] + sum][i - 1];
//                }
//            }
//        }
//        return dp[target + sum][nums.length - 1];
//    }
//
//    public static void main(String[] args) {
////        int[] nums = new int[]{1, 1, 1, 1, 1};
////        int target = 3;
//        int[] nums = new int[]{1};
//        int target = 2;
////        int[] nums = new int[]{7, 9, 3, 8, 0, 2, 4, 8, 3, 9};
////        int target = 0;
//        Solution solution = new Solution();
//        int res = solution.findTargetSumWays(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Review1
 * 效率貌似不高
 */
//class Solution {
//    public int findTargetSumWays(int[] nums, int target) {
//        int sum = 0;
//        for (int num : nums) {
//            sum += num;
//        }
//        // 注意这里保证了最后索引dp[target + sum]不会越界
//        if (target > sum || target < -sum) {
//            return 0;
//        }
//        int[] dp = new int[2 * sum + 1];
//        // 和为0应该有默认的1种初始值
//        dp[sum] = 1;
//        // 这里是看每一块石头对于每一种目标可能的影响
//        for (int num : nums) {
//            // 注意只是将这个位置赋为0不够，要把所有位置赋为0，所以干脆新建一个数组
////                next[i + sum] = 0;
//            int[] next = new int[2 * sum + 1];
//            // 这里i是目标和，i+sum是目标和在dp中的索引
//            for (int i = -sum; i <= sum; i++) {
//                if (i - num >= -sum) {
//                    next[i + sum] += dp[i - num + sum];
//                }
//                if (i + num <= sum) {
//                    next[i + sum] += dp[i + num + sum];
//                }
//            }
//            dp = next;
//        }
//        return dp[target + sum];
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 1, 1};
//        int target = 3;
////        int[] nums = new int[]{1};
////        int target = 2;
////        int[] nums = new int[]{7, 9, 3, 8, 0, 2, 4, 8, 3, 9};
////        int target = 0;
//        Solution solution = new Solution();
//        int res = solution.findTargetSumWays(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 动态规划解法
 * 注意这里题目限制了num都是正整数，这样其实简化了问题
 */
//class Solution {
//    public int findTargetSumWays(int[] nums, int target) {
//        int sum = 0;
//        for (int num : nums) {
//            sum += num;
//        }
//        // 如果neg是负数的话，是找不到方案的，注意要考虑到这个情况
//        if (sum < target) {
//            return 0;
//        }
//        // 注意如果实际的neg不是整数的话，也是找不到方案的
//        if ((sum - target) % 2 != 0) {
//            return 0;
//        }
//        int neg = (sum - target) / 2;
//        // 注意这里多预留了一些空间
//        int[][] res = new int[nums.length + 1][neg + 1];
//        // 这是起始条件
//        res[0][0] = 1;
//        // 注意对于nums的索引要减1
//        for (int i = 1; i <= nums.length; i++) {
//            // 二维数组的话从前往后/从后往前都可以
//            for (int j = neg; j >= 0; j--) {
//                if (nums[i - 1] > j) {
//                    res[i][j] = res[i - 1][j];
//                } else {
//                    res[i][j] = res[i - 1][j] + res[i - 1][j - nums[i - 1]];
//                }
//            }
//        }
//        return res[nums.length][neg];
//    }
//
//    public static void main(String[] args) {
////        int[] nums = new int[]{1, 1, 1, 1, 1};
////        int target = 3;
////        int[] nums = new int[]{1};
////        int target = 2;
//        int[] nums = new int[]{7, 9, 3, 8, 0, 2, 4, 8, 3, 9};
//        int target = 0;
//        Solution solution = new Solution();
//        int res = solution.findTargetSumWays(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 回溯解法，背包解法着实有点难
 */
//class Solution {
//
//    private int res = 0;
//
//    private int sum = 0;
//
//    public int findTargetSumWays(int[] nums, int target) {
//        find(nums, 0, target);
//        return res;
//    }
//
//    private void find(int[] nums, int i, int target) {
//        if (i == nums.length) {
//            return;
//        } else if (i == nums.length - 1) {
//            // 注意对于0的情况，这两种分支都满足，算两遍
//            if (nums[i] == 0 && sum == target) {
//                res += 2;
//            } else if (sum + nums[i] == target) {
//                res++;
//                // 注意前面只是判断，并没有实际进行加减，不需要补偿
////                sum -= nums[i];
//            } else if (sum - nums[i] == target) {
//                res++;
////                sum += nums[i];
//            }
//            return;
//        }
//        sum += nums[i];
//        find(nums, i + 1, target);
//        sum -= nums[i];
//        sum -= nums[i];
//        find(nums, i + 1, target);
//        sum += nums[i];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
////        int[] nums = new int[]{1, 1, 1, 1, 1};
//        int[] nums = new int[]{0, 1};
//        int res = solution.findTargetSumWays(nums, 1);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 动态规划解法
 * 其实对于01背包问题，你不管选不选某个石头，你所有的石头都是遍历了一遍的
 * 所以这道题对于数据的要求其实和01问题是一样的，自然能用动态规划解决
 * 只不过不是can数组可以用来存一些想要的中间值，信息更丰富了，不只是“能不能”
 * 能够用动态规划思路解决的问题的关键在于，它的结果是递进得到的，是与前面的状态有关的
 * 至于解决什么样的问题则是其它信息的处理了，总体框架是不变的
 */
//class Solution {
//
//    // 注意题目里面的target可能是负数
//    public int findTargetSumWays(int[] nums, int target) {
//        int[] solutions = new int[2 * target + 1];
//        for (int i = 0; i < nums.length; i++) {
//            // 注意对于每一个target，由于nums的数有正负，所以都有可能逼近target
//            for (int j = target; j >= 0; j++) {
//                solutions[j] = solutions[j - nums[i]] + solutions[i] + solutions[j+nums[i]] + solutions[-i];
//            }
//        }
//        return solutions[target];
//    }
//
//}

/**
 * Failure2
 * 注意题目说了nums[i]都是正数
 * 注意题目里面的target可能是负数
 */
//class Solution {
//    public int findTargetSumWays(int[] nums, int target) {
//        int maxSum = 0;
//        for (int num : nums) {
//            maxSum += num;
//        }
//        int[] solution = new int[2 * maxSum + 1];
//        solution[0] = 1;
//        for (int num : nums) {
//            for (int i = maxSum; i >= 0; i--) {
//                solution[i + num + maxSum] += solution[i + maxSum];
//                solution[i - num + maxSum] += solution[i + maxSum];
//            }
//        }
//        return solution[target + maxSum];
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
