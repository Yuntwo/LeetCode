//找出数组中重复的数字。
//
//
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。
//
// 示例 1：
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3
//
//
//
//
// 限制：
//
// 2 <= n <= 100000
// Related Topics 数组 哈希表 排序
// 👍 682 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                int error = nums[i];
                // 说明erro出现了2次
                if (nums[error] == error) {
                    return error;
                } // 如果error的位置放的不是error，那么可能放的是其它数字或者i，不好说，反正是乱的
                int j = i + 1;
                // 找到i实际所在的位置
                while (nums[j] != i) {
                    j++;
                }
                // 进行交换，反正整体顺序一定是打乱了的
                int temp = nums[i];
                nums[i] = i;
                nums[j] = temp;
            }
        }
        return -1;
    }
}

/**
 * Success3
 * 有特殊性质限制，用数组索引,特殊方法
 */
//class Solution {
//    public int findRepeatNumber(int[] nums) {
//        int[] index = new int[nums.length];
//        int i;
//        for (i = 0; i < nums.length && index[nums[i]] == 0; i++) {
//            index[nums[i]]++;
//        }
//        return nums[i];
//    }
//}

/**
 * Review1
 * 这是一种循环方式，不规则但是肯定能循环出来，循环次数也不少
 */
//class Solution {
//    public int findRepeatNumber(int[] nums) {
//        int i;
//        for (i = 0; i < nums.length; i++) {
//            while (nums[i] != i) {
//                if (nums[nums[i]] == nums[i]) {
//                    return nums[i];
//                } else {
//                    int temp = nums[nums[i]];
//                    nums[nums[i]] = nums[i];
//                    nums[i] = temp;
//                }
//            }
//        }
//        return nums[i];
//    }
//}

/**
 * Success2
 * 原地修改数组
 * 如果刚好没有重复数字，那么一次循环后修改数组使得数字i在索引i的位置
 * 如果某个索引位置i的数字不是i且索引位置nums[i]也是nums[i]则说明有重复数字
 */
//class Solution {
//    public int findRepeatNumber(int[] nums) {
//        int i;
//        for (i = 0; i < nums.length; i++) {
//            // 注意这里不能if，必须while让这个位置和与之相关的位置全部归位后才能进行下一步，才能保证不漏掉情况
//            while (nums[i] != i) {
//                if (nums[i] == nums[nums[i]]) {
//                    // 注意这里需要return了，否则break只是跳出一重循环
//                    return nums[i];
//                } else {
//                    int temp = nums[i];
//                    nums[i] = nums[nums[i]];
//                    // 注意这里是nums[temp]
//                    nums[temp] = temp;
//                }
//            }
//        }
//        return nums[i];
//    }
//}

/**
 * Success1
 * 没有特殊性质限制，用集合万能
 */
//class Solution {
//    public int findRepeatNumber(int[] nums) {
//        Set<Integer> set = new HashSet<>();
//        int i;
//        for (i = 0; i < nums.length && set.add(nums[i]); i++) ;
//        return nums[i];
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
