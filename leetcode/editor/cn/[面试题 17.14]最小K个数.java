//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
//
// 示例：
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
//
//
// 提示：
//
//
// 0 <= len(arr) <= 100000
// 0 <= k <= min(100000, len(arr))
//
// Related Topics 堆 排序 分治算法
// 👍 34 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] smallestK(int[] arr, int k) {
      heapSort(arr, arr.length);
      int[] result = Arrays.copyOf(arr, k);
      return result;
    }

    public void shiftDown(int[] arr, int n, int k) {
    // 意思应该是右孩子不一定存在
    while (2 * k + 1 < n) {

      int j = 2 * k + 1;
      // 先比较左右孩子
      // 这里要用短路判断，否则会超出索引
      if (j + 1 < n && arr[j + 1] > arr[j]) {
        j++;
      }

      if (arr[k] >= arr[j]) {
        break;
      }

      swap(arr, k, j);
      k = j;
    }
  }

    public void shiftUp(int[] arr, int n, int k) {
    while ((k - 1) / 2 >= 0 && arr[k] > arr[(k - 1) / 2]) {
      swap(arr, k, (k - 1) / 2);
      k = (k - 1) / 2;
    }
  }

    public void heapiFy(int[] arr) {
    int count = arr.length;
    for (int i = (count - 1) / 2; i >= 0; i--) {
      shiftDown(arr, count, i);
    }
  }

    public void heapSort(int[] arr, int n) {
    heapiFy(arr);
    for (int i = n - 1; i > 0; i--) {
      swap(arr, 0, i);
      shiftDown(arr, i, 0);
    }
  }

    public void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
//leetcode submit region end(Prohibit modification and deletion)
