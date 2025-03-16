//给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
//
// 如果不能形成任何面积不为零的三角形，返回 0。
//
//
//
//
//
//
// 示例 1：
//
// 输入：[2,1,2]
//输出：5
//
//
// 示例 2：
//
// 输入：[1,2,1]
//输出：0
//
//
// 示例 3：
//
// 输入：[3,2,3,4]
//输出：10
//
//
// 示例 4：
//
// 输入：[3,6,2,3]
//输出：8
//
//
//
//
// 提示：
//
//
// 3 <= A.length <= 10000
// 1 <= A[i] <= 10^6
//
// Related Topics 排序 数学
// 👍 88 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestPerimeter(int[] A) {
      int length = A.length;
      /**
       *  14ms
       */
      // quicksort(A, 0, length - 1);
      /**
       *  12ms
       */
      Arrays.sort(A);
      // Q:对于这种不重复遍历，不知道有没有更好的写法
      // 注意这道题是取最大值，要么按最大排序，要么按最小排序后反向索引
      /*for (int i = length - 1; i >= 0; i--) {
        for (int j = length - 1; j >= 0 & j != i; j--) {
          for (int k = length - 1; k >= 0 & k != i & k != j; k--) {
            if ((A[i] + A[j] > A[k]) & (A[i] + A[k] > A[j]) & (A[k] + A[j] > A[i])) {
              return (A[i] + A[j] + A[k]);
            }
          }
        }
      }*/
      // 利用有序数组本身的一些性质，其实我有些判断是多余的
      // 11ms
      for (int i = length - 1; i >= 2; i--) {
        if (A[i - 1] + A[i - 2] > A[i]) {
          return (A[i] + A[i - 1] + A[i - 2]);
        }
      }
      return 0;
    }

  private int partition(int[] array, int l, int r) {
    int v = array[l];
    int j = l;
    for (int i = l + 1; i <= r; i++) {
      if (array[i] <= v) {
        j++;
        swap(array, j, i);
      }
    }
    swap(array, l, j);
    return j;
  }

  private void quicksort(int[] array, int l, int r) {
    // Q:其实我对于这个return条件不是很理解
    if (l >= r) {
      return;
    }
    int p = partition(array, l, r);
    quicksort(array, l, p - 1);
    quicksort(array, p + 1, r);
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private void swap(int[][] array, int i, int j) {
    int[] temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    int[] test = new int[]{3, 6, 2, 3};
    TestSolution solution = new TestSolution();
    solution.largestPerimeter(test);
    System.out.println(test);
  }
}
//leetcode submit region end(Prohibit modification and deletion)
