//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
//
//
// 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
//
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
//
//
//
// 示例 1：
//
//
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于
//n 的值 3 。
//
//
// 示例 2：
//
//
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
//
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 600
// 1 <= strs[i].length <= 100
// strs[i] 仅由 '0' 和 '1' 组成
// 1 <= m, n <= 100
//
//
// Related Topics 数组 字符串 动态规划 👍 831 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * BEGIN
 */

/**
 * Review1
 * 注意背包问题的动态规划数组里面的数据形式可以任意，是我们想要的解题结果，固定的格式是动态规划数组的横纵坐标意义
 */
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] res = new int[m + 1][n + 1];
        for (int k = 1; k <= strs.length; k++) {
            int count0 = 0;
            int count1 = 0;
            for (int i = 0; i < strs[k - 1].length(); i++) {
                // 注意Java没有隐式的int01转boolean
                if ((strs[k - 1].charAt(i) - '0') == 1) {
                    count1++;
                } else {
                    count0++;
                }
            }
            // 注意我们这里要遍历到0，因为[0,0]没有意义，但是[x,0],[0,x]都是有意义的
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (count0 <= i && count1 <= j) {
                        res[i][j] = Math.max(res[i][j], res[i - count0][j - count1] + 1);
                    }
                }
            }
        }
        return res[m][n];
    }

    public static void main(String[] args) {
//        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
//        String[] strs = new String[]{"11111", "100", "1101", "1101", "11000"};
        String[] strs = new String[]{"10", "0", "1"};
        Solution solution = new Solution();
        int res = solution.findMaxForm(strs, 1, 1);
//        int res = solution.findMaxForm(strs, 5, 7);
//        int res = solution.findMaxForm(strs, 5, 3);
        System.out.println(res);
    }

}

/**
 * Success1
 * 应该是要用到二维动态数组
 */
//class Solution {
//    public int findMaxForm(String[] strs, int m, int n) {
//        int[][] nums = new int[m + 1][n + 1];
//        for (int i = 0; i < strs.length; i++) {
//            int zeroNum = 0;
//            int oneNum = 0;
//            for (int k = 0; k < strs[i].length(); k++) {
//                if (strs[i].charAt(k) == '0') {
//                    zeroNum++;
//                } else {
//                    oneNum++;
//                }
//            }
//            // 注意即使是二维数组，由于是01背包问题，而且没有用三维数组升维存储数据的话，还是得从大到小遍历
//            for (int x = m; x >= zeroNum; x--) {
//                for (int y = n; y >= oneNum; y--) {
//                    // 注意是选最大的子集数量，不能随便直接赋值
//                    nums[x][y] = Math.max(nums[x][y], nums[x - zeroNum][y - oneNum] + 1);
//                }
//            }
//        }
//        return nums[m][n];
//    }
//
//    public static void main(String[] args) {
////        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
//        String[] strs = new String[]{"11111", "100", "1101", "1101", "11000"};
//        Solution solution = new Solution();
//        int res = solution.findMaxForm(strs, 5, 7);
////        int res = solution.findMaxForm(strs, 5, 3);
//        System.out.println(res);
//    }
//}

/**
 * END
 */
//leetcode submit region end(Prohibit modification and deletion)
