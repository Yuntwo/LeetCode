//给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
//
// 在比较时，字母是依序循环出现的。举个例子：
//
//
// 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
//
//
//
//
// 示例 1：
//
//
//输入: letters = ["c", "f", "j"]，target = "a"
//输出: "c"
//
//
// 示例 2:
//
//
//输入: letters = ["c","f","j"], target = "c"
//输出: "f"
//
//
// 示例 3:
//
//
//输入: letters = ["c","f","j"], target = "d"
//输出: "f"
//
//
//
//
// 提示：
//
//
// 2 <= letters.length <= 10⁴
// letters[i] 是一个小写字母
// letters 按非递减顺序排序
// letters 最少包含两个不同的字母
// target 是一个小写字母
//
//
// Related Topics 数组 二分查找 👍 251 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 题目中说：在比较时，字母是依序循环出现的，意思就是一定是能找到的
// 这条件处理起来感觉比较麻烦
//class Solution {
//    public char nextGreatestLetter(char[] letters, char target) {
//        int l = 0;
//        int r = letters.length - 1;
//        // 循环出现的情况
//        if (letters[letters.length - 1] <= target) {
//            return letters[0];
//        }
//        // 第一个字母不一定是小于target的，如果它大的话就直接满足条件了
//        // 这一步是比较妙的
//        if (letters[0] > target) {
//            return letters[0];
//        }
//        while (l < r) {
//            int mid = (l + r + 1) / 2;
//            // 注意这道题的要求不是找相等，而是找大于的最小值
//            // 二分的逻辑是一样的，只是利用返回值的逻辑不一样，不能用mid那个值去比较，这样的返回还是很大
//            // ！！！注意这里要配合使用l+1而不是l
//            // 因为如果下面那个l=mid+1不能执行的话，l就永远是<=target的值，会上不到r去从而陷入死循环
//            if (letters[l + 1] > target) {
//                return letters[l + 1];
//            }
//            // 注意这里和照等于的逻辑不一样，是要保留所以大于的位置以免漏掉
//            if (letters[mid] <= target) {
//                // ！！！注意这里还是要让l=mid而不是mid+1，因为这个分支不一定会执行到，那么l就无法+1
//                l = mid;
//            } else {
//                // 到这个分支是mid>，不能mid-1这样就会错过这个大于的可能结果
//                r = mid;
//            }
//        }
//        // 按照逻辑来说最后这一步应该从来没有执行到过
//        return letters[r];
//    }
//}
// 另一种很简洁的写法，很巧妙！！！
// 二分对于边界条件和上下取整的把握很巧妙
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int length = letters.length;
        if (target >= letters[length - 1]) {
            return letters[0];
        }
        int low = 0, high = length - 1;
        // 它这里low的含义是小于等于target的后一个，high是大于target的最后一个
        // 当low==high时就刚好是我们要的那个结果，很巧妙
        while (low < high) {
            // 注意它这里的mid是下取整
            // 这里下取整的原因在于low和high相邻时，我们要的结果是low，让high靠近low去跳出循环
            int mid = (high - low) / 2 + low;
            if (letters[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return letters[low];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
