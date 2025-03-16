//给你两个数组，arr1 和 arr2，
//
//
// arr2 中的元素各不相同
// arr2 中的每个元素都出现在 arr1 中
//
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。
//
//
//
// 示例：
//
//
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
//
//
//
//
// 提示：
//
//
// 1 <= arr1.length, arr2.length <= 1000
// 0 <= arr1[i], arr2[i] <= 1000
// arr2 中的元素 arr2[i] 各不相同
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中
//
// Related Topics 排序 数组
// 👍 159 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    int[] tag = addTag(arr1, arr2);
    sort(tag, arr1);
    sortTail(tag, arr1);
    return arr1;
  }

  public int[] addTag(int[] arr1, int[] arr2) {
    int[] tag = new int[arr1.length];
    for (int i = 0; i < arr1.length; i++) {
      boolean notIn = true;
      for (int j = 0; j < arr2.length; j++) {
        if (arr1[i] == arr2[j]) {
          tag[i] = j;
          notIn = false;
          break;
        }
      }
      if (notIn) {
        tag[i] = 1000;
      }
    }
    return tag;
  }

  public void sort(int[] tag, int[] arr1) {
    for (int i = 1; i < tag.length; i++) {
      int tempTag = tag[i];
      int tempArr = arr1[i];
      int j;
      for (j = i; (j > 0) && (tempTag < tag[j - 1]); j--) {
        tag[j] = tag[j - 1];
        arr1[j] = arr1[j - 1];
      }
      tag[j] = tempTag;
      arr1[j] = tempArr;
    }
  }

  public void sortTail(int[] tag, int[] arr1) {
    for (int i = tag.length - 2; (i >= 0) && (tag[i] == 1000); i--) {
      int temp = arr1[i];
      int j;
      for (j = i; (j < tag.length - 1) && (temp > arr1[j + 1]); j++) {
        arr1[j] = arr1[j + 1];
      }
      arr1[j] = temp;
    }
  }
}
//leetcode submit region end(Prohibit modification and deletion)
