//给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1]
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4]
//输出：false
//
// 示例 3：
//
//
//输入：nums = [1,1,1,3,3,4,3,2,4,2]
//输出：true
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
//
// Related Topics 数组 哈希表 排序
// 👍 604 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 用不允许重复元素的集合
 * 其实还是用的hash表或者用字典也可以，都是用的现成的类
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
//        boolean res = true;
        // 注意这种写法
        for (int i : nums) {
//            if (!(res && set.add(i))) {
            if (!set.add(i)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Faliure2
 * Hash表太大，超出内存限制，不能用Hash法
 * 也不能用bit位数字的形式，因为还是数字取值范围太大，超出数值范围
 */
//class Solution {
//    public boolean containsDuplicate(int[] nums) {
//        int[] hash = new int[2 * (int) 1e9 + 1];
//        for (int i = 0; i < nums.length; i++) {
//            int temp = nums[i + (int) 1e9];
//            if (hash[temp] != 1) {
//                hash[temp]++;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }
//}

/**
 * Failure1
 * 因为数组值没有比较特别的性质，所以似乎不能用剑指offer题3的思路
 */
//class Solution {
//    public boolean containsDuplicate(int[] nums) {
//        for (int i = 0; i++; i < nums.length) {
//            while (nums[i] != i) {
//
//            }
//        }
//        return false;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
