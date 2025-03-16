//设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
//
// 请实现 KthLargest 类：
//
//
// KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
// int add(int val) 返回当前数据流中第 k 大的元素。
//
//
//
//
// 示例：
//
//
//输入：
//["KthLargest", "add", "add", "add", "add", "add"]
//[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
//输出：
//[null, 4, 5, 5, 8, 8]
//
//解释：
//KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
//kthLargest.add(3);   // return 4
//kthLargest.add(5);   // return 5
//kthLargest.add(10);  // return 5
//kthLargest.add(9);   // return 8
//kthLargest.add(4);   // return 8
//
//
//
//提示：
//
//
// 1 <= k <= 104
// 0 <= nums.length <= 104
// -104 <= nums[i] <= 104
// -104 <= val <= 104
// 最多调用 add 方法 104 次
// 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
//
// Related Topics 堆 设计
// 👍 165 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// Q:这个问题的输入和函数调用形式究竟是啥呀？
class KthLargest {

  private int[] arr = new int[204];

  private int length;

  {
    length = 0;
  }

  public KthLargest(int k, int[] nums) {
    this.length = nums.length;
    for (int i = 0; i < nums.length; i++) {
      this.arr[i] = nums[i];
    }
    heapiFy();
  }

  public void shiftUp(int k) {
    while ((k - 1) / 2 >= 0) {
      if (arr[k] > arr[(k - 1) / 2]) {
        swap(arr, k, (k - 1) / 2);
      }
      k = (k - 1) / 2;
    }
  }

  public void heapiFy() {
    for (int i = 1; i < length; i++) {
      shiftUp(i);
    }
  }

  public int findKthLargest() {
    shiftUp(length - 1);
    if (arr[1] > arr[2]) {
      return arr[2];
    }
    return arr[1];
  }

  public int add(int val) {
    this.arr[length++] = val;
    int result = findKthLargest();
    return result;
  }

  public void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String string = scanner.nextLine();
    System.out.println(string);

  }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)
