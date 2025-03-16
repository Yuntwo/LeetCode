//给你一个整数数组 nums ，请计算数组的 中心下标 。
//
// 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
//
// 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
//
// 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,7,3,6,5,6]
//输出：3
//解释：
//中心下标是 3 。
//左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
//右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
//
//
// 示例 2：
//
//
//输入：nums = [1, 2, 3]
//输出：-1
//解释：
//数组中不存在满足此条件的中心下标。
//
// 示例 3：
//
//
//输入：nums = [2, 1, -1]
//输出：0
//解释：
//中心下标是 0 。
//左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
//右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁴
// -1000 <= nums[i] <= 1000
//
//
//
//
//
// 注意：本题与主站 724 题相同： https://leetcode-cn.com/problems/find-pivot-index/
//
// Related Topics 数组 前缀和 👍 66 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 两次循环，最直白简单高效的做法
 * 这个题的卡点在于数学转换，明确目标就省去了很多循环，达到HashMap直击目标的效果
 */
class Solution {

    /**
     * Success2
     * 甚至不用HashMap或者其他存中间结果的数组会更快，不需要
     */
    public int pivotIndex(int[] nums) {
        // 用这个方法效率很低，还不如直接遍历就100%了
//        int total = Arrays.stream(nums).sum();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (2 * sum - nums[i] == total) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Success1
     * 多存了一个前缀和数组，占空间，有没有必要反而更慢
     */
//    public int pivotIndex(int[] nums) {
//        int n = nums.length;
//        int[] preSum = new int[n + 1];
//        for(int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + nums[i];
//        for(int i = 0; i < n; i++) if(preSum[i] == preSum[n] - preSum[i + 1]) return i;
//        return -1;
//    }
}

/**
 * Success1
 * 这里有点绕，HashMap中存的是以当前位置为标定点能达到左右子数组的和相等的数组总和，最后再找HashMap中有没有这个数组总和数的标定位置
 * 感觉不是很严谨
 * 注意不是直接sum/2，标定点不计算在和中
 * 很简洁了
 */
//class Solution {

//    public int pivotIndex(int[] nums) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//            hashMap.put(2 * sum - nums[i], Math.min(hashMap.getOrDefault(2 * sum - nums[i], i), i));
//        }
//        return hashMap.getOrDefault(sum, -1);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
