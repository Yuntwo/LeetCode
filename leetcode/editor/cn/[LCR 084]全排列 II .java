//给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
//
//
//
//
// 注意：本题与主站 47 题相同： https://leetcode-cn.com/problems/permutations-ii/
//
// Related Topics 数组 回溯 👍 60 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 这样效率不是很高
 */
class Solution {

    private List<Integer> res = new ArrayList<>();

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        reg(nums, 0);
        return ans;
    }

    private void reg(int[] nums, int start) {
        if (start == nums.length) {
            ans.add(new ArrayList<>(res));
            return;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (!hashSet.contains(nums[i])) {
                // >>> swap start
                int temp = nums[start];
                nums[start] = nums[i];
                nums[i] = temp;
                // <<<< swap end
                // to avoid repeat
                hashSet.add(nums[start]);
                res.add(nums[start]);
                reg(nums, start + 1);
                // when return, remove last
                res.remove(res.size() - 1);
                // >>> swap back start
                temp = nums[start];
                nums[start] = nums[i];
                nums[i] = temp;
                // <<<< swap back end
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
