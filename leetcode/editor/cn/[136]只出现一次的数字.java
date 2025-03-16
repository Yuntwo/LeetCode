//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 说明：
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//
// 示例 1:
//
// 输入: [2,2,1]
//输出: 1
//
//
// 示例 2:
//
// 输入: [4,1,2,1,2]
//输出: 4
// Related Topics 位运算 数组 👍 2374 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 这种题型的变化就是其余元素出现2次、3次，解决手段还是有区别的
/**
 * 这种方法还是用了额外的数组
 * 参考:https://blog.nowcoder.net/n/630a3cab9dba45a9b033b51263756845
 */
//class Solution {
//    public int singleNumber(int[] nums) {
//        HashSet<Integer> hashSet = new HashSet<Integer>();
//        for (int i = 0; i < nums.length; i++) {
//            if (!hashSet.contains(nums[i])) {
//                // 注意这里似乎不需要包装int为Integer
//                hashSet.add(nums[i]);
//            } else {
//                hashSet.remove(nums[i]);
//            }
//        }
//        // 注意这里的语法
//        Iterator<Integer> iterator = hashSet.iterator();
//        int res = Integer.valueOf(iterator.next());
//        return res;
//    }
//}
/**
 * 利用异或的方法，不是0、1异或，针对一般int的比较特殊的运算律
 * 参考:https://blog.csdn.net/qq_44668555/article/details/103175083
 * 注意异或的本质是转换为二进制进行计算
 * 相同的数异或为0；0异或任何数为任何数；其它情况要转换成二进制看
 */
class Solution {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
