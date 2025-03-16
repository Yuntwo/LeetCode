//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
//
//
//
// 示例：
//
//
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4]
//注：[3,1,2,4] 也是正确的答案之一。
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 50000
// 0 <= nums[i] <= 10000
//
// Related Topics 数组 双指针 排序 👍 195 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] exchange(int[] nums) {
        // 注意数组为空的特殊处理
        if (nums.length == 0) {
            return nums;
        }
        // 注意由于low设为了外部变量且有两个判断条件
        // 所以不管是for循环和while循环，跳出循环都有两种可能
        // 如果是超出索引那种，要单独判断然后减1，否则就超出数组正常索引了；如果是第二个条件就是正常的不用再处理
        int low;
        for (low = 0; low < nums.length && nums[low] % 2 == 0; low++) ;
        if (low == nums.length) {
            return nums;
        }
//        while (low < nums.length && nums[low] % 2 == 0) {
//            low++;
//        }
//        low--;
        int pivot = nums[low];
        // 要到找第一个为奇数的位置，并把它从逻辑上放到第一个位置，并且把low置为0，low从0开始遍历，否则就会跳过一些为偶数的部分
        nums[low] = nums[0];
        low = 0;
        int high = nums.length - 1;
        while (low < high) {
            while (low < high && nums[high] % 2 == 0) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] % 2 == 1) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
