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
// Related Topics 数组 回溯 👍 34 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    private int[] nums;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        dfs(0);
        return res;
    }

    private void dfs(int x) {
        if (x == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            // 注意这种写法
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = x; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            } else {
                set.add(nums[i]);
            }
            swap(x, i);
            dfs(x + 1);
            swap(x, i);
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
