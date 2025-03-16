//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 请你实现时间复杂度为
//O(n) 并且只使用常数级别额外空间的解决方案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,0]
//输出：3
//解释：范围 [1,2] 中的数字都在数组中。
//
// 示例 2：
//
//
//输入：nums = [3,4,-1,1]
//输出：2
//解释：1 在数组中，但 2 没有。
//
// 示例 3：
//
//
//输入：nums = [7,8,9,11,12]
//输出：1
//解释：最小的正数 1 没有出现。
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -2³¹ <= nums[i] <= 2³¹ - 1
//
//
// Related Topics 数组 哈希表 👍 2201 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;

/**
 * Failure1
 * 缺失的第一个正数不一定与最小的正数关联
 */
//class Solution {
//    public int firstMissingPositive(int[] nums) {
//        int min = nums.length + 1;
//        for (int num : nums) {
//            if (num > 0 && num < min) {
//                min = num;
//            }
//        }
//
//    }
//}

class Solution {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (nums[i] != res && nums[i - 1] != nums[i]) {
                    return res;
                }
                res++;
            }
        }
        return res;
    }
}

/**
 * Review2
 */
//class Solution {
//    public int firstMissingPositive(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
//                int temp = nums[nums[i] - 1];
//                nums[nums[i] - 1] = nums[i];
//                nums[i] = temp;
//            }
//
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i + 1) {
//                return i + 1;
//            }
//        }
//        return nums.length + 1;
//    }
//}

/**
 * Review1
 */
//class Solution {
//    public int firstMissingPositive(int[] nums) {
//        for(int i = 0; i < nums.length; i++) {
//            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
//                int temp = nums[nums[i] - 1];
//                nums[nums[i] - 1] = nums[i];
//                nums[i] = temp;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i + 1) {
//                return i + 1;
//            }
//        }
//        return nums.length + 1;
//    }
//}

/**
 * Success1
 */
//class Solution {
//    public int firstMissingPositive(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            // 注意单单是这个条件可能出现死循环，因为允许重复的数字所以可能正确位置已经有正确值了反复交换就跳不出去了
////            while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != i + 1) {
//            // 正确的是看正确位置是否已经有正确值了
//            while (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
//                int temp = nums[nums[i] - 1];
//                nums[nums[i] - 1] = nums[i];
//                nums[i] = temp;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i + 1) {
//                return i + 1;
//            }
//        }
//        return nums.length + 1;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
