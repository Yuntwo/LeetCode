//给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// nums 中的所有整数 互不相同
//
//
//
//
//
// 注意：本题与主站 46 题相同：https://leetcode-cn.com/problems/permutations/
//
// Related Topics 数组 回溯 👍 70 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 注意回溯时改删除的、该补回的
 */
class Solution {

    private List<List<Integer>> ans = new ArrayList<>();

    private List<Integer> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            temp.add(nums[i]);
        }
        reg(temp);
        return ans;
    }

    private void reg(List<Integer> nums) {
        if (nums.size() == 0) {
            ans.add(new ArrayList<>(res));
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            int temp = nums.get(i);
            res.add(nums.get(i));
            nums.remove(i);
            reg(nums);
            res.remove(res.size() - 1);
            nums.add(i, temp);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> ans = solution.permute(nums);
        System.out.println(ans);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
