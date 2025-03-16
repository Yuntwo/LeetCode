//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,2,3,2]
//输出：3
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,1,0,1,100]
//输出：100
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 10⁴
// -2³¹ <= nums[i] <= 2³¹ - 1
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
//
//
//
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//
//
//
// 注意：本题与主站 137 题相同：https://leetcode-cn.com/problems/single-number-ii/
// Related Topics 位运算 数组 👍 66 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Solution1
 * Use additional HashMap, slow
 */
//class Solution {
//    public int singleNumber(int[] nums) {
//        HashMap<Integer, Integer> hashMap = new HashMap();
//        for (int i = 0; i < nums.length; i++) {
//            if (hashMap.containsKey(nums[i])) {
//                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
//            } else {
//                hashMap.put(nums[i], 1);
//            }
//        }
//        // 注意这种迭代方法
//        for (Integer key : hashMap.keySet()) {
//            if (hashMap.get(key) == 1) {
//                return key;
//            }
//        }
//        return nums[0];
//    }
//
//    // 简洁写法
//    public int singleNumber1(int[] nums) {
//        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
//        for (int num : nums) {
//            // getOrDefault注意这个方法很好用
//            freq.put(num, freq.getOrDefault(num, 0) + 1);
//        }
//        int ans = 0;
//        // 注意对于Map的迭代方式
//        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
//            int num = entry.getKey(), occ = entry.getValue();
//            if (occ == 1) {
//                ans = num;
//                break;
//            }
//        }
//        return ans;
//    }
//
//}

/**
 * Solution2
 * Countbit
 */
class Solution {
    public int singleNumber(int[] nums) {
        int[] countBit = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                countBit[i] += (num >> i) & 1;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        // 如果是正数
        if (countBit[31] % 3 == 0) {
            for (int i = 30; i >= 0; i--) {
                countBit[i] %= 3;
                stringBuilder.append(countBit[i]);
            }
            return Integer.parseInt(stringBuilder.toString(), 2);
            // 如果是负数，要用补码算
        } else {
            for (int i = 30; i >= 0; i--) {
                countBit[i] %= 3;
                // 这里取反操作符感觉不太管用，用异或操作符和取反效果是一样的
                countBit[i] = countBit[i] ^ 1;
                stringBuilder.append(countBit[i]);
            }
            int res = Integer.parseInt(stringBuilder.toString(), 2);
            res += 1;
            return 0 - res;
        }
        // 注意这个Integer.parseInt方法，要指明进制，但是似乎不能解析负数格式的字符串
//        return Integer.parseInt(stringBuilder.toString(), 2);
        // 也可以用这个解析，同样要指明进制，似乎也不能解析负数格式的字符串，而且只能解析8位
//        return Byte.parseByte(stringBuilder.toString(), 2);
        // 注意这个方法返回的是十进制格式
//        Byte s = new Byte(stringBuilder.toString());
//        return s.intValue();
    }

    // 简洁写法，注意这样直接操作int，省去了转换的麻烦
    // 这样比起上面更快，但是空间效率似乎更低，不知道为什么
    public int singleNumber1(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            // total是计算对于第i位，所有的nums一共有的1
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            // 注意这里对ans的赋值方式很巧妙
            if (total % 3 != 0) {
                // 注意这是1左移i位，不是i左移1位
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = new int[]{2, 2, 3, 2};
        int[] nums = new int[]{-2, -2, 1, 1, 4, 1, 4, 4, -4, -2};
        int res = solution.singleNumber(nums);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
