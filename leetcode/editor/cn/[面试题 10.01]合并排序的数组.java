//给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
//
// 初始化 A 和 B 的元素数量分别为 m 和 n。
//
// 示例:
//
// 输入:
//A = [1,2,3,0,0,0], m = 3
//B = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6]
//
// 说明:
//
//
// A.length == n + m
//
//
// Related Topics 数组 双指针 排序 👍 175 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success1
 * 注意int是引用传递
 */
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int mergedIndex = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (A[m] >= B[n]) {
                A[mergedIndex--] = A[m--];
            } else {
                A[mergedIndex--] = B[n--];
            }
        }
        // 如果A中还有剩余元素没比较就已经在对应位置了，不需要额外操作
//        while (m >= 0) {
//            A[mergedIndex--] = A[m--];
//        }
        while (n >= 0) {
            A[mergedIndex--] = B[n--];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
