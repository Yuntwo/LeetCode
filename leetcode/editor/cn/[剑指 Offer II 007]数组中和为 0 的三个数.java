//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j !=
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
//
// 你返回所有和为 0 且不重复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
//
//
// 示例 3：
//
//
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 3000
// -10⁵ <= nums[i] <= 10⁵
//
//
//
// 注意：本题与主站 15 题相同：leetcode.cn/problems/3sum/
//
// Related Topics 数组 双指针 排序 👍 114 👎 0


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 用双指针把求三个数的和三重循环变为求一个数和双指针两个数的和双重循环
 */
class Solution {

    /**
     * Success2
     * 条件判断优化写法
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        int i, j, k;
        for (i = 0; i < nums.length; i++) {
            // 注意这种写法
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            j = i + 1;
            k = nums.length - 1;
            while (j < k) {
                // 意思是只有在第一个位置，才允许相等，才不是重复的；其它相等的位置都是重复的
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if (nums[j] + nums[k] < -nums[i]) {
                    j++;
                } else if (nums[j] + nums[k] == -nums[i]) {
                    List<Integer> ans = new LinkedList<>();
                    ans.add(nums[i]);
                    ans.add(nums[j]);
                    ans.add(nums[k]);
                    res.add(ans);
                    // 这种情况同时挪动
                    j++;
                    k--;
                    // 否则就是大于的情况
                } else {
                    k--;
                }
            }
        }
        return res;
    }

    /**
     * Success1
     */
//    public List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        List<List<Integer>> res = new LinkedList<>();
//        int i, j, k;
//        for (i = 0; i < nums.length; i++) {
//            if (i == 0 || nums[i] != nums[i - 1]) {
//                j = i + 1;
//                k = nums.length - 1;
//                while (j < k) {
//                    if (j == i + 1 || nums[j] != nums[j - 1]) {
//                        if (nums[j] + nums[k] < -nums[i]) {
//                            j++;
//                        } else if (nums[j] + nums[k] == -nums[i]) {
//                            List<Integer> ans = new LinkedList<>();
//                            ans.add(nums[i]);
//                            ans.add(nums[j]);
//                            ans.add(nums[k]);
//                            res.add(ans);
//                            // 这种情况同时挪动
//                            j++;
//                            k--;
//                            // 否则就是大于的情况
//                        } else {
//                            k--;
//                        }
//                    // 注意这里的if和j++是为了找到第一个起始点，和nums[j] + nums[k] < -nums[i]那里的j++含义不同
//                    } else {
//                        j++;
//                    }
//                }
//            }
//        }
//        return res;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.threeSum(new int[]{0, 0, 0, 0});
        System.out.println("End");
    }
}

/**
 * Failure1
 * 直接三重循环会超时
 */
//class Solution {
//    public List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        List<List<Integer>> res = new LinkedList<>();
//        for (int i = 0; i < nums.length; i++) {
//            // 注意这种框架都是为了防止重复
//            if (i == 0 || (nums[i] != nums[i - 1])) {
//                // 注意一次循环中每个数字只能用一次
//                for (int j = i + 1; j < nums.length; j++) {
//                    // 注意这里对于j和k不是0，是相对的索引
//                    if (j == i + 1 || (nums[j] != nums[j - 1])) {
//                        for (int k = j + 1; k < nums.length; k++) {
//                            if (k == j + 1 || (nums[k] != nums[k - 1])) {
//                                if (nums[i] + nums[j] + nums[k] == 0) {
//                                    List<Integer> ans = new LinkedList<>();
//                                    ans.add(nums[i]);
//                                    ans.add(nums[j]);
//                                    ans.add(nums[k]);
//                                    res.add(ans);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.threeSum(new int[]{0, 0, 0, 0});
//        System.out.println("End");
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
