//给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
//
//
//
// 示例 1：
//
//
//输入:nums = [1,1,1], k = 2
//输出: 2
//解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
//
//
// 示例 2：
//
//
//输入:nums = [1,2,3], k = 3
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 2 * 10⁴
// -1000 <= nums[i] <= 1000
// -10⁷ <= k <= 10⁷
//
//
//
//
// 注意：本题与主站 560 题相同： https://leetcode-cn.com/problems/subarray-sum-equals-k/
//
// Related Topics 数组 哈希表 前缀和 👍 159 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Success2
 * 用HashMap来存有多少个期望要减去的前缀和，就不需要再遍历一遍了，本质上是空间换时间
 */
class Solution {

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // 注意这个0的初始值是一定要有一个的
        hashMap.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 注意这里判断res要在前面，否则对于k=0的情况，先put就会多出
            res += hashMap.getOrDefault(sum - k, 0);
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

}

/**
 * Success1
 * 和+双重循环
 * 这里其实不能叫前缀和，因为存的和实际上是总和
 * 注意不能用滑动窗口的原因在于nums[i]有可能是负数，不能保证单调性
 */
//class Solution {
//
//    /**
//     * Success2
//     * 用一个变量存和就够了
//     */
//    public int subarraySum(int[] nums, int k) {
//        int res = 0;
//        // 以i为起始，向后找和
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                if (sum == k) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }
//
//    /**
//     * Success1
//     * 用数组存了和，其实用一个变量就够用了
//     */
////    public int subarraySum(int[] nums, int k) {
////        int res = 0;
////        int[] sum = new int[nums.length];
////        sum[0] = nums[0];
////        if (nums[0] == k) {
////            res++;
////        }
////        for (int i = 1; i < nums.length; i++) {
////            // sum[i]表示从0加到i的总和
////            sum[i] = nums[i] + sum[i - 1];
////            if (sum[i] == k) {
////                res++;
////            }
////        }
////        for (int i = 1; i < nums.length; i++) {
////            for (int j = 0; j < i; j++) {
////                if (sum[i] - sum[j] == k) {
////                    res++;
////                }
////            }
////        }
////        return res;
////    }
//}
//leetcode submit region end(Prohibit modification and deletion)
