//有一堆石头，每块石头的重量都是正整数。
//
// 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
//
//
// 如果 x == y，那么两块石头都会被完全粉碎；
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
//
//
// 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
//
//
//
// 示例：
//
// 输入：[2,7,4,1,8,1]
//输出：1
//解释：
//先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
//再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
//接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
//最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
//
//
//
// 提示：
//
//
// 1 <= stones.length <= 30
// 1 <= stones[i] <= 1000
//
// Related Topics 堆 贪心算法
// 👍 88 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Success2
 * 用PriorityQueue来实现堆，默认是最小堆，最大堆需要传入比较器
 * 效率比Success1直接在原数组上操作低
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
        // 这种写法更简便
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (!maxHeap.isEmpty() && maxHeap.size() != 1) {
            int x = maxHeap.poll() - maxHeap.poll();
            if (x != 0) {
                maxHeap.add(x);
            }
        }
        if (maxHeap.isEmpty()) {
            return 0;
        }
        return maxHeap.poll();
    }
}

/**
 * Success1
 * 手写一个堆，效率很高了
 */
//class Solution {
//  public int lastStoneWeight(int[] stones) {
//    for (int i = stones.length; i > 1; i--) {
//      oneRound(stones, i);
//    }
//    return stones[0];
//  }
//
//  public void oneRound(int[] arr, int n) {
//    heapiFy(arr, n);
//    swap(arr, 0, n - 1);
//    heapiFy(arr, n - 1);
//    swap(arr, 0, n - 2);
//    arr[n - 2] = arr[n - 1] - arr[n - 2];
//  }
//
//  public void swap(int[] array, int i, int j) {
//    int temp = array[i];
//    array[i] = array[j];
//    array[j] = temp;
//  }
//
//  public void heapiFy(int arr[], int n) {
//    int count = n;
//    for (int i = (count - 1) / 2; i >= 0; i--) {
//      shiftDown(arr, n, i);
//    }
//  }
//
//  public void shiftDown(int arr[], int n, int k) {
//    while (2 * k + 1 < n) {
//      int j = 2 * k + 1;
//      if (j + 1 < n && arr[j + 1] > arr[j]) {
//        j++;
//      }
//      if (arr[k] >= arr[j]) {
//        break;
//      }
//      swap(arr, j, k);
//      k = j;
//    }
//  }
//}
//leetcode submit region end(Prohibit modification and deletion)
