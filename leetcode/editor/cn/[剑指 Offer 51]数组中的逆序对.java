//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
//
//
// 示例 1:
//
// 输入: [7,5,6,4]
//输出: 5
//
//
//
// 限制：
//
// 0 <= 数组长度 <= 50000
//
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 908 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*

/**
 * Success1
 * 我写出来的归并排序思路
 * 如果直接遍历两遍，时间复杂度O(n^2)应该会超时
 */
//class Solution {
//
//    private int[] tmp;
//
//    private int[] nums;
//
//    private int res = 0;
//
//    public int reversePairs(int[] nums) {
//        this.tmp = new int[nums.length];
//        this.nums = nums;
//        mergeSort(0, nums.length - 1);
//        return res;
//    }
//
//    /**
//     * 整个mergeSort函数应该是体现的先分后治两个过程
//     */
//    private void mergeSort(int left, int right) {
//        // 分的返回条件，只有一个元素分不了
//        // 实际上应该只会判断到==
//        if (left >= right) {
//            return;
//        }
//        // 奇数情况下左边上取整
//        int mid = (left + right) / 2;
//        /* 先分 */
//        mergeSort(left, mid);
//        mergeSort(mid + 1, right);
//        /* 后治 */
//        // 1.复制数组
//        for (int i = left; i < right + 1; i++) {
//            tmp[i] = nums[i];
//        }
//        // i, j分别初始化为左右子数组的首元素位置
//        int i = left;
//        int j = mid + 1;
//        // k是要放到原数组中的位置
//        int k = left;
//        // 2.比较放元素
//        // k超出边界说明放完了
//        while (k != right + 1) {
//            // 左边元素已经放完了
//            if (i == mid + 1) {
//                nums[k] = tmp[j];
//                j++;
//            // 右边元素已经放完了
//            } else if (j == right + 1) {
//                nums[k] = tmp[i];
//                i++;
//            } else {
//                // 当前元素没有逆序对的情况
//                if (tmp[i] <= tmp[j]) {
//                    nums[k] = tmp[i];
//                    i++;
//                // 当前元素有逆序对的情况
//                } else {
//                    res += (mid - i + 1);
//                    nums[k] = tmp[j];
//                    j++;
//                }
//            }
//            k++;
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{7, 5, 6, 4};
//        int res = solution.reversePairs(nums);
//        System.out.println(res);
//    }
//}

/**
 * Success2
 * 思路没变，改变了一点写法，但似乎对效率的影响不大
 */
class Solution {

    public int reversePairs(int[] nums) {
        int[] tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1, nums, tmp);
    }

    /**
     * 整个mergeSort函数应该是体现的先分后治两个过程
     */
    private int mergeSort(int left, int right, int[] nums, int[] tmp) {
        // 分的返回条件，只有一个元素分不了
        // 实际上应该只会判断到==
        if (left >= right) {
            return 0;
        }
        // 奇数情况下左边上取整
        int mid = (left + right) / 2;
        /* 先分 */
        // ！！！注意res的组成关系，一定是包含子数组mergeSort的结果以及两个子数组合并的结果
        int res = mergeSort(left, mid, nums, tmp) + mergeSort(mid + 1, right, nums, tmp);
        /* 后治 */
        // 1.复制数组
        for (int i = left; i < right + 1; i++) {
            tmp[i] = nums[i];
        }
        // i, j分别初始化为左右子数组的首元素位置
        int i = left;
        int j = mid + 1;
        // 2.比较放元素
        // k是要放到原数组中的位置
        for (int k = left; k < right + 1; k++) {
            // 左边元素已经放完了
            if (i == mid + 1) {
                // 注意这种后++的写法
                nums[k] = tmp[j++];
                // 右边元素已经放完了
                // 注意分支的合并写法
            } else if (j == right + 1 || tmp[i] <= tmp[j]) {
                nums[k] = tmp[i++];
            } else {
                res += (mid - i + 1);
                nums[k] = tmp[j++];
            }
        }
        return res;
    }

}

/**
 *
 */
//leetcode submit region end(Prohibit modification and deletion)
