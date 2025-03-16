//给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否
//则返回 -1。
//
// 示例 1:
//
// 输入: nums = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 出现在 nums 中并且下标为 4
//
//
// 示例 2:
//
// 输入: nums = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不存在 nums 中因此返回 -1
//
//
//
//
// 提示：
//
//
// 你可以假设 nums 中的所有元素是不重复的。
// n 将在 [1, 10000]之间。
// nums 的每个元素都将在 [-9999, 9999]之间。
//
//
// Related Topics 数组 二分查找 👍 1043 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        // 这里必须是<=
        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 9;
        int res = solution.search(nums, target);
        System.out.println(res);
    }
}

/**
 * Review1
 */
//class Solution {
//    public int search(int[] nums, int target) {
//        int left = 0;
//        int right = nums.length - 1;
//        while (left <= right) {
//            // 注意这里的两个括号必须要，+的优先级高于>>
//            int mid = left + ((right - left) >> 1);
//            if (nums[mid] == target) {
//                return mid;
//            } else if (nums[mid] > target) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        return -1;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
//        int target = 9;
//        int res = solution.search(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 */
//class Solution {
//    public int search(int[] nums, int target) {
//        int l = 0;
//        int r = nums.length - 1;
//        // 由于l、r具有连续性，所以跳出循环的时候一定是l==r
//        while (l < r) {
//            // 这里加1是为了奇数除法上取整的意思
//            // 如果是下取整的话会死循环
//            // 不是能不能遍历到元素的问题，而是到了相邻位置，下取整永远不能使l==r从而跳出循环
//            int mid = (l + r + 1) / 2;
//            if (nums[mid] == target) {
//                return mid;
//            }
//            // 注意这样赋值之后是l小于等于，r大于等于，所以跳出循环是l==r的位置一定是==target
//            if (nums[mid] <= target) {
//                l = mid;
//            } else {
//                r = mid - 1;
//            }
//        }
//        // 只有一个元素/等于target的位置刚好在循环结束l==r那里
//        if (nums[l] == target) {
//            return l;
//        }
//        return -1;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
//        int res = solution.search(nums, 2);
////        int[] nums = new int[]{2, 5};
////        int res = solution.search(nums, 2);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
