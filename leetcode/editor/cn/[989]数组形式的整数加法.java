//整数的 数组形式 num 是按照从左到右的顺序表示其数字的数组。
//
//
// 例如，对于 num = 1321 ，数组形式是 [1,3,2,1] 。
//
//
// 给定 num ，整数的 数组形式 ，和整数 k ，返回 整数 num + k 的 数组形式 。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：num = [1,2,0,0], k = 34
//输出：[1,2,3,4]
//解释：1200 + 34 = 1234
//
//
// 示例 2：
//
//
//输入：num = [2,7,4], k = 181
//输出：[4,5,5]
//解释：274 + 181 = 455
//
//
// 示例 3：
//
//
//输入：num = [2,1,5], k = 806
//输出：[1,0,2,1]
//解释：215 + 806 = 1021
//
//
//
//
// 提示：
//
//
// 1 <= num.length <= 10⁴
// 0 <= num[i] <= 9
// num 不包含任何前导零，除了零本身
// 1 <= k <= 10⁴
//
//
// Related Topics 数组 数学 👍 254 👎 0


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int i = num.length - 1;
        int carry = 0;
        while (i >= 0 || k > 0) {
            int temp = carry;
            if (i >= 0) {
                temp += num[i];
                i--;
            }
            if (k > 0) {
                temp += (k % 10);
                k /= 10;
            }
            // 注意这里谁除谁余
            carry = temp / 10;
            res.add(0, temp % 10);
        }
        // 注意最后判断一下carry
        if (carry != 0) {
            res.add(0, carry);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
