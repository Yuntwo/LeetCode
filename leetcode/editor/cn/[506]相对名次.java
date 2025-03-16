//给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
//
// 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
//
//
// 名次第 1 的运动员获金牌 "Gold Medal" 。
// 名次第 2 的运动员获银牌 "Silver Medal" 。
// 名次第 3 的运动员获铜牌 "Bronze Medal" 。
// 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
//
//
// 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
//
//
//
// 示例 1：
//
//
//输入：score = [5,4,3,2,1]
//输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
//解释：名次为 [1ˢᵗ, 2ⁿᵈ, 3ʳᵈ, 4ᵗʰ, 5ᵗʰ] 。
//
// 示例 2：
//
//
//输入：score = [10,3,8,9,4]
//输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
//解释：名次为 [1ˢᵗ, 5ᵗʰ, 3ʳᵈ, 2ⁿᵈ, 4ᵗʰ] 。
//
//
//
//
// 提示：
//
//
// n == score.length
// 1 <= n <= 10⁴
// 0 <= score[i] <= 10⁶
// score 中的所有值 互不相同
//
//
// Related Topics 数组 排序 堆（优先队列） 👍 187 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 看上去挺麻烦的，时间和空间效率都还不错
class Solution {
    public String[] findRelativeRanks(int[] score) {
        int[] index = new int[score.length];
        for (int i = 0; i < score.length; i++) {
            index[i] = i;
        }
        // 注意end-1
        quickSort(score, index, 0, score.length - 1);
        String[] res = new String[score.length];
        res[index[0]] = "Gold Medal";
        // 注意判断长度的不同情况
        if (score.length == 1) {
            return res;
        }
        res[index[1]] = "Silver Medal";
        if (score.length == 2) {
            return res;
        }
        res[index[2]] = "Bronze Medal";
        if (score.length == 3) {
            return res;
        }
        for (int i = 3; i < score.length; i++) {
            // 注意名次要加1
            res[index[i]] = String.valueOf(i + 1);
        }
        return res;
    }

    private void quickSort(int[] score, int[] index, int start, int end) {
        if (start < end) {
            int pivotPos = partition(score, index, start, end);
            quickSort(score, index, start, pivotPos - 1);
            quickSort(score, index, pivotPos + 1, end);
        }
    }

    private int partition(int[] score, int[] index, int start, int end) {
        int pivotPos = index[start];
        while (start < end) {
            // 注意应该是由大到小排序
            // 注意这里各种索引关系，不要索引错了
            while (start < end && score[index[end]] <= score[pivotPos]) end--;
            index[start] = index[end];
            while (start < end && score[index[start]] >= score[pivotPos]) start++;
            index[end] = index[start];
        }
        index[start] = pivotPos;
        return start;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] score = {5, 4, 3, 2, 1};
        int[] score = {1};
        String[] res = solution.findRelativeRanks(score);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
