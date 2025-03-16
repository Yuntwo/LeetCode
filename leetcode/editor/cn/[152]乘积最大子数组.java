//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 测试用例的答案是一个 32-位 整数。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
//
//
// 示例 2:
//
//
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 2 * 10⁴
// -10 <= nums[i] <= 10
// nums 的任何子数组的乘积都 保证 是一个 32-位 整数
//
//
// Related Topics 数组 动态规划 👍 2315 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 主要是存在负数的情况所以导致最小最大的反转。也不需要按正负进行维护，就存最大最小值就可以了
 */
class Solution {
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            // 这里很巧妙，如果是有负数乘子直接最大最小换个位就可以了
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            // 注意由于状态存的是带上前一个数的最大最小值，所以nums[i]必须参与，不管带不带上前面的乘积
            // 这样处理的话有0也不怕了
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);
            res = Math.max(res, imax);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{0, 2}));
        System.out.println(solution.maxProduct(new int[]{-3, 0, 1, -2}));
    }
}

//leetcode submit region end(Prohibit modification and deletion)
