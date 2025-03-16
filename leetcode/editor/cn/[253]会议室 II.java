//给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑
//充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
//
// 示例 1:
//
// 输入: [[0, 30],[5, 10],[15, 20]]
//输出: 2
//
// 示例 2:
//
// 输入: [[7,10],[2,4]]
//输出: 1
// Related Topics 堆 贪心算法 排序
// 👍 195 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public int minMeetingRooms(int[][] intervals) {
    int n = intervals.length;
    if (n == 0) {
      return 0;
    }

    int[] arr = new int[2 * n];
    int[] tag = new int[2 * n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2; j++) {
        arr[2 * i + j] = intervals[i][j];
        tag[2 * i + j] = j;
      }
    }

    int roomNum = 1;
    int roomNumMax = 1;

    insertSort(arr, tag);

    for (int i = 1; i < 2 * n; i++) {
      if (tag[i] == 0) {
        roomNum++;
        if (roomNum > roomNumMax) {
          roomNumMax = roomNum;
        }
      } else {
        roomNum--;
      }
    }
    return roomNumMax;
  }

  public void insertSort(int[] arr, int[] tag) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      int temp = arr[i];
      int tempTag = tag[i];
      // 这里只是记录位置变了，实际数值还是那么多
      int j = i;
      while (j > 0 && arr[j - 1] >= temp) {
        // 先下后上，感觉这样写很多余呀
        if (arr[j - 1] == temp && tag[j - 1] < tempTag) {
          // 这种写法就可以控制局部条件控制外部变量
          // 但也还是得遍历所有情况
          arr[j] = arr[j - 1];
          tag[j] = tag[j - 1];
          j--;
        } else if (arr[j - 1] > temp) {
          arr[j] = arr[j - 1];
          tag[j] = tag[j - 1];
          j--;
        } else {
          break;
        }
        // 如果这样写，即使不满足条件，你也要用索引先判断，而且如果前面已经满足条件了，这里判断的j值其实已经发生变化了
        // 最好的方式是用else
//        if (arr[j - 1] == temp && tag[j - 1] >= tempTag) {
//          break;
//        }
        }
      arr[j] = temp;
      tag[j] = tempTag;
      // 这样写会导致=的情况中不满足的子情况同样会使得j--
      // 也就是是这种for循环里带if控制外部变量变化的情况，一定是要遍历所有情况
//      for (j = i; j > 0 && arr[j - 1] >= temp; j--) {
//        // 先下后上，感觉这样写很多余呀
//        if (arr[j - 1] == temp && tag[j - 1] < tempTag) {
//          arr[j] = arr[j - 1];
//          tag[j] = tag[j - 1];
//        }
//        if (arr[j - 1] > temp) {
//          arr[j] = arr[j - 1];
//          tag[j] = tag[j - 1];
//        }
//      }
//      arr[j] = temp;
//      tag[j] = tempTag;
    }
  }

  public static void main(String[] args) {
    TestSolution solution = new TestSolution();
    int[][] intervals = new int[][]{{9, 10}, {4, 9}, {4, 17}};
    int num = solution.minMeetingRooms(intervals);
    System.out.println(num);
  }

  //  public void insertSort(int[][] intervals) {
//    int n = intervals.length;
//    for (int i = 1; i < n; i++) {
//      int[] temp = intervals[i];
//      int temp0 = temp[0];
//      int temp1 = temp[1];
//      int j = i;
//      while (j > 0) {
//        if (temp0 < intervals[j - 1][0]) {
//          intervals[j] = intervals[j - 1];
//          j--;
//        }
//        else if (temp0 == intervals[j - 1][0] && temp1 > intervals[j - 1][1]) {
//          intervals[j] = intervals[j - 1];
//          j--;
//        }
//        else if (temp0 == intervals[j - 1][0] && temp1 <= intervals[j - 1][1]) {
//          break;
//        }
//        else if (temp0 > intervals[j - 1][0]) {
//          break;
//        }
//      }
//      intervals[j] = temp;
//    }

//  }
}
//leetcode submit region end(Prohibit modification and deletion)
