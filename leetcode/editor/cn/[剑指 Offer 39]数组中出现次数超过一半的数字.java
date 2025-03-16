//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//
//
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
//
//
// 示例 1:
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2
//
//
//
// 限制：
//
// 1 <= 数组长度 <= 50000
//
//
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
//
//
//
// Related Topics 数组 哈希表 分治 计数 排序 👍 325 👎 0
// 不知道为啥LeetCode编译器找不到Hashtable

//import java.util.Hashtable;
import java.util.HashMap;
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        // 注意这里必须初始化一下
        int maxNum = 0;
//        Map<Integer, Integer> table = new HashMap<Integer, Integer>();
        // new HashMap可以不指定Integer, Integer
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int temp = map.get(nums[i]) + 1;
                // 注意这里是大于长度的一半
                if (temp > nums.length/2) {
                    maxNum = nums[i];
                    break;
                }
                map.replace(nums[i], temp);
                // 可以直接put
//                map.put(nums[i], temp);
            } else {
                map.put(nums[i], 1);
            }
        }
        // 注意处理这个特殊情况
        if (nums.length == 1) {
            maxNum = nums[0];
        }
        return maxNum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        Solution solution = new Solution();
        int result = solution.majorityElement(nums);
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
