//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
//
//
//
// 示例 1：
//
//
//输入：timePoints = ["23:59","00:00"]
//输出：1
//
//
// 示例 2：
//
//
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
//
//
//
//
// 提示：
//
//
// 2 <= timePoints.length <= 2 * 10⁴
// timePoints[i] 格式为 "HH:MM"
//
//
//
//
//
// 注意：本题与主站 539 题相同： https://leetcode-cn.com/problems/minimum-time-difference/
//
// Related Topics 数组 数学 字符串 排序 👍 50 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 注意关键在于理解时间的大小是圈式循环的
 */
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] time = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] s = timePoints.get(i).split(":");
            time[i] = 60 * Integer.valueOf(s[0]) + Integer.valueOf(s[1]);
        }
        Arrays.sort(time);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < time.length; i++) {
            // 注意time本来就是递增排序的，所以不需要额外取绝对值
            // 注意这里的关键在于时间是圈式循环
//            int diff = Math.min(time[i] - time[i - 1], time[i - 1] + 1440 - time[i]);
            // 但是大部分情况下不需要考虑到圈，因为它是递增的，只需要考虑边界条件即可
            int diff = time[i] - time[i - 1];
            if (diff < res) {
                res = diff;
            }
        }
        // 注意由于是时间是圈式循环的，所以这种情况也有可能是最优解
        res = Math.min(res, time[0] + 1440 - time[time.length - 1]);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
