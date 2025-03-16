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
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
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
// Related Topics 位运算 数组 👍 853 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review2
 * 两种理解都可以，可以将ones和twos理解为0、1、2三种状态的两个bit位；也可以将ones和twos理解为出现1次和出现2次的bit位
 * 本质上都是一样的，不冲突
 * 这里的状态机以及逻辑表达式就是数电中的化简过程
 */
class Solution {

    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones = (num ^ ones) & ~twos;
            twos = (num ^ twos) & ~ones;
            // 都是可以的，注意优先级为~ & ^
//            ones = (num ^ ones) & ~twos;
//            twos = (num ^ twos) & ~ones;
        }
        return ones;
    }

}

/**
 * Review1
 */
//class Solution {
//
//    public int singleNumber(int[] nums) {
//        int[] bits = new int[32];
//        for (int num : nums) {
//            int index = 0;
//            while (num != 0) {
//                bits[index++] += (num & 1);
//                num >>>= 1;
//            }
//        }
//        int res = 0;
//        for (int i = 31; i >= 0; i--) {
//            // 这是一种写法
////            int bit = bits[i] % 3;
////            res = res * 2 + bit;
//            // 位运算写法
//            // 注意右移分无符号和有符号(符号位是否保留)，左移都是直接丢弃最高符号位(补码表示方法保证在不溢出的前提下正确x2)
//            res <<= 1;
//            res |= (bits[i] % 3);
//        }
//        return res;
//    }
//
//}

/**
 * Success2
 * 把数看成32位二进制(这是位运算相关题的基础，而不只是bit数组)，每一位出现次数余3就是最终数字的32位表达式
 */
//class Solution {
//
//    /**
//     * Success2
//     * 有限状态机，需要根据状态转移得出、化简逻辑运算表达式，比较麻烦，但是快
//     */
//    public int singleNumber(int[] nums) {
//        // 还是32位的个数情况(%3)，但是由于取值为0、1、2所以需要两位来表示
//        // 这里的ones和twos表示32位的个数情况，拆分表达为第一位one和第二位two
//        int ones = 0, twos = 0;
//        for(int num : nums){
//            ones = ones ^ num & ~twos;
//            twos = twos ^ num & ~ones;
//        }
//        return ones;
//    }
//
//    /**
//     * Success1
//     * 遍历统计
//     */
////    public int singleNumber(int[] nums) {
////        int[] bit = new int[32];
////        for (int num : nums) {
////            for (int i = 0; i < 32; i++) {
////                // 注意这里的顺序
////                bit[i] += (num & 1);
////                // 三个箭头的是无符号操作
////                num >>>= 1;
////            }
////        }
////        int res = 0;
////        // 注意因为左移所有要从高位开始
////        for (int i = 31; i >= 0; i--) {
////            // 注意这里的顺序，必须先把空留出来，再操作，这样跳出循环的时候才是正确的数字，而不是多移一位空出来的数字
////            res <<= 1;
////            res |= bit[i] % 3;
////        }
////        return res;
////    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{2, 2, 3, 2};
//        Solution solution = new Solution();
//        int res = solution.singleNumber(nums);
//        System.out.println(res);
//    }
//}

/**
 * Success1
 * 效率很低了
 * 常规解法，用额外的count数组进行计数
 * 不管是用HashMap还是bit数组，遍历一次是肯定的，但是由于它是出现1次和3次的差别而不是1次和0次的
 * 所以用HashMap还能进行移除，用bit数组就不行了
 */
//class Solution {
//
//    /**
//     * Success2
//     * 不用遍历一遍HashMap，但是效率没有很大的提升
//     */
//    public int singleNumber(int[] nums) {
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int num : nums) {
//            if (!hashMap.containsKey(num)) {
//                hashMap.put(num, 1);
//            } else {
//                if (hashMap.get(num) == 1) {
//                    hashMap.put(num, 2);
//                } else {
//                    hashMap.remove(num);
//                }
//            }
//        }
//        return hashMap.keySet().iterator().next();
//    }
//
//    /**
//     * Success1
//     * 要遍历一遍HashMap
//     */
////    public int singleNumber(int[] nums) {
////        HashMap<Integer, Integer> hashMap = new HashMap<>();
////        for (int num : nums) {
////            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
////        }
////        for (int key : hashMap.keySet()) {
////            if (hashMap.get(key) == 1) {
////                return key;
////            }
////        }
////        return 0;
////    }
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{2, 2, 3, 2};
//        Solution solution = new Solution();
//        int res = solution.singleNumber(nums);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
