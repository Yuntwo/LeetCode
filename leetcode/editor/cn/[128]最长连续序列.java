//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
// 示例 2：
//
//
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
//
//
// Related Topics 并查集 数组 哈希表 👍 2193 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 注意等于和大于的情况，处理方式不同
 * 注意这种子序列的问题也一般不是用滑动窗口而是排序；滑动窗口只适用于简单问题
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int cur = nums[0];
        int res = 1;
        int ans = res;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == cur + 1) {
                res++;
            }
            if (nums[i] > cur + 1) {
                res = 1;
            }
            ans = Math.max(ans, res);
            cur = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
