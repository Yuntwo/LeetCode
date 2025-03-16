//给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判
//断一个人是否能够参加这里面的全部会议。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[0,30],[5,10],[15,20]]
//输出：false
//
//
// 示例 2：
//
//
//输入：intervals = [[7,10],[2,4]]
//输出：true
//
//
//
//
// 提示：
//
//
// 0 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti < endi <= 106
//
// Related Topics 排序
// 👍 50 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public boolean canAttendMeetings(int[][] intervals) {
    for (int i = 1; i < intervals.length; i++) {
      int[] temp = intervals[i];
      int j;
      for (j = i; j > 0 && intervals[j - 1][0] > temp[0]; j--) {
        intervals[j] = intervals[j - 1];
      }
      intervals[j] = temp;
    }
    for (int i = 0; i < intervals.length; i++) {
      for (int j = i + 1; j < intervals.length; j++) {
        if (intervals[j][0] < intervals[i][1]) {
          return false;
        }
      }
    }
    return true;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
