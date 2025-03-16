//假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
//
// 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[
//i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
//
// 示例 1:
//
//
//输入: g = [1,2,3], s = [1,1]
//输出: 1
//解释:
//你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
//虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
//所以你应该输出1。
//
//
// 示例 2:
//
//
//输入: g = [1,2], s = [1,2,3]
//输出: 2
//解释:
//你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
//你拥有的饼干数量和尺寸都足以让所有孩子满足。
//所以你应该输出2.
//
//
//
//
// 提示：
//
//
// 1 <= g.length <= 3 * 10⁴
// 0 <= s.length <= 3 * 10⁴
// 1 <= g[i], s[j] <= 2³¹ - 1
//
//
// Related Topics 贪心 数组 双指针 排序 👍 599 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 注意这里题目没有说g和s是排好序的
// 时间效率不是很高
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int i = 0;
        int j = 0;
        int res = 0;
        quickSort(g, 0, g.length - 1);
        quickSort(s, 0, s.length - 1);
        // 注意g和s都要控制边界
        while (j < s.length && i < g.length) {
            if (s[j] >= g[i]) {
                res++;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return res;
    }

    private void quickSort(int[] nums, int start, int end) {
        // 注意这里是if而不是while
        if (start < end) {
            int pivotPos = partition(nums, start, end);
            quickSort(nums, start, pivotPos - 1);
            quickSort(nums, pivotPos + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= pivot) end--;
            nums[start] = nums[end];
            while(start<end && nums[start] <= pivot) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] g = {1, 2};
        int[] s = {1, 1};
        int res = solution.findContentChildren(g, s);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
