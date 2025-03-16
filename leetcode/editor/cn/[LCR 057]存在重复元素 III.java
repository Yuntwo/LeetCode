//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <=
//t ，同时又满足 abs(i - j) <= k 。
//
// 如果存在则返回 true，不存在返回 false。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 2 * 10⁴
// -2³¹ <= nums[i] <= 2³¹ - 1
// 0 <= k <= 10⁴
// 0 <= t <= 2³¹ - 1
//
//
//
//
//
// 注意：本题与主站 220 题相同： https://leetcode-cn.com/problems/contains-duplicate-iii/
//
// Related Topics 数组 桶排序 有序集合 排序 滑动窗口 👍 93 👎 0



import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

/**
 * Success1
 * 桶排序实际上就是范围划分
 */
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        HashMap<Long, Integer> buckets = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 注意实际的bucketSize是t+1
            long bucketId = getBucketId(nums[i], (long) t + 1);
            // 如果在相同桶中已经有元素了，相同桶中的元素值之差一定满足条件；需要检查下标之差
            if (buckets.containsKey(bucketId)) {
                if ((i - buckets.get(bucketId)) <= k) {
                    return true;
                }
            }
            // 相邻桶中的元素也有可能满足元素值之差；同时都需要检查下标之差
            if (buckets.containsKey(bucketId - 1) && Math.abs((long) nums[i] - nums[buckets.get(bucketId - 1)]) <= t && (i - buckets.get(bucketId - 1) <= k)) {
                return true;
            }
            if (buckets.containsKey(bucketId + 1) && Math.abs((long) nums[buckets.get(bucketId + 1)] - nums[i]) <= t && (i - buckets.get(bucketId + 1) <= k)) {
                return true;
            }
            // 之前桶中有没有元素都需要更新桶中的元素
            buckets.put(bucketId, i);
        }
        return false;
    }

    /**
     * 注意由于题目给的数值范围在int表示值的边界了，所以bucketId和buketSize都是可能超出int的表示范围的，必须转换为long进行计算
     * 注意这里的特殊处理，主要是考虑到负数除法的向上取整(靠近0)特性，假如bucketSize==5
     * 0的id是0，5的id是0，6的id是1；-5的id是-1(不特殊处理也是-1)，-6的id是-2(不特殊处理的话就是-1)
     */
    private long getBucketId(int num, long bucketSize) {
        return num < 0 ? (num + 1) / bucketSize - 1 : num / bucketSize;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = new int[]{1, 5, 9, 1, 5, 9};
//        boolean res = solution.containsNearbyAlmostDuplicate(nums, 2, 3);
        int[] nums = new int[]{2147483647, -1, 2147483647};
        boolean res = solution.containsNearbyAlmostDuplicate(nums, 1, 2147483647);
        System.out.println(res);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
