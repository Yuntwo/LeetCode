//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i
//- j) <= k 。如果存在，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false
//
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
// 0 <= k <= 105
//
// Related Topics 数组 哈希表 滑动窗口
// 👍 392 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashSet;
import java.util.Set;

/**
 * Success2
 * 借助HashMap的性质可以更简洁地实现下面的思路
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 注意这个数据结构
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            // 这个HashMap可以存储键值对
            // 注意这里是覆盖保存的，和滑动窗口也是一个意思
            map.put(num, i);
        }
        return false;
    }
}

/**
 * Failure2
 * 用HashSet的思路时间溢出
 */
//class Solution {
//    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            set.add(nums[i]);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int temp = i;
//            // 这种数据结构比起HashMap面这种数据结构的缺点就是没有存储多余的信息，比较的时候还得遍历一遍，时间自然就上去了
//            // 而且主要是不能获取位置信息
//            if (set.contains(nums[i])) {
//                for (int j = i + 1; j < nums.length; j++) {
//                    if (nums[j] == nums[i]) {
//                        if ((j - temp) <= k) {
//                            return true;
//                        } else {
//                            temp = j;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//}

/**
 * Failure1
 * 用排序的思路这个时间溢出
 */
//class Solution {
//    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        int[] sortedIndext = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            sortedIndext[i] = i;
//        }
//        for (int i = 1; i < nums.length; i++) {
//            int pirotNum = nums[i];
//            int pirotInex = sortedIndext[i];
//            int j;
//            for (j = i - 1; j > 0 && nums[j] > pirotNum; j--) {
//                nums[j + 1] = nums[j];
//                sortedIndext[j + 1] = sortedIndext[j];
//            }
//            nums[j + 1] = pirotNum;
//            sortedIndext[j + 1] = pirotInex;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j > 0 && nums[j] == nums[j-1]; j--) {
//                if ((sortedIndext[j] - sortedIndext[j - 1] <= k)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}

/**
 * Success1
 * Hash表+滑动窗口思想
 */
//class Solution {
//    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        HashSet<Integer> set = new HashSet<Integer>();
//        for(int i = 0; i < nums.length; i++) {
//            if(set.contains(nums[i])) {
//                return true;
//            }
//            set.add(nums[i]);
//            if(set.size() > k) {
//                // 注意这个函数
//                // 注意set本身还是线性表结构
//                set.remove(nums[i - k]);
//            }
//        }
//        return false;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
