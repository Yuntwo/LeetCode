//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
//
//
// 示例 1：
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
//
//
// 示例 2：
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0]
//
//
//
// 限制：
//
//
// 0 <= k <= arr.length <= 10000
// 0 <= arr[i] <= 10000
//
// Related Topics 堆 分治算法
// 👍 162 👎 0

//
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 手撕一个堆
 */
//class Solution {
//    public int[] getLeastNumbers(int[] arr, int k) {
//      heapSort(arr, arr.length);
//      int[] result = Arrays.copyOf(arr, k);
//      return result;
//    }
//    // 采用原地堆排序的思路
//    // 采用0位置开头索引
//    // 建最大堆
//    // 这只是针对单个元素的排序，要对整个排序还需在外层有遍历
//  public void shiftDown(int[] arr, int n, int k) {
//     // 意思应该是右孩子不一定存在
//    while (2 * k + 1 < n) {
//
//      int j = 2 * k + 1;
//      // 先比较左右孩子
//      // 这里要用短路判断，否则会超出索引
//      if (j + 1 < n && arr[j + 1] > arr[j]) {
//        j++;
//      }
//
//      if (arr[k] >= arr[j]) {
//        break;
//      }
//
//      swap(arr, k, j);
//      k = j;
//    }
//  }
//
//  public void shiftUp(int[] arr, int n, int k) {
//    while ((k - 1) / 2 >= 0 && arr[k] > arr[(k - 1) / 2]) {
//      swap(arr, k, (k - 1) / 2);
//      k = (k - 1) / 2;
//    }
//  }
//
//  public void heapiFy(int[] arr) {
//    int count = arr.length;
//    for (int i = (count - 1) / 2; i >= 0; i--) {
//      shiftDown(arr, count, i);
//    }
//  }
//
//  public void heapSort(int[] arr, int n) {
//    heapiFy(arr);
//    for (int i = n - 1; i > 0; i--) {
//      swap(arr, 0, i);
//      shiftDown(arr, i, 0);
//    }
//  }
//
//  public void swap(int[] array, int i, int j) {
//    int temp = array[i];
//    array[i] = array[j];
//    array[j] = temp;
//  }
//
//  public static void main(String[] args) {
//    int[] arr = new int[]{0,1,2,1};
//    Solution solution = new Solution();
//    solution.getLeastNumbers(arr, 1);
//    System.out.println("end");
//  }
//}
/**
 * PriorityQueue实现堆
 * 用堆的方法并不是最快的，但是用API实现比较简单
 */
//class Solution {
//    public int[] getLeastNumbers(int[] arr, int k) {
//        // PriorityQueue默认是小根堆，需要重写比较器方法变成大根堆
//        // 简化版
//        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>((v1, v2) -> v2 - v1);
//        // 完整版
////        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
////            public int compare(Integer num1, Integer num2) {
////                return num2 - num1;
////            }
////        });
//        // 注意这里为0的情况要单独处理，否则后面无法短路或会报NullPointerException
//        if (k == 0) {
//            return new int[k];
//        }
//        for (int num : arr) {
//            // 注意这里要用短路或，否则初始堆为空，peek会报NullPointerException
//            // 或者先加入k个元素到堆里
//            if (maxQueue.size() < k || num < maxQueue.peek()) {
//                maxQueue.offer(num);
//            }
//            if (maxQueue.size() > k) {
//                maxQueue.poll();
//            }
//        }
//        // 得到第k小的数可以直接这样返回
////        return maxQueue.peek();
//        int[] res = new int[k];
//        for (int i = 0; i < k; i++) {
//            // 这样得到的数组应该是递减序列
//            res[i] = maxQueue.poll();
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] arr = new int[]{0, 0, 0, 2, 0, 5};
//        int[] res = solution.getLeastNumbers(arr, 0);
//        System.out.println(res);
//    }
//}

/**
 * 快排实现
 * 会改变原数组
 */
class Solution {
    private int[] arr;
    private int[] res;
    private int i = 0;
    private int k;

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[k];
        }
        this.arr = arr;
        this.res = new int[k];
        this.k = k;
        quickSort(0, arr.length - 1);
        return this.res;
    }

    private void quickSort(int low, int high) {
        // 注意这里是等于的时候就不要直接返回了，虽然并不会改变顺序，但是能够操作到想要的数
        if (low > high) {
            return;
        }
        int pivotPos = partition(low, high);
        if (pivotPos < k) {
            res[i] = arr[pivotPos];
            i++;
        }
        // 注意这道题不能根据pivotPos来，要根据i来，保证前面都填上了，否则就是半路开跑
//        if (pivotPos == k) {
        if (i == k) {
            return;
        }
        // 注意这里根据实际的问题场景不一定需要对pivot两端都进行快排
        quickSort(low, pivotPos - 1);
        quickSort(pivotPos + 1, high);
    }

    private int partition(int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            // 注意这里一定要有等于，否则会始终有low<high的情况，无法跳出循环，无法越过相等的部分
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            // 注意这里一定要有等于，否则会始终有low<high的情况，无法跳出循环，无法越过相等的部分
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        TestSolution solution = new TestSolution();
        int[] arr = new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4};
        int[] res = solution.getLeastNumbers(arr, 8);
        System.out.println(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
