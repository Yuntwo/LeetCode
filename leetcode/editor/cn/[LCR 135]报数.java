//实现一个十进制数字报数程序，请按照数字从小到大的顺序返回一个整数数列，该数列从数字 1 开始，到最大的正整数 cnt 位数字结束。
//
//
//
// 示例 1:
//
//
//输入：cnt = 2
//输出：[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28
//,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,5
//5,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,
//82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99]
//
// Related Topics 数组 数学 👍 311 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Success2
 * 全排列递归解法，但是比较复杂的地方在于它是递归加上了循环
 * 而且由于不是通过返回值来沟通递归结果的，所以本质上还是回溯了的，手动维护一些递归回溯过程中的状态
 * 空间效率更高
 */
class Solution {

    private StringBuilder stringBuilder = new StringBuilder();

    private int count = 0;

    private int[] res;

    public int[] countNumbers(int cnt) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            stringBuilder.append('9');
        }
        int max = Integer.parseInt(stringBuilder.toString());
        res = new int[max];
        reg(cnt);
        return res;
    }

    private void reg(int cnt) {
        if (cnt == 0) {
            int temp = Integer.parseInt(stringBuilder.toString());
            // 注意0就不要存了
            if (temp != 0) {
                res[count++] = temp;
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            stringBuilder.append(i);
            reg(cnt - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

}

/**
 * Success1
 * 主要考察点是大数问题，直观但是低效的解法
 */
//class Solution {
//    public int[] countNumbers(int cnt) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < cnt; i++) {
//            stringBuilder.append('9');
//        }
//        int max = Integer.parseInt(stringBuilder.toString());
//        int[] res = new int[max];
//        // 这个判断有性能消耗，尤其是当数字变得很大的时候
//        for (int i = 1; i <= max; i++) {
//            res[i - 1] = i;
//        }
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
