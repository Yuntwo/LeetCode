//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
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
// Related Topics 数组 回溯 👍 2303 👎 0


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 由于题目中说了nums中的所有整数互不相同，所以不需要进行剪枝操作
 * 这里直接交换位置到时很方便
 */
class Solution {

    private List<List<Integer>> res = new LinkedList<List<Integer>>();
    private List<Integer> resSingle = new LinkedList<Integer>();

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private void dfs(int[] nums, int i) {
        if (i == nums.length - 1) {
            resSingle.add(nums[i]);
            // 注意resSingle的通用角色
            res.add(new LinkedList<>(resSingle));
            // 注意清空用这clear而不是removeAll
//            resSingle.clear();
            // 注意因为这里链表实际上是引用，所以你其实要生成新的链表才行，否则清空的还是原来的结果
//            resSingle = new LinkedList<Integer>();
            // 但其实应该也不是全部都清空了，因为本质上是递归也是要前面的结果的
            resSingle.remove(resSingle.size() - 1);
            return;
        }
        for (int j = i; j < nums.length; j++) {
            // 注意这里是先add(nums[j])而不是nums[i]，除非你先swap再add
            resSingle.add(nums[j]);
            swap(nums, i, j);
            dfs(nums, i + 1);
            resSingle.remove(resSingle.size() - 1);
            // 注意交换完之后要换回来再进行下一轮交换，否则逻辑会混乱
            swap(nums, i, j);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = solution.permute(nums);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
