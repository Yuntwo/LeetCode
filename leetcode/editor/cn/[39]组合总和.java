//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
//
//
//
// 示例 1：
//
//
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。
//
// 示例 2：
//
//
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]]
//
// 示例 3：
//
//
//输入: candidates = [2], target = 1
//输出: []
//
//
//
//
// 提示：
//
//
// 1 <= candidates.length <= 30
// 2 <= candidates[i] <= 40
// candidates 的所有元素 互不相同
// 1 <= target <= 40
//
//
// Related Topics 数组 回溯 👍 2278 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import java.util.ArrayList;
import java.util.List;

// 注意它这道题的意思就是不考虑排列顺序了
// 然后返回的数据结构也有点区别
// 但是这样存起来有点麻烦和耗空间，如果真要存那么多List的话
// 而且由于都是引用数据类型，所以必须要拷贝才行

/**
 * Success1
 * 引用数据类型
 * 虽然是组合类题目，但似乎用背包问题的解题思路比较难得到想要结果的数据类型，但也能办
 * 就是时间和空间效率比较低
 */
//class Solution {
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
////        int[] coms = new int[target + 1];
//        // 注意这样都只是声明了一个数组的空间
//        // 对于引用变量类型来说该数组位置的空间还是null
//        List<List<Integer>>[] comsList = new ArrayList[target + 1];
//        for (int i = 0; i < comsList.length; i++) {
//            comsList[i] = new ArrayList<>();
//        }
////        coms[0] = 1;
//        comsList[0].add(new ArrayList<>());
//        for (int i = 0; i < candidates.length; i++) {
//            for (int j = candidates[i]; j < target + 1; j++) {
////                coms[j] += coms[j - candidates[i]];
//                for (int k = 0; k < comsList[j - candidates[i]].size(); k++) {
//                    List<Integer> temp = new ArrayList<>(comsList[j - candidates[i]].get(k));
//                    temp.add(candidates[i]);
//                    comsList[j].add(temp);
//                }
//            }
//        }
//        return comsList[target];
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{2, 3, 6, 7};
//        int target = 7;
//        List<List<Integer>> res = solution.combinationSum(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 用回溯法的话，由于每个元素可以选很多次，这就让问题变得更复杂了
 * 好在这只能加，问题还没有可以加减那么复杂
 */
//class Solution {
//
//    private List<List<Integer>> res = new ArrayList<>();
//
//    private List<Integer> now = new ArrayList<>();
//
//    private int sum = 0;
//
//    private int[] candidates;
//
//    private int target;
//
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        this.candidates = candidates;
//        this.target = target;
//        reg(0);
//        return res;
//    }
//
//    private void reg(int i) {
//        // 还是像逐个加石头那样，加到最后一个石头才返回
//        // 这样也能保证顺序不重复
//        // 中间满足结果要求只是加结果
//        if (i == candidates.length) {
//            return;
//        }
//        // 一个石头可以选多次
//        for (int j = 1; sum + j * candidates[i] <= target; j++) {
//            sum += j * candidates[i];
//            for (int k = 0; k < j; k++) {
//                now.add(candidates[i]);
//            }
//            // 判断加上当前石头的之前所有能否等于target
//            if (sum == target) {
//                res.add(new ArrayList<>(now));
//            }
//            reg(i + 1);
//            sum -= j * candidates[i];
//            for (int k = 0; k < j; k++) {
//                // 一定是移除的末尾的元素
//                now.remove(now.size() - 1);
//            }
//        }
//        // 不选这个石头
//        // 不会对sum造成任何影响，直接进行下一层递归
//        reg(i + 1);
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{2, 3, 6, 7};
//        int target = 7;
//        List<List<Integer>> res = solution.combinationSum(nums, target);
//        System.out.println(res);
//    }
//}

/**
 * Success3
 * 题解做法
 * 表达更精炼
 * 同时一些细节上的改进使得理解更难
 * Q:但是感觉它这种写法是每种石头只选了一遍啊
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        // 不管之前选没选，统一在这里判断是否达到target，加入ans
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            // 因为它这里是选择了当前数后又从当前数开始递归，所以相当于可以重复选择当前数
            // 挺巧妙的，当然对于整个过程也更加不好理解
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2, 3, 6, 7};
        int target = 6;
        List<List<Integer>> res = solution.combinationSum(nums, target);
        System.out.println(res);
    }

}

/**
 *
 */
//leetcode submit region end(Prohibit modification and deletion)
