//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2：
//
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 10⁴
// intervals[i].length == 2
// 0 <= starti <= endi <= 10⁴
//
//
//
//
//
// 注意：本题与主站 56 题相同： https://leetcode-cn.com/problems/merge-intervals/
//
// Related Topics 数组 排序 👍 67 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);
        int[] cur = intervals[0];
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (cur[1] >= intervals[i][0]) {
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                res.add(cur);
                cur = intervals[i];
            }
        }
        // 注意最后这里还要add一下
        res.add(cur);
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void quickSort(int[][] intervals, int low, int high) {
        if (low >= high) {
            return;
        }
        int[] pivot = intervals[low];
        int left = low;
        int right = high;
        while (left < right) {
            // 注意快排一定要先找小于等于的位置，否则不用换位置的临界条件始终会大于low
            while (left < right && intervals[right][0] > pivot[0]) {
                right--;
            }
            while (left < right && intervals[left][0] <= pivot[0]) {
                left++;
            }
            int[] temp = intervals[left];
            intervals[left] = intervals[right];
            intervals[right] = temp;
        }
        intervals[low] = intervals[left];
        intervals[left] = pivot;
        quickSort(intervals, low, left - 1);
        quickSort(intervals, left + 1, high);
    }

    public static void main(String[] args) {
        int[][] nums = new int[4][];
        nums[0] = new int[]{1, 3};
        nums[1] = new int[]{2, 6};
        nums[2] = new int[]{8, 10};
        nums[3] = new int[]{15, 18};
        Solution solution = new Solution();
        solution.merge(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
