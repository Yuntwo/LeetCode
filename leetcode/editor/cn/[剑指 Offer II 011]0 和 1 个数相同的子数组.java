//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
//
//
// 示例 1：
//
//
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
//
// 示例 2：
//
//
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// nums[i] 不是 0 就是 1
//
//
//
//
//
// 注意：本题与主站 525 题相同： https://leetcode-cn.com/problems/contiguous-array/
//
// Related Topics 数组 哈希表 前缀和 👍 138 👎 0




//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Success2
 * 改进Failure2，统计zeros和ones的差值也行，但是本质上思路还是(必须)变成Success1的思路了
 * 注意对于空间换时间的思路，一定是HashMap中存了某个需要一重循环的过程结果，所以能直接省去这个一重循环get
 * 当然对于外层逻辑的设计也要巧妙一点，尽量设计成一重循环
 * HashMap的结果一般与外重循环要的结果是一致的，这样才能真正将二重循环换成一重循环，否则思路错误白搭
 * 但不知道为什么效率也没有100%
 */
class Solution {
    public int findMaxLength(int[] nums) {
        int res = 0;
        int zeros = 0;
        int ones = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            } else {
                ones++;
            }
            res = Math.max(res, i - hashMap.getOrDefault(ones - zeros, i));
            hashMap.put(ones - zeros, Math.min(hashMap.getOrDefault(ones - zeros, i), i));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
        Solution solution = new Solution();
        int res = solution.findMaxLength(nums);
        System.out.println(res);
    }
}

/**
 * Success1
 * 还是按照前缀和的思路，这里需要进行数学转换，将0、1个数相等问题转化为0减1、1加1、和为0前缀和问题
 * 然后这里由于求的是最长数组，所以HashMap中存的key要是sum，但是value变成所有sum对应的最小索引
 * 根据具体问题，如果是求sum=target的子数组个数，那么value就是sum的个数
 */
//class Solution {
//    public int findMaxLength(int[] nums) {
//        int res = 0;
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        // 注意这个初始值是必须要的
//        hashMap.put(0, -1);
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += (nums[i] == 0 ? -1 : 1);
//            // 注意这里数学运算换位置，是sum不是0-sum
//            res = Math.max(res, i - hashMap.getOrDefault(sum, i));
//            hashMap.put(sum, Math.min(hashMap.getOrDefault(sum, i), i));
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
//        Solution solution = new Solution();
//        int res = solution.findMaxLength(nums);
//        System.out.println(res);
//    }
//}

/**
 * Failure2
 * HashSet中换成int[]还是超时，应该是外层逻辑太麻烦了
 * 这里还是有二重循环，要用HashMap空间换时间的话就必须想办法变成一重循环，要能直接从HashMap中get到想要的value，而不是再循环一遍
 * 说到底这里错误的原因是HashSet里面存的是唯一的key，zeros和ones共同组成的是key，用HashMap存zeros的key和ones的value本质上还是存的key
 * 这个额外空间根本没有存一重循环的结果，当然没有换时间
 */
//class Solution {
//    public int findMaxLength(int[] nums) {
//        int res = 0;
//        int zeros = 0;
//        int ones = 0;
//        HashSet<int[]> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                zeros++;
//            } else {
//                ones++;
//            }
//            if (zeros == ones) {
//                res = Math.max(res, zeros + ones);
//            } else {
//                for (int[] entry : set) {
//                    if (zeros - entry[0] == ones - entry[1]) {
//                        res = Math.max(res, 2 * (zeros - entry[0]));
//                    }
//                }
//                set.add(new int[]{zeros, ones});
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
//        Solution solution = new Solution();
//        int res = solution.findMaxLength(nums);
//        System.out.println(res);
//    }
//}

/**
 * Faiure1
 * 这种数据结构还是复杂了，超时
 */
//class Solution {
//    public int findMaxLength(int[] nums) {
//        int res = 0;
//        int zeros = 0;
//        int ones = 0;
//        // 注意不能用HashMap，里面的键不能重复
////        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        HashSet<Map.Entry<Integer, Integer>> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                zeros++;
//            } else {
//                ones++;
//            }
//            if (zeros == ones) {
//                res = Math.max(res, zeros + ones);
//            } else {
//                for (Map.Entry<Integer, Integer> entry : set) {
//                    if (zeros - entry.getKey() == ones - entry.getValue()) {
//                        res = Math.max(res, 2 * (zeros - entry.getKey()));
//                    }
//                }
//                set.add(new AbstractMap.SimpleEntry<>(zeros, ones));
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
//        Solution solution = new Solution();
//        int res = solution.findMaxLength(nums);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
