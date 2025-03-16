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
// Related Topics 数组 回溯 👍 39 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//
//    // 这里使用ArrayList和LinkedList似乎没有多少时间和空间上的差别
//    // 懒汉模式还是恶汉模式似乎对于时间效率是没有差别的，不知道对于空间效率怎么样
//    private List<List<Integer>> res = new ArrayList<List<Integer>>();
//
//    private List<Integer> resSingle = new ArrayList<>();
//
//    private int[] nums;
//
//    public List<List<Integer>> permute(int[] nums) {
//        this.nums = nums;
//        dfs(0);
//        return res;
//    }
//
//    private void dfs(int x) {
//        if (x == nums.length - 1) {
//            resSingle.add(nums[x]);
//            res.add(new LinkedList<Integer>(resSingle));
//            resSingle.remove(nums.length - 1);
//            return;
//        }
//        for (int i = x; i < nums.length; i++) {
//            resSingle.add(nums[i]);
//            swap(i, x);
//            dfs(x + 1);
//            resSingle.remove(x);
//            swap(i, x);
//        }
//    }
//
//    private void swap(int i, int j) {
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
//}
// 直接操作数组再生成List，而不是复制list到res中似乎时间效率会更高
// 时间效率是高很多
class Solution {

    // 这里使用ArrayList和LinkedList似乎没有多少时间和空间上的差别
    private List<List<Integer>> res;

    private int[] nums;

    public List<List<Integer>> permute(int[] nums) {
//        res = new ArrayList<List<Integer>>();
        // 甚至都可以不指明里面的类型形式
        res = new ArrayList<>();
        this.nums = nums;
        dfs(0);
        return res;
    }

    private void dfs(int x) {
        if (x == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
            }
            res.add(list);
            return;
        }
        for (int i = x; i < nums.length; i++) {
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
