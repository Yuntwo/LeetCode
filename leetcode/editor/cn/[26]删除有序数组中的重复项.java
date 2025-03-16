//给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致
//。
//
// 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个
//元素应该保存最终结果。
//
// 将最终结果插入 nums 的前 k 个位置后返回 k 。
//
// 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//
// 判题标准:
//
// 系统会用下面的代码来测试你的题解:
//
//
//int[] nums = [...]; // 输入数组
//int[] expectedNums = [...]; // 长度正确的期望答案
//
//int k = removeDuplicates(nums); // 调用
//
//assert k == expectedNums.length;
//for (int i = 0; i < k; i++) {
//    assert nums[i] == expectedNums[i];
//}
//
// 如果所有断言都通过，那么您的题解将被 通过。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,2]
//输出：2, nums = [1,2,_]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
//
//
// 示例 2：
//
//
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 10⁴
// -10⁴ <= nums[i] <= 10⁴
// nums 已按 升序 排列
//
//
// Related Topics 数组 双指针 👍 2940 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 如果不使用额外空间的话，那么遍历两次时间复杂度就有点高了
// 我的写法，没有调对
//class Solution {
//    public int removeDuplicates(int[] nums) {
//        int len = 0;
//        // 这里其实有效长度也发生了变化，必须控制新的边界
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] == nums[i + 1]) {
//                len++;
//                int j = i + 2;
//                while (nums[j] == nums[i]) {
//                    j++;
//                }
//                for (int k = 0; k < nums.length - j; k++) {
//                    nums[i + k + 1] = nums[j + k];
//                }
//            }
//        }
//        return len;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
//        Solution solution = new Solution();
//        int res = solution.removeDuplicates(nums);
//        System.out.println(res);
//    }
//}
// 非常经典易懂的快慢指针解法
class Solution {
    public int removeDuplicates(int[] nums) {
        // i表示下一个存储的位置，i-1是上一次不重复的数字
        int i = 1;
        // j表示将要比较的数字
        int j = 1;
//        while (j < nums.length) {
//            // 注意nums[i-1]就是上一次相同的元素，所以不需要额外的存储空间来存了
//            if (nums[j] != nums[i - 1]) {
//                nums[i] = nums[j];
//                i++;
//                j++;
//            } else {
//                j++;
//            }
//        }
        // 可以简化写法
        while (j < nums.length) {
            // 注意nums[i-1]就是上一次相同的元素，所以不需要额外的存储空间来存了
            if (nums[j] != nums[i - 1]) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
