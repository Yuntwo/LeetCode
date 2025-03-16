//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
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
// Related Topics 数组 回溯 👍 1238 👎 0


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 注意这里里面要是抽象接口
    List<List<Integer>> res = new LinkedList<List<Integer>>();
    List<Integer> resSingle = new LinkedList<Integer>();
    int[] nums;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        dfs(0);
        return res;
    }

    private void dfs(int x) {
        if (x == nums.length - 1) {
            resSingle.add(nums[x]);
            res.add(new LinkedList<Integer>(resSingle));
            resSingle.remove(nums.length - 1);
            return;
        }
        // 注意这里要用set来判断是否重复
        // 而不能用等于x处的值，否则无法排除x处的情况，或者再判断一下代码不简洁
        // 而且重复也不只是与x处重复
        HashSet<Integer> set = new HashSet<>();
        for (int i = x; i < nums.length; i++) {
            // 就多了这样一个判断重复枝以及减枝操作
            if (set.contains(nums[i])) {
                continue;
            } else {
                set.add(nums[i]);
            }
            resSingle.add(nums[i]);
            swap(i, x);
            dfs(x + 1);
            // 注意向上返回之前一定删除自身这个元素
            // 而且注意它是层层返回的
            resSingle.remove(resSingle.size() - 1);
            swap(i, x);
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
