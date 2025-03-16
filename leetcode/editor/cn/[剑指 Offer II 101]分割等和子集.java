//给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,5,11,5]
//输出：true
//解释：nums 可以分割成 [1, 5, 5] 和 [11] 。
//
// 示例 2：
//
//
//输入：nums = [1,2,3,5]
//输出：false
//解释：nums 不可以分为和相等的两部分
//
//
//
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100
//
//
//
//
//
// 注意：本题与主站 416 题相同： https://leetcode-cn.com/problems/partition-equal-subset-
//sum/
//
// Related Topics 数学 字符串 模拟 👍 69 👎 0


import java.util.EnumSet;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 还是得转化为01背包问题
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] res = new boolean[target + 1];
        res[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = target; j >= 1; j--) {
                // 注意索引是nums[i-1]
                if (nums[i - 1] <= j) {
                    res[j] |= res[j - nums[i - 1]];
                }
            }
        }
        return res[target];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = new int[]{2, 2, 3, 5};
        int[] nums = new int[]{1, 2, 3, 5};
//        int[] nums = new int[]{1, 2, 5};
//        int[] nums = new int[]{1, 5, 11, 5};
//        int[] nums = new int[]{1, 2, 3, 5};
        boolean res = solution.canPartition(nums);
        System.out.println(res);
    }
}

/**
 * Success1
 * 二维dp版本
 * 这样二维的「思路切面」(并不是指实际的计算空间)是必须的，再少就会引起数据的交叉和混乱
 */
//class Solution {
//    public boolean canPartition(int[] nums) {
//        if (nums.length < 2) {
//            return false;
//        }
//        int maxNum = nums[0];
//        int sum = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] > maxNum) {
//                maxNum = nums[i];
//            }
//            sum += nums[i];
//        }
//        if (sum % 2 == 1) {
//            return false;
//        }
//        int target = sum / 2;
//        // 等于target就还是有可能的
//        if (maxNum > target) {
//            return false;
//        }
//        boolean[][] can = new boolean[nums.length][target + 1];
//        for (int i = 0; i < nums.length; i++) {
//            can[i][0] = true;
//        }
//        can[0][nums[0]] = true;
//        for (int i = 1; i < nums.length; i++) {
//            // 这里由于需要复制状态，所以不能像一维数组那样简化j<nums[i]的情况
//            for (int j = 1; j <= target; j++) {
//                if (nums[i] > j) {
//                    can[i][j] = can[i - 1][j];
//                } else {
//                    // 注意这里||是逻辑或，只有逻辑运算符(而非位运算符)才会短路计算
//                    can[i][j] = can[i - 1][j] || can[i - 1][j - nums[i]];
//                }
//            }
//        }
//        return can[nums.length - 1][target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{2, 2, 3, 5};
////        int[] nums = new int[]{1, 2, 5};
////        int[] nums = new int[]{1, 5, 11, 5};
////        int[] nums = new int[]{1, 2, 3, 5};
//        boolean res = solution.canPartition(nums);
//        System.out.println(res);
//    }
//}

/**
 * Failure2
 * 注意这种方法因为你是以每一个nums[i]要不要拿作为构建思路的
 * 思路是对的，但是在思考具体路径时太扁平混淆了
 * 你在计算后面的can时可能反复用了之前的can，那就不清楚实际上对于某个nums[i]拿进拿出了多少次
 * 而你对于每一个目标重量去找拿到第i个石头的可能性，这样实际上切分为两个维度，就不会混淆重复
 * 这种思维层次的划分明晰非常重要
 */
//class Solution {
//    public boolean canPartition(int[] nums) {
//        int sum = 0;
//        for (int num :
//            nums) {
//            sum += num;
//        }
//        // 注意和为奇数的一定不可能分成由整数划分的和为xx.5的两半
//        if (sum % 2 == 1) {
//            return false;
//        }
//        // 注意这里是下取整
//        int target = sum / 2;
//        boolean[] can = new boolean[target + 1];
//        int load = 0;
//        for (int num :
//            nums) {
//            load += num;
//            if (load == target) {
//                return true;
//            } else if (load < target) {
//                can[load] = true;
//            } else {
//                // 注意是最少要扔初始值那么多才够
//                // 可以多扔腾出更多的空间，但是最多扔掉没放出num之前占的那些
//                for (int i = load - target; i <= load - num; i++) {
//                    if (can[i]) {
//                        if ((load - i) == target) {
//                            return true;
//                        }
//                        // 没必要改变状态，这样可能带来资源重复使用的问题
////                        can[load - i] = true;
//                    }
//                }
//                load -= num;
//            }
//        }
//        return can[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{2, 2, 3, 5};
////        int[] nums = new int[]{1, 2, 5};
////        int[] nums = new int[]{1, 5, 11, 5};
////        int[] nums = new int[]{1, 2, 3, 5};
//        boolean res = solution.canPartition(nums);
//        System.out.println(res);
//    }
//}

/**
 * Failure1
 * 这种情况是不完全的解法，可能撞上正确的情况
 */
//class Solution {
//    public boolean canPartition(int[] nums) {
//        int sum = 0;
//        for (int num :
//            nums) {
//            sum += num;
//        }
//        // 注意和为奇数的一定不可能分成由整数划分的和为xx.5的两半
//        if (sum % 2 == 1) {
//            return false;
//        }
//        // 注意这里是下取整
//        int target = sum / 2;
//        boolean[] can = new boolean[target + 1];
//        int load = 0;
//        for (int num :
//            nums) {
//            load += num;
//            if (load == target) {
//                return true;
//            } else if (load < target) {
//                can[load] = true;
//                // 如果超重，直接不要这块石头，但是也不做过多混乱的尝试
//            } else {
//                load -= num;
//            }
//        }
//        return can[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{2, 2, 3, 5};
////        int[] nums = new int[]{1, 2, 5};
////        int[] nums = new int[]{1, 5, 11, 5};
////        int[] nums = new int[]{1, 2, 3, 5};
//        boolean res = solution.canPartition(nums);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)


