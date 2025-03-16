//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
//
// 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3
//,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。
//
// 示例 1：
//
//
//输入：[3,4,5,1,2]
//输出：1
//
//
// 示例 2：
//
//
//输入：[2,2,2,0,1]
//输出：0
//
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-
//sorted-array-ii/
// Related Topics 数组 二分查找 👍 504 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 顺序查找
 * */
//class Solution {
//    public int minArray(int[] numbers) {
//        // 主要是感觉元素可以重复的话细节上就比较麻烦
//        // 处理只有一个元素的情况，直接返回
//        if (numbers.length == 1) {
//            return numbers[0];
//        }
//        // 处理所有元素都相同的情况，不能用下面的方法，否则会一直循环下去
//        boolean isTheSame = true;
//        for (int i = 0; i < numbers.length-1; i++) {
//            if (numbers[i] != numbers[i + 1]) {
//                isTheSame = false;
//                break;
//            }
//        }
//        if (isTheSame) {
//            return numbers[0];
//        }
//        // 一般的情况
//        int i = 0;
//        while (numbers[(i + 1)% numbers.length] >= numbers[i]) {
//            i++;
//        }
//        return numbers[(i + 1) % numbers.length];
//    }
//}
class Solution {
    public int minArray(int[] numbers) {
        

    }
}
//leetcode submit region end(Prohibit modification and deletion)
