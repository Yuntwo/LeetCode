//给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
//
// 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
//
// 源数组中可能存在 重复项 。
//
// 注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余
//运算。
//
//
//
// 示例 1：
//
//
//输入：nums = [3,4,5,1,2]
//输出：true
//解释：[1,2,3,4,5] 为有序的源数组。
//可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
//
//
// 示例 2：
//
//
//输入：nums = [2,1,3,4]
//输出：false
//解释：源数组无法经轮转得到 nums 。
//
//
// 示例 3：
//
//
//输入：nums = [1,2,3]
//输出：true
//解释：[1,2,3] 为有序的源数组。
//可以轮转 x = 0 个位置（即不轮转）得到 nums 。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 1 <= nums[i] <= 100
//
//
// Related Topics 数组 👍 42 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 问题简单，但是各种特殊情况不好统一代码讨论
//class Solution {
//    public boolean check(int[] nums) {
//        int minIndex = 0;
//        int min = nums[minIndex];
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] < min) {
//                minIndex = i;
//                min = nums[minIndex];
//            }
//        }
//        if (minIndex != 0 && nums[0] < nums[nums.length - 1]) {
//            return false;
//        }
//        for (int i = minIndex + 1; i < nums.length; i++) {
//            if (nums[i] >= nums[i - 1]) {
//                continue;
//            } else {
//                return false;
//            }
//        }
//        for (int i = 1; i < minIndex; i++) {
//            if (nums[i] >= nums[i - 1]) {
//                continue;
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }
//}
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int x = 0;
        // 在以0为起始判断递增的过程中，记录得到不递增的起始位置
        // 一举两得，分离了两种情况
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                x = i;
                break;
            }
        }
        if (x == 0) {
            return true;
        }
        for (int i = x + 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        // 如果是分段，一定是两段递增的情况，但是在这两段递增的情况中要判断首尾是否能衔接上
        // 注意这种返回方式，表达很简洁
        // 注意这两段递增是完整的，而且只有可能前面这段接到后面这段后面，直接前面后面两段就是原来的排列方式不可能连续递增
        return nums[0] >= nums[n - 1];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
