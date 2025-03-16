//沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
//
// 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
//
// 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
//
// 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
//
// 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
//
// 返回小偷的 最小 窃取能力。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,5,9], k = 2
//输出：5
//解释：
//小偷窃取至少 2 间房屋，共有 3 种方式：
//- 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
//- 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
//- 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
//因此，返回 min(5, 9, 9) = 5 。
//
//
// 示例 2：
//
//
//输入：nums = [2,7,9,3,1], k = 2
//输出：2
//解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// 1 <= nums[i] <= 10⁹
// 1 <= k <= (nums.length + 1)/2
//
//
// Related Topics 数组 二分查找 👍 41 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 二分+动态规划，反正这种建模和思路感觉做多少遍我都想不出来
 */
class Solution {

    /**
     * Success2
     * 1维动态数组，但是写法还是很麻烦，没有题解那么巧妙精简
     */
    public int minCapability(int[] nums, int k)  {
        if (nums.length == 1) {
            return nums[0];
        // 注意这种特殊情况要返回，否则到下面结果不对
        } else if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }
        int left = 0;
        int right = (int) 1e9;
        int ans = right;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 注意这里都是要和mid比较
            int f_i_minus_2 = nums[0] <= mid ? 1 : 0;
            // 注意这种写法直接是综合考虑了
            int f_i_minus_1 = Math.min(nums[0], nums[1]) <= mid ? 1 : 0;
            int f = 0;
            for (int i = 2; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    f = Math.max(f_i_minus_1, f_i_minus_2 + 1);
                } else {
                    f = f_i_minus_1;
                }
                f_i_minus_2 = f_i_minus_1;
                f_i_minus_1 = f;
            }
            if (f >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * Success1
     * 不节省空间的写法
     */
//    public int minCapability(int[] nums, int k) {
//        // 注意边界条件
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        int left = 0;
//        // 根据nums[i]的取值范围得出的
//        int right = (int) 1e9;
//        int ans = right;
//        while (left <= right) {
//            // 注意这样写除以2是最高效的
//            // 以这个值为标杆，遍历nums，找出在前i个房屋中窃取，窃取能力不超过mid的每个i的最大房屋数
//            int mid = (left + right) >> 1;
//            int[] dp = new int[nums.length];
//            dp[0] = nums[0] <= mid ? 1 : 0;
//            // 注意dp[1]临界条件要考虑dp[0]到底窃取没有
//            // 注意Java不能强制将boolean型转成int型
//            dp[1] = (dp[0] == 1) ? 1 : (nums[1] <= mid ? 1 : 0);
//            for (int i = 2; i < nums.length; i++) {
//                // 可以窃取i处的房屋
//                if (nums[i] <= mid) {
//                    // 可以窃取的条件下窃不窃取看哪个最大房屋数大
//                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + 1);
//                // 一定不能窃取i处的房屋，否则不符合窃取能力限制了
//                } else {
//                    dp[i] = dp[i - 1];
//                }
//            }
//            // 最后看dp[nums.length-1]处的值是否大于k
//            // 大于说明至少满足了题目最少窃取k个房屋的条件，所以要保存结果
//            // 但是有更多的话会使结果偏大，所以要往小了找(Q:不是很懂)
//            if (dp[nums.length - 1] >= k) {
//                ans = mid;
//                right = mid - 1;
//            // 小于说明不满足最少窃取k个房屋的条件，所以不能保存结果
//            // 要窃取更多的房间一定会使结果变大，所以要往大了找
//            } else {
//                left = mid + 1;
//            }
//        }
//        return ans;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.minCapability(new int[]{2, 2}, 1);
        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
