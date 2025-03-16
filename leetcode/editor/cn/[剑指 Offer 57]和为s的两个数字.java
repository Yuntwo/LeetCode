//输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
//
//
//
// 示例 1：
//
// 输入：nums = [2,7,11,15], target = 9
//输出：[2,7] 或者 [7,2]
//
//
// 示例 2：
//
// 输入：nums = [10,26,30,31,47,60], target = 40
//输出：[10,30] 或者 [30,10]
//
//
//
//
// 限制：
//
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^6
//
// Related Topics 数组 双指针 二分查找 👍 168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//最笨的方法—顺序

// 暴力搜索，超出时间限制
//class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        int temp = 0;
//        // 注意开辟数组空间也需要new，注意不需要{}
//        int[] res = new int[2];
////        int midSum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                temp = nums[i] + nums[j];
//                if (temp == target) {
//                    res[0] = nums[i];
//                    res[1] = nums[j];
//                }
//            }
//        }
//        return res;
//    }
//}

import java.util.HashSet;

// 利用集合来存储历史遍历数据，只遍历一遍
// 主要是这种数据结构的作用很巧妙！！！
// 但是也没有比双指针快
//class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        HashSet<Integer> set = new HashSet<>();
//        //  注意这种for循环写法
//        for (int num : nums) {
//            if (set.contains(target - num)) {
//                return new int[]{num, target - num};
//            } else {
//                set.add(num);
//            }
//        }
//        return new int[2];
//    }
//}
// 双指针，对撞指针，只需遍历一遍，这样很快
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        // 注意用这种方法，而不是用加起来等于，否则会造成溢出
        // Q:怎么就溢出了？
        while (nums[i]!=target-nums[j]) {
            if (nums[i] < target - nums[j]) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{nums[i], nums[j]};
    }
}

// 双指针，利用有序数组特性从中间向两边寻找，遍历两遍
// 其实找等于target/2的数的位置可以用二分法快一点
//class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        // 注意开辟数组空间也需要new，注意不需要{}
//        int[] res = new int[2];
//        int midSumi = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            // 注意这里控制条件十分细节，有个等于都不行，因为取值特殊情况
//            if (nums[i + 1] > target / 2) {
//                midSumi = i;
//                break;
//            }
//        }
//        int k = midSumi;
//        int v = midSumi;
//        int temp = nums[k] + nums[v];
//        // 注意这里控制条件十分细节
//        while (temp != target || k == v) {
//            if (temp > target) {
//                k--;
//            } else {
//                v++;
//            }
//            temp = nums[k] + nums[v];
//        }
//        res[0] = nums[k];
//        res[1] = nums[v];
//        return res;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{45,46,67,73,74,74,77,83,89,98};
//        Solution solution = new Solution();
//        int[] res = solution.twoSum(nums, 147);
//        System.out.println(res);
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
