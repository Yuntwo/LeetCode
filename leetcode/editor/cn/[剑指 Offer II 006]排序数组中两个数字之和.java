//给定一个已按照 升序排列 的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
//
// 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，所以答案数组应当满足 0 <= answer[0]
// < answer[1] < numbers.length 。
//
// 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
//
//
//
// 示例 1：
//
//
//输入：numbers = [1,2,4,6,10], target = 8
//输出：[1,3]
//解释：2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
//
//
// 示例 2：
//
//
//输入：numbers = [2,3,4], target = 6
//输出：[0,2]
//
//
// 示例 3：
//
//
//输入：numbers = [-1,0], target = -1
//输出：[0,1]
//
//
//
//
// 提示：
//
//
// 2 <= numbers.length <= 3 * 10⁴
// -1000 <= numbers[i] <= 1000
// numbers 按 递增顺序 排列
// -1000 <= target <= 1000
// 仅存在一个有效答案
//
//
//
//
// 注意：本题与主站 167 题相似（下标起点不同）：https://leetcode-cn.com/problems/two-sum-ii-input-
//array-is-sorted/
//
// Related Topics 数组 双指针 二分查找 👍 62 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 感觉不只是二分查找这么简单
 */
//class Solution {
//    public int[] twoSum(int[] numbers, int target) {
//        int left = 0;
//        int right = numbers.length - 1;
//        int sum = numbers[left] + numbers[right];
//        int[] res = new int[2];
//        res[0] = left;
//        res[1] = right;
//        while (sum != target) {
//            int mid = (left + right) / 2;
//            if (sum > target) {
//                right = mid;
//            } else if (sum < target) {
//                left = mid;
//            }
//
//        }
//        return res;
//    }
//}

/**
 * Success1
 * 效率已经很高了
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        /* Step1: 找到两个数字分属的界限*/
        // 这里的细节有点不好处理，因为有可能找不到等于target的那个点
        int left = 0;
        int right = numbers.length - 1;
        int mid = 0;
        int partition = 0;
        while (left <= right) {
            // 这里上取整还是下取整都没有关系
            // 索引从0开始，奇数的时候不管上取整还是下取整，结果都是奇数的那个值，只有非这种情况才会影响取大的还是取小的
            mid = (left + right) / 2;
            if (numbers[mid] == target) {
                partition = mid;
                break;
            } else if (numbers[mid] > target) {
                // 注意这里找下一个边界的时候一定要+-1，否则到了边界的时候容易死循环出不来
                // 这是二分查找的关键
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        partition = mid;
        /* Step2: 在两部分中遍历和，注意有两种情况 */
        // 情况一：一个在小的那堆，一个在大的那堆
        left = 0;
        right = numbers.length - 1;
        while (left <= partition && right >= partition + 1) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] > target) {
                left++;
            } else {
                return new int[]{left, right};
            }
        }
        // 情况二：两个都在小的那堆
        left = 0;
        right = partition;
        while (left <= partition && right >= 0) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                return new int[]{left, right};
            }
        }
        return new int[]{left, right};
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 4, 6, 10};
        int target = 8;
        Solution solution = new Solution();
        int[] res = solution.twoSum(numbers, target);
        System.out.println(res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
